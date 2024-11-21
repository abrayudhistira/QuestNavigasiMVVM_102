package com.umy.questnavigasimvvm_102.ui.view.viewmodel

import androidx.lifecycle.ViewModel
import com.umy.questnavigasimvvm_102.model.RencanaStudi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class RencanaStudyViewModel : ViewModel(){
    private val _krsState = MutableStateFlow(RencanaStudi())
    val krsStateUi: StateFlow<RencanaStudi> = _krsState.asStateFlow()

    fun setMataKuliah(mkPilihan: String) {
        _krsState.update { stateMk -> stateMk.copy(namaMK = mkPilihan) }
    }

    fun setKelas(kelasPilihan: String) {
        _krsState.update { stateKelas -> stateKelas.copy(kelas = kelasPilihan) }
    }

    fun saveDataKRS(ls: MutableList<String>) {
        _krsState.update { status -> status.copy(
            namaMK = ls[0],
            kelas = ls[1]
        ) }
    }

}