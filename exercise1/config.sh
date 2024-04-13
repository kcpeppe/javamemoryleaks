#!/bin/sh

. ../setEnv.sh


export JETTY_BASE=.

$JAVA -jar $JETTY_HOME/start.jar --add-modules=server,http,ee10-deploy
