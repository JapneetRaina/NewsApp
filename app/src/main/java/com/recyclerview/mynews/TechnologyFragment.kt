package com.recyclerview.mynews

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_business.*
import kotlinx.android.synthetic.main.fragment_technology.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TechnologyFragment : Fragment() {
    private lateinit var newsAdapter: NewsAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        getnews()
        return inflater.inflate(R.layout.fragment_technology, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    }

    private fun getnews() {
        val news = NewsApi.newsService.newsInstance.getCategoryHeadlines(
            "in",
            "technology", 30, 1
        )
        news.enqueue(object : Callback<MainNews?> {
            override fun onResponse(call: Call<MainNews?>, response: Response<MainNews?>) {
                val newss = response.body()
                if (newss != null) {
                    newsAdapter = NewsAdapter(
                        this@TechnologyFragment, newss.articles as ArrayList<Articles>
                    )
                    techRecyclerView.adapter = newsAdapter
                    techRecyclerView.layoutManager = LinearLayoutManager(activity)
                    newsAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<MainNews?>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}