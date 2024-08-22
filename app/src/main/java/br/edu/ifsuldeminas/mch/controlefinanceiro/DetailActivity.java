package br.edu.ifsuldeminas.mch.controlefinanceiro;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import br.edu.ifsuldeminas.mch.controlefinanceiro.model.Bill;

public class DetailActivity extends AppCompatActivity {

    private TextView detailTxtName, detailTxtValue, detailTxtDateBill, detailTxtDescription;
    private Button btnShare;
    private Bill bill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_bill_page);

        // Referências das TextViews
        detailTxtName = findViewById(R.id.detailTxtName);
        detailTxtValue = findViewById(R.id.detailTxtValue);
        detailTxtDateBill = findViewById(R.id.detailTxtDateBill);
        detailTxtDescription = findViewById(R.id.detailTxtDescription);

        // Recebe a Bill passada pela ListActivity
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("contaDetalhada")) {
            bill = (Bill) intent.getSerializableExtra("contaDetalhada");
            populateDetails();
        }

        // Implementação do botão de compartilhar
        btnShare = findViewById(R.id.detailBtnShare);
        btnShare.setOnClickListener(view -> shareBillDetails());
    }

    // Método para popular os TextViews com os detalhes da conta
    private void populateDetails() {
        if (bill != null) {
            detailTxtName.setText(bill.getCategory());
            detailTxtValue.setText("R$ " + String.format("%.2f", bill.getValue()));
            detailTxtDateBill.setText("00/00/0000"); // placeholder para a data
            detailTxtDescription.setText("Lorem ipsum dolor sit amet..."); // placeholder para a descrição
        }
    }

    // Método para compartilhar os detalhes da conta
    private void shareBillDetails() {
        if (bill != null) {
            String message = String.format("Conta: %s\nValor: R$ %.2f\nCategoria: %s\nDescrição: %s",
                    bill.getCategory(), bill.getValue(), bill.getCategory(), detailTxtDescription.getText().toString());

            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, message);
            sendIntent.setType("text/plain");

            Intent shareIntent = Intent.createChooser(sendIntent, null);
            startActivity(shareIntent);
        }
    }
}
