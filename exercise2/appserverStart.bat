

call ..\setEnv.bat
call .\jvm.bat
if "%JAVA%" == "" goto usage
call .\compile.bat

Set APP_PROPS=-Dcom.kodewerk.stocks.properties=stocksdb.properties

%JAVA% -version
%JAVA% %FLAGS% %APP_PROPS% -jar %JETTY_HOME%\start.jar

goto end

:usage
echo "Java executable must be specified in setEnv.bat."

:end
pause
