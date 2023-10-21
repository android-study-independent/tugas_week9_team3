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

class LoginActivity : BaseActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // ini menuju ke halaman ForgotPasswordActivity yaa
        val txtForgotPassword = findViewById<TextView>(R.id.txtForgotPassword)
        txtForgotPassword.setOnClickListener {
            Intent(this, ForgotPasswordActivity::class.java).also {
                startActivity(it)
            }
        }

        // ini action button login yaa
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        btnLogin.setOnClickListener {
            loginUser()
        }

        // ini menuju ke action login Google yaaa
        // btw ini itu belum di isi yaa

        // ini menuju ke halaman RegisterActivity yaa
        val txtRegister = findViewById<TextView>(R.id.txtRegister)
        txtRegister.setOnClickListener {
            Intent(this, RegisterActivity::class.java).also {
                startActivity(it)
            }
        }

        // inisiasi firebase nya
        auth = Firebase.auth
//        if (auth.currentUser!= null) {
//            startActivity(Intent(this, LoginActivity::class.java))
//            finish()
//        }
    }

    private fun loginUser() {
        val inputEmail = findViewById<TextInputLayout>(R.id.inputEmail).editText?.text.toString()
        val inputPassword =
            findViewById<TextInputLayout>(R.id.inputPassword).editText?.text.toString()


        val emailLayout = findViewById<TextInputLayout>(R.id.inputEmail)
        val passwordLayout = findViewById<TextInputLayout>(R.id.inputPassword)

        if (validateForm(
                emailLayout,
                passwordLayout,
                inputEmail,
                inputPassword
            )
        ) {
            showProgressBar()
            auth.signInWithEmailAndPassword(inputEmail, inputPassword)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful)
                    {
                        showToast(this, "Berhasil Login, yeayy!")
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                        hideProgressBar()
                    } else {
                        showToast(this, "Tidak bisa login!")
                        hideProgressBar()
                    }

                }
        }
    }

    private fun validateForm(
        emailLayout: TextInputLayout,
        passwordLayout: TextInputLayout,
        email: String,
        password: String
    ): Boolean {
        return when {

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