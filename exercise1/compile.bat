
call ..\setEnv.bat
if "%JAVA%" == "" goto usage


set DOMAIN=src/main/java/com/kodewerk/stock/*.java src/main/java/com/kodewerk/web/*.java src/main/java/com/kodewerk/db/*.java
set SOURCES=%DOMAIN%
set EXPLODED=webapps/lab

set CP=-classpath %JETTY_JARS%;lib\hsqldb.jar

%JAVAC% %CP% -d %EXPLODED%\WEB-INF\classes %SOURCES% 
goto end

:usage
echo Java executable must be specified in setEnv.bat

:end
pause
