package com.twobig.bimbo.bitacora;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.twobig.bimbo.constants.Constants;
import com.twobig.bimbo.task.CallShells;

/**
 * Hello world!
 *
 */
public class MainBitacora {
	
	public static HSSFWorkbook workbook = new HSSFWorkbook();
	public static OutputStream out;
	
	public static void main(String[] args) {

		 if (args.length == 0 || args.length < 6 || args.length > 7) {
		
		 System.out.println(Constants.PARAMETERS_ERROR_MESSAGE);
		
		 return;
		
		 }
		String countries="";
		String yearExtraction = args[0];
		String monthExtraction = args[1];
		String dayExtraction = args[2];
		String yearTransformation = args[3];
		String monthTransformation = args[4];
		String dayTransformation = args[5];
		
		 if(args.length == 7) {
			 countries = args[6];
		 }
		 boolean response;
		 CallShells callShells = new CallShells();
		 response = callShells.createExtractionFile(yearExtraction, monthExtraction, dayExtraction, countries);
//		 
		 if(!response) {
			 System.out.println("Error al correr el shell de Extraccion");
				
			 return;
		 }
		 response =callShells.createTransformationFile(yearTransformation, monthTransformation, dayTransformation, countries);
		 
		 if(!response) {
			 System.out.println("Error al correr el shell de Transformacion");
				
			 return;
		 }
//		 
		 String fileNameExtraction = getLastModified(new File(Constants.FILE_ERRORS_PATH_EXTRACTIONS));
		 
		 String fileNameTransformation = getLastModified(new File(Constants.FILE_ERRORS_PATH_TRANSFORMATION));
		 
		if(fileNameExtraction.equals("")) {
			System.out.println(Constants.FILE_LOG_ERROR_MESSAGE);
			return;
		}
		
//		 String fileNameExtraction = "Errores_TME.txt";
		 
		ReportExtraction reportExtraction = new ReportExtraction();
		reportExtraction.GetReportExtraction(fileNameExtraction, countries, yearExtraction,
				monthExtraction, dayExtraction, args.length);
		
		if(fileNameTransformation.equals("")) {
			System.out.println(Constants.FILE_LOG_ERROR_MESSAGE);
			return;
		}
		 
//		 String fileNameTransformation = "ErroresTrans.txt";
		
		ReportTransformation reportTransformation = new ReportTransformation();
		reportTransformation.GetReportTransformation(fileNameTransformation, countries, yearTransformation,
				monthTransformation, dayTransformation, args.length);
		
		try {
			Date date = new Date();
			DateFormat dateFormat = new SimpleDateFormat(Constants.DATE_FORMAT);
			if(args.length == 7) {
				out = new FileOutputStream(Constants.XLS_FILE_PATH + "Errores_"+ countries + "_" + 
						dateFormat.format(date) + ".xls");
//				out = new FileOutputStream("ErroresT_"+ countries + "_" + 
//						dateFormat.format(date) + ".xls");
			 }else {
				 out = new FileOutputStream(Constants.XLS_FILE_PATH + "Errores_"+ "todos_" + 
						 dateFormat.format(date) + ".xls");	
//				 out = new FileOutputStream("Errores_"+ "todos_" + 
//						 dateFormat.format(date) + ".xls");	
			 }
//			MainBitacora.out = new FileOutputStream("ErroresT_" + 
//					yearTransformation + "-" + monthTransformation + "-" + dayTransformation + ".xls");
			workbook.write(out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String getLastModified(File directory) {
	    File[] files = directory.listFiles();
	    if (files.length == 0) return "";
	    Arrays.sort(files, new Comparator<File>() {
	        public int compare(File o1, File o2) {
	            return new Long(o2.lastModified()).compareTo(o1.lastModified()); //latest 1st
	        }});
//	   System.out.println(files[0].getName());
	    return files[0].getName();
	}
}
