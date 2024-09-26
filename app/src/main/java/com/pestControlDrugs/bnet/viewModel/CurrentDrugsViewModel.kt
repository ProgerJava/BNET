package com.pestControlDrugs.bnet.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pestControlDrugs.bnet.model.CurrentDrugsModel
import com.pestControlDrugs.bnet.model.dataclass.AllDrugs
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

class CurrentDrugsViewModel @Inject constructor(private val currentDrugsModel: CurrentDrugsModel): ViewModel() {

    val currentDrugs = MutableLiveData<AllDrugs>()

    fun getCurrentDrugs(drugsName: String) {
        CoroutineScope(Dispatchers.Main).launch {
            currentDrugs.value = async {currentDrugsModel.getCurrentDrugs(drugsName)}.await()
        }
    }

}
class CurrentDrugsFactory @Inject constructor(private val currentDrugsModel: CurrentDrugsModel) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CurrentDrugsViewModel(currentDrugsModel) as T
    }

}