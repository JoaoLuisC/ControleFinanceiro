package br.edu.ifsuldeminas.mch.controlefinanceiro.model.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsuldeminas.mch.controlefinanceiro.model.Bill;

public class BillDAO extends DAO {

    public BillDAO(Context context) {
        super(context);
    }

    public boolean save(Bill bill) {
        SQLiteDatabase dataBase = openToWrite();

        ContentValues contentValues = new ContentValues();
        contentValues.put("description", bill.getDescription());
        contentValues.put("category", bill.getCategory());
        contentValues.put("value", bill.getValue());

        dataBase.insert("bills", null, contentValues);

        dataBase.close();

        return true;
    }

    public List<Bill> loadBills() {
        SQLiteDatabase dataBase = openToRead();
        List<Bill> bills = new ArrayList<Bill>();
        String sql = "SELECT * FROM bills;";
        Cursor cursor = dataBase.rawQuery(sql, null);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
            String description = cursor.getString(
                    cursor.getColumnIndexOrThrow("description"));
            String category = cursor.getString(cursor.getColumnIndexOrThrow("category"));
            Double value = cursor.getDouble(cursor.getColumnIndexOrThrow("value"));
            Bill bill = new Bill(id, description, category, value);
            bills.add(bill);
        }
        cursor.close();
        dataBase.close();
        return bills;
    }

    public void delete(Bill bill) {
        SQLiteDatabase dataBase = openToWrite();

        String[] params = {bill.getId().toString()};
        dataBase.delete("bills", "id = ?", params);

        dataBase.close();
    }

    public void update(Bill bill) {
        SQLiteDatabase dataBase = openToWrite();

        ContentValues contentValues = new ContentValues();
        contentValues.put("description", bill.getDescription());
        contentValues.put("category", bill.getCategory());
        contentValues.put("value", bill.getValue());

        String[] params = {bill.getId().toString()};
        dataBase.update("bills", contentValues, "id = ?", params);

        dataBase.close();
    }
}
