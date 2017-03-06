package com.twobig.bimbo.task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.twobig.bimbo.constants.Constants;
public class CallShells {

	
	public boolean createExtractionFile(String anio, String mes, String dia,String paises) {

		boolean response = false;

		try {
			ProcessBuilder processBuilder;
			
			if(paises.equals("")) {
				processBuilder = new ProcessBuilder(Constants.EXTRACTIONS_FILE_SCRIPT_PATH,
						Constants.YEAR_CONFIG_FILE_ATTRIBUTE + anio,
						Constants.MONTH_CONFIG_FILE_ATTRIBUTE + mes,
						Constants.DAY_CONFIG_FILE_ATTRIBUTE + dia);
			}else {
				processBuilder = new ProcessBuilder(Constants.EXTRACTIONS_FILE_SCRIPT_PATH,
						Constants.YEAR_CONFIG_FILE_ATTRIBUTE + anio,
						Constants.MONTH_CONFIG_FILE_ATTRIBUTE + mes,
						Constants.DAY_CONFIG_FILE_ATTRIBUTE + dia,
						Constants.COUNTRIES_CONFIG_FILE_ATTRIBUTE + paises);
				
			}
			processBuilder.redirectErrorStream(true);
			Process shell = processBuilder.start();

			BufferedReader stdInput = new BufferedReader(new InputStreamReader(shell.getInputStream()));

			String s = null;
			while ((s = stdInput.readLine()) != null) {
			//	LOGGER.info(s);
				System.out.println(s);
			}

			shell.waitFor();
			int exitStatus = shell.exitValue();

			if (exitStatus == Constants.EXIT_STATUS_ERROR_CREATE_CONFIG_FILE) {
				response = false;
			} else {
				response = true;
			}

			try {
				stdInput.close();
			} catch (IOException e) {
				//LOGGER.error(e.getMessage(), e);
				System.out.println(e.getMessage());
			}
		} catch (IOException e) {
			//LOGGER.error(e.getMessage(), e);
			System.out.println(e.getMessage());
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
			//LOGGER.error(e.getMessage(), e);
		}

		return response;
	}
	
	public boolean createTransformationFile(String anio, String mes, String dia,String paises) {

		boolean response = false;

		try {
			ProcessBuilder processBuilder;
			
			if(paises.equals("")) {
				processBuilder = new ProcessBuilder(Constants.TRANSFORMATIONS_FILE_SCRIPT_PATH,
						Constants.YEAR_CONFIG_FILE_ATTRIBUTE + anio,
						Constants.MONTH_CONFIG_FILE_ATTRIBUTE + mes,
						Constants.DAY_CONFIG_FILE_ATTRIBUTE + dia);
			}else {
				processBuilder = new ProcessBuilder(Constants.TRANSFORMATIONS_FILE_SCRIPT_PATH,
						Constants.YEAR_CONFIG_FILE_ATTRIBUTE + anio,
						Constants.MONTH_CONFIG_FILE_ATTRIBUTE + mes,
						Constants.DAY_CONFIG_FILE_ATTRIBUTE + dia,
						Constants.COUNTRIES_CONFIG_FILE_ATTRIBUTE + paises);
				
			}
			processBuilder.redirectErrorStream(true);
			Process shell = processBuilder.start();

			BufferedReader stdInput = new BufferedReader(new InputStreamReader(shell.getInputStream()));

			String s = null;
			while ((s = stdInput.readLine()) != null) {
			//	LOGGER.info(s);
				System.out.println(s);
			}

			shell.waitFor();
			int exitStatus = shell.exitValue();

			if (exitStatus == Constants.EXIT_STATUS_ERROR_CREATE_CONFIG_FILE) {
				response = false;
			} else {
				response = true;
			}

			try {
				stdInput.close();
			} catch (IOException e) {
				//LOGGER.error(e.getMessage(), e);
				System.out.println(e.getMessage());
			}
		} catch (IOException e) {
		
			System.out.println(e.getMessage());
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
			//LOGGER.error(e.getMessage(), e);
		}

		return response;
	}
}
