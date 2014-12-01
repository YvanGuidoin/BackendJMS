@echo off
call .\jms\bin\startup.bat
%JAVA_HOME%\bin\java.exe %CD%\Annuaire\annuaire_jms.jar AnnuaireJMS
%JAVA_HOME%\bin\java.exe %CD%\Banque\banque.jar Banque
%JAVA_HOME%\bin\java.exe %CD%\Clientele\clientele.jar Clientele
%JAVA_HOME%\bin\java.exe %CD%\Electromenager\objects.jar Biens
%JAVA_HOME%\bin\java.exe %CD%\Hifi\objects.jar Biens
%JAVA_HOME%\bin\java.exe %CD%\Informatique\objects.jar Biens
%JAVA_HOME%\bin\java.exe %CD%\Z\z.jar LaunchServer
pause
exit