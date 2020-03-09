package com.nematjon.socialapp.ui.profile

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.nematjon.socialapp.R

class ProfileFragment : Fragment() {

    private lateinit var profileViewModel: ProfileViewModel

    private lateinit var photo1: ImageView
    private lateinit var photo2: ImageView
    private lateinit var photo3: ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        profileViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_profile, container, false)
        val username: TextView = root.findViewById(R.id.username)
        val gender: TextView = root.findViewById(R.id.gender)
        val birthday: TextView = root.findViewById(R.id.birthday)
        val location: TextView = root.findViewById(R.id.location)
        val bio: TextView = root.findViewById(R.id.bio)

        photo1 = root.findViewById(R.id.photo_1)
        photo2 = root.findViewById(R.id.photo_2)
        photo3 = root.findViewById(R.id.photo_3)

        profileViewModel.getUserData()
            .observe(viewLifecycleOwner, Observer<ProfileViewModel.UserData> { userData ->
                username.text = userData.username
                gender.text = userData.gender
                birthday.text = userData.birthday
                location.text = userData.location
                bio.text = userData.bio
            })

        photo1.setOnClickListener { onPhotoClickHandle(photo1) }
        photo2.setOnClickListener { onPhotoClickHandle(photo2) }
        photo3.setOnClickListener { onPhotoClickHandle(photo3) }

        return root
    }

    private fun onPhotoClickHandle(imageView: ImageView) {
        val emptyPhotoBitmap: Bitmap? =
            activity?.getDrawable(R.drawable.empty_photo)?.toBitmap()
        if (imageView.drawable.toBitmap() == emptyPhotoBitmap) {
            pickImage(imageView)
        } else {
            //TODO: show the full image
            Toast.makeText(activity, "TODO: show the full image", Toast.LENGTH_SHORT).show()
        }
    }

    private fun pickImage(imageView: ImageView) {
        if (ActivityCompat.checkSelfPermission(
                activity!!.baseContext,
                READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            val intent = Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.INTERNAL_CONTENT_URI
            )
            intent.type = "image/*"
            intent.putExtra("crop", "true")
            intent.putExtra("scale", true)
            intent.putExtra("outputX", 350)
            intent.putExtra("outputY", 350)
            intent.putExtra("aspectX", 1)
            intent.putExtra("aspectY", 1)
            intent.putExtra("return-data", true)
            var requestCode = 0
            when (imageView) {
                photo1 -> requestCode = PICK_IMAGE_1_REQUEST_CODE
                photo2 -> requestCode = PICK_IMAGE_2_REQUEST_CODE
                photo3 -> requestCode = PICK_IMAGE_3_REQUEST_CODE
            }
            startActivityForResult(intent, requestCode)
        } else {
            ActivityCompat.requestPermissions(
                activity!!,
                arrayOf(READ_EXTERNAL_STORAGE),
                READ_EXTERNAL_STORAGE_REQUEST_CODE
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && (requestCode == PICK_IMAGE_1_REQUEST_CODE || requestCode == PICK_IMAGE_2_REQUEST_CODE || requestCode == PICK_IMAGE_3_REQUEST_CODE)) {
            val extras: Bundle? = data!!.extras
            when (requestCode) {
                PICK_IMAGE_1_REQUEST_CODE -> {
                    //Get image
                    val newProfilePic: Bitmap? = extras!!.getParcelable("data")
                    photo1.setImageBitmap(newProfilePic)
                }
                PICK_IMAGE_2_REQUEST_CODE -> {
                    //Get image
                    val newProfilePic: Bitmap? = extras!!.getParcelable("data")
                    photo2.setImageBitmap(newProfilePic)
                }
                PICK_IMAGE_3_REQUEST_CODE -> {
                    //Get image
                    val newProfilePic: Bitmap? = extras!!.getParcelable("data")
                    photo3.setImageBitmap(newProfilePic)
                }
            }

        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            READ_EXTERNAL_STORAGE_REQUEST_CODE -> {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // pick image after request permission success
                    Toast.makeText(activity, "Permissions are set", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    companion object {
        const val PICK_IMAGE_1_REQUEST_CODE = 1
        const val PICK_IMAGE_2_REQUEST_CODE = 2
        const val PICK_IMAGE_3_REQUEST_CODE = 3
        const val READ_EXTERNAL_STORAGE_REQUEST_CODE = 1001
    }
}
