# Java Memory Leaks

## Exercise 1: Session Leak

### Purpose
Resolve a memory leak.

### Description
`Stocks` is a simple Jetty web application that is used to view stock prices. To run the web app:

1. Open a terminal window and `cd` into `exercise1`.
2. Compile and start Jetty using the `compile` and `appServerStart` scripts.
3. Test the app using the URL `http://localhost:8080/lab/stock`.
4. Start JMeter using the provided `jmeter` script.

#### Some tips:
- It is recommended to restart the webapp (Jetty) before each run.
- You can view latency information in the "Aggregate Report" view.
- The double broom icon can be used to clear out results from the previous run.
- Load can be adjusted in the "Thread Group" view labeled "History." The value to adjust is labeled "Number of threads (Users)."
- It is possible that all processes may "hang" if load is too high. In this case, it's best to terminate JMeter and the Jetty server.

### Part 1 - Monitor Stocks

The objective is to monitor Java heap occupancy for the Stocks webapp.

#### Steps
1. Using the instructions above, start the webapp and JMeter.
2. Ensure the monitoring tool is ready to go.
3. Run the JMeter script.
4. Use the various tools demonstrated in the presentation to monitor Java heap:
    1. jcmd
    2. jstat
    3. VisualVM
    4. Java Mission Control
    5. Garbage collection logs

### Part 2 - Generational Counts for Stocks

The objective is to use the generational count information produced by the memory profiler in VisualVM to identify the leaking objects.

#### Steps
1. Using the instructions above, start the webapp and JMeter.
2. Start VisualVM and attach it to the Jetty server. It should be listed as "start.jar".
3. Configure the memory profiler and start it.
4. Run the JMeter script.

### Part 3 - Investigate the heap dump with MAT and JMC

The objective is to examine the heap dump using MAT (Memory Analyzer Tool) and JMC (Java Mission Control) to understand how they expose the memory leak.

#### Steps
1. Using the instructions above, start the webapp and JMeter.
2. At about halfway through a JMeter script run, use jcmd to dump the heap.
3. Start MAT:
    1. Load the heap dump.
    2. Execute the "leak suspects" report.
    3. Try running a few of the other reports.
    4. Compare what the reports are telling you with what you know about the leak.
4. Start JMC:
    1. Load the heap dump.
    2. How is the GUI exposing the leak? Can you easily see it?

---
Copyright 2024 Kodewerk Ltd. All Rights Reserved.