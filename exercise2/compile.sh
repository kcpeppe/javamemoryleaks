#!/bin/sh

. ../setEnv.sh

export DOMAIN="src/main/java/com/kodewerk/stock/*.java src/main/java/com/kodewerk/web/*.java src/main/java/com/kodewerk/db/*.java src/main/java/com/kodewerk/cache/*"
export SOURCES="$DOMAIN"
export EXPLODED="webapps/lab"


export CP="-classpath $JETTY_JARS"

$JAVAC $CP -d $EXPLODED/WEB-INF/classes $SOURCES 
