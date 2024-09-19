package com.example.dispatcher.presentation.filter.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.dispatcher.domain.repository.sources.ISourcesRepository
import com.example.dispatcher.domain.repository.sources.SourcesRepositoryImpl
import com.example.dispatcher.presentation.filter.view.model.EnumFilter
import kotlinx.coroutines.launch

class FilterViewModel(application: Application) : AndroidViewModel(application) {
    private val sourcesRepository: ISourcesRepository = SourcesRepositoryImpl()

    private val _filterChoose = MutableLiveData<Boolean>()
    val filterChoose: LiveData<Boolean> get() = _filterChoose

    private val _sources = MutableLiveData<List<String>>()
    val sources: LiveData<List<String>> get() = _sources

    private var filterType : EnumFilter = EnumFilter.SEARCHIN

    init {
        _filterChoose.value = false
    }

    fun setFilterChoose(value: Boolean) {
        _filterChoose.value = value
    }

    fun getFilterType(): EnumFilter {
        return filterType
    }

    fun setFilterType(type: EnumFilter) {
        filterType = type
    }

    fun fetchSources(filterType:EnumFilter) {
        if (filterType == EnumFilter.SOURCES) {
            viewModelScope.launch {
                try {
                    val sourceNames = sourcesRepository.getSourceNames()
                    _sources.postValue(sourceNames)
                } catch (e: Exception) {
                }
            }
        }
        else{
            val sourceNames = listOf("everything", "top-headlines")
                _sources.postValue(sourceNames)
        }
    }

}
