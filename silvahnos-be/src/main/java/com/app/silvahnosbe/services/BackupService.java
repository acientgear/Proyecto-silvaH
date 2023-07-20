package com.app.silvahnosbe.services;

import java.io.File;
import java.io.IOException;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


/**
 * servicio encargado de realizar respaldos a la base de datos de manera automatica
 *
 * @author IgnacioGrez
 *
 */



@Service
public class BackupService {


    @Value("${spring.datasource.username}")
    private String databaseUsername;

    @Value("${spring.datasource.password}")
    private String databasePassword;

	@Value("${spring.datasource.name}")///debe ser definido en el aplicacion.propertie. nombre de la base de datos
	private String databaseName;

	@Value("${backup.pathname}")///debe ser definido en el aplication.propertires. ruta donde se guardaran los backups
	private String backupPath;


	/**
	 * funcion que crea los backup de manera automatica
	 * @params ninguno
	 * @return null
	 */



	///@Scheduled(cron = "0 */1 * * * *")
    public void makeBackup() throws ClassNotFoundException, IOException, SQLException{
        System.out.println("Backup Started at " + new Date());
		
		Date backupDate = new Date();
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		String backupDateStr = format.format(backupDate);

		String fileName = "Backup";
		File f1 = new File(backupPath);
		f1.mkdir(); 

		String saveFileName = fileName + backupDateStr +".sql";
		String savePath = backupPath + File.separator + saveFileName;


		///por como se ejecuta, la carpeta bin de mysql debe de estar en el PATH del sistema. si no, debe darse la ruta completa al ejecutable de mysqldump
		String executeCmd = "mysqldump -u " + databaseUsername + " -p" + databasePassword+ "  --databases " + databaseName
				+ " -r " + savePath; 

		Process runtimeProcess = null;
		try {
			runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            if (runtimeProcess==null){

                return;
            }
		} catch (IOException e) {
			e.printStackTrace();
            return;
		}
		int processComplete = 0;
		try {
			processComplete = runtimeProcess.waitFor();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		if (processComplete == 0) {
			System.out.println("Backup Complete at " + new Date());
		} else {
			System.out.println("Backup Failure");
		}
	}

}
