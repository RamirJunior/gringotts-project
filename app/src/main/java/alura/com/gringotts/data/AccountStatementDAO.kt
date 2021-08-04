package alura.com.gringotts.data

import alura.com.gringotts.data.models.home.Transaction
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountStatementDAO {

    @Query("SELECT * FROM [transaction]")
    fun getAllTransactions(): Flow<List<Transaction>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransactions(transaction: List<Transaction>)

    @Query("DELETE FROM [transaction]")
    suspend fun deleteAllTransactions()

}
