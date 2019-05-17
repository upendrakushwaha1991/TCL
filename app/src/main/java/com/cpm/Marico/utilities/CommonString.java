package com.cpm.Marico.utilities;
import android.os.Environment;


public class CommonString {

    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_RIGHTNAME = "rightname";
    public static final String KEY_DATE = "date";
    public static final String KEY_DDATE = "DATE";
    public static final String KEY_YYYYMMDD_DATE = "yyyymmddDate";
    public static final String KEY_STOREVISITED_STATUS = "STOREVISITED_STATUS";
    public static final String KEY_NOTICE_BOARD = "NOTICE_BOARD";
    public static final String KEY_QUIZ_URL = "QUIZ_URL";
    public static final String KEY_PATH = "path";
    public static final String KEY_VERSION = "APP_VERSION";
    public static final String KEY_LOGIN_DATA = "login_data";
    public static final String KEY_CHECKOUT_IMAGE = "CHECKOUT_IMAGE";
    public static final String KEY_NOTICE_BOARD_LINK = "NOTICE_BOARD_LINK";
    public static final String KEY_STORE_ID = "STORE_ID";
    public static final String KEY_CATEGORY_CD = "CATEGORY_CD";
    public static final String KEY_CATEGORY_IMAGE = "CATEGORY_IMAGE";
    public static final String KEY_VISIT_DATE = "VISIT_DATE";
    public static final String KEY_USER_ID = "USER_ID";
    public static final String KEY_STORE_IN_TIME = "STORE_IN_TIME";
    public static final String KEY_LATITUDE = "LATITUDE";
    public static final String KEY_LONGITUDE = "LONGITUDE";
    public static final String KEY_REASON_ID = "REASON_ID";
    public static final String KEY_REASON = "REASON";
    public static final String KEY_COVERAGE_REMARK = "REMARK";
    public static final String KEY_IMAGE = "IMAGE";
    public static final String KEY_ID = "Id";
    public static final String KEY_IID = "KEY_ID";
    public static final String TAG_OBJECT = "OBJECT";
    public static final String KEY_LIST = "List";
    public static final String TAG_WINDOW_OBJECT = "WINDOW_OBJECT";

    public static final String KEY_DOWNLOAD_INDEX = "download_Index";

    //KEYS RELATED TO T2P COMPLIANCE

    public static final String KEY_COMMON_ID = "COMMON_ID";
    public static final String KEY_CHECKLIST_ID = "CHECKLIST_ID";
    public static final String KEY_STOCK = "STOCK";
    public static final String KEY_BRAND_ID = "BRAND_ID";
    public static final String KEY_CATEGORY_ID = "CATEGORY_ID";
    public static final String KEY_CATEGORY = "CATEGORY";
    public static final String KEY_IMAGE1 = "IMAGE1";
    public static final String KEY_IMAGE2 = "IMAGE2";
    public static final String KEY_BRAND_CD = "Brand_Cd";
    public static final String KEY_BRAND = "Brand";
    public static final String KEY_JOURNEY_PLAN = "JOURNEY_PLAN";
    public static final String TABLE_Journey_Plan = "Journey_Plan";
    public static final String TABLE_Journey_Plan_DBSR_Saved = "Journey_Plan_DBSR_Saved";
    public static final String TABLE_Deviation_Journey_Plan = "Deviation_Journey_Plan";
    public static final String KEY_P = "P";
    public static final String KEY_D = "D";
    public static final String KEY_U = "U";
    public static final String KEY_C = "C";
    public static final String KEY_Y = "Y";
    public static final String KEY_N = "N";
    public static final String STORE_STATUS_LEAVE = "L";
    public static final String KEY_VALID = "Valid";
    public static final String KEY_CHECK_IN = "I";
    // webservice constants

    public static final String KEY_SUCCESS = "Success";
    public static final String KEY_FAILURE = "Failure";

    public static final int LOGIN_SERVICE = 1;
    public static final int DOWNLOAD_ALL_SERVICE = 2;
    public static final int COVERAGE_DETAIL = 3;
    public static final int UPLOADJCPDetail = 4;
    public static final int UPLOADJsonDetail = 5;
    public static final int COVERAGEStatusDetail = 6;
    public static final int CHECKOUTDetail = 7;
    public static final int DELETE_COVERAGE = 8;
    public static final int COVERAGE_NONWORKING = 9;
    public static final int COVERAGE_DETAIL_CLIENT = 10;
    public static final int CHECKOUTDetail_CLIENT = 11;
    public static final int CHANGE_PASSWORD_SERVICE = 12;
    public static String URL = "http://marico.parinaam.in/webservice/Maricoservice.svc/";
    public static String URL3 = "http://marico.parinaam.in/webservice/Imageupload.asmx/";
    public static String URLGORIMAG = "http://marico.parinaam.in/webservice/Imageupload.asmx/";
    public static final String BACKUP_FILE_PATH = Environment.getExternalStorageDirectory() + "/Marico_Backup/";
    public static final String MESSAGE_CHANGED = "Invalid UserId Or Password";
    public static final String MESSAGE_INTERNET_NOT_AVALABLE = "No Internet Connection.Please Check Your Network Connection";
    public static final String MESSAGE_EXCEPTION = "Problem Occured : Report The Problem To Parinaam ";
    public static final String MESSAGE_SOCKETEXCEPTION = "Network Communication Failure. Please Check Your Network Connection";
    public static final String MESSAGE_NO_RESPONSE_SERVER = "Server Not Responding.Please try again.";
    public static final String MESSAGE_XmlPull = "Problem Occured xml pull: Report The Problem To Parinaam";
    public static final String MESSAGE_INVALID_JSON = "Problem Occured while parsing Json : invalid json data";
    public static final String MESSAGE_NUMBER_FORMATE_EXEP = "Invailid Mid";
    public static final String TABLE_STORE_GEOTAGGING = "STORE_GEOTAGGING";
    public static final String TABLE_COVERAGE_DATA = "COVERAGE_DATA";
    public static final String KEY_PROMOTION = "PROMOTION";
    public static final String KEY_POSM = "POSM";
    public static final String KEY_STORE_NAME = "STORE_NAME";
    public static final String TAG_FROM = "FROM";
    public static final String TAG_FROM_JCP = "from_jcp";
    public static final int TAG_FROM_PREVIOUS = 0;
    public static final int TAG_FROM_CURRENT = 1;
    public static final String TAG_FROM_NONWORKING = "from_NonWorking";
    public static final String KEY_WINDOW_ID = "WINDOW_ID";
    public static final String KEY_EXISTORNOT = "EXISTORNOT";
    public static final String KEY_WINDOW_IMAGE = "WINDOW_IMAGE";
    public static final String KEY_WINDOW_IMAGE2 = "WINDOW_IMAGE2";
    public static final String KEY_WINDOW_CD = "WINDOW_CD";
    public static final String KEY_STATUS = "STATUS";
    public static final String KEY_POSM_ID = "POSM_ID";

    //File Path
    public static final String FILE_PATH = Environment.getExternalStorageDirectory() + "/.Marico_Images/";
    public static final String FILE_PATH1 = Environment.getExternalStorageDirectory() + "/SaleConfirmation_Images/";
    public static final String FOLDER_NAME_WITH_PATH = Environment.getExternalStorageDirectory() + "/.Marico_Images";
    public static final String FOLDER_NAME_IMAGE = ".Marico_Images";
    public static final String FILE_PATH_Downloaded = Environment.getExternalStorageDirectory() + "/Marico_Downloaded_Img/";
    public static final String ONBACK_ALERT_MESSAGE = "Unsaved data will be lost - Do you want to continue?";
    public static final String KEY_ANSWER_CD = "ANSWER_CD";
    public static final int CAPTURE_MEDIA = 131;
    public static final int GRID_CAMERA_REQUEST_CODE = 1;
    public static final String TABLE_WINDOWS_DATA = "WINDOWS_DATA";
    public static final String TABLE_TESTER_STOCK_DATA = "Tester_Stock_Data";
    public static final String KEY_TESTER_STOCK_EXIST = "Tester_Stock_Exist";
    public static final String KEY_SKU_ID = "Sku_Id";
    public static final String TABLE_CATEGORY_DBSR_DATA = "CATEGORY_DBSR_DATA";
    public static final String KEY_STORE_TYPE = "STORE_TYPE";
    public static final String KEY_STORE_TYPE_CD = "STORE_TYPE_CD";
    public static final String KEY_STORE_ADDRESS = "STORE_ADDRESS";
    public static final String KEY_STORE_CITY = "STORE_CITY";
    public static final String TABLE_STORE_PROFILE_DATA = "STORE_PROFILE_DATA";
    public static final String KEY_EXIST = "Exist";
    public static final String TABLE_POSM_DEPLOYMENT = "POSM_Deployment";
    public static final String KEY_STATE_ID = "State_Id";
    public static final String KEY_STORE_TYPE_ID = "Store_Type_Id";
    public static final String KEY_IMAGE_CLOSEUP = "IMAGE_CLOSEUP";
    public static final String KEY_IMAGE_LONGSHOT = "LONGSHOT";
    public static final String KEY_LONGSHOT = "LONGSHOT";
    public static final String KEY_ANSWER = "ANSWER";
    public static final String KEY_PRESENT_EXIST = "PRESENT_EXIST";
    public static final String KEY_WORKING_EXIST = "WORKING_EXIST";
    public static final String KEY_LOCATION_EXIST = "LOCATION_EXIST";
    public static final String KEY_PURITY_EXIST = "PURITY_EXIST";
    public static final String KEY_PLANOGRAM_EXIST = "PLANOGRAM_EXIST";
    public static final String KEY_CHEKLIST_ID = "CHEKLIST_ID";
    public static final String KEY_CHEKLIST = "CHEKLIST";
    public static final String TABLE_VISICOOLER = "DR_VISICOOLER";
    public static final String TABLE_VISICOOLER_CHEKLIST = "DR_VISICOOLER_CHEKLIST";
    public static final String TABLE_INSERT_FOCUS_PRODUCT_STOCK_OPENINGHEADER_DATA = "FOCUS_PRODUCT_OPENINGHEADER_DATA";
    public static final String TABLE_STORE_FOCUS_PRODUCT_DATA = "FOCUS_PRODUCT_STOCK_DATA";
    public static final String KEY_MENU_ID = "MENU_ID";
    public static final String TABLE_VISICOOLER_DATA = "DR_VISICOOLER_DATA";
    public static final String TABLE_WINDOW_HEADER = "WINDOW_HEADER";
    public static final String TABLE_WINDOW_BRAND_LIST = "WINDOW_BRAND_LIST";
    public static final String TABLE_WINDOW_CHECK_LIST = "WINDOW_CHECK_LIST";
    public static final String TABLE_BRAND_CHECK_LIST = "BRAND_CHECK_LIST";
    public static final String TABLE_CTU_BRAND_HEADER = "CTU_BRAND_HEADER";
    public static final String TABLE_CTU_BRAND_CHECK_LIST = "CTU_BRAND_CHECK_LIST";
    public static final String TABLE_SECONDARY_VISIBILITY_HEADER = "SECONDARY_VISIBILITY_HEADER";
    public static final String TABLE_SECONDARY_VISIBILITY_CHECK_LIST = "CTU_BRAND_CHECK_LIST";
    public static final String KEY_WINDOW = "WINDOW";
    public static final String KEY_CHECKLIST = "CHECKLIST";
    public static final String KEY_DISPLAY_ID = "DISPLAY_ID";
    public static final String KEY_DISPLAY = "DISPLAY";
    public static final String TABLE_MONKEUSUN_DATA = "DR_MONKEUSUN_DATA";
    public static final String TABLE_MONKEUSUN_CHEKLIST = "DR_MONKEUSUN_CHEKLIST";
    public static final String TABLE_BACKOF_STORE_HEADER_DATA = "DR_BACKOF_STORE_HEADER_DATA";
    public static final String TABLE_HEADER_BACK_OF_STORE = "DR_HEADER_BACK_OF_STORE";
    public static final String TABLE_CHILD_BACK_OF_STORE_DATA = "DR_CHILD_BACK_OF_STORE_DATA";
    public static final String TABLE_JAR_DATA = "DR_JAR_DATA";
    public static final String TABLE_JAR_CHEKLIST = "DR_JAR_CHEKLIST";
    public static final String TABLE_FEEDBACK_QUESTIONS_DATA = "Feedback_Questions_Data";
    public static final String TABLE_SOS_CHECKLIST_QUESTIONS_DATA = "SOS_Feedback_Questions_Data";
    public static final String TABLE_SOS_HEADER_DATA = "SOS_Header_Data";
    public static final String TABLE_SOS_CHILD_DATA = "SOS_Child_Data";
    public static final String KEY_QUESTION_ID = "Question_Id";
    public static final String KEY_QUESTION = "Question";
    public static final String KEY_CORRECT_ANSWER_ID = "Correct_Answer_Id";
    public static final String KEY_CATEGORY_FACING = "Category_Facing";
    public static final String KEY_BRAND_FACING = "Brand_Facing";
    public static final String KEY_SOS_PERCENTAGE = "SOS_Percentage";
    public static String IS_PASSWORD_CHECK = "IS_PASSWORD_CHECK";
    public static String MPIN = "MPIN";
    public static final String KEY_IS_QUIZ_DONE = "is_quiz_done";
    public static final String KEY_QUESTION_CD = "question_cd";
    public static final String TABLE_STOCK_IMAGE = "STOCK_IMAGE";
    public static final String TABLE_INSERT_OPENINGHEADER_DATA = "openingHeader_data";
    public static final String TABLE_STOCK_DATA = "STOCK_DATA";
    public static final String TABLE_CATEGORY_SHARE_OF_SHELF_IMAGE = "DR_CATEGORY_SHARE_OF_SHELF_IMAGE";
    public static final String TABLE_SHARE_OF_SHELF_FACING_DATA = "DR_SHARE_OF_SHELF_FACING_DATA";
    public static final String TABLE_INSERT_SAMPLED_DATA = "SAMPLED_DATA";
    public static final String TABLE_INSERT_SAMPLED_CHCEKLIST_DATA = "SAMPLED_CHECKLIST_DATA";
    public static final String TABLE_INSERT_MARKET_INTELLI_DATA = "MARKET_INTELLIGENCE";
    public static final String TABLE_GROOMING_IMAGE_DATA = "STORE_GROOMING_IMAGE_DATA";
    public static final String TABLE_PROMOTION_HEADER_DATA = "Promotion_Header_Data";
    public static final String TABLE_PROMOTION_CHILD_DATA = "Promotion_Child_Data";
    public static final String KEY_PROMOTION_ID = "Promotion_Id";
    public static final String KEY_PROMOTION_PRESENT = "Promotion_Present";
    public static final String TABLE_PAID_VISIBILITY_HEADER_DATA = "Paid_Visibility_Header_Data";
    public static final String TABLE_PAID_VISIBILITY_CHILD_DATA = "Paid_Visibility_Child_Data";
    public static final String KEY_PAID_VISIBILITY_PRESENT = "Paid_Visibility_Present";
    public static final String TABLE_INSERT_CHECKLIST_DATA = "CheckList_DATA";
    public static final String TABLE_CATEGORY_DRESSING_DATA = "CATEGORY_DRESSING_DATA";
    public static final String TABLE_INSERT_CATEGORY_DRESSING_CHECKLIST_DATA = "CATEGORY_DRESSING_CHECKLIST_DATA";
    public static final String KEY_SKU = "Sku";


    public static final String CREATE_TABLE_STORE_GEOTAGGING = "CREATE TABLE IF NOT EXISTS "
            + TABLE_STORE_GEOTAGGING
            + " ("
            + "KEY_ID"
            + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + "STORE_ID"
            + " INTEGER,"
            + "LATITUDE"
            + " VARCHAR,"
            + "LONGITUDE"
            + " VARCHAR,"
            + "GEO_TAG"
            + " VARCHAR,"
            + "STATUS"
            + " VARCHAR,"
            + "FRONT_IMAGE" + " VARCHAR)";


    public static final String CREATE_TABLE_COVERAGE_DATA = "CREATE TABLE  IF NOT EXISTS "
            + TABLE_COVERAGE_DATA
            + " (" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + KEY_STORE_ID + " INTEGER,USER_ID VARCHAR, "
            + KEY_VISIT_DATE + " VARCHAR,"
            + KEY_LATITUDE + " VARCHAR,"
            + KEY_LONGITUDE + " VARCHAR,"
            + KEY_IMAGE + " VARCHAR,"
            + KEY_CHECKOUT_IMAGE + " VARCHAR,"
            + KEY_REASON_ID + " INTEGER,"
            + KEY_COVERAGE_REMARK + " VARCHAR,"
            + KEY_REASON + " VARCHAR)";


    //-------------------------------------Neeraj--------------------------



    public static final String CREATE_TABLE_TESTER_STOCK = "CREATE TABLE IF NOT EXISTS "
            + TABLE_TESTER_STOCK_DATA
            + "("
            + "KEY_ID"
            + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + KEY_VISIT_DATE
            + " VARCHAR,"
            + KEY_USERNAME
            + " VARCHAR,"
            + KEY_STORE_ID
            + " VARCHAR,"
            + KEY_MENU_ID + " VARCHAR,"
            + KEY_SKU
            + " VARCHAR,"
            + KEY_SKU_ID
            + " VARCHAR,"
            + KEY_TESTER_STOCK_EXIST
            + " VARCHAR)";

    public static final String CREATE_TABLE_STOCK_IMAGE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_STOCK_IMAGE +
                    "(" +
                    "Key_Id" +
                    " INTEGER PRIMARY KEY AUTOINCREMENT, " +

                    "STORE_ID" +
                    " INTEGER, " +

                    "CATEGORY_CD" +
                    " INTEGER, " +

                    "CATEGORY" +
                    " VARCHAR, " +

                    "IMAGE_CAT_ONE" +
                    " VARCHAR, " +

                    "VISIT_DATE" +
                    " VARCHAR" +
                    ")";


    public static final String CREATE_TABLE_insert_OPENINGHEADER_DATA =
            "CREATE TABLE IF NOT EXISTS " + TABLE_INSERT_OPENINGHEADER_DATA
                    + " ("
                    + "KEY_ID" +
                    " INTEGER PRIMARY KEY AUTOINCREMENT,"

                    + "STORE_ID" +
                    " VARCHAR," +

                    "BRAND_CD" +
                    " INTEGER, " +

                    "BRAND" +
                    " VARCHAR, " +

                    "CATEGORY_CD" +
                    " VARCHAR,"

                    + "CATEGORY" +
                    " VARCHAR"
                    + ")";

    public static final String CREATE_TABLE_STOCK_DATA =
            "CREATE TABLE IF NOT EXISTS " + TABLE_STOCK_DATA +
                    "(" +
                    "Common_Id" +
                    " INTEGER, " +

                    "STORE_ID" +
                    " INTEGER, " +

                    "CATEGORY_CD" +
                    " INTEGER, " +

                    "CATEGORY" +
                    " VARCHAR, " +

                    "BRAND_CD" +
                    " INTEGER, " +

                    "BRAND" +
                    " VARCHAR, " +

                    "SKU_CD" +
                    " INTEGER," +

                    "FOCUS" +
                    " INTEGER," +

                    "SKU" +
                    " VARCHAR, " +

                    "MIDDAY_STOCK" +
                    " VARCHAR, " +

                    "OPENING_STOCK" +
                    " INTEGER, " +

                    "CLOSING_STOCK" +
                    " INTEGER" +
                    ")";



    public static final String CREATE_TABLE_SHARE_OF_SHELF_IMAGE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_CATEGORY_SHARE_OF_SHELF_IMAGE +
                    "(" +
                    "Key_Id" +
                    " INTEGER PRIMARY KEY AUTOINCREMENT, " +

                    "STORE_ID" +
                    " INTEGER, " +

                    "CATEGORY_CD" +
                    " INTEGER, " +

                    "SUB_CATEGORY_CD" +
                    " INTEGER, " +

                    "SUB_CATEGORY" +
                    " VARCHAR, " +

                    "CATEGORY" +
                    " VARCHAR, " +

                    "IMAGE_CAT_FACING" +
                    " VARCHAR, " +

                    "CAT_FACING" +
                    " INTEGER, " +

                    "VISIT_DATE" +
                    " VARCHAR" +
                    ")";


    public static final String CREATE_TABLE_SHARE_OF_SHELF_DATA =
            "CREATE TABLE IF NOT EXISTS " + TABLE_SHARE_OF_SHELF_FACING_DATA +
                    "(" +
                    "Common_Id" +
                    " INTEGER, " +

                    "STORE_ID" +
                    " INTEGER, " +

                    "CATEGORY_CD" +
                    " INTEGER, " +

                    "CATEGORY" +
                    " VARCHAR, " +

                    "SUB_CATEGORY_CD" +
                    " INTEGER, " +

                    "SUB_CATEGORY" +
                    " VARCHAR, " +

                    "BRAND_CD" +
                    " INTEGER, " +

                    "BRAND" +
                    " VARCHAR, " +

                    "FACING" +
                    " INTEGER" +
                    ")";


    public static final String CREATE_TABLE_INSERT_SAMPLED_DATA = "CREATE TABLE IF NOT EXISTS "
            + TABLE_INSERT_SAMPLED_DATA
            + "("
            + "KEY_ID"
            + " INTEGER PRIMARY KEY AUTOINCREMENT ,"

            + "CATEGORY_CD"
            + " INTEGER,"

            + "CATEGORY"
            + " VARCHAR,"

            + "SKU_CD"
            + " INTEGER,"

            + "SKU"
            + " VARCHAR,"

            + "SAMPLED"
            + " VARCHAR,"

            + "PHOTO"
            + " VARCHAR,"

            + "CHECKBOX"
            + " boolean,"

            + "FEEDBACK"
            + " VARCHAR,"

            + "VISIT_DATE"
            + " VARCHAR,"
            + "USER_ID"
            + " VARCHAR,"

            + "EMAIL"
            + " VARCHAR,"

            + "MOBILE"
            + " VARCHAR,"

            + "NAME"
            + " VARCHAR,"

            + "STORE_ID"
            + " INTEGER)";



    public static final String CREATE_TABLE_INSERT_SAMPLED_CHECKLIT_DATA = "CREATE TABLE IF NOT EXISTS "
            + TABLE_INSERT_SAMPLED_CHCEKLIST_DATA
            + "("
            + "KEY_ID"
            + " INTEGER PRIMARY KEY AUTOINCREMENT ,"

            + "COMMON_ID"
            + " INTEGER,"

            + "CHCEKLIST_ID"
            + " VARCHAR,"

            + "CHCEKLIST_CORRECT_ANSWER_CD"
            + " VARCHAR,"

            + "STORE_ID"
            + " INTEGER,"

            + "CHCEKLIST_ANSWER"
            + " VARCHAR)";


    //additional visibility

    public static final String CREATE_TABLE_INSERT_MARKET_INTELLIGENCE_DATA = "CREATE TABLE IF NOT EXISTS "
            + TABLE_INSERT_MARKET_INTELLI_DATA
            + "("
            + "KEY_ID"
            + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + "CHECKBOX"
            + " boolean,"
            + "COMPANY_CD"
            + " INTEGER,"
            + "COMPANY"
            + " VARCHAR,"
            + "CATEGORY_CD"
            + " INTEGER,"
            + "CATEGORY"
            + " VARCHAR,"
            + "PROMOTYPE_CD"
            + " INTEGER,"
            + "PROMOTYPE"
            + " VARCHAR,"
            + "PHOTO"
            + " VARCHAR,"
            + "REMARK"
            + " VARCHAR,"
            + "VISIT_DATE"
            + " VARCHAR,"
            + "USER_ID"
            + " VARCHAR,"
            + "STORE_ID"
            + " INTEGER)";


    public static final String CREATE_TABLE_GROOMING_IMAGE_DATA = "CREATE TABLE  IF NOT EXISTS "
            + TABLE_GROOMING_IMAGE_DATA + " (" + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT ," + KEY_STORE_ID
            + " INTEGER,USER_ID VARCHAR, "
            + KEY_VISIT_DATE + " VARCHAR,"
            + KEY_IMAGE + " VARCHAR)";


    //ng


    public static final String CREATE_TABLE_PROMOTION_HEADER_DATA = "CREATE TABLE  IF NOT EXISTS "
            + TABLE_PROMOTION_HEADER_DATA
            + " (" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + KEY_STORE_ID + " INTEGER,USER_ID VARCHAR, "
            + KEY_VISIT_DATE + " VARCHAR,"
            + KEY_MENU_ID + " VARCHAR,"
            + KEY_CATEGORY + " VARCHAR,"
            + KEY_CATEGORY_ID + " VARCHAR)";



    public static final String CREATE_TABLE_PROMOTION_CHILD_DATA = "CREATE TABLE  IF NOT EXISTS "
            + TABLE_PROMOTION_CHILD_DATA
            + " (" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + KEY_STORE_ID + " INTEGER,USER_ID VARCHAR, "
            + KEY_PROMOTION_ID + " VARCHAR,"
            + KEY_PROMOTION + " VARCHAR,"
            + KEY_VISIT_DATE + " VARCHAR,"
            + KEY_PROMOTION_PRESENT + " VARCHAR,"
            + KEY_MENU_ID + " VARCHAR,"
            + KEY_CATEGORY_ID + " VARCHAR,"
            + KEY_COMMON_ID + " VARCHAR,"
            + KEY_IMAGE1 + " VARCHAR,"
            + KEY_IMAGE2 + " VARCHAR,"
            + KEY_REASON_ID + " VARCHAR)";



    public static final String CREATE_TABLE_PAID_VISIBILITY_HEADER_DATA = "CREATE TABLE  IF NOT EXISTS "
            + TABLE_PAID_VISIBILITY_HEADER_DATA
            + " (" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + KEY_STORE_ID + " INTEGER,USER_ID VARCHAR, "
            + KEY_VISIT_DATE + " VARCHAR,"
            + KEY_BRAND + " VARCHAR,"
            + KEY_BRAND_ID + " VARCHAR)";



    public static final String CREATE_TABLE_PAID_VISIBILITY_CHILD_DATA = "CREATE TABLE  IF NOT EXISTS "
            + TABLE_PAID_VISIBILITY_CHILD_DATA
            + " (" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + KEY_STORE_ID + " INTEGER,USER_ID VARCHAR, "
            + KEY_DISPLAY_ID + " VARCHAR,"
            + KEY_DISPLAY + " VARCHAR,"
            + KEY_VISIT_DATE + " VARCHAR,"
            + KEY_PAID_VISIBILITY_PRESENT + " VARCHAR,"
            + KEY_BRAND_ID + " VARCHAR,"
            + KEY_COMMON_ID + " VARCHAR,"
            + KEY_IMAGE1 + " VARCHAR,"
            + KEY_IMAGE2 + " VARCHAR,"
            + KEY_REASON_ID + " VARCHAR)";


}
