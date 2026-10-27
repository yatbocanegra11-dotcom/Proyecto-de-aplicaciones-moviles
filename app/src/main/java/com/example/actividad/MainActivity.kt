package com.example.actividad

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var etCorreo : EditText
    private lateinit var etPass : EditText
    private lateinit var btnInicio : Button
    private lateinit var dbHelper: DataBaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        val tvCrearCuenta = findViewById<TextView>(R.id.tvCrearCuenta)

        //referiencia a componentes
        etCorreo = findViewById(R.id.etCorreo)
        etPass  = findViewById(R.id.etpassword)
        btnInicio = findViewById(R.id.btnIngresar)

        dbHelper = DataBaseHelper(this)

        //evento para inciar sesion
        btnInicio.setOnClickListener {
            //TODO: AGREGAR METODO DE INCIO DE SESION
            iniciarSesion()
        }


        tvCrearCuenta.setOnClickListener {
            val intent = Intent(this, RegistrarActivity::class.java)
            startActivity(intent)
        }

    }
    //METODO PARA INICIAR SESION
    private fun iniciarSesion(){

        //acceder a la informacion que escribe el usuario
        val correo = etCorreo.text.toString().trim()
        val pass = etPass.text.toString().trim()

        if (correo.isEmpty() || pass.isEmpty()){
            Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT)
            return
        }

        //llamar al metodo de incio de sesion de la BD
        val resultado = dbHelper.IniciarSesion(correo,pass)
        if (resultado){
            Toast.makeText(this, "BIENVENIDO", Toast.LENGTH_SHORT)
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)

            //CERRAR PANTALLA ACTUAL
            finish()

        }else{
            Toast.makeText(this, "DATOS INCORRECTOS", Toast.LENGTH_SHORT)
        }




    }
}