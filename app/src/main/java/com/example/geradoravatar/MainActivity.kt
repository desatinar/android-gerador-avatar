package com.example.geradoravatar

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:5000/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api = retrofit.create(AvatarApiService::class.java)
    private lateinit var adapter: AvatarAdapter

    private var idParaEditar: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etPrompt = findViewById<EditText>(R.id.etPrompt)
        val btnAcao = findViewById<Button>(R.id.btnCriar)
        val rvAvatares = findViewById<RecyclerView>(R.id.rvAvatares)

        adapter = AvatarAdapter(
            onEditarClick = { avatar ->
                etPrompt.setText(avatar.prompt)
                idParaEditar = avatar.id
                btnAcao.text = "SALVAR ALTERAÇÃO"
            },
            onDeletarClick = { id ->
                deletarAvatar(id)
            }
        )

        rvAvatares.layoutManager = LinearLayoutManager(this)
        rvAvatares.adapter = adapter

        btnAcao.setOnClickListener {
            val texto = etPrompt.text.toString()
            if (texto.isNotEmpty()) {
                if (idParaEditar == null) {
                    // Se não tem ID, é criação nova
                    criarAvatar(texto)
                } else {
                    editarAvatar(idParaEditar!!, texto)

                    idParaEditar = null
                    btnAcao.text = "CRIAR AVATAR"
                }

                etPrompt.text.clear()
                val imm = getSystemService(INPUT_METHOD_SERVICE) as android.view.inputmethod.InputMethodManager
                imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
            }
        }

        carregarDados()
    }

    private fun carregarDados() {
        api.listarAvatares().enqueue(object : Callback<List<Avatar>> {
            override fun onResponse(call: Call<List<Avatar>>, response: Response<List<Avatar>>) {
                if (response.isSuccessful) {
                    adapter.atualizarLista(response.body() ?: emptyList())
                }
            }
            override fun onFailure(call: Call<List<Avatar>>, t: Throwable) {
                Toast.makeText(applicationContext, "Erro ao listar", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun criarAvatar(prompt: String) {
        Toast.makeText(this, "Criando...", Toast.LENGTH_SHORT).show()
        api.criarAvatar(AvatarRequest(prompt)).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    Toast.makeText(applicationContext, "Sucesso!", Toast.LENGTH_SHORT).show()
                    carregarDados()
                }
            }
            override fun onFailure(call: Call<Void>, t: Throwable) {}
        })
    }

    private fun editarAvatar(id: Int, novoPrompt: String) {
        Toast.makeText(this, "Atualizando...", Toast.LENGTH_SHORT).show()
        api.editarAvatar(id, AvatarRequest(novoPrompt)).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    Toast.makeText(applicationContext, "Atualizado!", Toast.LENGTH_SHORT).show()
                    carregarDados()
                }
            }
            override fun onFailure(call: Call<Void>, t: Throwable) {}
        })
    }

    private fun deletarAvatar(id: Int) {
        Toast.makeText(this, "Deletando...", Toast.LENGTH_SHORT).show()
        api.deletarAvatar(id).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    Toast.makeText(applicationContext, "Deletado!", Toast.LENGTH_SHORT).show()

                    if (idParaEditar == id) {
                        idParaEditar = null
                        findViewById<Button>(R.id.btnCriar).text = "CRIAR AVATAR"
                        findViewById<EditText>(R.id.etPrompt).text.clear()
                    }
                    carregarDados()
                }
            }
            override fun onFailure(call: Call<Void>, t: Throwable) {}
        })
    }
}