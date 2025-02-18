package com.example.preferencias;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editText_nombre;
    RadioButton radioButton_planta;
    RadioButton radioButton_animal;
    RadioButton radioButton_figura;
    CalendarView calendarView_fecha;
    Button button_guardar;
    Button button_ver_preferencias;

    String selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText_nombre = findViewById(R.id.editText_nombre);
        radioButton_planta = findViewById(R.id.radioButton_planta);
        radioButton_animal = findViewById(R.id.radioButton_animal);
        radioButton_figura = findViewById(R.id.radioButton_figura);
        button_guardar = findViewById(R.id.button_guardar);
        button_ver_preferencias = findViewById(R.id.button_ver_preferencias);
        calendarView_fecha = findViewById(R.id.calendarView_fecha);

        // Escuchador del calendario
        calendarView_fecha.setOnDateChangeListener(
                new CalendarView.OnDateChangeListener() {
                    @Override
                    public void onSelectedDayChange(@NonNull CalendarView calendarView,
                                                    int year,
                                                    int month,
                                                    int day) {
                        selectedDate = day + "/" + (month + 1) + "/" + year;
                    }
                }
        );

        // Escuchador para botón "Guardar preferencias"
        button_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Gestión de Preferencias
                SharedPreferences sharedPreferences =
                        getSharedPreferences("preferencias1",
                                Context.MODE_PRIVATE);
                // Objeto para almacenar preferencias
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString("nombre",
                        editText_nombre.getText().toString());
                // Revisar elección en elementos radioButton
                if (radioButton_planta.isChecked()) {
                    // Eligio "Planta"
                    editor.putString("tema","planta");
                } else if (radioButton_animal.isChecked()) {
                    // Eligio "Animal"
                    editor.putString("tema","animal");
                } else if (radioButton_figura.isChecked()) {
                    // Eligio "Figura"
                    editor.putString("tema","figura");
                }

                // Almacenar la fecha
                editor.putString("fecha", selectedDate);
                // Guardar datos
                boolean bandera;
                bandera = editor.commit();

                if (bandera) {
                    // Mensaje
                    Toast.makeText(MainActivity.this,
                            "preferencias guardadas",
                            Toast.LENGTH_SHORT).show();
                }else {
                    // Mensaje
                    Toast.makeText(MainActivity.this,
                            "preferencias no guardadas",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        button_ver_preferencias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent verPreferencias =
                        new Intent(MainActivity.this,
                                VerDatos.class);
                                startActivity(verPreferencias);
            }
        });
    }
}