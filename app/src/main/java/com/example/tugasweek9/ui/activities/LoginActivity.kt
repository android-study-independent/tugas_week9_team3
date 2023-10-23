package com.example.tugasweek9.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.tugasweek9.MainActivity
import com.example.tugasweek9.R
import com.example.tugasweek9.SingWithGoogle
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : BaseActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    // analytic
    private lateinit var firebaseAnalytics: FirebaseAnalytics

    companion object
    {val RC_GOOGLE_SINGIN = 1}



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        firebaseAnalytics = FirebaseAnalytics.getInstance(this)

        firebaseAnalytics = FirebaseAnalytics.getInstance(this)

        //analityc 1
//        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_ITEM){
//            params(FirebaseAnalytics.Param.ITEM_ID,"ffDBEnWHRdUXfh0KLfn4o5dnRnL2")
//            params(FirebaseAnalytics.Param.ITEM_NAME,"TEST")
//        }


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

            val bundle = Bundle().apply {
                putString(FirebaseAnalytics.Param.ITEM_ID, "123")
                putString(FirebaseAnalytics.Param.ITEM_NAME, "Test Item")
                putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image")
            }
            firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle)
            loginUser()

//            val bundle = Bundle().apply {
//                putString(FirebaseAnalytics.Param.ITEM_ID, "123")
//                putString(FirebaseAnalytics.Param.ITEM_NAME, "Test Item")
//                putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image")
//            }
//            firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle)
           // analityc Login USERPASS
            val bundle = Bundle()
            bundle.putString(FirebaseAnalytics.Param.METHOD, "userpass")
            firebaseAnalytics.logEvent(FirebaseAnalytics.Event.LOGIN, bundle)
            //analityc 2.2
        }

        // ini menuju ke action login Google yaaa
        val btnGoogle = findViewById<SignInButton>(R.id.btnGoogle)
        btnGoogle.setOnClickListener {
            val singInIntent = googleSignInClient.signInIntent
            startActivityForResult(singInIntent, RC_GOOGLE_SINGIN)

            val bundle = Bundle().apply {
                putString(FirebaseAnalytics.Param.ITEM_ID, "123")
                putString(FirebaseAnalytics.Param.ITEM_NAME, "Test Item")
                putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image")
            }
            firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle)

            //Analytic debugview
//            val bundle = Bundle()
//            bundle.putString("loginGoogle","Login Google")
//            firebaseAnalytics.logEvent("login_google", bundle)

        }
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.webclient_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)



        // ini menuju ke halaman RegisterActivity yaa
        val txtRegister = findViewById<TextView>(R.id.txtRegister)
        txtRegister.setOnClickListener {
            Intent(this, RegisterActivity::class.java).also {
                startActivity(it)
            }
        }

        // inisiasi firebase nya
        auth = Firebase.auth
    }

    private fun params(itemId: String, s: String) {

    }


    // ini login with google yaa
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == SingWithGoogle.RC_GOOGLE_SINGIN) {

            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                Log.d("nabila_tag", "firebaseAuthWithGoogle: ${account.idToken}")

                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException){
                Log.e("nabila_tag", "error -> ${e.localizedMessage}")

            }
        }
    }
    private fun firebaseAuthWithGoogle(idToken: String?) {
        Log.d("nabila_tag", "token -> $idToken")
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d("nabila_tag", "singInWithCredential:success")
                    val user = auth.currentUser

                    Toast.makeText(this, "berhasil regist", Toast.LENGTH_SHORT).show()
//                    //analityc 2.1
//                    firebaseAnalytics.logEvent(FirebaseAnalytics.Event.LOGIN){
//                        params(FirebaseAnalytics.Param.METHOD,"google")
//                  }
//                    //analityc Login GOOGLE
//                    val bundle = Bundle()
//                    bundle.putString(FirebaseAnalytics.Param.METHOD, "userpass")
//                    firebaseAnalytics.logEvent(FirebaseAnalytics.Event.LOGIN, bundle)

                    Toast.makeText(
                        this,
                        "Berhasil Sign In ${user?.displayName}",
                        Toast.LENGTH_SHORT
                    ).show()
                    // redirect ke halaman mainactivity
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Log.w("nabila_tag","singInWithCredential:failure", task.exception)
                    Toast.makeText(this, "Gagal Sign In", Toast.LENGTH_SHORT).show()
                }
            }

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