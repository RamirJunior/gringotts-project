package alura.com.gringotts.data

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [alura.com.gringotts.data.models.home.Transaction::class], version = 1)
abstract class AccountStatementDatabase : RoomDatabase() {
    abstract fun accountStatementDAO(): AccountStatementDAO

    companion object {
        fun provideDatabase(app: Application): AccountStatementDatabase =
            Room.databaseBuilder(
                app,
                AccountStatementDatabase::class.java,
                STATEMENT_DB_NAME
            )
                .build()

        private const val STATEMENT_DB_NAME = "account_statement_database"
    }
}
