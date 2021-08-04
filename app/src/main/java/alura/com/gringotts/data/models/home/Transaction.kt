package alura.com.gringotts.data.models.home

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "transaction")
data class Transaction(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val status: String,
    val time: String,
    val type: String,
    val date: String,
    val value: String,
    @SerializedName("type_description") val typeDescription: String
)
