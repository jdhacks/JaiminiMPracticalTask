package com.jaimini.dave.home.ui

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.jaimini.dave.R
import com.jaimini.dave.databinding.LayoutDialogBinding
import com.jaimini.dave.databinding.UserItemLayBinding

class ShowDetailsDialog : DialogFragment(){
    private  var urlm : String? = null
    private lateinit var binding: LayoutDialogBinding

    companion object {

        private const val KEY_URL = "URL"

        fun newInstance( url: String?): ShowDetailsDialog {
            val args = Bundle()
            args.putString(KEY_URL, url)
             val fragment = ShowDetailsDialog()
            fragment.urlm=url
            fragment.arguments = args
            return fragment
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= LayoutDialogBinding.inflate(inflater)

        binding.txtmurl.text =urlm
        Glide.with(binding.imgdialoguser.context)
            .load(urlm)
            .into(binding.imgdialoguser)
        binding.btnok.setOnClickListener {
            // TODO: Do some task here
            dismiss()
        }

        return  binding.root
        //return inflater.inflate(R.layout.layout_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(view)
        setupClickListeners(view)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

    private fun setupView(view: View) {
        binding.txtmurl.text = urlm

    }

    private fun setupClickListeners(view: View) {
        binding.btnok.setOnClickListener {
            // TODO: Do some task here
            dismiss()
        }
    }
}