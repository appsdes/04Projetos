package com.example.a04projetos;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private EditText etDia, etMes, etAno;
    private Button btnCalcular;
    private TextView tvIdade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etDia = findViewById(R.id.et_dia);
        etMes = findViewById(R.id.et_mes);
        etAno = findViewById(R.id.et_ano);
        btnCalcular = findViewById(R.id.btn_calcular);
        tvIdade = findViewById(R.id.tv_idade);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularIdade();
            }
        });
    }

    private void calcularIdade() {
        String diaStr = etDia.getText().toString();
        String mesStr = etMes.getText().toString();
        String anoStr = etAno.getText().toString();

        if (!diaStr.isEmpty() && !mesStr.isEmpty() && !anoStr.isEmpty()) {
            int dia = Integer.parseInt(diaStr);
            int mes = Integer.parseInt(mesStr);
            int ano = Integer.parseInt(anoStr);

            Calendar hoje = Calendar.getInstance();
            int diaAtual = hoje.get(Calendar.DAY_OF_MONTH);
            int mesAtual = hoje.get(Calendar.MONTH) + 1; // Janeiro é 0
            int anoAtual = hoje.get(Calendar.YEAR);

            int anos = anoAtual - ano;
            int meses = mesAtual - mes;
            int dias = diaAtual - dia;


            if (meses < 0 || (meses == 0 && dias < 0)) {
                anos--;
                meses += 12;
                if (dias < 0) {
                    meses--;
                    dias += hoje.getActualMaximum(Calendar.DAY_OF_MONTH);
                }
            }


            tvIdade.setText("Idade: " + anos + " anos, " + meses + " meses e " + dias + " dias.");
        } else {
            Toast.makeText(this, "Por favor, insira o dia, mês e ano de nascimento.", Toast.LENGTH_SHORT).show();
        }
    }
}
