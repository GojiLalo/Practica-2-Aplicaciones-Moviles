package com.example.practica2v2

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.practica2v2.databinding.FragmentPerfilBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class PerfilFragment : Fragment() {

    private lateinit var binding: FragmentPerfilBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPerfilBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicializa Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Obtén el usuario actual
        val currentUser: FirebaseUser? = auth.currentUser

        // Verifica si el usuario está autenticado
        if (currentUser != null) {
            // Actualiza la vista con la información del usuario
            updateUI(currentUser)
        } else {
            // Si no hay usuario autenticado, puedes redirigir al login o mostrar un mensaje
            binding.nameTextView.text = "No autenticado"
            binding.emailTextView.text = ""
        }

        binding.btnLogout.setOnClickListener {
            logoutUser()
        }
    }

    private fun updateUI(user: FirebaseUser) {
        // Muestra el nombre del usuario (si está disponible)
        val displayName = user.displayName ?: "Nombre no disponible"
        binding.nameTextView.text = displayName

        // Muestra el correo electrónico del usuario
        binding.emailTextView.text = user.email

        // Si el usuario tiene una foto de perfil, puedes cargarla usando una librería como Glide o Picasso
        // Ejemplo con Glide:
        /*
        val photoUrl = user.photoUrl
        if (photoUrl != null) {
            Glide.with(this)
                .load(photoUrl)
                .into(binding.profileImageView)
        }
        */
    }

    private fun logoutUser() {
        auth.signOut()
        redirectToLogin()
    }

    private fun redirectToLogin() {
        // Crea un Intent para redirigir al usuario a la actividad de login
        val intent = Intent(requireContext(), LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK // Limpia la pila de actividades
        startActivity(intent)

        // Cierra el fragmento actual (opcional)
        requireActivity().finish()
    }
}