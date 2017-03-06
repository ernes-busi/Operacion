package com.twobig.bimbo.constants;

public class Constants {
	
	public final static String PARAMETERS_ERROR_MESSAGE = "Error los parametros no son correctos";
	
	public final static String FILE_LOG_ERROR_MESSAGE = "No se genero el archivo de logs";
	
	public final static int EXIT_STATUS_ERROR_CREATE_CONFIG_FILE = 11;
	public final static String DIRECTORY_SEPARATOR = "/";
	public final static String USER_HOME = System.getProperty("user.home") + DIRECTORY_SEPARATOR;
	public final static String EXTRACTIONS_FILE_SCRIPT_PATH = USER_HOME + "IC/Notifications/GetErrorExtractions2_0.sh";
	public final static String TRANSFORMATIONS_FILE_SCRIPT_PATH = USER_HOME + "IC/Notifications/GetErrorTransformations2_0.sh";
	public final static String FILE_ERRORS_PATH_EXTRACTIONS = "/tmp/Logs/Operations/IC/Extractions/";
	public final static String FILE_ERRORS_PATH_TRANSFORMATION = "/tmp/Logs/Operations/IC/Transformations/";
	public final static String XLS_FILE_PATH = "/tmp/Logs/Operations/IC/Reports/";
	
	/**
	 * This constant indicates the year attribute that is used by the
	 * configuration file script.
	 */
	public final static String YEAR_CONFIG_FILE_ATTRIBUTE = "--year ";
	
	/**
	 * This constant indicates the month attribute that is used by the
	 * configuration file script.
	 */
	public final static String MONTH_CONFIG_FILE_ATTRIBUTE = "--month ";
	
	/**
	 * This constant indicates the day attribute that is used by the
	 * configuration file script.
	 */
	public final static String DAY_CONFIG_FILE_ATTRIBUTE = "--day ";
	
	/**
	 * This constant indicates the countries attribute that is used by the
	 * configuration file script.
	 */
	public final static String COUNTRIES_CONFIG_FILE_ATTRIBUTE = "--countries ";
	
	public final static String DATE_FORMAT = "yyyy-MM-dd";
	
	public final static String FIRST_SHEET_NAME = "Extracción";
	
	public final static String SECOND_SHEET_NAME = "Transformación";
	
	public final static String FIRST_COLUMN_NAME = "FECHA";
	
	public final static String SECOND_COLUMN_NAME = "PAIS";
	
	public final static String THIRD_COLUMN_NAME = "AGENCIA";
	
	public final static String QUARTER_COLUMN_NAME = "TABLA";
	
	public final static String FIFTH_COLUMN_NAME = "TIPO";
	
	public final static String SIXTH_COLUMN_NAME = "ERRORES";
	
	public final static String FIRST_COLUMN_NAME_SHEET_TWO = "FECHA";
	
	public final static String SECOND_COLUMN_NAME_SHEET_TWO = "Nombre Archivo";
	
	public final static String THIRD_COLUMN_NAME_SHEET_TWO = "Error";
	
	public final static String QUARTER_COLUMN_NAME_SHEET_TWO = "Query";
	
	////Extractions
	
	public final static String FIRST_PRINCIPAL_ERROR_TEXT_EXTRACTION = "ERROR";
	
	public final static String SECOND_PRINCIPAL_ERROR_TEXT_EXTRACTION = "acquire";
	
	public final static String THIRD_PRINCIPAL_ERROR_TEXT_EXTRACTION = "Domain error";
	
	public final static String QUARTER_PRINCIPAL_ERROR_TEXT_EXTRACTION = "FAILED";
	
	public final static String FIFTH_PRINCIPAL_ERROR_TEXT_EXTRACTION = "failed";
	
	public final static String FIRST_SECONDARY_ERROR_TEXT_EXTRACTION = "Error executing statement: com.sybase.jdbc4.jdbc.SybSQLException:";
	
	public final static String SECOND_SECONDARY_ERROR_TEXT_EXTRACTION = "com.sybase.jdbc4.jdbc.SybSQLException:";
	
	public final static String THIRD_SECONDARY_ERROR_TEXT_EXTRACTION = "not found";
	
	public final static String QUARTER_SECONDARY_ERROR_TEXT_EXTRACTION = "permission";
	
	public final static String FIFTH_SECONDARY_ERROR_TEXT_EXTRACTION = "object";
	
	public final static String SIXTH_SECONDARY_ERROR_TEXT_EXTRACTION = "Table not found";
	
	public final static String SEVENTH_SECONDARY_ERROR_TEXT_EXTRACTION = "cannot recognize input near";
	
	public final static String EIGHTH_SECONDARY_ERROR_TEXT_EXTRACTION = "near";
	
	public final static String NINETH_SECONDARY_ERROR_TEXT_EXTRACTION = "in";
	
	public final static String TENTH_SECONDARY_ERROR_TEXT_EXTRACTION = "insert into oper_stats.extractions_details values (";
	
	public final static String MESSAGE_ERROR_TEXT_1_EXTRACTION = "No se encontro la tabla: ";
	
	public final static String MESSAGE_ERROR_TEXT_2_EXTRACTION = "Permiso denegado a la tabla: ";
	
	public final static String MESSAGE_ERROR_TEXT_3_EXTRACTION = "No se reconoce la entrada cerca de: ";
	
	public final static String SQOOP_TEXT = "SQOOP EXTRACTION";
	
	public final static String MOVE_TEXT = "MOVE DATA";
	
	
////Transformation
	
	public final static String FIRST_PRINCIPAL_ERROR_TEXT_TRANSFORMATION = "insert into";
	
	public final static String SECOND_PRINCIPAL_ERROR_TEXT_TRANSFORMATION = "create table";
	
	public final static String THIRD_PRINCIPAL_ERROR_TEXT_TRANSFORMATION = "drop";
	
	public final static String QUARTER_PRINCIPAL_ERROR_TEXT_TRANSFORMATION = "select";

}
