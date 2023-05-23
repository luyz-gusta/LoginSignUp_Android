package br.senai.sp.jandira.login_sigup.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.senai.sp.jandira.login_sigup.model.User

@Database(entities = [User::class], version = 1)
// abstract - nao permite que outras classes a herdam
abstract class TripDb : RoomDatabase() {

    abstract fun userDao(): UserDao

    //tudo que estiver dentro do companion object esta estatico
    companion object {
        //lateinit - inicialização preguiçosa
        private lateinit var instanceDb: TripDb

        //vai retornar uma instancia do meu banco
        fun getDatabase(context: Context): TripDb {

            // :: =  entregar uma instancia do objeto, me devolve um false se não existir nada

            //se isso for verdade
            if (!::instanceDb.isInitialized) {
                //.databaseBuilder =  criar um banco de dados
                // criar a instancia
                instanceDb = Room
                    .databaseBuilder(
                        context, //contexto da minha aplicação
                        TripDb::class.java, //ja esta criando automaticamente a instancia
                        "db_trip" // nome do banco
                    ).allowMainThreadQueries().build()
            }
            return instanceDb
        }


    }


}