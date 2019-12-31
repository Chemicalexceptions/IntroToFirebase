package com.example.introtofirebase

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {


    val etWriteHere by lazy { findViewById<EditText>(R.id.editText) }
    val btnSend by lazy { findViewById<Button>(R.id.button) }
    val btnReadData by lazy { findViewById<Button>(R.id.button2) }
    val tvMessage by lazy { findViewById<TextView>(R.id.textView) }


    //firebase database
    val mFbDatabase by lazy { FirebaseDatabase.getInstance() }
    val mRef by lazy { mFbDatabase.reference }
    val mRefLogin by lazy { mFbDatabase.getReference("login") }


    lateinit var  mValueEventListener : ValueEventListener

    val TAG = javaClass.name


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btnSend.setOnClickListener({ runcode() })
        btnReadData.setOnClickListener({ readData() })


        mValueEventListener = (object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {



                for (item in p0.children){

                    var map = item.value as Map<String,Object>
                    Log.e(TAG,"onDataChanged name: ${map.get("name")}")
                    Log.e(TAG,"onDataChanged pass: ${map.get("pass")}")
                    Log.e(TAG,"onDataChanged key: ${item.key}")

                }


//                var map = p0.value as Map<String,Object>
//                var data = p0.value.toString()
//                textView.setText("${map.get("name").toString()} ${map.get("pass").toString()}")

            }
        })


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
        mRefLogin//.child("pass")
            .addListenerForSingleValueEvent(mValueEventListener)


    }

    private fun runcode() {

        var data = etWriteHere.text.toString()

//        //inserting data using push method in firebase
//        var key = mRefLogin.push().key!!
//        mRefLogin.child(key).child("name").setValue("Ankit Roy")
//        mRefLogin.child(key).child("pass").setValue("cbsl@123")
//

        //Batch update using map

//        var map = HashMap<String,Any>()
//        map.put("-LxQkoGFABD0D7PWQ78O/name","Mayank")
//        map.put("-LxQkoGFABD0D7PWQ78O/pass","Thailand")
//
//        mRefLogin.updateChildren(map)

        //for removing child

        //both ways we can delete nodes
      // var task: Task<Void> = mRefLogin.child("user1").removeValue() // option 1
//       var task: Task<Void> = mRefLogin.child("user1").setValue(null) //option 2
//       task.addOnCompleteListener(object : OnCompleteListener<Void> {
//           override fun onComplete(p0: Task<Void>) {
//
//               Toast.makeText(this@MainActivity,"Task removed successfully",Toast.LENGTH_LONG).show()
//
//
//           }
//
//       })





//        mRefLogin.child("user1").child("pass").setValue(data).addOnCompleteListener(object :
//            OnCompleteListener<Void> {
//            override fun onComplete(p0: Task<Void>) {
//
//                Toast.makeText(this@MainActivity, "Data updated successfully", Toast.LENGTH_LONG)
//                    .show()
//
//
//            }
//        }).addOnFailureListener {
//
//            Toast.makeText(this@MainActivity, "Data updated Failed", Toast.LENGTH_LONG).show()
//        }


    }

    override fun onDestroy() {
        super.onDestroy()
        mRefLogin.child("user1").child("pass").removeEventListener(mValueEventListener)
    }
}
