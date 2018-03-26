package hash.application

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
    }

    override fun onStart(){
        super.onStart()
        val query = intent.extras.getString("query")


        val players = arrayOf("Lionel Messi", "Christiano Ronaldo", "Neymar", "Gareth Bale")
        val adapter : ArrayAdapter<String> = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, players)
        listView1.adapter = adapter
    }
}
