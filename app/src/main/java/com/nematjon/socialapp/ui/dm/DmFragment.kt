package com.nematjon.socialapp.ui.dm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.nematjon.socialapp.R

class DmFragment : Fragment() {

    private lateinit var dmViewModel: DmViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dmViewModel =
            ViewModelProvider(this).get(DmViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dm, container, false)
        val textView: TextView = root.findViewById(R.id.text_dm)
        dmViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}
