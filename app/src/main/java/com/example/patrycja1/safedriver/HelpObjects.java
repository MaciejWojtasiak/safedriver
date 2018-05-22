package com.example.patrycja1.safedriver;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Patrycja1 on 22.05.2018.
 */

public enum HelpObjects {

    KROK1(1," ",R.drawable.vitalsigns," KROK 1 "),
    KROK2(2," ",R.drawable.breath," KROK 2 "),
    KROK3(3," ",R.drawable.breathgive," KROK 3 "),
    KROK4(4," ",R.drawable.push," KROK 4 "),
    KROK5(5," ",R.drawable.side," KROK 5 ");


    private int id;
    private String instruction;
    private int helpImageId;
    private String info;


    HelpObjects( int id,String instruction,int helpImageId,String info ){
        this.id=id;
        this.instruction=instruction;
        this.helpImageId=helpImageId;
        this.info=info;
    }

    public int getId() {
        return id;
    }

    public String getInstruction() {
        return instruction;
    }

    public String getInfo() {
        return info;
    }

    public int gethelpImageId() {
        return helpImageId;
    }

    public static HelpObjects findHelpById(int identify){
        for (HelpObjects helpObjects:HelpObjects.values()){
            if(helpObjects.getId()==identify){
                return helpObjects;
            }
        }
        return null;
    }
}
