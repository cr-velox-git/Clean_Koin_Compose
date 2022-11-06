package com.phoenix.dikoin.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.phoenix.dikoin.data.dto.ApiResponse
import com.phoenix.dikoin.domain.repositories.MainRepository
import com.phoenix.dikoin.domain.usecase.GetHeadLineUseCase
import com.phoenix.dikoin.utils.Resource
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainViewModel(
    private val useCase: GetHeadLineUseCase, //contractor injection,
    //the view model only depends on abstraction ie MainRepository
    //sometime we might not want to actual call just simulation or test case

) : ViewModel() {

    private val _response: MutableLiveData<ApiResponse> = MutableLiveData()
    val response: LiveData<ApiResponse> get() = _response // this is life cycle aware

    //    private val _error:MutableLiveData<String> = MutableLiveData()
//    val error: LiveData<String> get() = _error
//    var error by mutableStateOf<UiErrorText?>(null)
//        private set


    init {
        Log.d("ViewModel","view model initiated")
        viewModelScope.launch {
            val res = useCase.invoke(1,"us")
            Log.d("ViewModel response",res.data.toString())

            when(res){
                is Resource.Error -> {

                }
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    _response.value = res.data!!
                }
            }

        }
    }

}