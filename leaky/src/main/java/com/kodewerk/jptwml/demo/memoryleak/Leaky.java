package com.kodewerk.jptwml.demo.memoryleak;

/********************************************
 * Copyright (c) 2019 Kirk Pepperdine
 * All right reserved
 ********************************************/

import javax.swing.*;
import java.awt.*;
import java.lang.management.ManagementFactory;
import java.util.concurrent.*;

public class Leaky extends JFrame {
    private final static ExecutorService leakerService =
            Executors.newSingleThreadExecutor();
    private final LeakyModel model = new LeakyModel();

    public Leaky() {
        super("Leaky");

        var numberOfObjectsField = new JTextField("1000000", 10);
        var button = new JButton("Do Stuff");
        button.addActionListener(e -> {
            button.setEnabled(false);
            numberOfObjectsField.setEnabled(false);
            long numberOfObjects = Long.parseLong(numberOfObjectsField.getText());
            leakerService.submit(() -> {
                try {
                    model.leak(numberOfObjects);
                    EventQueue.invokeLater(() -> {
                        button.setEnabled(true);
                        numberOfObjectsField.setEnabled(true);
                        numberOfObjectsField.requestFocus();
                    });
                } catch (Throwable t) {
                    t.printStackTrace();
                }
            });
        });
        button.setDefaultCapable(true);
        getRootPane().setDefaultButton(button);

        var controls = new JPanel();
        controls.add(numberOfObjectsField);
        controls.add(button);

        var heapOccupancyTextField = new JTextField("0", 15);
        heapOccupancyTextField.setEditable(false);
        JPanel views = new JPanel();
        views.add(new JLabel("Heap Occupancy (MB):"));
        views.add(heapOccupancyTextField);

        var root = new JPanel();
        root.setLayout(new BoxLayout(root, BoxLayout.Y_AXIS));
        root.add(views);
        root.add(controls);

        add(root);

        new Timer(1000, e -> {
            var heapOccupancy = ManagementFactory.getMemoryMXBean()
                    .getHeapMemoryUsage()
                    .getUsed() / (1024 * 1024);
            heapOccupancyTextField.setText("" + heapOccupancy);
        }).start();
    }

    public static void main(String... args) {
        System.out.println("Turning on Verbose GC");
        ManagementFactory.getMemoryMXBean().setVerbose(true);
        EventQueue.invokeLater(() -> {
            Leaky leaky = new Leaky();
            leaky.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            leaky.setSize(450, 120);
            leaky.setLocationRelativeTo(null);
            leaky.setVisible(true);
        });
    }
}

