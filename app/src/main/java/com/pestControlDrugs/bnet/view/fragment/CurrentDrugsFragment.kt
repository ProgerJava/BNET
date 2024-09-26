package com.pestControlDrugs.bnet.view.fragment

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pestControlDrugs.bnet.constant.BASE_URL
import com.pestControlDrugs.bnet.constant.DRUGS_LIST_FRAGMENT
import com.pestControlDrugs.bnet.constant.DRUGS_NAME
import com.pestControlDrugs.bnet.databinding.FragmentCurrentDrugsBinding
import com.pestControlDrugs.bnet.di.MyApplication
import com.pestControlDrugs.bnet.model.dataclass.AllDrugsItem
import com.pestControlDrugs.bnet.tools.picassoLoad
import com.pestControlDrugs.bnet.tools.showToast
import com.pestControlDrugs.bnet.view.activities.MainActivity
import com.pestControlDrugs.bnet.viewModel.CurrentDrugsViewModel
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import javax.inject.Inject

class CurrentDrugsFragment : Fragment() {

    private lateinit var binding: FragmentCurrentDrugsBinding
    private lateinit var mainActivity: MainActivity
    @Inject
    lateinit var sharedPreferences: SharedPreferences
    @Inject
    lateinit var currentDrugsViewModel: CurrentDrugsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding =  FragmentCurrentDrugsBinding.inflate(inflater, container, false)
        mainActivity = (requireActivity() as MainActivity)
        (mainActivity.application as MyApplication).appComponent.inject(this)
        /////////////////////Получаем карточку препарада, если переход был по карточке, а не по SearchView
        val drugsName = sharedPreferences.getString(DRUGS_NAME, "")!!
        if (drugsName.isNotEmpty()) {
            currentDrugsViewModel.getCurrentDrugs(sharedPreferences.getString(DRUGS_NAME, "").toString())
        }
        ////////////////////Выход на предыдущий экран
        binding.arrowBack.setOnClickListener {
            mainActivity.changeFragment(DRUGS_LIST_FRAGMENT)
        }
        currentDrugsViewModel.currentDrugs.observe(requireActivity()) {
            /////////////Делаем компоненты видимыми, убираем progressBar
            binding.progressBar.visibility = View.GONE
            binding.drugsName.visibility = View.VISIBLE
            binding.edit.visibility = View.VISIBLE
            binding.drugsPhoto.visibility = View.VISIBLE
            binding.drugsDescription.visibility = View.VISIBLE
            binding.imageDrugsType.visibility = View.VISIBLE
            binding.buttonPlaceOfPurchase.visibility = View.VISIBLE

            setDrugsData(it[0])
        }

        return binding.root
    }
    private fun setDrugsData(allDrugsItem: AllDrugsItem) {
        binding.drugsName.text = allDrugsItem.name
        binding.drugsDescription.text = allDrugsItem.description
        setDrugsPhoto(allDrugsItem.image)
        setDrugsIcon(allDrugsItem.categories.icon)
    }
    private fun setDrugsPhoto(image: String) {
        val url = BASE_URL + image
        picassoLoad(url, binding.drugsPhoto, requireContext())
    }
    private fun setDrugsIcon(icon: String) {
        val url = BASE_URL + icon
        picassoLoad(url, binding.imageDrugsType, requireContext())
    }

}