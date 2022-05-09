package com.ragul.android.firebasedemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.firebase.firestore.FirebaseFirestore
import com.ragul.android.firebasedemo.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    var db = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        loadData()
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun loadData() {
        db.collection("user").document("user1").get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    binding.labelFirstName.text = document.data?.get("first_name").toString()
                    binding.labelLastName.text = document.data?.get("last_name").toString()
                    binding.labelAge.text = document.data?.get("age").toString()
                }
            }
            .addOnFailureListener { exception ->
                println("error retrieving data")
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}