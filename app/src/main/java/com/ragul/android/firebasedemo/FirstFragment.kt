package com.ragul.android.firebasedemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.firebase.firestore.FirebaseFirestore
import com.ragul.android.firebasedemo.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    var db = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imageView.setOnClickListener{
            binding.imageView
        }

        binding.firstName.setOnClickListener {
            binding.firstName.text = null
        }

        binding.lastName.setOnClickListener {
            binding.lastName.text = null
        }

        binding.age.setOnClickListener {
            binding.age.text = null
        }

        binding.registerButton.setOnClickListener {
            saveData()

        }
        binding.viewButton.setOnClickListener{
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    private fun saveData() {

        //getting values from bindings
        val firstName = binding.firstName.text.toString()
        val lastName = binding.lastName.text.toString()
        val age = binding.age.text.toString().toInt()

        //creating a map
        var user = mapOf("first_name" to firstName, "last_name" to lastName, "age" to age)

        //storing the map in firestore
        db.collection("user").document("user1").set(user)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}