package com.example.preferencias;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class VerDatos extends AppCompatActivity {

    TextView textView_nombre;
    TextView textView_fecha;
    ImageView imageView_tema;
    Button button_regresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_datos);

        textView_nombre = findViewById(R.id.textView_nombre);
        textView_fecha = findViewById(R.id.textView_fecha);
        imageView_tema = findViewById(R.id.imageView_tema);
        button_regresar = findViewById(R.id.button_regresar);

        button_regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // Recuperar preferencias almacenadas
        SharedPreferences sharedPreferences =
                getSharedPreferences("preferencias1",
                        Context.MODE_PRIVATE);

        textView_nombre.setText(sharedPreferences.getString("nombre", ""));
        textView_fecha.setText(sharedPreferences.getString("fecha", ""));

        // Recuperar el nombre del tema para mostrar la figura adecuada
        String nombreFigura = "";
        nombreFigura = sharedPreferences.getString("tema", "");
        if (nombreFigura.equals("planta")) {
            imageView_tema.setImageResource(R.drawable.planta);
        } else if (nombreFigura.equals("animal")) {
            imageView_tema.setImageResource(R.drawable.animal);
        } else if (nombreFigura.equals("figura")) {
            imageView_tema.setImageResource(R.drawable.figura);
        }
    }
}