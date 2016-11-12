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
 * DAO for table "JOBS".
*/
public class JobsDao extends AbstractDao<Jobs, Void> {

    public static final String TABLENAME = "JOBS";

    /**
     * Properties of entity Jobs.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Integer.class, "id", false, "ID");
        public final static Property LogoUrl = new Property(1, String.class, "logoUrl", false, "LOGO_URL");
        public final static Property Title = new Property(2, String.class, "title", false, "TITLE");
        public final static Property Place = new Property(3, String.class, "place", false, "PLACE");
        public final static Property Time = new Property(4, String.class, "time", false, "TIME");
        public final static Property Clicks = new Property(5, Integer.class, "clicks", false, "CLICKS");
    }


    public JobsDao(DaoConfig config) {
        super(config);
    }
    
    public JobsDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"JOBS\" (" + //
                "\"ID\" INTEGER," + // 0: id
                "\"LOGO_URL\" TEXT," + // 1: logoUrl
                "\"TITLE\" TEXT," + // 2: title
                "\"PLACE\" TEXT," + // 3: place
                "\"TIME\" TEXT," + // 4: time
                "\"CLICKS\" INTEGER);"); // 5: clicks
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"JOBS\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Jobs entity) {
        stmt.clearBindings();
 
        Integer id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String logoUrl = entity.getLogoUrl();
        if (logoUrl != null) {
            stmt.bindString(2, logoUrl);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(3, title);
        }
 
        String place = entity.getPlace();
        if (place != null) {
            stmt.bindString(4, place);
        }
 
        String time = entity.getTime();
        if (time != null) {
            stmt.bindString(5, time);
        }
 
        Integer clicks = entity.getClicks();
        if (clicks != null) {
            stmt.bindLong(6, clicks);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Jobs entity) {
        stmt.clearBindings();
 
        Integer id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String logoUrl = entity.getLogoUrl();
        if (logoUrl != null) {
            stmt.bindString(2, logoUrl);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(3, title);
        }
 
        String place = entity.getPlace();
        if (place != null) {
            stmt.bindString(4, place);
        }
 
        String time = entity.getTime();
        if (time != null) {
            stmt.bindString(5, time);
        }
 
        Integer clicks = entity.getClicks();
        if (clicks != null) {
            stmt.bindLong(6, clicks);
        }
    }

    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    @Override
    public Jobs readEntity(Cursor cursor, int offset) {
        Jobs entity = new Jobs( //
            cursor.isNull(offset + 0) ? null : cursor.getInt(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // logoUrl
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // title
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // place
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // time
            cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5) // clicks
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Jobs entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getInt(offset + 0));
        entity.setLogoUrl(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setTitle(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setPlace(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setTime(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setClicks(cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5));
     }
    
    @Override
    protected final Void updateKeyAfterInsert(Jobs entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    @Override
    public Void getKey(Jobs entity) {
        return null;
    }

    @Override
    public boolean hasKey(Jobs entity) {
        // TODO
        return false;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
