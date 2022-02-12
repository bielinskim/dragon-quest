package com.dragonquest.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dragonquest.R
import com.dragonquest.models.User
import com.dragonquest.utils.RetrofitService
import com.dragonquest.viewmodels.CharactersViewModel

class LoginFragment : Fragment() {

    lateinit var loginMessage: TextView
    lateinit var loginLoginInput: TextView
    lateinit var loginPasswordInput: TextView
    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        navController = Navigation.findNavController(requireActivity(), R.id.navigationHost)

        val registerTabTextView: TextView = view.findViewById(R.id.registerTabTextView)
        val loginButton: Button = view.findViewById(R.id.loginButton)

        loginMessage = view.findViewById(R.id.loginMessage)
        loginLoginInput = view.findViewById(R.id.loginLoginInput)
        loginPasswordInput = view.findViewById(R.id.loginPasswordInput)

        registerTabTextView.setOnClickListener {
            navController.navigate(R.id.action_loginFragment_to_registerFragment)
        }

        loginButton.setOnClickListener {
            val login = loginLoginInput.text.toString()
            val password = loginPasswordInput.text.toString()

            val isValid = validate(login, password)

            if (isValid) {
                login(login, password)
            }
        }

        return view;
    }

    private fun validate(login: String, password: String): Boolean {

        if (login == "") {
            val message = "Pole login nie może byc puste"
            setMessage(message, "ERROR")

            return false;
        }

        if (password == "") {
            val message = "Pole hasło nie może byc puste"
            setMessage(message, "ERROR")

            return false;
        }

        return true;

    }

    private fun login(login: String, password: String) {
        val api = RetrofitService

        val user = User(null, login, password)

        api.loginUser(user, {
            if (it?.userId != null) {
                val message = "Zalogowano"
                setMessage(message, "SUCCESS")
                clearInputs()
                onLoginSuccess()
            } else {
                val message = "Błąd podczas logowania"
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
                loginMessage.setTextColor(errorColor)
            }

        }

        if (type == "SUCCESS") {
            val successColor = context?.getColor(R.color.success)

            if (successColor != null) {
                loginMessage.setTextColor(successColor)
            }
        }

        loginMessage.text = message
    }

    private fun clearInputs() {
        loginLoginInput.text = ""
        loginPasswordInput.text = ""
    }

    private fun onLoginSuccess() {
        navController.navigate(R.id.action_loginFragment_to_charactersFragment)
    }

}