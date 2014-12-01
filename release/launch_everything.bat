@echo off
./jms/bin/startup.bat
%JAVA_HOME%\bin\javaw.exe ./Annuaire/annuaire_jms.jar
%JAVA_HOME%\bin\javaw.exe ./Banque/banque.jar
%JAVA_HOME%\bin\javaw.exe ./Clientele/clientele.jar
%JAVA_HOME%\bin\javaw.exe ./Electromenager/objects.jar
%JAVA_HOME%\bin\javaw.exe ./Hifi/objects.jar
%JAVA_HOME%\bin\javaw.exe ./Informatique/objects.jar
%JAVA_HOME%\bin\javaw.exe ./Z/z.jar
