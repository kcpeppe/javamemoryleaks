package com.kodewerk.monitoring;

import javax.management.openmbean.CompositeDataSupport;
import javax.management.openmbean.TabularDataSupport;

public class MemoryPool {

    private String poolName;
    private long committed;
    private long init;
    private long max;
    private long used;

    public MemoryPool(TabularDataSupport table,String poolName) {
        explode(table, poolName);
        this.poolName = poolName;
    }

    private void explode(TabularDataSupport table, String poolName) {
        Object[] key = { poolName };
        CompositeDataSupport data = (CompositeDataSupport) table.get(key).get("value");
        committed = (Long)data.get("committed");
        init = (Long)data.get("init");
        max = (Long)data.get("max");
        used = (Long)data.get("used");
    }

    public long getCommitted() {
        return committed;
    }

    public long getInit() {
        return init;
    }

    public long getMax() {
        return max;
    }

    public long getUsed() {
        return used;
    }

    @Override
    public String toString() {
        return "[" + poolName + ": used=" + used / 1024 + "K, committed=" + committed / 1024 + "K, init=" + init / 1024 + "K, max=" + max / 1024 + "K]";
    }
}
