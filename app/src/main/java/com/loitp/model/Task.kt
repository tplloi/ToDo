package com.loitp.model

import androidx.annotation.Keep
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.core.base.BaseModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Keep
@Entity(tableName = "Task")
class Task : BaseModel() {
    @ColumnInfo(name = "title")
    @SerializedName("title")
    @Expose
    var title: String = ""

    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    @Expose
    var id: String = ""

    @ColumnInfo(name = "isComplete")
    @SerializedName("isComplete")
    @Expose
    var isComplete: Boolean = false

    @ColumnInfo(name = "description")
    @SerializedName("description")
    @Expose
    var description: String = ""
}
