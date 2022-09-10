package com.example.foody

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.foody.databinding.ActivityMainBinding
import com.example.foody.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    lateinit var b:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)
        supportActionBar?.hide()
    /*val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this,viewModelFactory)[MainViewModel::class.java]
        viewModel.getAllFood(getString(R.string.apiKey))
        viewModel.response.observe(this, Observer {
            it.enqueue(object :Callback<Root>{
                *//* override fun onResponse(call: Call<List<Root>>, response: Response<List<Root>>) {
                    b.textview.text =response.body()!![0].totalResults.toString()
                }

                override fun onFailure(call: Call<List<Root>>, t: Throwable) {
                    b.textview.text = t.message.toString()
                }
*//*
                *//*override fun onResponse(call: Call<RootCat>, response: Response<RootCat>) {
                    b.textview.text = response.body()!!.categories?.get(0)?.strCategory.toString()
                }

                override fun onFailure(call: Call<RootCat>, t: Throwable) {
                    b.textview.text = t.message.toString()
                }*//*
                override fun onResponse(call: Call<Root>, response: Response<Root>) {
                    Log.d("result",response.body()?.searchResults?.get(0)?.totalResults.toString())
                    b.textview.text = response.body()?.searchResults?.get(0)?.results?.get(0)?.name
                }

                override fun onFailure(call: Call<Root>, t: Throwable) {
                    b.textview.text = t.message.toString()
                }


            })
        })*/
    }
}