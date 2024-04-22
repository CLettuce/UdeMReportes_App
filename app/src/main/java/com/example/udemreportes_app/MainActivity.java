package com.example.udemreportes_app;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.udemreportes_app.response.AuthenticationManager;



public class MainActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private AuthenticationManager authenticationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializa el AuthenticationManager
        authenticationManager = new AuthenticationManager(this);

        // Obtiene referencias a los campos de entrada de usuario y contraseña
        usernameEditText = findViewById(R.id.editTextText);
        passwordEditText = findViewById(R.id.editTextTextPassword);

        // Obtiene una referencia al botón de inicio de sesión y configurar un listener para él
        Button loginButton = findViewById(R.id.button2);
        loginButton.setOnClickListener(view -> loginUser());
    }

    private void loginUser() {
        // Obtener el nombre de usuario y la contraseña ingresados por el usuario
        String username = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        // Validar si se han ingresado los datos de inicio de sesión
        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Por favor, ingresa tu nombre de usuario y contraseña.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Realizar la autenticación del usuario utilizando el AuthenticationManager
        authenticationManager.authenticateUser(username, password);
    }
}