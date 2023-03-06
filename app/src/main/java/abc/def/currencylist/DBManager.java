package abc.def.currencylist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DBManager
{
    DBHelper dbHelper;
    SQLiteDatabase database;
    Context context;

    public DBManager(Context c)
    {
        context = c;
    }
    public DBManager Open()
    {
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }
    public void Close()
    {
        dbHelper.close();
    }
    public void Insert(String country, String currency)
    {
        ContentValues values = new ContentValues();
        values.put(dbHelper._NAME, country);
        values.put(dbHelper._CURRENCY, currency);

        database.insert(dbHelper.TABLE_NAME, null,values);

        Log.d("Country", country);
        Log.d("Currency", currency);

    }

    public Cursor fetch()
    {
        String [] columns = new String[] {
                DBHelper._ID,
                DBHelper._NAME,
                DBHelper._CURRENCY
        };
        Cursor query = database.query(DBHelper.TABLE_NAME, columns, null, null
                , null, null, null);

        if(query != null) {

            query.moveToFirst();
        }
        return query;
    }

    public void Update(Long id, String country, String currency) {

        ContentValues contentValues = new ContentValues();

        contentValues.put(DBHelper._NAME,country);
        contentValues.put(DBHelper._CURRENCY,currency);

        database.update(DBHelper.TABLE_NAME,contentValues,DBHelper._ID +" = " + id,null);

    }

    public void Delete(Long id) {

        database.delete(DBHelper.TABLE_NAME,DBHelper._ID +" =?", new String[] { id.toString()});

    }
}
