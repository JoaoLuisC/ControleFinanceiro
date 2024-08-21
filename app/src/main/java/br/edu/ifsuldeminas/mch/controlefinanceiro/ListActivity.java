package br.edu.ifsuldeminas.mch.controlefinanceiro;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import br.edu.ifsuldeminas.mch.controlefinanceiro.model.Bill;
import br.edu.ifsuldeminas.mch.controlefinanceiro.model.db.BillDAO;

public class ListActivity extends AppCompatActivity {

    ListView billsList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bill_list_page);

        billsList = findViewById(R.id.bill_list);
        registerForContextMenu(billsList);

        billsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Bill bill = (Bill) billsList.getItemAtPosition(position);

                Intent intent = new Intent(ListActivity.this, DetailActivity.class);
                intent.putExtra("contaDetalhada", bill);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateBills();
    }

    private void updateBills() {
        BillDAO dao = new BillDAO(this);
        List<Bill> billList = dao.loadBills();

        ArrayAdapter<Bill> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, billList);
        billsList.setAdapter(arrayAdapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem itemDelete = menu.add("Deletar Conta");
        itemDelete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(@NonNull MenuItem item) {
                AdapterView.AdapterContextMenuInfo itemClicado = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Bill bill = (Bill) billsList.getItemAtPosition(itemClicado.position);

                BillDAO dao = new BillDAO(ListActivity.this);

                dao.delete(bill);
                updateBills();

                Toast toast = Toast.makeText(ListActivity.this, "Conta deletada com sucesso!", Toast.LENGTH_SHORT);
                toast.show();

                return true;
            }
        });

        MenuItem itemDetail = menu.add("Editar Conta");
        itemDetail.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(@NonNull MenuItem item) {
                AdapterView.AdapterContextMenuInfo itemClicado = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Bill bill = (Bill) billsList.getItemAtPosition(itemClicado.position);

                Intent intent = new Intent(ListActivity.this, AddActivity.class);
                intent.putExtra("contaEdicao", bill);
                startActivity(intent);

                return true;
            }
        });
    }
}
