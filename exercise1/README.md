# Java Memory Leaks

## Exercise 1: Session Leak

### Purpose
Resolve a memory leak.

### Description
`Stocks` is a simple Jetty web application that is used to view stock prices.

### Steps
1. Open a terminal window and `cd` into `jptwml/exercise1`.
2. Compile and start Jetty using the `compile` and `appServerStart` scripts.
3. Test the app using the URL `http://localhost:8080/lab/stock`.
4. Start JMeter using the provided `jmeter` script.
5. Run the JMeter script.
6. Triage the leak, resolve it, and confirm the solution.

### Copyright Notice
Copyright 2015 Kodewerk Ltd. All Rights Reserved.
