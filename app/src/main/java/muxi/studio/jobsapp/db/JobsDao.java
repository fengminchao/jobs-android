package muxi.studio.jobsapp.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import muxi.studio.jobsapp.data.User;

/**
 * Created by ybao on 16/6/29.
 */
public class JobsDao {

    private SQLiteDatabase db;

    public JobsDao() {
        db = DBUtils.getInstance();
    }

    public List<User> loadUsers(String name){
        Cursor cursor = db.rawQuery("select * from " + DBUtils.TABLE_ACCOUT +
                " where " + DBUtils.KEY_NAME + " = ? ",new String[]{
                name
        });
        List<User> users = new ArrayList<>();
        if (cursor.getCount() > 0){
            while (cursor.moveToNext()){
                User user = new User();
                user.setName(cursor.getString(cursor.getColumnIndex(DBUtils.KEY_NAME)));
                user.setPwd(cursor.getString(cursor.getColumnIndex(DBUtils.KEY_PWD)));
                users.add(user);
            }
        }
        return users;
    }

    public void insertUsers(User user){
        db.execSQL("insert into " + DBUtils.TABLE_ACCOUT +" values (null,?,?)",
                new String[]{
                        user.getName(),
                        user.getPwd()
                });
    }
}
