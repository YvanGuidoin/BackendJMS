@echo off
JAVA_FILE=" %JAVA_HOME%\bin\javaw.exe -jar"
start "" %JAVA_FILE% objects.jar localhost ELECTROMENAGER
exit