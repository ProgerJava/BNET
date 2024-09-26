package com.pestControlDrugs.bnet.view.fragment

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import com.pestControlDrugs.bnet.constant.CURRENT_DRUGS_FRAGMENT
import com.pestControlDrugs.bnet.constant.DRUGS_NAME
import com.pestControlDrugs.bnet.databinding.FragmentDrugsListBinding
import com.pestControlDrugs.bnet.di.MyApplication
import com.pestControlDrugs.bnet.model.dataclass.AllDrugs
import com.pestControlDrugs.bnet.view.activities.MainActivity
import com.pestControlDrugs.bnet.view.recycler.RecyclerAllDrugsList
import com.pestControlDrugs.bnet.viewModel.DrugsViewModel
import javax.inject.Inject


class DrugsListFragment : Fragment() {

    private lateinit var binding: FragmentDrugsListBinding
    private lateinit var mainActivity: MainActivity
    @Inject
    lateinit var viewModel: DrugsViewModel
    @Inject
    lateinit var sharedPreferencesEditor: SharedPreferences.Editor

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDrugsListBinding.inflate(inflater, container, false)
        mainActivity = (requireActivity() as MainActivity)
        (mainActivity.application as MyApplication).appComponent.inject(this)
        ///////////////////Запрашиваем общий список препаратов
        if (viewModel.allDrugs.value == null) {
            viewModel.getAllDrugs()
        }else {
            setAdapter(viewModel.allDrugs.value!!)
        }

        ////////////////////Заполняем RecyclerView сущностями Drugs
        viewModel.allDrugs.observe(requireActivity()){
            binding.progressBar.visibility = View.GONE
            setAdapter(it)
        }
        binding.searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                sharedPreferencesEditor.putString(DRUGS_NAME, query).commit()
                mainActivity.changeFragment(CURRENT_DRUGS_FRAGMENT)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {return true}

        })


        return binding.root
    }
    private fun setAdapter(allDrugs: AllDrugs) {
        binding.recyclerView.adapter = RecyclerAllDrugsList (allDrugs, mainActivity, sharedPreferencesEditor)
    }

    override fun onStart() {
        super.onStart()
        ///////////////Каждый раз при переходе на экран со всеми препарами чистим SP
        sharedPreferencesEditor.clear().commit()
    }


}