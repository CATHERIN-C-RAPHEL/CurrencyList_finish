package abc.def.currencylist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    //Definition of Contract
    private static final String DB_Name = "currencyList.db";
    public static final String TABLE_NAME = "COUNTRIES";
    //coloumn
    public static final String _ID = "_id";
    public static final String _NAME = "name";
    public static final String _CURRENCY = "currency";
    //version
    private static final int _VERSION = 1;
    //table querry
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "( "+ _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + _NAME + "" +
            " TEXT NOT NULL ,"+_CURRENCY + " TEXT);";



    public DBHelper(@Nullable Context context) {
        super(context, DB_Name, null, _VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }
}
