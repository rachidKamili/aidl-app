package me.kamili.rachid.server.utils;

import android.database.sqlite.SQLiteDatabase;

public class LegendGroupContract {

    public static final String LEGENDGROUP_TABLE_NAME = "legendgroup";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME    = "name";
    public static final String COLUMN_SLOGAN     = "slogan";
    public static final String COLUMN_DESCRIPTION   = "description";
    public static final String COLUMN_LOGO   = "logo";
    public static final String COLUMN_IMAGE = "image";

    public static final String CREATE_TABLE = "CREATE TABLE "
            + LEGENDGROUP_TABLE_NAME
            + " (" + COLUMN_ID
            + " INTEGER primary key autoincrement, "
            + COLUMN_NAME + " TEXT not null, "
            + COLUMN_SLOGAN + " INTEGER, "
            + COLUMN_DESCRIPTION + " TEXT, "
            + COLUMN_LOGO + " TEXT, "
            + COLUMN_IMAGE + " TEXT)";

    public static void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_TABLE);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
