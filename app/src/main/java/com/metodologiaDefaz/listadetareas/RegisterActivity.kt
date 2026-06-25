package com.metodologiaDefaz.listadetareas

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var tilEmail: TextInputLayout
    private lateinit var tilPassword: TextInputLayout
    private lateinit var etEmail: TextInputEditText
    private lateinit var etPassword: TextInputEditText
    private lateinit var btnRegister: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = FirebaseAuth.getInstance()

        tilEmail = findViewById(R.id.tilEmailRegister)
        tilPassword = findViewById(R.id.tilPasswordRegister)
        etEmail = findViewById(R.id.etEmailRegister)
        etPassword = findViewById(R.id.etPasswordRegister)
        btnRegister = findViewById(R.id.btnRegister)

        btnRegister.setOnClickListener {
            if (validateInputs()) {
                val email = etEmail.text.toString().trim()
                val password = etPassword.text.toString().trim()
                registerUser(email, password)
            }
        }

        val tvGoToLogin = findViewById<android.widget.TextView>(R.id.tvGoToLogin)
        tvGoToLogin.setOnClickListener {
            finish()
        }
    }

    private fun validateInputs(): Boolean {
        var isValid = true
        val email = etEmail.text.toString().trim()
        val password = etPassword.text.toString().trim()

        if (email.isEmpty()) {
            tilEmail.error = "El correo es obligatorio"
            isValid = false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            tilEmail.error = "Formato de correo no válido"
            isValid = false
        } else {
            tilEmail.error = null
        }

        if (password.isEmpty()) {
            tilPassword.error = "La contraseña es obligatoria"
            isValid = false
        } else if (password.length < 6) {
            tilPassword.error = "Debe tener al menos 6 caracteres"
            isValid = false
        } else {
            tilPassword.error = null
        }

        return isValid
    }

    private fun registerUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Cuenta creada con éxito", Toast.LENGTH_SHORT).show()
                    goToMain()
                } else {
                    val errorMessage = when {
                        task.exception?.message?.contains("already in use") == true ->
                            "Este correo ya está registrado"
                        task.exception?.message?.contains("badly formatted") == true ->
                            "El formato del correo no es válido"
                        else -> "Error al registrar: revisa tu conexión a internet"
                    }
                    Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
                }
            }
    }

    private fun goToMain() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}