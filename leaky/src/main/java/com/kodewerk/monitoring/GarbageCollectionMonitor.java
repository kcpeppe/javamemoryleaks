package com.kodewerk.monitoring;

import javax.management.MBeanServer;
import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.Notification;
import javax.management.NotificationFilter;
import javax.management.NotificationListener;
import javax.management.ObjectName;
import javax.management.openmbean.CompositeDataSupport;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.lang.management.ManagementFactory;
import java.util.Arrays;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class GarbageCollectionMonitor implements NotificationListener {


    private static String[] PARALLEL = { "PS Scavenge", "PS MarkSweep" };
    private static String[] CONCMARKSWEEP = { "ParNew", "ConcurrentMarkSweep" };
    private static String PARNEW = "ParNew";

    class GarbageCollectionNotificationFilter implements NotificationFilter {

        private String name;
        GarbageCollectionNotificationFilter(String name) {
            this.name = name;
        }

        @Override
        public boolean isNotificationEnabled(Notification notification) {
            Object object = notification.getSource();
            System.out.println("Filter -> " + object.toString());
            if ( object instanceof ObjectName) {
                return ((ObjectName) object).getCanonicalName().contains(name);
            }
            return false;
        }
    }

    private void init(String name[]) throws Exception {
        JMXServiceURL uri = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:9999/jmxrmi");
        JMXConnector connector = JMXConnectorFactory.connect(uri,null);
        //MBeanServerConnection mbeanServerConnection = connector.getMBeanServerConnection();
        //String[] domains = mbeanServerConnection.getDomains();
        //Arrays.stream(domains).forEach(System.out::println);
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        System.out.println(ManagementFactory.getRuntimeMXBean().getStartTime());
        server.addNotificationListener(new ObjectName("java.lang:type=GarbageCollector,name=" + name[0]), this, new GarbageCollectionNotificationFilter(name[0]), new Object());
        server.addNotificationListener(new ObjectName("java.lang:type=GarbageCollector,name=" + name[1]), this, new GarbageCollectionNotificationFilter(name[1]), new Object());
    }

    Queue<CompositeDataSupport> data = new ConcurrentLinkedQueue<>();
    @Override
    public void handleNotification(Notification notification, Object handback) {
        data.add((CompositeDataSupport)notification.getUserData());
        System.out.println("Event count -> " + data.size());
    }

    private void inspect(CompositeDataSupport data) {
        PSFull full = new PSFull(data);
        System.out.println(full);
    }

    void inspect() {
        while ( data.size() > 0) {
            CompositeDataSupport notification = data.poll();
            Object o = notification.get("gcAction");
            System.out.println(o.toString());
            o = notification.get("gcCause");
            System.out.println(o.toString());
            o = notification.get("gcInfo");
            inspect((CompositeDataSupport) o);
            o = notification.get("gcName");
            System.out.println(o.toString());
        }
    }

    public static void main(String[] args) throws Exception {
        GarbageCollectionMonitor monitor = new GarbageCollectionMonitor();
        monitor.init(PARALLEL);
        Thread.sleep(2000);
        System.gc();
        //System.gc();
        Thread.sleep(1000);
        monitor.inspect();
        Thread.sleep(1000);
    }
}
