package com.solutionsjs.masterremotetest.domain

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solutionsjs.masterremotetest.data.Repository
import com.solutionsjs.masterremotetest.data.RepositoryImpl
import com.solutionsjs.masterremotetest.data.model.Member
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MasterViewModel : ViewModel() {
    private val repository: Repository
    private val _membersLiveData = MutableLiveData<List<Member>>()
    val membersLiveData: LiveData<List<Member>> = _membersLiveData

    init {
        repository = RepositoryImpl()
    }

    fun login(username: String, password: String, context: Context) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.login(username, password, context)
        }

    fun getMembers() {
        viewModelScope.launch {
            val members = withContext(Dispatchers.IO) {
                repository.getMembers()
            }
            _membersLiveData.postValue(members)
        }
    }
}
