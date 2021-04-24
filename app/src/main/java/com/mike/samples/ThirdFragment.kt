package com.mike.samples

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.mike.samples.databinding.FragmentIndexBinding
import java.util.concurrent.atomic.AtomicInteger

class ThirdFragment : Fragment(R.layout.fragment_index) {
    private var viewBinding: FragmentIndexBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding = FragmentIndexBinding.bind(view)
        viewBinding?.tvIndex?.text = "Third-" + S_INDEX.addAndGet(1).toString()

        view.setOnClickListener {
            findNavController().navigate(R.id.indexFragment)
        }
    }

//    override fun onDestroyView() {
//        viewBinding = null
//        super.onDestroyView()
//    }

    companion object {
        private val S_INDEX = AtomicInteger()
    }
}