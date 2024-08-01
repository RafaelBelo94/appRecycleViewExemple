package com.example.applayoutandtoast

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Color.RED
import android.os.Bundle
import android.telephony.ims.RcsUceAdapter
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.doOnTextChanged
import com.example.applayoutandtoast.DatabaseHelper
import com.example.applayoutandtoast.databinding.ActivityMainBinding
import com.example.applayoutandtoast.databinding.ActivityMainCadastroBinding
import com.google.android.material.snackbar.Snackbar


class MainCadastro : AppCompatActivity() {
    private lateinit var binding: ActivityMainCadastroBinding
    private lateinit var editEmail: String
    private lateinit var editName: String
    private lateinit var editPass: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val database = DatabaseHelper(this)

        var email: String
        var password: String
        var name : String

        binding.editPassword.doOnTextChanged { text, start, before, count ->
            if (text!!.length<8){
                binding.editPassword.error = "A senha precisa ter mais de 8 digitos"
            }else{
                binding.editPassword.error = null
            }
        }

        binding.buttonCadastro.setOnClickListener {
            editEmail = binding.editEmail.text.toString().trim()
            editName = binding.editName.text.toString().trim()
            editPass = binding.editPassword.text.toString().trim()


            if(editEmail.equals("")){
                binding.email.error= "*Campo obrigatorio"
            }else{
                binding.email.error = null
            }

            if(editName.equals("")){
                binding.nome.error= "*Campo obrigatorio"
            }else{
                binding.nome.error = null
            }
            if(editPass.equals("")){
                binding.password.error= "*Campo obrigatorio"
            }else{
                binding.password.error = null
            }

            if (database.validarEmail(editEmail)){
                Snackbar.make(binding.root,"Email já Cadastrado!",Snackbar.LENGTH_SHORT).show()
            }else{
                database.cadastrarUsuario(editName,editEmail,editPass)
                startActivity(Intent(this,MainActivity::class.java))
            }

        }

        /*
        binding.buttonRegister.setOnClickListener {

            name = binding.inputNameRegister.text.toString().trim()
            email = binding.inputEmailRegister.text.toString().trim()
            password = binding.inputPassRegister.text.toString().trim()



            if (name.equals("")){

            }else {
                if (!password.equals("") && email.equals("")) {
                    binding.boxRegisterPass.error = null
                }

                if (email.equals("")) {
                    binding.boxRegisterEmail.error = "*Campo Obrigatorio"
                } else if (password.equals("")) {
                    binding.boxRegisterEmail.error = null
                    binding.boxRegisterPass.error = "*Campo Obrigatorio"
                } else {
                    binding.boxRegisterEmail.error = null
                    binding.boxRegisterPass.error = null


                }
            }
            }
            */
    }
}