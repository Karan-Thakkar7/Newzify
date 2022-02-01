package com.example.newzify

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newzify.data.News
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
const val Tag="MainActivity"
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val news = NewsService.NewsInstance.getHeadlines("in", 1)
        news.enqueue(object : Callback<News> {
            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d(Tag,"some error occour",t)
            }

            override fun onResponse(call: Call<News>, response: Response<News>) {
                val News=response.body()
                if (News != null) {
                    RecyclerView.layoutManager=LinearLayoutManager(this@MainActivity)
                    RecyclerView.adapter=NewsAdapter(this@MainActivity,News.articles,
                        object : NewsAdapter.OnClicked {

                            override fun OnitemClicked(position: Int, url: String) {
                                startActivity(Intent(Intent.ACTION_VIEW,Uri.parse(url)))

                            }

                        })

                }
            }
        })
    }
}
