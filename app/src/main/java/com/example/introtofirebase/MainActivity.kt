package com.example.introtofirebase

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    val etWriteHere by lazy { findViewById<EditText>(R.id.editText) }
    val btnSend by lazy { findViewById<Button>(R.id.button) }
    val btnReadData by lazy { findViewById<Button>(R.id.button2) }
    val tvMessage by lazy { findViewById<TextView>(R.id.textView) }


    //firebase database
    val mFbDatabase by lazy { FirebaseDatabase.getInstance() }
    val mRef by lazy { mFbDatabase.reference }
    val mRefLogin by lazy { mFbDatabase.getReference("login") }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btnSend.setOnClickListener({ runcode() })
        btnReadData.setOnClickListener({ readData() })


//        //this trigger on every database update
//        mRefLogin.child("user1").child("pass").addValueEventListener(object : ValueEventListener {
//
//            override fun onCancelled(p0: DatabaseError) {
//
//            }
//
//            override fun onDataChange(p0: DataSnapshot) {
//
//                var data = p0.value.toString()
//                textView.setText(data)
//
//            }
//
//        })


    }

    private fun readData() {

        //
//        //this trigger on every database update
//        mRefLogin.child("user1").child("pass").addValueEventListener(object : ValueEventListener {
//
//            override fun onCancelled(p0: DatabaseError) {
//
//            }
//
//            override fun onDataChange(p0: DataSnapshot) {
//
//                var data = p0.value.toString()
//                textView.setText(data)
//
//            }
//
//        })


        //this trigger only once
        mRefLogin.child("user1").child("pass")
            .addListenerForSingleValueEvent(object : ValueEventListener {

                override fun onCancelled(p0: DatabaseError) {

                }

                override fun onDataChange(p0: DataSnapshot) {

                    var data = p0.value.toString()
                    textView.setText(data)

                }

            })


    }

    private fun runcode() {

        var data = etWriteHere.text.toString()
        mRefLogin.child("user1").child("pass").setValue(data).addOnCompleteListener(object :
            OnCompleteListener<Void> {
            override fun onComplete(p0: Task<Void>) {

                Toast.makeText(this@MainActivity, "Data updated successfully", Toast.LENGTH_LONG)
                    .show()


            }
        }).addOnFailureListener {

            Toast.makeText(this@MainActivity, "Data updated Failed", Toast.LENGTH_LONG).show()
        }


    }


}
