package com.muxistudio.jobs.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "USER_INFO".
*/
public class UserInfoDao extends AbstractDao<UserInfo, Long> {

    public static final String TABLENAME = "USER_INFO";

    /**
     * Properties of entity UserInfo.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Mail = new Property(1, String.class, "mail", false, "MAIL");
        public final static Property Avator = new Property(2, String.class, "avator", false, "AVATOR");
        public final static Property Name = new Property(3, String.class, "name", false, "NAME");
        public final static Property Gender = new Property(4, String.class, "gender", false, "GENDER");
        public final static Property Live = new Property(5, String.class, "live", false, "LIVE");
        public final static Property Birth = new Property(6, String.class, "birth", false, "BIRTH");
        public final static Property Politic = new Property(7, String.class, "politic", false, "POLITIC");
        public final static Property College = new Property(8, String.class, "college", false, "COLLEGE");
        public final static Property Mobile = new Property(9, String.class, "mobile", false, "MOBILE");
    }


    public UserInfoDao(DaoConfig config) {
        super(config);
    }
    
    public UserInfoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"USER_INFO\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"MAIL\" TEXT," + // 1: mail
                "\"AVATOR\" TEXT," + // 2: avator
                "\"NAME\" TEXT," + // 3: name
                "\"GENDER\" TEXT," + // 4: gender
                "\"LIVE\" TEXT," + // 5: live
                "\"BIRTH\" TEXT," + // 6: birth
                "\"POLITIC\" TEXT," + // 7: politic
                "\"COLLEGE\" TEXT," + // 8: college
                "\"MOBILE\" TEXT);"); // 9: mobile
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"USER_INFO\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, UserInfo entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String mail = entity.getMail();
        if (mail != null) {
            stmt.bindString(2, mail);
        }
 
        String avator = entity.getAvator();
        if (avator != null) {
            stmt.bindString(3, avator);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(4, name);
        }
 
        String gender = entity.getGender();
        if (gender != null) {
            stmt.bindString(5, gender);
        }
 
        String live = entity.getLive();
        if (live != null) {
            stmt.bindString(6, live);
        }
 
        String birth = entity.getBirth();
        if (birth != null) {
            stmt.bindString(7, birth);
        }
 
        String politic = entity.getPolitic();
        if (politic != null) {
            stmt.bindString(8, politic);
        }
 
        String college = entity.getCollege();
        if (college != null) {
            stmt.bindString(9, college);
        }
 
        String mobile = entity.getMobile();
        if (mobile != null) {
            stmt.bindString(10, mobile);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, UserInfo entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String mail = entity.getMail();
        if (mail != null) {
            stmt.bindString(2, mail);
        }
 
        String avator = entity.getAvator();
        if (avator != null) {
            stmt.bindString(3, avator);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(4, name);
        }
 
        String gender = entity.getGender();
        if (gender != null) {
            stmt.bindString(5, gender);
        }
 
        String live = entity.getLive();
        if (live != null) {
            stmt.bindString(6, live);
        }
 
        String birth = entity.getBirth();
        if (birth != null) {
            stmt.bindString(7, birth);
        }
 
        String politic = entity.getPolitic();
        if (politic != null) {
            stmt.bindString(8, politic);
        }
 
        String college = entity.getCollege();
        if (college != null) {
            stmt.bindString(9, college);
        }
 
        String mobile = entity.getMobile();
        if (mobile != null) {
            stmt.bindString(10, mobile);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public UserInfo readEntity(Cursor cursor, int offset) {
        UserInfo entity = new UserInfo( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // mail
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // avator
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // name
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // gender
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // live
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // birth
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // politic
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // college
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9) // mobile
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, UserInfo entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setMail(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setAvator(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setName(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setGender(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setLive(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setBirth(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setPolitic(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setCollege(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setMobile(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(UserInfo entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(UserInfo entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(UserInfo entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}