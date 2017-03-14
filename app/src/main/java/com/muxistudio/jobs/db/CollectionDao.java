package com.muxistudio.jobs.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * DAO for table "COLLECTION".
 */
public class CollectionDao extends AbstractDao<Collection, Void> {

    public static final String TABLENAME = "COLLECTION";

    public CollectionDao(DaoConfig config) {
        super(config);
    }


    public CollectionDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists ? "IF NOT EXISTS " : "";
        db.execSQL("CREATE TABLE " + constraint + "\"COLLECTION\" (" + //
                "\"MAIL\" TEXT," + // 0: mail
                "\"ID\" INTEGER," + // 1: id
                "\"TITLE\" TEXT," + // 2: title
                "\"PLACE\" TEXT," + // 3: place
                "\"SCHOOL\" TEXT," + // 4: school
                "\"DATE\" TEXT," + // 5: date
                "\"TIME\" TEXT," + // 6: time
                "\"TYPE\" TEXT);"); // 7: type
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"COLLECTION\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Collection entity) {
        stmt.clearBindings();

        String mail = entity.getMail();
        if (mail != null) {
            stmt.bindString(1, mail);
        }

        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(2, id);
        }

        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(3, title);
        }

        String place = entity.getPlace();
        if (place != null) {
            stmt.bindString(4, place);
        }

        String school = entity.getSchool();
        if (school != null) {
            stmt.bindString(5, school);
        }

        String date = entity.getDate();
        if (date != null) {
            stmt.bindString(6, date);
        }

        String time = entity.getTime();
        if (time != null) {
            stmt.bindString(7, time);
        }

        String type = entity.getType();
        if (type != null) {
            stmt.bindString(8, type);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Collection entity) {
        stmt.clearBindings();

        String mail = entity.getMail();
        if (mail != null) {
            stmt.bindString(1, mail);
        }

        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(2, id);
        }

        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(3, title);
        }

        String place = entity.getPlace();
        if (place != null) {
            stmt.bindString(4, place);
        }

        String school = entity.getSchool();
        if (school != null) {
            stmt.bindString(5, school);
        }

        String date = entity.getDate();
        if (date != null) {
            stmt.bindString(6, date);
        }

        String time = entity.getTime();
        if (time != null) {
            stmt.bindString(7, time);
        }

        String type = entity.getType();
        if (type != null) {
            stmt.bindString(8, type);
        }
    }

    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }

    @Override
    public Collection readEntity(Cursor cursor, int offset) {
        Collection entity = new Collection( //
                cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // mail
                cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1), // id
                cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // title
                cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // place
                cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // school
                cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // date
                cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // time
                cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7) // type
        );
        return entity;
    }

    @Override
    public void readEntity(Cursor cursor, Collection entity, int offset) {
        entity.setMail(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setId(cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1));
        entity.setTitle(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setPlace(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setSchool(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setDate(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setTime(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setType(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
    }

    @Override
    protected final Void updateKeyAfterInsert(Collection entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }

    @Override
    public Void getKey(Collection entity) {
        return null;
    }

    @Override
    public boolean hasKey(Collection entity) {
        return false;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }

    /**
     * Properties of entity Collection.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Mail = new Property(0, String.class, "mail", false, "MAIL");
        public final static Property Id = new Property(1, Integer.class, "id", false, "ID");
        public final static Property Title = new Property(2, String.class, "title", false, "TITLE");
        public final static Property Place = new Property(3, String.class, "place", false, "PLACE");
        public final static Property School = new Property(4, String.class, "school", false,
                "SCHOOL");
        public final static Property Date = new Property(5, String.class, "date", false, "DATE");
        public final static Property Time = new Property(6, String.class, "time", false, "TIME");
        public final static Property Type = new Property(7, String.class, "type", false, "TYPE");
    }

}
