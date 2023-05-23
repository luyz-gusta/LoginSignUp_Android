package br.senai.sp.jandira.login_sigup.repository

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import br.senai.sp.jandira.login_sigup.R
import br.senai.sp.jandira.login_sigup.model.Category

class CategoryRepository {
    companion object {
        @Composable
        fun getCategories(): List<Category> {
            return listOf(
                Category(
                    id = 1,
                    name = "Montaine",
                    icon = painterResource(id = R.drawable.mountains)
                ),
                Category(
                    id = 2,
                    name = "Beach",
                    icon = painterResource(id = R.drawable.beach)
                ),
                Category(
                    id = 3,
                    name = "Shopping",
                    icon = painterResource(id = R.drawable.shopping)
                ),
                Category(
                    id = 1,
                    name = "Ski",
                    icon = painterResource(id = R.drawable.ski)
                )
            )
        }
    }

}