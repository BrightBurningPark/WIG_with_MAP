package team4.com.wig_aware;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.util.ArrayList;

public class ExDBHelper extends SQLiteOpenHelper {

    private static String DB_NAME = "locations.db";
    private SQLiteDatabase db;
    private final Context context;
    private String DB_PATH;

    public ExDBHelper(Context context) {
        super(context, DB_NAME, null, 6);
        this.context = context;
        DB_PATH = Environment.getExternalStorageDirectory() + "/AWARE/";
    }

    private boolean checkDataBase() {
        File dbFile = new File(DB_PATH + DB_NAME);
        return dbFile.exists();
    }

    public String[] getLocation() {
        String myPath = DB_PATH + DB_NAME;
        String[] location = new String[2];
        db = SQLiteDatabase.openDatabase(myPath, null,
                SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = db.rawQuery("SELECT double_latitude, double_longitude FROM locations WHERE _id = (SELECT MAX(_id) FROM locations);", null);
        // Note: Master is the one table in External db. Here we trying to access the records of table from external db.

        cursor.moveToFirst();
        location[0] = cursor.getString(cursor.getColumnIndex("double_latitude"));
        location[1] = cursor.getString(cursor.getColumnIndex("double_longitude"));
        cursor.close();

        return location;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
    }
}