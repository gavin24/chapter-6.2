package com.ackerman.j.gavin.zootrack.Repository.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.ackerman.j.gavin.zootrack.Config.DbConstants.DbConstants;
import com.ackerman.j.gavin.zootrack.Domain.Food;
import com.ackerman.j.gavin.zootrack.Repository.FoodRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by gavin.ackerman on 2016-04-23.
 */
public class FoodRepositoryImpl extends SQLiteOpenHelper implements FoodRepository {
    public static final String TABLE_NAME = "food";
    private SQLiteDatabase db;



    public static final String COLUMN_ID = "id";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_STOCK = "stock";

    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_PRICE + " REAL  NOT NULL , "
            + COLUMN_TYPE + " TEXT  NOT NULL , "
            + COLUMN_STOCK + " INTEGER  NOT NULL , "
            + COLUMN_NAME + " TEXT NOT NULL );";


    public FoodRepositoryImpl(Context context) {
        super(context, DbConstants.DATABASE_NAME, null, DbConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public Food findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_PRICE,
                        COLUMN_TYPE,
                        COLUMN_STOCK,
                        COLUMN_NAME},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final Food food = new Food.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .price(cursor.getFloat(cursor.getColumnIndex(COLUMN_PRICE)))
                    .type(cursor.getString(cursor.getColumnIndex(COLUMN_TYPE)))
                    .stock(cursor.getInt(cursor.getColumnIndex(COLUMN_STOCK)))
                    .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                    .build();
            return food;
        } else {
            return null;
        }
    }



    @Override
    public Food save(Food entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_PRICE, entity.getprice());
        values.put(COLUMN_TYPE, entity.getType());
        values.put(COLUMN_STOCK, entity.getStock());
        values.put(COLUMN_NAME, entity.getname());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        Food insertedEntity = new Food.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public Food update(Food entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_PRICE, entity.getprice());
        values.put(COLUMN_TYPE, entity.getType());
        values.put(COLUMN_STOCK, entity.getStock());
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
    public Food delete(Food entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<Food> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Food> food = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final Food election = new Food.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .price(cursor.getFloat(cursor.getColumnIndex(COLUMN_PRICE)))
                        .type(cursor.getString(cursor.getColumnIndex(COLUMN_TYPE)))
                        .stock(cursor.getInt(cursor.getColumnIndex(COLUMN_STOCK)))
                        .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                        .build();
                food.add(election);
            } while (cursor.moveToNext());
        }
        return food;
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
