@echo off
start cmd /k Call %CD%\jms\bin\startup.bat
TIMEOUT 5
start %JAVA_HOME%\bin\javaw.exe -jar %CD%\Annuaire\annuaire_jms.jar localhost
TIMEOUT 5
start %JAVA_HOME%\bin\javaw.exe -jar %CD%\Banque\banque.jar localhost
start %JAVA_HOME%\bin\javaw.exe -jar %CD%\Clientele\clientele.jar localhost
start %JAVA_HOME%\bin\javaw.exe -jar %CD%\Electromenager\objects.jar localhost ELECTROMENAGER
start %JAVA_HOME%\bin\javaw.exe -jar %CD%\Hifi\objects.jar localhost HIFI
start %JAVA_HOME%\bin\javaw.exe -jar %CD%\Informatique\objects.jar localhost INFORMATIQUE
TIMEOUT 5
start %JAVA_HOME%\bin\javaw.exe -jar %CD%\Z\z.jar localhost localhost
exit