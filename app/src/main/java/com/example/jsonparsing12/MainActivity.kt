package com.example.jsonparsing12

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.task8.Model
import com.example.task8.MyAdapter
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity()
{
    lateinit var listView: ListView
    lateinit var list:MutableList<Model>

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.list)
        list = ArrayList()

        //StringRequest
        var stringRequest = StringRequest(
                Request.Method.GET,"http://igeniusdev.com/demoapi/demojson.json",
            {
                    response->
                try
                {
                   var jsonobject = JSONObject(response)
                   var jsonArray = jsonobject.getJSONArray("data")

                    for(i in 0 until jsonArray.length())
                    {


                        var jsonObject2 = jsonArray.getJSONObject(i)

                        var name = jsonObject2.getString("name")
                        var categoryname = jsonObject2.getString("categoryname")
                        var image = jsonObject2.getString("imagejsonurl")

                        Log.d("Tops1234",response.toString())

                        var m = Model()
                        m.name=name
                        m.categoryname=categoryname
                        m.imagejsonurl=image
                        list.add(m)

                    }
                    var myAdapter = MyAdapter(applicationContext,list)
                    listView.adapter=myAdapter


                }
                catch(e:Exception)
                {
                    e.printStackTrace()
                }

                var myAdapter = MyAdapter(applicationContext,list)
                listView.adapter=myAdapter
            },
            {
                Toast.makeText(applicationContext,"No Internet", Toast.LENGTH_LONG).show()
            })

        var queue: RequestQueue = Volley.newRequestQueue(this)
        queue.add(stringRequest)


    }
}