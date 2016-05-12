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
import com.ackerman.j.gavin.zootrack.Domain.Tickets;
import com.ackerman.j.gavin.zootrack.Repository.FoodRepository;
import com.ackerman.j.gavin.zootrack.Repository.TicketsRepository;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by gavin.ackerman on 2016-04-23.
 */
public class TicketsRepositoryImpl extends SQLiteOpenHelper implements TicketsRepository {
    public static final String TABLE_NAME = "tickets";
    private SQLiteDatabase db;



    public static final String COLUMN_ID = "id";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_DAY = "day";
    public static final String COLUMN_TYPE = "type";


    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_PRICE + " REAL NOT NULL , "
            + COLUMN_DAY + " DATE NOT NULL , "
            + COLUMN_TYPE + " TEXT NOT NULL );";


    public TicketsRepositoryImpl(Context context) {
        super(context, DbConstants.DATABASE_NAME, null, DbConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public Tickets findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_PRICE,
                        COLUMN_DAY,
                        COLUMN_TYPE },
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final Tickets tickets = new Tickets.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .day(AppUtil.getDate(cursor.getString(cursor.getColumnIndex(COLUMN_DAY))))
                    .price(cursor.getFloat(cursor.getColumnIndex(COLUMN_PRICE)))
                    .type(cursor.getString(cursor.getColumnIndex(COLUMN_TYPE)))
                    .build();
            return tickets;
        } else {
            return null;
        }
    }



    @Override
    public Tickets save(Tickets entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_PRICE, entity.getPrice());
        values.put(COLUMN_DAY, entity.getDate().toString());
        values.put(COLUMN_TYPE, entity.getType());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        Tickets insertedEntity = new Tickets.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public Tickets update(Tickets entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_PRICE, entity.getPrice());
        values.put(COLUMN_DAY, entity.getDate().toString());
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
    public Tickets delete(Tickets entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<Tickets> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Tickets> tickets = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final Tickets election = new Tickets.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .day(AppUtil.getDate(cursor.getString(cursor.getColumnIndex(COLUMN_DAY))))
                        .price(cursor.getFloat(cursor.getColumnIndex(COLUMN_PRICE)))
                        .type(cursor.getString(cursor.getColumnIndex(COLUMN_TYPE)))
                        .build();
                tickets.add(election);
            } while (cursor.moveToNext());
        }
        return tickets;
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
