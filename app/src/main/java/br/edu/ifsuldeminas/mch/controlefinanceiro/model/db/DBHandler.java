package br.edu.ifsuldeminas.mch.controlefinanceiro.model.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "financial.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_TASKS_CREATE_SQL =
            " CREATE TABLE " +
                    " IF NOT EXISTS bills ( " +
                    " id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    " description varchar(255), " +
                    " category varchar(25), " +
                    " value decimal(10, 2))";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLE_TASKS_CREATE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // não vamos atualizar
    }
}