package com.koiralapankaj007gmail.nepalgovtcallerapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHandler extends SQLiteOpenHelper {

    //DATABASE VERSION AND NAME OF THE DATABASE
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "contact_manager.db";

    //TABLE CONTACT_DETAILS     (TABLE NO: 1)
    private static final String CONTACTS_TABLE = "contacts_details";
    private static final String CONTACT_ID = "contact_id";
    private static final String CONTACT_IMAGE = "contact_image";
    private static final String CONTACT_FIRST_NAME = "first_name";
    private static final String CONTACT_LAST_NAME = "last_name";
    private static final String CONTACT_COMPANY_NAME = "company_name";
    private static final String CONTACT_NOTE = "notes";

    //TABLE PHONE NUMBER        (TABLE NO: 2)
    private static final String PHONE_NUMBERS_TABLE = "phone_numbers";
    private static final String PHONE_NO_ID = "phone_no_id";
    private static final String PHONE_NO_TYPE = "phone_type";
    private static final String PHONE_NO = "phone_no";

    //TABLE EMAIL ID        (TABLE NO: 3)
    private static final String EMAIL_IDS_TABLE = "email_ids";
    private static final String EMAIL_ID_ID = "email_id_id";
    private static final String EMAIL_ID_TYPE = "email_id_type";
    private static final String EMAIL_ID = "email_id";

    //TABLE URL        (TABLE NO: 4)
    private static final String URLS_TABLE = "urls";
    private static final String URL_ID = "url_id";
    private static final String URL_TYPE = "url_type";
    private static final String URL = "url";

    //TABLE SOCIAL PROFILE        (TABLE NO: 5)
    private static final String SOCIAL_PROFILES_TABLE = "social_profiles";
    private static final String SOCIAL_PROFILE_ID = "social_profile_id";
    private static final String SOCIAL_PROFILE_TYPE = "social_profile_type";
    private static final String SOCIAL_PROFILE = "social_profile";

    //TABLE SOCIAL PROFILE        (TABLE NO: 6)
    private static final String INSTANT_MESSAGE_TABLE = "instant_messages";
    private static final String INSTANT_MSG_ID = "instant_msg_id";
    private static final String INSTANT_MSG_TYPE = "instant_msg_type";
    private static final String INSTANT_MSG = "instant_msg";


    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String SQL_CREATE_CONTACTS_TABLE =
                "CREATE TABLE IF NOT EXISTS " + CONTACTS_TABLE + " (" +
                        CONTACT_ID + " INT NOT NULL AUTO INCREMENT, " +
                        CONTACT_FIRST_NAME + " VARCHAR(50) NOT NULL, " +
                        CONTACT_LAST_NAME + " VARCHAR(50) NOT NULL, " +
                        CONTACT_COMPANY_NAME + " VARCHAR(100) NULL, " +
                        CONTACT_IMAGE + " VARCHAR(100) NULL, " +
                        CONTACT_NOTE + " TEXT(500) NULL, " +
                        " PRIMARY KEY (" + CONTACT_ID + "));";

        final String SQL_CREATE_PHONE_NUMBERS_TABLE =
                "CREATE TABLE IF NOT EXISTS " + PHONE_NUMBERS_TABLE + " ( " +
                        PHONE_NO_ID + " INT NOT NULL AUTO INCREMENT, " +
                        CONTACT_ID + " INT NOT NULL, " +
                        PHONE_NO_TYPE + " VARCHAR(25) NULL, " +
                        PHONE_NO + " VARCHAR(25) NULL, " +
                        "PRIMARY KEY (" + PHONE_NO_ID + "), " +
                        "INDEX fk_phone_no_contacts1_idx (" + CONTACT_ID + " ASC), " +
                        "CONSTRAINT fk_phone_no_contacts1 " +
                        "FOREIGN KEY (" + CONTACT_ID + ") " +
                        "REFERENCES " + DATABASE_NAME + "." + CONTACTS_TABLE + " (" + CONTACT_ID + ") " +
                        "ON DELETE NO ACTION " +
                        "ON UPDATE NO ACTION) ";

        final String SQL_CREATE_EMAIL_IDS_TABLE =
                "CREATE TABLE IF NOT EXISTS " + EMAIL_IDS_TABLE + " ( " +
                        EMAIL_ID_ID + " INT NOT NULL AUTO INCREMENT, " +
                        CONTACT_ID + " INT NOT NULL, " +
                        EMAIL_ID_TYPE + " VARCHAR(25) NULL, " +
                        EMAIL_ID + " VARCHAR(50) NULL, " +
                        "PRIMARY KEY (" + EMAIL_ID_ID + "), " +
                        "INDEX fk_email_contacts1_idx (" + CONTACT_ID + " ASC), " +
                        "CONSTRAINT fk_email_contacts1 " +
                        "FOREIGN KEY (" + CONTACT_ID + ") " +
                        "REFERENCES " + DATABASE_NAME + "." + CONTACTS_TABLE + " (" + CONTACT_ID + ") " +
                        "ON DELETE NO ACTION " +
                        "ON UPDATE NO ACTION) ";

        final String SQL_CREATE_URLS_TABLE =
                "CREATE TABLE IF NOT EXISTS " + URLS_TABLE + " ( " +
                        URL_ID + " INT NOT NULL AUTO INCREMENT, " +
                        CONTACT_ID + " INT NOT NULL, " +
                        URL_TYPE + " VARCHAR(25) NULL, " +
                        URL + " VARCHAR(50) NULL, " +
                        "PRIMARY KEY (" + URL_ID + "), " +
                        "INDEX fk_url_contacts1_idx (" + CONTACT_ID + " ASC), " +
                        "CONSTRAINT fk_url_contacts1 " +
                        "FOREIGN KEY (" + CONTACT_ID + ") " +
                        "REFERENCES " + DATABASE_NAME + "." + CONTACTS_TABLE + " (" + CONTACT_ID + ") " +
                        "ON DELETE NO ACTION " +
                        "ON UPDATE NO ACTION) ";

        final String SQL_CREATE_SOCIAL_PROFILES_TABLE =
                "CREATE TABLE IF NOT EXISTS " + SOCIAL_PROFILES_TABLE + " ( " +
                        SOCIAL_PROFILE_ID + " INT NOT NULL AUTO INCREMENT, " +
                        CONTACT_ID + " INT NOT NULL, " +
                        SOCIAL_PROFILE_TYPE + " VARCHAR(25) NULL, " +
                        SOCIAL_PROFILE + " VARCHAR(50) NULL, " +
                        "PRIMARY KEY (" + SOCIAL_PROFILE_ID + "), " +
                        "INDEX fk_social_contacts1_idx (" + CONTACT_ID + " ASC), " +
                        "CONSTRAINT fk_social_contacts1 " +
                        "FOREIGN KEY (" + CONTACT_ID + ") " +
                        "REFERENCES " + DATABASE_NAME + "." + CONTACTS_TABLE + " (" + CONTACT_ID + ") " +
                        "ON DELETE NO ACTION " +
                        "ON UPDATE NO ACTION) ";

        final String SQL_CREATE_INSTANT_MESSAGE_TABLE =
                "CREATE TABLE IF NOT EXISTS " + INSTANT_MESSAGE_TABLE + " ( " +
                        INSTANT_MSG_ID + " INT NOT NULL AUTO INCREMENT, " +
                        CONTACT_ID + " INT NOT NULL, " +
                        INSTANT_MSG_TYPE + " VARCHAR(25) NULL, " +
                        INSTANT_MSG + " VARCHAR(50) NULL, " +
                        "PRIMARY KEY (" + INSTANT_MSG_ID + "), " +
                        "INDEX fk_instant_msg_contacts1_idx (" + CONTACT_ID + " ASC), " +
                        "CONSTRAINT fk_instant_msg_contacts1 " +
                        "FOREIGN KEY (" + CONTACT_ID + ") " +
                        "REFERENCES " + DATABASE_NAME + "." + CONTACTS_TABLE + " (" + CONTACT_ID + ") " +
                        "ON DELETE NO ACTION " +
                        "ON UPDATE NO ACTION) ";

        db.execSQL(SQL_CREATE_CONTACTS_TABLE);
        db.execSQL(SQL_CREATE_PHONE_NUMBERS_TABLE);
        db.execSQL(SQL_CREATE_EMAIL_IDS_TABLE);
        db.execSQL(SQL_CREATE_URLS_TABLE);
        db.execSQL(SQL_CREATE_SOCIAL_PROFILES_TABLE);
        db.execSQL(SQL_CREATE_INSTANT_MESSAGE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + INSTANT_MESSAGE_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + SOCIAL_PROFILES_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + URLS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + EMAIL_IDS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + PHONE_NUMBERS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + CONTACTS_TABLE);
        onCreate(db);
    }

    void addToSQLite(CompleteContactInformationHolder completeContactInformationHolder) {
        ContentValues contact_table = new ContentValues();
        ContentValues phone_table = new ContentValues();
        ContentValues email_table = new ContentValues();
        ContentValues url_table = new ContentValues();
        ContentValues social_table = new ContentValues();
        ContentValues instant_table = new ContentValues();

        SQLiteDatabase db = getWritableDatabase();

        contact_table.put(CONTACT_FIRST_NAME, completeContactInformationHolder.getFIRST_NAME());
        contact_table.put(CONTACT_LAST_NAME, completeContactInformationHolder.getLAST_NAME());
        contact_table.put(CONTACT_COMPANY_NAME, completeContactInformationHolder.getCOMPANY_NAME());
        contact_table.put(String.valueOf(CONTACT_IMAGE), completeContactInformationHolder.getCONTACT_IMAGE());
        contact_table.put(CONTACT_NOTE, completeContactInformationHolder.getNOTES());

        db.insert(CONTACTS_TABLE, null, contact_table);
        int id = Integer.parseInt(databaseToString());

        phone_table.put(CONTACT_ID, id);
        phone_table.put(PHONE_NO, completeContactInformationHolder.getPHONE_NUMBER_1());
        email_table.put(CONTACT_ID, id);
        email_table.put(EMAIL_ID, completeContactInformationHolder.getEMAIL_ID_1());
        url_table.put(CONTACT_ID, id);
        url_table.put(URL, completeContactInformationHolder.getURL_1());
        instant_table.put(CONTACT_ID, id);
        social_table.put(SOCIAL_PROFILE, completeContactInformationHolder.getSOCIAL_PROFILE_1());
        instant_table.put(CONTACT_ID, id);
        instant_table.put(INSTANT_MSG, completeContactInformationHolder.getINSTANT_MESSAGE_1());



        db.insert(PHONE_NUMBERS_TABLE, null, phone_table);
        db.insert(EMAIL_IDS_TABLE, null, email_table);
        db.insert(URLS_TABLE, null, url_table);
        db.insert(SOCIAL_PROFILES_TABLE, null, social_table);
        db.insert(INSTANT_MESSAGE_TABLE, null, instant_table);
        db.close();
    }

//    void deleteContact(String product) {
//        SQLiteDatabase db = getWritableDatabase();
//        db.execSQL("DELETE FROM " + "CONTACTS_TABLE " + "WHERE " + CONTACT_FIRST_NAME + "=\"" + product + "\";" );
//    }
//
    private String databaseToString() {
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();

        String query = "SELECT contact_id FROM " + CONTACTS_TABLE;
        Cursor c = db.rawQuery(query, null);
        dbString = c.getString(c.getColumnIndex("contact_id"));

//        c.moveToFirst();
//
//        while (!c.isAfterLast()) {
//            if (c.getString(c.getColumnIndex("product")) != null) {
//                dbString += c.getString(c.getColumnIndex("product"));
//                dbString += "\n";
//            }
//            c.moveToNext();
//        }
        db.close();
        c.close();
        return dbString;
    }
}
