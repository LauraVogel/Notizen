package com.laura.notizen;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Laura on 21.04.2015.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASENAME = "notizen.db";
    private static final int DATABASEVERSION = 1;

    //Tabelleninfo
    private static final String NOTETABLE = "notizen";   //Tabellenname
    //Spalten
    private static final String NOTEID = "_ID";
    private static final String NOTETITEL = "TITEL";
    private static final String NOTECONTENT = "CONTENT";


    public MySQLiteHelper(Context context) {
        super(context, DATABASENAME, null, DATABASEVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createDB = "CREATE TABLE " + NOTETABLE +" ("+ NOTEID +" INTEGER PRIMARY KEY AUTOINCREMENT, " + NOTETITEL + " TEXT," + NOTECONTENT +" TEXT)";
        db.execSQL(createDB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ NOTETABLE);
        onCreate(db);
    }

    public void setEntry(Note n)
    {
        try {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(NOTETITEL, n.getTitle());
            values.put(NOTECONTENT, n.getContent());

            db.insert(NOTETABLE, null, values);
            db.close();

            Log. d("Eintrag", "gespeichert");
        }
        catch (Exception e){
            Log. d("Eintrag nicht", "gespeichert");
        }
    }

    public Note[] getNotizen()
    {
        String queryT = "SELECT " + NOTETITEL +  " FROM " + NOTETABLE;
        String queryC = "SELECT " + NOTECONTENT + " FROM " + NOTETABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryT,null);
        Cursor curserC = db.rawQuery(queryC,null);

        Note [] allNotizen = new Note[1];

        //Wenn db leer dann erscheint infotext
        if(getNumber() == 0)
        {
            Note helper = new Note();
            helper.setTitle("Noch keine Notizen gespeichert");
            allNotizen[0] = helper;
            return allNotizen;
        }
        int number = 0;

        if(cursor.moveToFirst() && curserC.moveToFirst())
        {
            //Arraygröße wird angepasst
            allNotizen= new Note[getNumber()];
            do
            {
                //aktueller Titel und aktuelle Notiz werden abgefragt, in ein Notizobjekt geschrieben und im Array gespeichert
                Note temp = new Note();
                temp.setTitle(cursor.getString(0));
                temp.setContent(curserC.getString(0));
                allNotizen[number] = temp;
                number++;
            }
            while (cursor.moveToNext() && curserC.moveToNext());
        }

        return allNotizen;
    }
    //Anzahl der Datensäze wird ermittelt
    public int getNumber()
    {
        int number = -1;
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.query(NOTETABLE, null ,null,null,null,null,null);
            number = cursor.getCount();
            return number;
        }
        finally {
            return  number;
        }
    }
}
