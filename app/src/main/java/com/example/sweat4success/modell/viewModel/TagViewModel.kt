package com.example.sweat4success.modell.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.sweat4success.database.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TagViewModel(application: Application): AndroidViewModel(application) {
    private val repository: TagRepository
    val readAllData: LiveData<List<TagDb>>

    init{
        val tagDao = TagDataBase.getDatabase(application).tagDao()
        repository = TagRepository(tagDao)
        readAllData = repository.readAllData
    }

    fun getAll(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAll()
        }
    }

    fun getById(id: Int): TagDb{
            return repository.findById(id)
    }

    fun addTag(tag: TagDb){
        viewModelScope.launch(Dispatchers.IO){
            repository.addTag(tag)
        }

    }

    fun deleteTag(tag: TagDb){
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(tag)
        }
    }
}