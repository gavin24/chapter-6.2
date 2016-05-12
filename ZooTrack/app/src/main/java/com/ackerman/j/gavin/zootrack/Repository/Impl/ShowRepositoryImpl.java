package com.ackerman.j.gavin.zootrack.Repository.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.ackerman.j.gavin.zootrack.Config.DbConstants.DbConstants;
import com.ackerman.j.gavin.zootrack.Config.Util.AppUtil;
import com.ackerman.j.gavin.zootrack.Domain.Show;
import com.ackerman.j.gavin.zootrack.Repository.FoodRepository;
import com.ackerman.j.gavin.zootrack.Repository.ShowRepository;

import java.sql.Date;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by gavin.ackerman on 2016-04-23.
 */
public class ShowRepositoryImpl extends SQLiteOpenHelper implements ShowRepository {
    public static final String TABLE_NAME = "show";
    private SQLiteDatabase db;



    public static final String COLUMN_ID = "id";
    public static final String COLUMN_SHOWDAY= "showDay";
    public static final String COLUMN_NAME = "name";



    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_SHOWDAY + " DATE NOT NULL , "

            + COLUMN_NAME + " TEXT NOT NULL );";


    public ShowRepositoryImpl(Context context) {
        super(context, DbConstants.DATABASE_NAME, null, DbConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public Show findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_SHOWDAY,

                        COLUMN_NAME},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final Show show = new Show.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .day(AppUtil.getDate(cursor.getString(cursor.getColumnIndex(COLUMN_SHOWDAY))))

                    .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                            .build();
            return show;
        } else {
            return null;
        }
    }



    @Override
    public Show save(Show entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_SHOWDAY, entity.getDay().toString());
        values.put(COLUMN_NAME, entity.getname());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        Show insertedEntity = new Show.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public Show update(Show entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_SHOWDAY, entity.getDay().toString());
        values.put(COLUMN_NAME, entity.getname());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public Show delete(Show entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<Show> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Show> show = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final Show election = new Show.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .day(AppUtil.getDate(cursor.getString(cursor.getColumnIndex(COLUMN_SHOWDAY))))
                        .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                        .build();
                show.add(election);
            } while (cursor.moveToNext());
        }
        return show;
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
