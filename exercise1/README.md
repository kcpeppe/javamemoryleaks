# Java Memory Leaks

## Exercise 1: Session Leak

### Purpose
Resolve a memory leak.

### Description
`Stocks` is a simple Jetty web application that is used to view stock prices.
To run the web app;

<ol type=1>
<li>Open a terminal window and `cd` into `jptwml/exercise1`.</li>
<li>Compile and start Jetty using the `compile` and `appServerStart` scripts.</li>
<li>Test the app using the URL `http://localhost:8080/lab/stock`.</li>
<li>Start JMeter using the provided `jmeter` script.</li>
</ol>

##### Some tips;
<ul>
<li>It is recommended to restart the webapp (jetty) before each run</li>
<li>You can view latency information in the "Aggregate Report" view.</li>
<li>The double broom icon can be used to clear out results from the previous run</li>
<li>Load can be adjusted in the "Thread Group" view labeled "History. The value to adjust is labeled "Number of threads (Users).
<li>it is possible that all processes may "hang" if load is too high. In this case it's best to terminate jmeter and the jetty server.
</ul>
<li>Run the JMeter script.</li>
</ul>

### Part 1 - Monitor Stocks

The object is to monitor Java heap occupancy for the Stocks webapp.

##### Steps
<ol type=1>
<li>Using the instructions above, start the webapp and jmeter.</li>
<li>Ensure the monitoring tool is ready to go</li>
<li>Run the JMeter script.</li>
<li>Use the various tools demonstrated in the presentation to monitor Java heap.</li>
<ol type=I>
<li>jcmd</li>
<li>jstat</li>
<li>VisualVM</li>
<li>Java Mission Control</li>
<li>Garbage collection logs</li>
</ol>
</ol>

### Part 2 - Generational Counts for Stocks

The objective is to use the generational count information produced by the memory profiler in VisualVM to identify the leaking objects.
 
### Steps
<ol type=1>
<li>Using the instructions above, start the webapp and jmeter.</li>
<li>Start VisualVM and attach it to jetty server. It should be listed as "start.jar"</li>
<li>Configure the memory profiler and start it."</li>
<li>Run the JMeter script.</li>
<li>Start </li>
</ol>

### Part 3 - Investigate the heap dump with MAT and JMC

The objective is to examine the heap dump using MAT and JMC to understand how they expose the memory leak.
 
### Steps
<ol type=1>
<li>Using the instructions above, start the webapp and jmeter.</li>
<li>At about half way through a JMeter script run, use jcmd to dump the heap</li>
<li>Start MAT</li>
<ol type=a>
<li>load the heap dump</li>
<li>execute the "leak suspects" report</li>
<li>try running a few of the other reports</li>
<li>compare what the reports are telling you with what you know about the leak</li>
</ol>
<li>Start JMC</li>
<ol>
<li>load the heap dump</li>
<li>how is the GUI exposing the leak? Can you easily see it?</li>
</ol>
</ol>
<br/>
<p style="text-align: center;">Copyright 2024 Kodewerk Ltd. All Rights Reserved.</p>


