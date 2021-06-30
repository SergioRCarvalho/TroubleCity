package ipvc.estg.troublecity.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ipvc.estg.troublecity.R
import ipvc.estg.troublecity.Notas_detalhes
import ipvc.estg.room.entities.Notas
import ipvc.estg.troublecity.MainActivity
import ipvc.estg.troublecity.NotasActivity

const val ID = "ID"
const val DESCRICAO = "DESCRICAO"
const val TITULO = "TITULO"
const val HORA = "HORA"
const val DATA = "DATA"




class notasAdapter internal constructor(
    context: Context, private val callbackInterface: NotasActivity
) : RecyclerView.Adapter<notasAdapter.NotasViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var notas = emptyList<Notas>()


    class NotasViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val notasItemView: TextView = itemView.findViewById(R.id.descricao)
        val titulo: TextView = itemView.findViewById(R.id.titulo)
        val data: TextView = itemView.findViewById(R.id.data)
        val hora: TextView = itemView.findViewById(R.id.hora)
        val edit : RelativeLayout = itemView.findViewById(R.id.layoutEdit)
        val delete : ImageView = itemView.findViewById(R.id.Imagedelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotasViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return NotasViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: NotasViewHolder, position: Int) {
        val current = notas[position]
        holder.notasItemView.text = current.descric
        holder.titulo.text = current.titulo
        //holder.hora.text = current.hora
        holder.data.text = current.data
        val id: Int? = current.id

        //merge current
        holder.edit.setOnClickListener {
            val context = holder.notasItemView.context
            val titulo1 = holder.titulo.text.toString()
            val desc = holder.notasItemView.text.toString()
            val hora1 = holder.hora.text.toString()
            val data1 = holder.data.text.toString()
            val intent = Intent(context, Notas_detalhes::class.java).apply {
                putExtra(TITULO, titulo1)
                putExtra(DESCRICAO, desc)
                putExtra(ID, id)
                putExtra (HORA, hora1)
                putExtra(DATA, data1)
            }
            context.startActivity(intent)
        }

        holder.delete.setOnClickListener {
            callbackInterface.delete(current.id)
        }



    }
        internal fun setNotas(notas: List<Notas>) {
        this.notas = notas
        notifyDataSetChanged()
    }

    //teste commit
    override fun getItemCount() = notas.size
}