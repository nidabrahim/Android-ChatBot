package com.youssefnida.winou.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by youssefNIDA on 03/04/2017.
 */

public class DBSQLite extends SQLiteOpenHelper {

    //DATABASE INFO
    private static final int VERSION_BDD = 1;
    private static final String NOM_BDD = "winoudbb.db";
    //TABLE CITATION
    private static final String TABLE_CITATION = "tablecitation";
    private static final String COL_IDCITATION = "ID";
    private static final String COL_CITATION = "citationtext";
    private static final String CREATE_BDD_CITATION = "CREATE TABLE " + TABLE_CITATION + " ("+ COL_IDCITATION + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_CITATION + " TEXT NOT NULL )";

    //TABLE CITATION ALARM
    private static final String TABLE_CITATION_ALARM = "tablecitationalarm";
    private static final String COL_IDALARM = "idalarm";
    private static final String COL_ALARMH1 = "alarmh1";
    private static final String COL_ALARMM1 = "alarmm1";
    private static final String CREATE_BDD_CITATIONALARM = "CREATE TABLE " + TABLE_CITATION_ALARM + " ("+ COL_IDALARM + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_ALARMH1 + " INTEGER NOT NULL, " + COL_ALARMM1 + " INTEGER NOT NULL )";


    public DBSQLite(Context context){
        super(context,  NOM_BDD, null, VERSION_BDD );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_BDD_CITATION);
        db.execSQL(CREATE_BDD_CITATIONALARM);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE "+ TABLE_CITATION + ";");
        db.execSQL("DROP TABLE "+ TABLE_CITATION_ALARM + ";");
        onCreate(db);
    }
}
