package com.example.practica2v2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.practica2v2.databinding.FragmentOperacionesBinding

class OperacionesFragment : Fragment() {
    private lateinit var binding: FragmentOperacionesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = FragmentOperacionesBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_operaciones, container, false)
    }

}