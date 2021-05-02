package ipvc.estg.troublecity

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import ipvc.estg.troublecity.adapters.*
import ipvc.estg.room.entities.Notas
import ipvc.estg.room.viewModel.NotasViewModel
import java.text.SimpleDateFormat
import java.util.*


class Notas_detalhes : AppCompatActivity() {

    private lateinit var descText: EditText
    private lateinit var dataText: TextView
    private lateinit var horaText: TextView
    private lateinit var tituloText: EditText
    private lateinit var notasViewModel: NotasViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notas_detalhes)

        val editTitulo = intent.getStringExtra(TITULO)
        val editDescricao = intent.getStringExtra(DESCRICAO)
        val getData = intent.getStringExtra(DATA)
        val getHora = intent.getStringExtra(HORA)


        findViewById<EditText>(R.id.add_descricao).setText(editDescricao)
        findViewById<EditText>(R.id.add_titulo).setText(editTitulo)
        findViewById<TextView>(R.id.data).setText(getData)
        findViewById<TextView>(R.id.hora).setText(getHora)

        notasViewModel = ViewModelProvider(this).get(NotasViewModel::class.java)

        descText = findViewById(R.id.add_descricao)
        dataText = findViewById(R.id.data)
        tituloText = findViewById(R.id.add_titulo)
        horaText = findViewById(R.id.hora)





        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {

            var message = intent.getIntExtra(ID, 0)
            val replyIntent = Intent()
            if(TextUtils.isEmpty((descText.text)) || TextUtils.isEmpty((tituloText.text))){

                if(TextUtils.isEmpty((descText.text)) && !TextUtils.isEmpty((tituloText.text))){
                    descText.error = getString(R.string.aviso_desc)
                }
                if(!TextUtils.isEmpty((descText.text)) && TextUtils.isEmpty((tituloText.text))){
                    tituloText.error = getString(R.string.aviso_titulo)
                }
                if(TextUtils.isEmpty((descText.text)) && TextUtils.isEmpty((tituloText.text))){
                    descText.error = getString(R.string.aviso_desc)
                    tituloText.error = getString(R.string.aviso_titulo)
                }
            }

            else if (message != 0)
            {

                val nota = Notas(
                        id = message,
                        descric = descText.text.toString(),
                        data = dataText.text.toString(),
                        hora = horaText.text.toString(),
                        titulo = tituloText.text.toString())
                notasViewModel.updateNotas(nota)
                finish()

            }
            else {
              //  val dialogBuilder = AlertDialog.Builder(this)

                // set message of alert dialog
             //   dialogBuilder.setMessage("Do you want to close this application ?")
                        // if the dialog is cancelable
                       // .setCancelable(false)
                        // positive button text and action
                       // .setPositiveButton("Proceed", DialogInterface.OnClickListener {
                        //    dialog, id -> finish()
                       // })
                        // negative button text and action
                      //  .setNegativeButton("Cancel", DialogInterface.OnClickListener {
                    //        dialog, id -> dialog.cancel()
                  //      })

                // create dialog box
                // val alert = dialogBuilder.create()
                // set title for alert dialog box
               // alert.setTitle("AlertDialogExample")
                // show alert dialog
              //  alert.show()
                val CurrentTime: TextView = findViewById(R.id.data)
                val sdf = SimpleDateFormat("dd-MM-yyyy HH:mm")
                val currentDateandTime: String = sdf.format(Date())
                CurrentTime.text = currentDateandTime

                replyIntent.putExtra(EXTRA_REPLY_DESCRIC, descText.text.toString())
                replyIntent.putExtra(EXTRA_REPLY_DATA, CurrentTime.text.toString())
                replyIntent.putExtra(EXTRA_REPLY_HORA, horaText.text.toString())
                replyIntent.putExtra(EXTRA_REPLY_TITULO, tituloText.text.toString())
                setResult(Activity.RESULT_OK, replyIntent)
                finish()

            }
        }



    }





    companion object {
        const val EXTRA_REPLY_DESCRIC = "com.example.android.descric"
        const val EXTRA_REPLY_DATA = "com.example.android.data"
        const val EXTRA_REPLY_HORA = "com.example.android.hora"
        const val EXTRA_REPLY_TITULO = "com.example.android.titulo"



    }
}