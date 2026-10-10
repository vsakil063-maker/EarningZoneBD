package com.example.microtask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // এই লাইনটি নিশ্চিত করবে যে অ্যাপ চালু হলে আপনার নতুন ডিজাইনটি দেখাবে
            MicroTaskScreen() 
        }
    }
}
