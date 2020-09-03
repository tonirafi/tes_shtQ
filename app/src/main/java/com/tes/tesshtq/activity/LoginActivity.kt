package com.tes.tesshtq.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.tes.tesshtq.R
import com.tes.tesshtq.utils.snackBarSaller
import kotlinx.android.synthetic.main.activity_login.*
import java.util.*


class LoginActivity : AppCompatActivity() {

    private lateinit var callbackManager: CallbackManager
    private lateinit var mGoogleSignInClient: GoogleSignInClient




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setLoginFaceBook()
        setLoginGoogle()

        setUi()
    }


    private fun setUi() {

        bt_facebook.setOnClickListener {

            LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));
        }

        sign_in_button.setOnClickListener {
            signIn()
        }

        bt_login.setOnClickListener {
            if(validasi()){
                getHome()
            }else{
                snackBarSaller(
                    this,
                    findViewById(android.R.id.content),
                    "User Name & Password Tidak Boleh Kosong",
                    R.color.read_100
                )
            }
        }


    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {

        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode === 1) {
            val task: Task<GoogleSignInAccount> =  GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account =
                completedTask.getResult(ApiException::class.java)

           getHome()
        } catch (e: ApiException) {
            showPesan("signInResult:failed code=" + e.statusCode)
        }
    }

    fun showPesan(pesan:String){
        Toast.makeText(this,pesan,Toast.LENGTH_LONG).show()
    }


    private fun signIn() {
        val signInIntent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, 1)
    }

    fun getHome(){
        var intent=Intent(this@LoginActivity,MainActivity::class.java)
        startActivity(intent)
        finish()
    }


    fun setLoginGoogle(){
        val gso =GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

    }

    fun setLoginFaceBook(){
        callbackManager = CallbackManager.Factory.create()
        LoginManager.getInstance().registerCallback(callbackManager,
            object : FacebookCallback<LoginResult?> {
                override fun onSuccess(loginResult: LoginResult?) {

                    getHome()
                }

                override fun onCancel() {
                }

                override fun onError(exception: FacebookException) {
                    showPesan(exception.message.toString())

                }
            })
    }


    fun validasi():Boolean{

        return when {
            ed_username.text.toString().equals("") -> {
                false
            }
            ed_password.text.toString().equals("") -> {
                false
            }
            else -> {
                true
            }
        }
    }

}