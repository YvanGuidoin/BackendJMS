@echo off
JAVA_FILE=" %JAVA_HOME%\bin\javaw.exe -jar"
cd jms\bin
START /B CMD /C CALL "startup.bat"
TIMEOUT 5
cd ..\..\Annuaire
start %JAVA_FILE% annuaire_jms.jar localhost
cd ..\Banque
TIMEOUT 5
start %JAVA_FILE% banque.jar localhost
cd ..\Clientele
start %JAVA_FILE% clientele.jar localhost
cd..\Electromenager
rem start "" %JAVA_FILE% objects.jar localhost ELECTROMENAGER -launch
START /B CMD /C CALL "launch.bat"
pause
cd ..\Hifi
start "" %JAVA_FILE% objects.jar localhost HIFI -launch
cd ..\Informatique
start "" %JAVA_FILE% objects.jar localhost INFORMATIQUE  -launch
cd..\Z
TIMEOUT 5
start "" %JAVA_FILE% z.jar localhost localhost -launch
exit