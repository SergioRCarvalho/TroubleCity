package ipvc.estg.troublecity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ipvc.estg.room.entities.Notas
import ipvc.estg.room.viewModel.NotasViewModel
import ipvc.estg.troublecity.adapters.notasAdapter

class MainActivity : AppCompatActivity() {


    private lateinit var notasViewModel: NotasViewModel
    private val newWordActivityRequestCode = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


//testemerg
        // recycler view
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = notasAdapter(this,this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(this,2)

        // view model
        notasViewModel = ViewModelProvider(this).get(NotasViewModel::class.java)
        notasViewModel.allNotas.observe(this, Observer { notas ->
            // Update the cached copy of the words in the adapter.
            notas?.let { adapter.setNotas(it) }
        })

        //Fab
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, Notas_detalhes::class.java)
           startActivityForResult(intent, newWordActivityRequestCode)
      }

    }



    fun delete(id : Int?){
        notasViewModel.delete(id)


    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == newWordActivityRequestCode && resultCode == Activity.RESULT_OK) {

            val pdescric = data?.getStringExtra(Notas_detalhes.EXTRA_REPLY_DESCRIC)
            val pdata = data?.getStringExtra(Notas_detalhes.EXTRA_REPLY_DATA)
            val phora = data?.getStringExtra(Notas_detalhes.EXTRA_REPLY_HORA)
            val ptitulo = data?.getStringExtra(Notas_detalhes.EXTRA_REPLY_TITULO)



            if (pdescric!= null || pdata!=null || phora!=null || ptitulo !=null ) {
                val notas = Notas(descric= pdescric!!, data=pdata!! , hora=phora!!, titulo = ptitulo!! )
                notasViewModel.insert(notas)
            }

        }
    }
   /* override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.note_details_menu, menu)
        return true
    }*/


}


