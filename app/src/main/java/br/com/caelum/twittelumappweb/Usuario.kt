package br.com.caelum.twittelumappweb

data class Usuario(val nome: String = "",
                   val username: String = "",
                   val senha: String = "",
                   val foto: String? = null,
                   val id: Long = 0)