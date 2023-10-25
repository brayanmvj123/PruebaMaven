package com.bdb.opaloshare.util;

public class Constants {

    private Constants() {
    }

    // ********** BASES  **********
    public static final double BASE_360 = 360F;
    public static final double BASE_365 = 365F;
    public static final double BASE_366 = 366F;

    // ********** PERIODO  **********
    public static final double PER_1 = 30F;
    public static final double PER_2 = 60F;
    public static final double PER_3 = 90F;
    public static final double PER_4 = 120F;
    public static final double PER_5 = 150F;
    public static final double PER_6 = 180F;
    public static final double PER_7 = 210F;
    public static final double PER_8 = 240F;
    public static final double PER_10 = 300F;
    public static final double PER_12 = 360F;
    public static final double PER_18 = 540F;
    public static final double PER_24 = 720F;
    public static final double PER_30 = 900F;
    public static final double PER_36 = 1080F;
    public static final double PER_42 = 1260F;
    public static final double PER_48 = 1440F;
    public static final double PER_60 = 1800F;

    public static final String DECIMAL_FORMAT_2 = "#.##";
    public static final String DECIMAL_FORMAT_4 = "#.####";
    public static final String DECIMAL_FORMAT_6 = "#.######";
    public static final String DECIMAL_FORMAT_AVOID_NOTATION = "%.0f";

    public static final String ERR_MSG = "######### Un error o excepcion ha ocurrido: ";

    // ********** BATCHDIARIOS:  ***********
    public static final String FILEINPATRI = "FILE_INPUT_PATRIMONIALES";
    public static final Long FIND_URL_GENEXCEL = 3L;
    public static final String PGDIGITAL = "Reportes de instrucciones de pago para CDT digital.";
    public static final String PGDAUTOR = "OPALO BATCH";
    public static final Long FIND_URL_TESOCDTRENOVA = 6L;
    public static final Long FIND_URL_TESOCDTCANCEL = 5L;
    public static final String BDB_DATE_FORMAT = "yyyyMMdd";
    public static final String FILE_DCV_DERPATRI = "FILE_INPUT_DCV_PATRIMONIALES";
    public static final String FILE_WEEK_DCV_DERPATRI = "FILE_INPUT_WEEK_DCV_PATRIMONIALES";
    public static final String FILE_DEPOSITOR_DCV_DERPATRI = "FILE_DEPOSITOR_DCV_PATRIMONIALES";
    //*********** CONSTANTES ARCHIVO EXCEL SERVICIO BLOQUEAR USUARIO
    public static final String TITLE_FILE_BLOQUEAR_USUARIO = "REPORTE INACTIVACION DE USUARIOS";
    public static final String AUTHOR_FILE_BLOQUEAR_USUARIO = "OPALO BATCH";
    public static final Long FIND_URL_EMAIL = 4L;
    //*********** CONSTANTES ARCHIVO EXCEL SERVICIO ENVIO REPORTE HIS LOGIN USER
    public static final String TITLE_FILE_REPORTE_HIS_LOGIN = "REPORTE DE USUARIOS OPALO";
    public static final String AUTHOR_FILE_REPORTE_HIS_LOGIN = "OPALO BATCH ADMIN";

    // ********** BATCHSEMANAL:  **********
    public static final String CDTREIN = "Reportes CDTs reinvetidos.";
    public static final String CDTCANC = "Reportes CDTs cancelados.";
    public static final String DIAS_TESORCDT = "DIAS_TESORCDT";


}
