package com.example.soluciontaller3lr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.soluciontaller3lr.basesdedatos.AdminSQLiteOpenHelper;

public class NotasActivity extends AppCompatActivity {

    private EditText etCodigo, etDescripcion, etNota1, etNota2, etNota3, etNota4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas);
        etCodigo = findViewById(R.id.txtCodigo);
        etDescripcion = findViewById(R.id.txtDescripcion);
        etNota1 = findViewById(R.id.txtNota1);
        etNota2 = findViewById(R.id.txtNota2);
        etNota3 = findViewById(R.id.txtNota3);
        etNota4 = findViewById(R.id.txtNota4);
    }

    public void Registrar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase baseDeDatos = admin.getWritableDatabase();

        String codigo = etCodigo.getText().toString();
        String descripcion = etDescripcion.getText().toString();
        String nota1 = etNota1.getText().toString();
        String nota2 = etNota2.getText().toString();
        String nota3 = etNota3.getText().toString();
        String nota4 = etNota4.getText().toString();

        int calificacion1 = Integer.parseInt(nota1);
        int calificacion2 = Integer.parseInt(nota2);
        int calificacion3 = Integer.parseInt(nota3);
        int calificacion4 = Integer.parseInt(nota4);
        int totalNota;
        totalNota = ((calificacion1 + calificacion2 + calificacion3 + calificacion4) / 4);

        if(!codigo.isEmpty() && !descripcion.isEmpty() && !nota1.isEmpty()){
            ContentValues registro = new ContentValues();

            registro.put("codigo", codigo);
            registro.put("descripcion", descripcion);
            registro.put("nota1", nota1);
            registro.put("nota2", nota2);
            registro.put("nota3", nota3);
            registro.put("nota4", nota4);
            registro.put("total", totalNota);




            baseDeDatos.insert("notas", null, registro);

            baseDeDatos.close();
            etCodigo.setText("");
            etDescripcion.setText("");
            etNota1.setText("");
            etNota2.setText("");
            etNota3.setText("");
            etNota4.setText("");

            Toast.makeText(this,"Registro exitoso", Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }


}
