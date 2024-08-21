package br.edu.ifsuldeminas.mch.controlefinanceiro;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

import br.edu.ifsuldeminas.mch.controlefinanceiro.model.Bill;
import br.edu.ifsuldeminas.mch.controlefinanceiro.model.db.BillDAO;

public class AddActivity extends AppCompatActivity {

    private Bill bill;

    Button btnAdd;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_bill_page);

        Intent chooserIntent = getIntent();
        bill = (Bill) chooserIntent.getSerializableExtra("contaEdicao");

        EditText descTextInput = findViewById(R.id.editTextDesc);
        EditText categoryTextInput = findViewById(R.id.editTextCategory);
        EditText valueTextInput = findViewById(R.id.editTextValue);

        if (bill != null) {
            descTextInput.setText(bill.getDescription());
            categoryTextInput.setText(bill.getCategory());

            double value = bill.getValue();
            valueTextInput.setText(String.format(Locale.getDefault(), "%.2f", value));
        }

        btnAdd = findViewById(R.id.btnAddBill);
        btnAdd.setOnClickListener(view -> {

            String description = descTextInput.getText().toString();
            String category = categoryTextInput.getText().toString();
            String value = valueTextInput.getText().toString();

            if (description.isEmpty() || category.isEmpty() || value.isEmpty()) {
                Toast toast = Toast.makeText(AddActivity.this, "Preencha todos os campos!", Toast.LENGTH_SHORT);
                toast.show();
            } else {
                BillDAO dao = new BillDAO(this);
                if (this.bill == null) {
                    bill = new Bill();
                    bill.setDescription(description);
                    bill.setCategory(category);
                    bill.setValue(Double.parseDouble(value));

                    dao.save(bill);
                    Toast toast = Toast.makeText(this, "Conta salva com sucesso!", Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    bill.setDescription(description);
                    bill.setCategory(category);
                    bill.setValue(Double.parseDouble(value));

                    dao.update(bill);
                    Toast toast = Toast.makeText(this, "Conta atualizada com sucesso!", Toast.LENGTH_LONG);
                    toast.show();
                }
                finish();
            }
        });
    }


}
