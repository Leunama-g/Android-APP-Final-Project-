package com.example.notes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "NOTES";
    public static final String TITLE_COLUMN = "TITLE";
    public static final String DATA_COLUMN = "DATA";
    public static final String LAST_EDITED_COLUMN = "LAST_EDITED";
    public static final String CATEGORY_COLUMN = "CATEGORY";
    public static final String ID_COLUMN = "ID";

    public DBHelper(Context context) {
        super(context, "Notes.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + TABLE_NAME + " (" + ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TITLE_COLUMN + " TEXT, " + DATA_COLUMN + " TEXT, " + LAST_EDITED_COLUMN + " INTEGER, " + CATEGORY_COLUMN + " TEXT)";
        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
    public void saveNote(Note data){
        SQLiteDatabase sq = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TITLE_COLUMN, data.title);
        cv.put(DATA_COLUMN, data.data);
        cv.put(CATEGORY_COLUMN, data.category);
        cv.put(LAST_EDITED_COLUMN, System.currentTimeMillis());
        if(data.id == -1)
            sq.insert(TABLE_NAME, null, cv);
        else
            sq.update(TABLE_NAME, cv,  ID_COLUMN + " = ?", new String[]{Integer.toString(data.id)});
        sq.close();
    }

    public List<Note> loadNotes(String cat){
        List<Note> notes = new ArrayList<>();
        String[] arg = null;
        Note buff = new Note();

        String statement = "SELECT * FROM " + TABLE_NAME;
        if(!cat.equals("")){
            statement += " WHERE CATEGORY = ?";
            arg = new String[1];
            arg[0] = cat;
        }
        statement += " ORDER BY "+ LAST_EDITED_COLUMN +" DESC";

        SQLiteDatabase sq = this.getReadableDatabase();
        Cursor cur = sq.rawQuery(statement, arg);

        if(cur.moveToFirst()){
            do{
                buff.id = cur.getInt(0);
                buff.title = cur.getString(1);
                buff.data = cur.getString(2);
                buff.lastEdited = cur.getInt(3);
                buff.category = cur.getString(4);
                notes.add(buff);
                buff = new Note();
            }while(cur.moveToNext());
        }
        cur.close();
        sq.close();
        return notes;
    }

    public void deleteOne(int id){
        SQLiteDatabase sq = this.getWritableDatabase();
        sq.delete(TABLE_NAME, ID_COLUMN + "=?", new String[]{Integer.toString(id)});
        sq.close();
    }
}
