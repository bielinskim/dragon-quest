package com.dragonquest.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.dragonquest.R
import com.dragonquest.models.User
import com.dragonquest.utils.RetrofitService

class RegisterFragment : Fragment() {

    lateinit var registerMessage: TextView
    lateinit var registerLoginInput: TextView
    lateinit var registerPasswordInput: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_register, container, false)
        val navController = Navigation.findNavController(requireActivity(), R.id.navigationHost)

        val loginTabTextView: TextView = view.findViewById(R.id.loginTabTextView)
        val registerButton: Button = view.findViewById(R.id.registerButton)

        registerMessage = view.findViewById(R.id.registerMessage)
        registerLoginInput = view.findViewById(R.id.registerLoginInput)
        registerPasswordInput = view.findViewById(R.id.registerPasswordInput)

        loginTabTextView.setOnClickListener {
            navController.navigate(R.id.action_registerFragment_to_loginFragment)
        }

        registerButton.setOnClickListener {
            val login = registerLoginInput.text.toString()
            val password = registerPasswordInput.text.toString()

            val isValid = validate(login, password)

            if (isValid) {
                register(login, password)
            }
        }

        return view;
    }

    private fun validate(login: String, password: String): Boolean {

        if (login == "") {
            val message = "Login field cannot be empty"
            setMessage(message, "ERROR")

            return false;
        }

        if (password == "") {
            val message = "Password field cannot be empty"
            setMessage(message, "ERROR")

            return false;
        }

        return true;

    }

    private fun register(login: String, password: String) {
        val api = RetrofitService

        val user = User(null, login, password, null, null)

        api.registerUser(user, {
            if (it?.userId != null) {
                val message = "Account has been created"
                setMessage(message, "SUCCESS")
                clearInputs()
            } else {
                val message = "Error while account creating"
                setMessage(message, "ERROR")
            }
        },
        {
            if (it is String) {
                setMessage(it, "ERROR")
            }
        })
    }

    private fun setMessage(message: String, type: String) {
        if (type == "ERROR") {
            val errorColor = context?.getColor(R.color.error)

            if (errorColor != null) {
                registerMessage.setTextColor(errorColor)
            }

        }

        if (type == "SUCCESS") {
            val successColor = context?.getColor(R.color.success)

            if (successColor != null) {
                registerMessage.setTextColor(successColor)
            }
        }

        registerMessage.text = message
    }

    private fun clearInputs() {
        registerLoginInput.text = ""
        registerPasswordInput.text = ""
    }

}