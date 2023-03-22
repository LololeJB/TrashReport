package com.example.trashreport.DbRoomARemplacer;

public class GlobalVariables {
    private static GlobalVariables mInstance= null;

    private int userid;

    private int characterid;

    public int getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public int getCharacterid() {
        return characterid;
    }

    public void setCharacterid(int characterid) {
        this.characterid = characterid;
    }

    public int getStatsid() {
        return statsid;
    }

    public void setStatsid(int statsid) {
        this.statsid = statsid;
    }

    private int statsid;

    protected GlobalVariables(){}


    public static synchronized GlobalVariables getInstance() {
        if(null == mInstance){
            mInstance = new GlobalVariables();
        }
        return mInstance;
    }
}
