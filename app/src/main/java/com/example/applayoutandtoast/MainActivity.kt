package com.example.applayoutandtoast

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.CheckBox
import android.widget.Switch
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.applayoutandtoast.databinding.ActivityMainBinding
import com.google.android.material.materialswitch.MaterialSwitch
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    /*
    private lateinit var customToastBiding: CustomLayoutToastBinding
    private lateinit var customToastWelcomeBiding : CustomLayoutWelcomeBinding
    private lateinit var customToasValidationBiding : CustomLayoutValidationBinding
     */
    private lateinit var sharedPrefUser: SharedPreferences
    private lateinit var editSharedPrefUser:SharedPreferences.Editor
    private lateinit var sharedPrefRemember:SharedPreferences
    private lateinit var editsharedPrefRemember:SharedPreferences.Editor


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        /*customToastBiding = CustomLayoutToastBinding.inflate(layoutInflater)
        customToastWelcomeBiding = CustomLayoutWelcomeBinding.inflate(layoutInflater)
        customToasValidationBiding = CustomLayoutValidationBinding.inflate(layoutInflater)
         */

        val database = DatabaseHelper(this)
        var validarLogin = false

        var email: String
        var password: String

        /*
        val toast = Toast (applicationContext).apply{
            duration= Toast.LENGTH_SHORT
            view = customToastBiding.root
        }

        val toastWelcome = Toast(applicationContext).apply {
            duration = Toast.LENGTH_SHORT
            view = customToastWelcomeBiding.root }

        val toastValidation = Toast(applicationContext).apply {
            duration = Toast.LENGTH_SHORT
            view = customToasValidationBiding.root
        }
        */

        //Configurar o arquivo de Shared Preferences
        sharedPrefUser = getSharedPreferences(getString(R.string.user), Context.MODE_PRIVATE)
        sharedPrefRemember = getSharedPreferences("login_remember", Context.MODE_PRIVATE)


        if (sharedPrefRemember.getBoolean("login_remember", false)){
            binding.editEmail.setText(sharedPrefUser.getString(getString(R.string.user), ""))
            binding.editPassword.setText(sharedPrefUser.getString(getString(R.string.pass),""))
            binding.guardar.isChecked = true
        }



        binding.buttonEntrar.setOnClickListener {

            email = binding.editEmail.text.toString().trim()
            password = binding.editPassword.text.toString().trim()
            var remember: MaterialSwitch = binding.guardar

            if(!password.equals("") && email.equals("")){
                binding.email.error = null
            }

            if (email.equals("")) {
                binding.editEmail.error = "*Campo Obrigatorio"
            }else if(password.equals("")) {
                binding.editEmail.error = null
                binding.editPassword.error = "*Campo Obrigatorio"
            }else{
                binding.editEmail.error = null
                binding.editPassword.error = null

                validarLogin = database.validarLogin(email,password)
                if (validarLogin){
                    editSharedPrefUser = sharedPrefUser.edit()
                    editSharedPrefUser.putString(getString(R.string.user),email)
                    editSharedPrefUser.putString(getString(R.string.pass),password)
                    editSharedPrefUser.apply()

                    editsharedPrefRemember = sharedPrefRemember.edit()
                    editsharedPrefRemember.putBoolean("login_remember", remember.isChecked)
                    editsharedPrefRemember.apply()

                    startActivity(Intent(this, MainHome::class.java))
                    finish()

                }else{
                    Snackbar.make(binding.root,"Usuario Cadastrado",Snackbar.LENGTH_SHORT)
                }
            }
        }

        binding.buttonCadastrar.setOnClickListener {
            startActivity(Intent(this, MainCadastro::class.java))
        }

    }
}