package com.example.trashreport.DbRoomARemplacer;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CharacterDao {

    @Query("Select charUid from character where ownerUid=ownerUid")
    List<Integer> getCharactersofUser(int ownerUid);

}
