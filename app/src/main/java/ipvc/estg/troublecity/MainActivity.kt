package ipvc.estg.troublecity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
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




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    fun goToNotas(view: View) {
         val intent = Intent(this, NotasActivity::class.java)
         startActivity(intent)
    }
    fun goToLogin(view: View) {
         val intent = Intent(this, LoginActivity::class.java)
         startActivity(intent)
    }


   /* override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.note_details_menu, menu)
        return true
    }*/


}


