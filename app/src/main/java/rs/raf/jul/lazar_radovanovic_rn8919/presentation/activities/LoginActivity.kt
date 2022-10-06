package rs.raf.jul.lazar_radovanovic_rn8919.presentation.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import org.koin.androidx.viewmodel.compat.SharedViewModelCompat.sharedViewModel
import rs.raf.jul.lazar_radovanovic_rn8919.R
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

import rs.raf.jul.lazar_radovanovic_rn8919.presentation.contracts.StoreContract
import rs.raf.jul.lazar_radovanovic_rn8919.presentation.viewmodel.StoreViewModel
import java.util.regex.Pattern

class LoginActivity : AppCompatActivity(){

    private val storeViewModel: StoreContract.ViewModel by viewModel<StoreViewModel>()

    private lateinit var loginBtn : Button
    private lateinit var username : EditText
    private lateinit var password : EditText



    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initObservers()
        splashScreen.setKeepOnScreenCondition(
            SplashScreen.KeepOnScreenCondition {
                val p = applicationContext.getSharedPreferences("P1", MODE_PRIVATE)

                if (p.getBoolean("login", false)) {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    setContentView(R.layout.activity_login)
                    init()
                }
                false
            })

    }
    private fun init(){
        val p = applicationContext.getSharedPreferences("P1", MODE_PRIVATE)
        val pe = p.edit()
        loginBtn = findViewById<Button>(R.id.loginBtn2) as Button
        username = findViewById<EditText>(R.id.usernameTw2) as EditText
        password = findViewById<EditText>(R.id.passwordTw2) as EditText

        loginBtn.setText("Login")

        loginBtn.setOnClickListener{


            val usernameStr = username.text.toString()
            val passwordStr = password.text.toString()

            var error = 0


            if (usernameStr.isEmpty() ||  passwordStr.isEmpty()) {
                val errorToast =
                    Toast.makeText(this, "You must fill all fields!", Toast.LENGTH_SHORT)
                errorToast.show()
                error = 1
            }  else if (passwordStr.length <= 5) {
                val errorToast = Toast.makeText(
                    this,
                    "Password must contain more than 5 characters!",
                    Toast.LENGTH_SHORT
                )
                errorToast.show()
                error = 1
            }

            if (error == 0) {
                makeCalls()


            }


        }


        }
    private fun makeCalls() = with(storeViewModel){
        userLogin(username.text.toString(),password.text.toString())
    }
    private fun initObservers() = with(storeViewModel) {
        user.observe(this@LoginActivity) {
            val p = applicationContext.getSharedPreferences("P1", MODE_PRIVATE)
            val pe = p.edit()
            if (user.value.toString()!=null){
                val p = applicationContext.getSharedPreferences("P1", MODE_PRIVATE)
                val pe = p.edit()
                println(user.value.toString()+"11111111111111111111111111111111111111111111")
                pe.putBoolean("login", false)
                pe.putString("fn",storeViewModel.user.value?.firstName.toString())
                pe.putString("gender",storeViewModel.user.value?.gender.toString())
                pe.putString("username",storeViewModel.user.value?.username.toString())
                pe.putString("email",storeViewModel.user.value?.email.toString())
                pe.putString("ln",storeViewModel.user.value?.lastName.toString())
                pe.putString("img",storeViewModel.user.value?.image.toString())

                pe.commit()

                val k=p.getString("fn","")
                println(k+"11111111111111111111111111111111111111111111")

                pe.putBoolean("login", false)
                pe.commit()
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }

        }
        error.observe(this@LoginActivity) {
            if (error.value==true){
                val errorToast = Toast.makeText(
                    this@LoginActivity,
                    "Wrong credentials!",
                    Toast.LENGTH_SHORT
                )
                errorToast.show()
            }

        }
    }
}