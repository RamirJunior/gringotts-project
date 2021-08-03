package alura.com.gringotts.data

import alura.com.gringotts.data.models.home.TransactionListItem
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TransactionListItem::class], version = 1)
abstract class AccountStatementDatabase : RoomDatabase() {
        abstract fun AccountStatementDAOD(): AccountStatementDAO
}
