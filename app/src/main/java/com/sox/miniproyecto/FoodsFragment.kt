package com.sox.miniproyecto

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.core.view.forEach
import androidx.core.view.get
import androidx.navigation.findNavController
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FoodsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FoodsFragment : Fragment() {
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
        var arrayAdapter:ArrayAdapter<*>
        val comidasColombianas= mutableListOf("Lechona","Bandeja Paisa","Arepa","Ajiaco","Sancocho","Oblea")
        val comidasEcuatorianas= mutableListOf("Hornado","Humitas","Encebollado","Fritada","Churrasco")
        //val comidasOferta = mutableListOf("Lechona","Bandeja Paisa","Arepa","Hornado","Humitas","Encebollado","Fritada")

        val view = inflater.inflate(R.layout.fragment_foods, container, false)
        val lvFoods= view.findViewById<ListView>(R.id.lv_foods)
        //val chipComida= view.findViewById<ChipGroup>(R.id.chip_group_comida)
        val btn_siguiente = view.findViewById<FloatingActionButton>(R.id.siguiente)


        arrayAdapter= ArrayAdapter(this.requireContext(),android.R.layout.simple_expandable_list_item_1,comidasColombianas+comidasEcuatorianas)
        //arrayAdapter= ArrayAdapter(this.requireContext(),android.R.layout.simple_expandable_list_item_1,comidasEcuatorianas)
        lvFoods.adapter = arrayAdapter



        val chip_ec=view.findViewById<Chip>(R.id.chip_ecuatoriana)
            chip_ec.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked)
                {
                    //Toast.makeText(this.context, "Estoy seleccionado", Toast.LENGTH_SHORT).show()

                    arrayAdapter= ArrayAdapter(this.requireContext(),android.R.layout.simple_expandable_list_item_1,comidasEcuatorianas)
                    lvFoods.adapter = arrayAdapter

                }
                else
                {
                    arrayAdapter= ArrayAdapter(this.requireContext(),android.R.layout.simple_expandable_list_item_1,comidasColombianas+comidasEcuatorianas)
                    lvFoods.adapter = arrayAdapter
                    //Toast.makeText(this.context, "No estoy seleccionado", Toast.LENGTH_SHORT).show()
                    //chip_ec.setTextColor(R.color.black)
                }
            }

        val chip_co=view.findViewById<Chip>(R.id.chip_colombiana)
        chip_co.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked)
            {
                //Toast.makeText(this.context, "Estoy seleccionado", Toast.LENGTH_SHORT).show()

                arrayAdapter= ArrayAdapter(this.requireContext(),android.R.layout.simple_expandable_list_item_1,comidasColombianas)
                lvFoods.adapter = arrayAdapter
            }
            else
            {
                arrayAdapter= ArrayAdapter(this.requireContext(),android.R.layout.simple_expandable_list_item_1,comidasEcuatorianas+comidasEcuatorianas)
                lvFoods.adapter = arrayAdapter
                //Toast.makeText(this.context, "No estoy seleccionado", Toast.LENGTH_SHORT).show()
                //chip_ec.setTextColor(R.color.black)
            }
        }
        var productos_seleccionados=arrayListOf<String>()
        lvFoods.setOnItemClickListener { parent, view, position, id ->
            productos_seleccionados.add(lvFoods.getItemAtPosition(position).toString())
            //Toast.makeText(this.context,"Has añadido "+lvFoods.getItemAtPosition(position).toString()+" a tu orden", Toast.LENGTH_SHORT).show()
            Snackbar.make(this.requireContext(),view,"Has añadido "+lvFoods.getItemAtPosition(position).toString()+" a tu orden",Snackbar.LENGTH_LONG,).show()
        }

        btn_siguiente.setOnClickListener {
            //Toast.makeText(this.context, "Estoy seleccionado", Toast.LENGTH_SHORT).show()
            val action = FoodsFragmentDirections.actionFoodsFragmentToOrderFragment(productos_seleccionados.toString())
            view.findNavController().navigate(action)

        }
        return view
    }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FoodsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FoodsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}