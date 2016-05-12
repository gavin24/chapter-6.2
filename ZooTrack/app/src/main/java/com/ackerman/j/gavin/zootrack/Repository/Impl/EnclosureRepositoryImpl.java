package com.ackerman.j.gavin.zootrack.Repository.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.ackerman.j.gavin.zootrack.Config.DbConstants.DbConstants;
import com.ackerman.j.gavin.zootrack.Domain.Enclosure;
import com.ackerman.j.gavin.zootrack.Repository.EnclosureRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by gavin.ackerman on 2016-04-23.
 */
public class EnclosureRepositoryImpl extends SQLiteOpenHelper implements EnclosureRepository {
    public static final String TABLE_NAME = "enclosure";
    private SQLiteDatabase db;



    public static final String COLUMN_ID = "id";
    public static final String COLUMN_COACH = "coach";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_TYPE = "type";

    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_COACH + " TEXT  NOT NULL , "
            + COLUMN_TYPE + " TEXT  NOT NULL , "
            + COLUMN_NAME + " TEXT NOT NULL );";


    public EnclosureRepositoryImpl(Context context) {
        super(context, DbConstants.DATABASE_NAME, null, DbConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public Enclosure findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_TYPE,
                        COLUMN_COACH,
                        COLUMN_NAME},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final Enclosure enclosure = new Enclosure.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .coach(cursor.getString(cursor.getColumnIndex(COLUMN_COACH)))
                    .type(cursor.getString(cursor.getColumnIndex(COLUMN_TYPE)))
                    .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                    .build();
            return enclosure;
        } else {
            return null;
        }
    }



    @Override
    public Enclosure save(Enclosure entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_TYPE, entity.getType());
        values.put(COLUMN_NAME, entity.getname());
        values.put(COLUMN_COACH, entity.getCoach());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        Enclosure insertedEntity = new Enclosure.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public Enclosure update(Enclosure entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_TYPE, entity.getType());
        values.put(COLUMN_NAME, entity.getname());
        values.put(COLUMN_COACH, entity.getCoach());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public Enclosure delete(Enclosure entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<Enclosure> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Enclosure> enclosure = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final Enclosure election = new Enclosure.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .coach(cursor.getString(cursor.getColumnIndex(COLUMN_COACH)))
                        .type(cursor.getString(cursor.getColumnIndex(COLUMN_TYPE)))
                        .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                        .build();
                enclosure.add(election);
            } while (cursor.moveToNext());
        }
        return enclosure;
    }

    @Override
    public int deleteAll() {
        open();
        int rowsDeleted = db.delete(TABLE_NAME,null,null);
        close();
        return rowsDeleted;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(this.getClass().getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }
}
