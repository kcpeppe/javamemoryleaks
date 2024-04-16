#!/bin/sh

##### Edit this path
##### The path should point to the root directory of your Java installation
##### 
export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk-21.jdk/Contents/Home


#### DO NOT EDIT BELOW THIS COMMENT
#### Values will be derived from the JAVA_HOME setting from above
export JAVA=$JAVA_HOME/bin/java
export JAVAC=$JAVA_HOME/bin/javac

export JETTY_HOME=../bin/jetty
export JETTY_JARS=$JETTY_HOME/lib/jakarta.servlet-api-6.0.0.jar

echo $JETTY_JARS
$JAVA -version

