#!/bin/sh

. ../setEnv.sh
. ./jvm.conf
. ./compile.sh



export JETTY_BASE=.


export FLAGS="$COLLECTORS $MEMORY $GC_LOGGING"
export APP_PROPS="-Dcom.kodewerk.stocks.properties=stocksdb.properties"

echo $FLAGS
echo $APP_PROPS
$JAVA -version

$JAVA $FLAGS $APP_PROPS -jar $JETTY_HOME/start.jar
