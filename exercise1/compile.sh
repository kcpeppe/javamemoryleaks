#!/bin/sh

. ../setEnv.sh

export DOMAIN="src/com/kodewerk/stock/*.java src/com/kodewerk/web/*.java src/com/kodewerk/db/*.java"
export SOURCES="$DOMAIN"
export EXPLODED="webapps/lab"


export CP="-classpath $JETTY_JARS"

$JAVAC $CP -d $EXPLODED/WEB-INF/classes $SOURCES 
