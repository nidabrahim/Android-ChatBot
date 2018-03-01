package com.youssefnida.winou.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.youssefnida.winou.Citation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by youssefNIDA on 03/04/2017.
 */

public class CITATION_DBMANAGER {

    private static final String TABLE_CITATION = "tablecitation";
    private static final String COL_ID = "ID";
    private static final int NUM_COL_ID = 0;
    private static final String COL_CITATION = "citationtext";
    private static final int NUM_COL_CITATION = 1;

    //TABLE CITATION ALARM
    private static final String TABLE_CITATION_ALARM = "tablecitationalarm";
    private static final String COL_IDALARM = "idalarm";
    private static final int NUM_COL_IDALARM = 0;
    private static final String COL_ALARMH1 = "alarmh1";
    private static final int NUM_COL_ALARMH1 = 1;
    private static final String COL_ALARMM1 = "alarmm1";
    private static final int NUM_COL_ALARMM1 = 2;

    private static final int HEURE1_DEFAULT = 7;
    private static final int MINUTE1_DEFAULT = 58;


    private SQLiteDatabase bdd;
    private DBSQLite maBaseSQLite;

    public CITATION_DBMANAGER(Context context){

        maBaseSQLite = new DBSQLite(context);
    }

    public void open(){

        bdd = maBaseSQLite.getWritableDatabase();
    }

    public void close(){

        bdd.close();
    }

    //L'INSERTION D'UNE CITATION
    public long insertCitation(Citation citation){

        this.open();

        ContentValues values = new ContentValues();
        values.put(COL_CITATION, citation.getCitation_text());

        return bdd.insert(TABLE_CITATION, null, values);
    }

    public void supprimerCitation(Citation citation){

        this.open();
        bdd.delete(TABLE_CITATION ,COL_CITATION +" = ? ", new String[]{citation.getCitation_text()});

    }

    //RECUPERER TOUTES LES CITATION
    public List<Citation> getAllCitation(){

        this.open();

        Cursor c = bdd.rawQuery("SELECT * FROM " + TABLE_CITATION, null);

        return cursorToListeCitation(c);
    }

    private List<Citation> cursorToListeCitation(Cursor c){

        List<Citation> allCitation = new ArrayList<Citation>();

        if(c.moveToFirst())
            while(c.isAfterLast() == false) {
                Citation citation = new Citation();
                citation.setCitation_text(c.getString(NUM_COL_CITATION));
                allCitation.add( citation );
                c.moveToNext();
            }

        c.close();

        return allCitation;
    }



    //L'INSERTION D'UNE CITATION
    public long insertCitationAlarm(int h1, int m1){

        this.open();

        ContentValues values = new ContentValues();
        values.put(COL_ALARMH1, h1);
        values.put(COL_ALARMM1, m1);

        Cursor c = bdd.rawQuery("SELECT * FROM " + TABLE_CITATION_ALARM, null);
        if(c.moveToFirst()){

            return bdd.update(TABLE_CITATION_ALARM,values,COL_IDALARM+"=1",null);
        }
        else{

            return bdd.insert(TABLE_CITATION_ALARM, null, values);
        }

    }
    //RECUPERER ALARM
    public int getHeure1(){

        int h1 = HEURE1_DEFAULT;
        this.open();
        Cursor c = bdd.rawQuery("SELECT * FROM " + TABLE_CITATION_ALARM, null);
        if(c.moveToFirst()) h1 = c.getInt(NUM_COL_ALARMH1);
        return h1;
    }
    public int getMinute1(){

        int m1 = MINUTE1_DEFAULT;
        this.open();
        Cursor c = bdd.rawQuery("SELECT * FROM " + TABLE_CITATION_ALARM, null);
        if(c.moveToFirst()) m1 = c.getInt(NUM_COL_ALARMM1);
        return m1;
    }

}
