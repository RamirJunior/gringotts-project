package alura.com.gringotts.data

import alura.com.gringotts.data.models.home.TransactionListItem
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountStatementDAO {

    @Query("SELECT * FROM transactions")
    fun getAllTransactions(): Flow<List<TransactionListItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(restaurants: List<TransactionListItem>)

    @Query("DELETE FROM transactions")
    suspend fun deleteAllTransactions()

}
