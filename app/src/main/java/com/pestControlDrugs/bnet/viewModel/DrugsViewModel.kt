package com.pestControlDrugs.bnet.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pestControlDrugs.bnet.model.DrugsListModel
import com.pestControlDrugs.bnet.model.dataclass.AllDrugs
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

class DrugsViewModel @Inject constructor(private val drugsListModel: DrugsListModel): ViewModel() {

    val allDrugs = MutableLiveData<AllDrugs>()

    fun getAllDrugs () {
        CoroutineScope(Dispatchers.Main).launch {
            allDrugs.value = async {drugsListModel.getAllDrugs()}.await()
        }
    }

}

class DrugsFactory @Inject constructor(private val drugsListModel: DrugsListModel) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DrugsViewModel(drugsListModel) as T
    }

}