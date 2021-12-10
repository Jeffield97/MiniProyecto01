package com.sox.miniproyecto

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        val btn_ingresar = view.findViewById<Button>(R.id.btn_ingresar)
        btn_ingresar.setOnClickListener {
        val txt_user = view.findViewById<EditText>(R.id.edx_user).text.toString()
        val txt_pass = view.findViewById<EditText>(R.id.edx_password).text.toString()
        if (auth(txt_user,txt_pass)){

                val action = LoginFragmentDirections.actionLoginFragmentToFoodsFragment()
                view.findNavController().navigate(action)

        }
        else
        {
            //Snackbar.make(this.requireContext(),view,"Fallo al intresar",Snackbar.LENGTH_SHORT,).show()
            Toast.makeText(this.context,"Error al autenticar",Toast.LENGTH_SHORT).show()
        }
        }

        return view
    }

    fun auth(user:String,password:String):Boolean
    {
        var success=false
        if (user == "admin" && password=="password")
        {
            success=true
        }
        return success
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LoginFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}