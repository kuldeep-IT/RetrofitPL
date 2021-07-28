package com.peerbitskuldeep.retrofitpl

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.peerbitskuldeep.retrofitpl.adapter.RecAdapter
import com.peerbitskuldeep.retrofitpl.databinding.ActivityMainBinding
import com.peerbitskuldeep.retrofitpl.databinding.ItemTodoBinding
import com.peerbitskuldeep.retrofitpl.retrofit.RetrofitInstance
import com.peerbitskuldeep.retrofitpl.retrofit.TodoApi
import retrofit2.HttpException
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var recAdapter: RecAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        recAdapter = RecAdapter()
        binding.recyclerView.apply {

            adapter = recAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)

        }


        lifecycleScope.launchWhenCreated {
            binding.progressBar.isVisible = true
            val response = try {

                RetrofitInstance.api.getTodos()

            } catch (e: IOException) {
                Toast.makeText(
                    this@MainActivity,
                    "Error! Please check interner connectivity",
                    Toast.LENGTH_SHORT
                ).show()
                Log.d("TAG", "Error! Please check interner connectivity")
                binding.progressBar.isVisible = false
                return@launchWhenCreated
            } catch (e: HttpException) {
                Toast.makeText(
                    this@MainActivity,
                    "HttpException, unexpected response",
                    Toast.LENGTH_SHORT
                ).show()
                Log.d("TAG", "Error! HttpException, unexpected response")
                binding.progressBar.isVisible = false
                return@launchWhenCreated
            }

            if (response.isSuccessful && response.body() != null) {
                recAdapter.todos = response.body()!!
            } else {
                Log.d("TAG", "Response not successful")
            }
            binding.progressBar.isVisible = false

        }



        setContentView(binding.root)
    }
}