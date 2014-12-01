@echo off
xcopy "%CD%\..\AnnuaireJMS\store\AnnuaireJMS.jar" 		"%CD%\Annuaire\annuaire_jms.jar" /Y
xcopy "%CD%\..\JMSBanque\store\JMSBanque.jar" 			"%CD%\Banque\banque.jar" /Y
xcopy "%CD%\..\JMSClientele\store\JMSClientele.jar" 	"%CD%\Clientele\clientele.jar" /Y
xcopy "%CD%\..\JMSObjects\store\JMSObjects.jar" 		"%CD%\Electromenager\objects.jar" /Y
xcopy "%CD%\..\JMSObjects\store\JMSObjects.jar" 		"%CD%\Hifi\objects.jar" /Y
xcopy "%CD%\..\JMSObjects\store\JMSObjects.jar" 		"%CD%\Informatique\objects.jar" /Y
xcopy "%CD%\..\ServeurZ\store\ServeurZ.jar" 			"%CD%\Z\z.jar" /Y
pause
exit