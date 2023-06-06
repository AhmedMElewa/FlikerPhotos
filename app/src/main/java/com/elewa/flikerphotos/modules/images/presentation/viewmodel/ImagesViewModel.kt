package com.elewa.flikerphotos.modules.images.presentation.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.elewa.flikerphotos.modules.images.domain.interactor.GetImagesUseCase
import com.elewa.flikerphotos.modules.images.presentation.model.ImagesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.fold
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class ImagesViewModel @Inject constructor(
    private val getImagesUseCase: GetImagesUseCase
) : ViewModel() {

    private val _imagesStateMutable = MutableStateFlow<ImagesState>(ImagesState())
    val imagesState: StateFlow<ImagesState>
        get() = _imagesStateMutable

    init {
        getImages()
    }

    fun getImages() {
        viewModelScope.launch(Dispatchers.IO) {
            _imagesStateMutable.emit(ImagesState(isLoading = true))
            try {
                getImagesUseCase.execute(null).cachedIn(viewModelScope).collect {
                    _imagesStateMutable.emit(ImagesState(data = it))
                }
            } catch (e: IOException) {
                _imagesStateMutable.emit(ImagesState(error = e))
            } catch (e: Exception) {
                _imagesStateMutable.emit(ImagesState(error = e))
            }

        }
    }


}