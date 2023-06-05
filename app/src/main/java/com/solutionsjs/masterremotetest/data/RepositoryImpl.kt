package com.solutionsjs.masterremotetest.data

import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.solutionsjs.masterremotetest.data.model.Member
import com.solutionsjs.masterremotetest.ui.MainActivity
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class RepositoryImpl : Repository {
    private val db: DatabaseReference = FirebaseDatabase.getInstance().reference
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    override suspend fun getMembers(): List<Member> = suspendCoroutine { continuation ->
        val membersRef = db.child("users")
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val members = mutableListOf<Member>()
                for (memberSnapshot in snapshot.children) {
                    val member = memberSnapshot.getValue(Member::class.java)
                    member?.let {
                        members.add(it)
                    }
                }
                continuation.resume(members)
            }

            override fun onCancelled(error: DatabaseError) {
                continuation.resumeWithException(error.toException())
            }
        }
        membersRef.addListenerForSingleValueEvent(listener)
    }

    override suspend fun login(username: String, password: String, context: Context) {
        val email = "$username@gmail.com"
        val senha = "${password}admin"

        auth.signInWithEmailAndPassword(email, senha)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val intent = Intent(context, MainActivity::class.java)
                    context.startActivity(intent)
                }
            }
            .addOnFailureListener {
                Toast.makeText(context, "A conexão não foi concluída!", Toast.LENGTH_SHORT).show()
            }
    }
}
