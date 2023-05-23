package com.example.parcialcruz

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.parcialcruz.R
import com.example.parcialcruz.R
import com.example.parcialcruz.databinding.FragmentNewairportBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class NewMovieFragment : Fragment() {

    private val viewModel: airportViewModel by activityViewModels{
        airportViewModel.Factory
    }



    private lateinit var binding: FragmentNewairportBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewairportBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        setViewModel()
        observeStatus()
    }


    private fun setViewModel(){
        binding.viewmodel = viewModel
    }

    private fun observeStatus () {
        viewModel.status.observe(viewLifecycleOwner){ status ->
            when{
                status.equals(MovieViewModel.MOVIE_CTREATTED) ->{
                    Log.d("APP TAG", status)
                    Log.d("APP TAG", viewModel.getMovies().toString())

                    viewModel.clearStatus()
                    viewModel.clearData()

                    findNavController().popBackStack()
                }
                status.equals(MovieViewModel.WRONG_DATA) ->{
                    Log.d("APP TAG", status)
                    viewModel.clearStatus()

                }
            }
        }
    }


}