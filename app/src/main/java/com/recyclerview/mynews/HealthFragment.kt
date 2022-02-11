package com.recyclerview.mynews

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_business.*
import kotlinx.android.synthetic.main.fragment_health.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HealthFragment : Fragment() {
    private lateinit var newsAdapter: NewsAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        getnews()
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_health, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    }

    private fun getnews() {
        val news = NewsApi.newsService.newsInstance.getCategoryHeadlines("in",
            "health",30, 1)
        news.enqueue(object : Callback<MainNews?> {
            override fun onResponse(call: Call<MainNews?>, response: Response<MainNews?>) {
                val newss = response.body()
                if (newss!=null){
                    newsAdapter = NewsAdapter(
                        this@HealthFragment, newss.articles as ArrayList<Articles>
                    )
                    healthRecyclerView.adapter = newsAdapter
                    healthRecyclerView.layoutManager = LinearLayoutManager(activity)
                    newsAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<MainNews?>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}