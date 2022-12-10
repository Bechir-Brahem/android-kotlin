package com.example.tp3

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.tp3.enums.Gender
import com.mikhaellopez.circularimageview.CircularImageView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = ""
    private var param2: String? = ""
    private var param3: Gender? = Gender.MALE
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString("name")
            param2 = it.getString("detail")
            param3 = it.getSerializable("gender") as Gender
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_details, container, false)
            val name: TextView = view.findViewById(R.id.name)
            name.setText(param1)
            val details: TextView = view.findViewById(R.id.details)
            val icon: CircularImageView = view.findViewById(R.id.icon)
            details.setText(param2)
            if (param3 == Gender.MALE)
                icon.setImageResource(R.drawable.male)
            else
                icon.setImageResource(R.drawable.female)
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DetailsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String, param3: Gender) =
            DetailsFragment().apply {
                arguments = Bundle().apply {
                    putString("name", param1)
                    putString("detail", param2)
                    putSerializable("gender", param3)
                }
            }
    }
}