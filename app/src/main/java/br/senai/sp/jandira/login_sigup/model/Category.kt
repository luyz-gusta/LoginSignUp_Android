package br.senai.sp.jandira.login_sigup.model

import androidx.compose.ui.graphics.painter.Painter

data class Category(
    var id: Long = 0,
    var name: String = "",
    var icon: Painter? = null
)
