package com.example.tugasweek9.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.tugasweek9.MainActivity
import com.example.tugasweek9.R
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterActivity : BaseActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // ini action untuk btn register yaaa
        val btnRegister =  findViewById<Button>(R.id.btnRegister)
        btnRegister.setOnClickListener {
            registerUser()
        }

        // ini menuju ke halaman LoginActivity yaaa
        val txtLogin = findViewById<TextView>(R.id.txtLogin)
        txtLogin.setOnClickListener {
            Intent(this, LoginActivity::class.java).also {
                startActivity(it)
            }
        }

        // inisiasi firebase nya
        auth = Firebase.auth

    }

    private fun registerUser() {
        val inputName = findViewById<TextInputLayout>(R.id.inputName).editText?.text.toString()
        val inputEmail = findViewById<TextInputLayout>(R.id.inputEmail).editText?.text.toString()
        val inputPassword =
            findViewById<TextInputLayout>(R.id.inputPassword).editText?.text.toString()

        val nameLayout = findViewById<TextInputLayout>(R.id.inputName)
        val emailLayout = findViewById<TextInputLayout>(R.id.inputEmail)
        val passwordLayout = findViewById<TextInputLayout>(R.id.inputPassword)

        if (validateForm(
                nameLayout,
                emailLayout,
                passwordLayout,
                inputName,
                inputEmail,
                inputPassword
            )
        ) {
            showProgressBar()
            auth.createUserWithEmailAndPassword(inputEmail, inputPassword)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful)
                    {
                        showToast(this, "user id created successfully")
                        hideProgressBar()
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    } else {
                        showToast(this, "user id not created")
                        hideProgressBar()
                    }

                }
        }
    }

    private fun validateForm(
        nameLayout: TextInputLayout,
        emailLayout: TextInputLayout,
        passwordLayout: TextInputLayout,
        name: String,
        email: String,
        password: String
    ): Boolean {
        return when {
            name.isEmpty() -> {
                nameLayout.error = "Nama tidak boleh kosong"
                false
            }

            !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                emailLayout.error = "Alamat email tidak valid"
                false
            }

            password.length < 6 -> {
                passwordLayout.error = "Kata sandi harus memiliki panjang minimal 6 karakter"
                false
            }

            else -> true
        }
    }
}