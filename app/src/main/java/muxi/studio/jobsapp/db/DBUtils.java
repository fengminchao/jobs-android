package muxi.studio.jobsapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import muxi.studio.jobsapp.App;

/**
 * Created by ybao on 16/6/28.
 */
public class DBUtils extends SQLiteOpenHelper{

    private static DBUtils instance;

    private static final String DB_NAME = "jabs_db";
    private static final int DB_VERSION = 1;
    public static final String TABLE_ACCOUT = "accout";

    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_PWD = "pwd";


    public DBUtils(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public static SQLiteDatabase getInstance(){
        if (instance == null){
            instance = new DBUtils(App.getContext(),DB_NAME,null,DB_VERSION);
        }
        return instance.getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableAccount = "create table if not exists " + TABLE_ACCOUT +
                "( " + KEY_ID + " integer primary key autoincrement , " +
                KEY_NAME + " text , " +
                KEY_PWD + " text); ";
        db.execSQL(createTableAccount);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTableAccount = "drop table if exists " + TABLE_ACCOUT;
        db.execSQL(dropTableAccount);
    }
}
