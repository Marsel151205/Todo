package com.example.todo.ui.fragments

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.todo.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.IOException
import java.util.*


class ProfileFragment : Fragment() {

    lateinit var binding: FragmentProfileBinding
    private val fireStore = Firebase.firestore
    private lateinit var auth: FirebaseAuth
    private val PICK_IMAGE_REQUEST = 1
    private var imageUri: Uri? = null
    private var storage: FirebaseStorage? = null
    private var storageRef: StorageReference? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(layoutInflater)
        return binding.root
    }

    @SuppressLint("IntentReset")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addUserInfo()
        auth = Firebase.auth
        storage = FirebaseStorage.getInstance()
        storageRef = storage!!.reference.child("myImages/" + "profile_image")

        val userImage = storageRef!!.downloadUrl
        userImage.addOnCompleteListener { result ->
            Log.d("item", "${result.result}")
            Glide.with(binding.ivProfileImage).load(result.result).into(binding.ivProfileImage)
        }
        binding.ivProfileImage.setOnClickListener {
            launchGallery()
        }
        binding.btnUploadImage.setOnClickListener {
            uploadImage()
        }
    }


    private fun addUserInfo() {
        //userRegistrationDate
        val sharedPreferences: SharedPreferences =
            requireContext().getSharedPreferences("userDateRegistration", Context.MODE_PRIVATE)
        val userDateRegistration =
            sharedPreferences.getString("dateRegistration", "ошибочка:(").toString()
        binding.tvUserLoggedIn.text = userDateRegistration
        fireStore.collection("user").get().addOnSuccessListener { result ->
            for (document in result) {
                with(binding) {
                    //user name
                    tvUserName.text = document.get("name").toString()
                    //user password
                    tvUserPassword.text = document.get("password").toString()
                }
            }
        }
        //quantity task user
        fireStore.collection("task").get().addOnCompleteListener { result ->
            if (result.isSuccessful) {
                result.result?.apply {
                    binding.tvUserNumberOfTask.text = size().toString()
                }
            } else {
                result.exception?.message?.let {
                    print(it)
                }
            }
        }
    }

    private fun launchGallery() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            if (data == null || data.data == null) {
                return
            }
            imageUri = data.data
            try {
                val bitmap =
                    MediaStore.Images.Media.getBitmap(requireActivity().contentResolver, imageUri)
                binding.ivProfileImage.setImageBitmap(bitmap)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    private fun uploadImage() {
        if (imageUri != null) {
            Toast.makeText(requireContext(), "success", Toast.LENGTH_SHORT).show()
            storageRef?.putFile(imageUri!!)
        } else {
            Toast.makeText(requireContext(), "Please Upload an Image", Toast.LENGTH_SHORT).show()
        }
    }
}