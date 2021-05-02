package ipvc.estg.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notas_table")

class Notas(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "descric") val descric: String,
    @ColumnInfo(name = "data") val data: String,
    @ColumnInfo(name = "hora") val hora: String,
    @ColumnInfo(name = "titulo") val titulo: String
)