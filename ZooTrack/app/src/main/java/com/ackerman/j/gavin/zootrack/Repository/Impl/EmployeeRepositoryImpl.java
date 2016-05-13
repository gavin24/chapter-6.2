package com.ackerman.j.gavin.zootrack.Repository.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.ackerman.j.gavin.zootrack.Config.DbConstants.DbConstants;
import com.ackerman.j.gavin.zootrack.Domain.Employee;
import com.ackerman.j.gavin.zootrack.Repository.EmployeeRepository;
import com.ackerman.j.gavin.zootrack.Repository.EmployeeRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by gavin.ackerman on 2016-04-23.
 */
public class EmployeeRepositoryImpl extends SQLiteOpenHelper implements EmployeeRepository {
    public static final String TABLE_NAME = "employee";
    private SQLiteDatabase db;



    public static final String COLUMN_ID = "id";
    public static final String COLUMN_SURNAME = "surname";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_AGE = "age";
    public static final String COLUMN_COUNTRY = "country";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_EMAIL = "email";
    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_SURNAME + " TEXT UNIQUE NOT NULL , "
            + COLUMN_AGE + " INTEGER  NOT NULL, "
            + COLUMN_COUNTRY + " TEXT  NOT NULL , "
            + COLUMN_PASSWORD + " TEXT UNIQUE  NOT NULL, "
            + COLUMN_EMAIL + " TEXT UNIQUE  NOT NULL , "
            + COLUMN_NAME + " TEXT NOT NULL );";


    public EmployeeRepositoryImpl(Context context) {
        super(context, DbConstants.DATABASE_NAME, null, DbConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public Employee findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_SURNAME,
                        COLUMN_AGE,
                        COLUMN_COUNTRY,
                        COLUMN_PASSWORD,
                        COLUMN_EMAIL,
                        COLUMN_NAME},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final Employee employee = new Employee.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .surname(cursor.getString(cursor.getColumnIndex(COLUMN_SURNAME)))
                    .age(cursor.getInt(cursor.getColumnIndex(COLUMN_AGE)))
                    .Country(cursor.getString(cursor.getColumnIndex(COLUMN_COUNTRY)))
                    .password(cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD)))
                    .email(cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)))
                    .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))

                    .build();
            return employee;
        } else {
            return null;
        }
    }



    @Override
    public Employee save(Employee entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_SURNAME, entity.getsurname());

        values.put(COLUMN_AGE, entity.getAge());
        values.put(COLUMN_COUNTRY, entity.getCountry());
        values.put(COLUMN_PASSWORD, entity.getPassword());
        values.put(COLUMN_EMAIL, entity.getEmail());
        values.put(COLUMN_NAME, entity.getName());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        Employee insertedEntity = new Employee.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public Employee update(Employee entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_SURNAME, entity.getsurname());
        values.put(COLUMN_AGE, entity.getAge());
        values.put(COLUMN_COUNTRY, entity.getCountry());
        values.put(COLUMN_PASSWORD, entity.getPassword());
        values.put(COLUMN_EMAIL, entity.getEmail());
        values.put(COLUMN_NAME, entity.getName());

        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public Employee delete(Employee entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<Employee> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Employee> employee = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final Employee election = new Employee.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .surname(cursor.getString(cursor.getColumnIndex(COLUMN_SURNAME)))
                        .age(cursor.getInt(cursor.getColumnIndex(COLUMN_AGE)))
                        .Country(cursor.getString(cursor.getColumnIndex(COLUMN_COUNTRY)))
                        .password(cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD)))
                        .email(cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)))
                        .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))

                        .build();
                employee.add(election);
            } while (cursor.moveToNext());
        }
        return employee;
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
