package com.ackerman.j.gavin.zootrack.Repository.Impl;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.ackerman.j.gavin.zootrack.Config.DbConstants.DbConstants;
import com.ackerman.j.gavin.zootrack.Domain.Animal;
import com.ackerman.j.gavin.zootrack.Domain.Food;
import com.ackerman.j.gavin.zootrack.Repository.AnimalRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by gavin.ackerman on 2016-04-23.
 */
public class AnimalRepositoryImpl extends SQLiteOpenHelper implements AnimalRepository {
    public static final String TABLE_NAME = "animal";
    private SQLiteDatabase db;



    public static final String COLUMN_ID = "id";
    public static final String COLUMN_SPECIES = "species";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_AGE = "age";
    public static final String COLUMN_COUNTRY = "country";
    public static final String COLUMN_FOOD = "food";

    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NAME + " TEXT  NOT NULL , "
            + COLUMN_AGE + " INTEGER NOT NULL ),"
             + COLUMN_COUNTRY + " TEXT  NOT NULL , "
             + COLUMN_SPECIES + " TEXT NOT NULL ),"
             + COLUMN_FOOD + " TEXT NOT NULL );";


    public AnimalRepositoryImpl(Context context) {
        super(context, DbConstants.DATABASE_NAME, null, DbConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public Animal findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_NAME,
                         COLUMN_AGE,
                        COLUMN_COUNTRY,
                        COLUMN_SPECIES,
                         COLUMN_FOOD},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final Animal animal = new Animal.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .species(cursor.getString(cursor.getColumnIndex(COLUMN_SPECIES)))
                    .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                    .age(cursor.getInt(cursor.getColumnIndex(COLUMN_AGE)))
                    .Country(cursor.getString(cursor.getColumnIndex(COLUMN_COUNTRY)))

                    .build();
            // Animal animal = new Animal.Builder(id).name(name).species(species).age(age).Country(Country).food(food)
            return animal;
        } else {
            return null;
        }
    }



    @Override
    public Animal save(Animal entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_COUNTRY, entity.getCountry());
        values.put(COLUMN_NAME, entity.getName());
        values.put(COLUMN_SPECIES, entity.getSpecies());
        values.put(COLUMN_AGE, entity.getAge());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        Animal insertedEntity = new Animal.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public Animal update(Animal entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_COUNTRY, entity.getCountry());
        values.put(COLUMN_NAME, entity.getName());
        values.put(COLUMN_SPECIES, entity.getSpecies());
        values.put(COLUMN_AGE, entity.getAge());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public Animal delete(Animal entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<Animal> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Animal> animal = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final Animal animals = new Animal.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .species(cursor.getString(cursor.getColumnIndex(COLUMN_SPECIES)))
                        .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                        .age(cursor.getInt(cursor.getColumnIndex(COLUMN_AGE)))
                        .Country(cursor.getString(cursor.getColumnIndex(COLUMN_COUNTRY)))
                        .build();
                animal.add(animals);
            } while (cursor.moveToNext());
        }
        return animal;
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
