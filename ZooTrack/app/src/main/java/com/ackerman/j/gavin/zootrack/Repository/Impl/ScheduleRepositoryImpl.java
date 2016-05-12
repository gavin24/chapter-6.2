package com.ackerman.j.gavin.zootrack.Repository.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.ackerman.j.gavin.zootrack.Config.DbConstants.DbConstants;
import com.ackerman.j.gavin.zootrack.Domain.Schedule;
import com.ackerman.j.gavin.zootrack.Domain.Show;
import com.ackerman.j.gavin.zootrack.Repository.FoodRepository;
import com.ackerman.j.gavin.zootrack.Repository.ScheduleRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by gavin.ackerman on 2016-04-23.
 */
public class ScheduleRepositoryImpl extends SQLiteOpenHelper implements ScheduleRepository {
    public static final String TABLE_NAME = "schedule";
    private SQLiteDatabase db;



    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_DURATION = "duration";
    public static final String COLUMN_COACH = "coach";


    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_TYPE + " TEXT  NOT NULL , "
            + COLUMN_DURATION + " TEXT  NOT NULL , "
            + COLUMN_COACH  + " TEXT NOT NULL );";


    public ScheduleRepositoryImpl(Context context) {
        super(context, DbConstants.DATABASE_NAME, null, DbConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public Schedule findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_TYPE,
                        COLUMN_DURATION,
                        COLUMN_COACH},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final Schedule schedule = new Schedule.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .coach(cursor.getString(cursor.getColumnIndex(COLUMN_COACH)))
                    .duration(cursor.getString(cursor.getColumnIndex(COLUMN_DURATION)))
                    .type(cursor.getString(cursor.getColumnIndex(COLUMN_TYPE)))
                    .build();
            return schedule;
        } else {
            return null;
        }
    }



    @Override
    public Schedule save(Schedule entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_COACH, entity.getCoach());
        values.put(COLUMN_DURATION, entity.getDuration());
        values.put(COLUMN_TYPE, entity.getType());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        Schedule insertedEntity = new Schedule.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public Schedule update(Schedule entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_COACH, entity.getCoach());
        values.put(COLUMN_DURATION, entity.getDuration());
        values.put(COLUMN_TYPE, entity.getType());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public Schedule delete(Schedule entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<Schedule> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Schedule> schedule = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final Schedule election = new Schedule.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .coach(cursor.getString(cursor.getColumnIndex(COLUMN_COACH)))
                        .duration(cursor.getString(cursor.getColumnIndex(COLUMN_DURATION)))
                        .type(cursor.getString(cursor.getColumnIndex(COLUMN_TYPE)))
                        .build();
                schedule.add(election);
            } while (cursor.moveToNext());
        }
        return schedule;
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
