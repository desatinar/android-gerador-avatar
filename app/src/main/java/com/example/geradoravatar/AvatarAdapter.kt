package com.example.geradoravatar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class AvatarAdapter(
    private val onEditarClick: (Avatar) -> Unit,
    private val onDeletarClick: (Int) -> Unit
) : RecyclerView.Adapter<AvatarAdapter.ViewHolder>() {

    private var lista: List<Avatar> = emptyList()

    fun atualizarLista(novaLista: List<Avatar>) {
        lista = novaLista
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_avatar, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val avatar = lista[position]
        holder.txtPrompt.text = avatar.prompt

        val urlImagem = "http://10.0.2.2:5000/" + avatar.caminho_imagem

        Glide.with(holder.itemView.context)
            .load(urlImagem)
            .placeholder(android.R.drawable.ic_menu_gallery)
            .into(holder.imgAvatar)

        holder.btnEditar.setOnClickListener {
            onEditarClick(avatar)
        }

        holder.btnDeletar.setOnClickListener {
            avatar.id?.let { id -> onDeletarClick(id) }
        }
    }

    override fun getItemCount(): Int = lista.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgAvatar: ImageView = view.findViewById(R.id.imgAvatar)
        val txtPrompt: TextView = view.findViewById(R.id.txtPrompt)
        val btnEditar: Button = view.findViewById(R.id.btnEditar)
        val btnDeletar: Button = view.findViewById(R.id.btnDeletar)
    }
}