package com.example.trashreport.DbRoomARemplacer;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "character")
public class Character {

    @ColumnInfo(name="charUid")
    @PrimaryKey(autoGenerate = true)
    private int charuid;

    @ColumnInfo(name= "ownerUid")
    private int owneruid;

    @ColumnInfo(name= "characterName")
    private String characterName;

    @ColumnInfo(name= "class")
    private String characterClass;

}
