package com.solutionsjs.masterremotetest.data

import android.content.Context
import com.solutionsjs.masterremotetest.data.model.Member

interface Repository {
    suspend fun getMembers(): List<Member>
    suspend fun login(username: String, password: String, context: Context)
}
