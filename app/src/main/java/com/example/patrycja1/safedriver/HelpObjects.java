package com.example.patrycja1.safedriver;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Patrycja1 on 22.05.2018.
 */

public enum HelpObjects {

    KROK1(1,"Sprawdź czy poszkowodany jest przytomny. Podejdź ostrożnie do poszkodowanego, delikatnie potrząśnij za ramiona i głośno zapytaj:Czy wszystko w porządku?",R.drawable.vitalsigns," KROK 1 "),
    KROK2(2,"Oceń czy poszkowodwany oddycha. Oceń prawidłowość oddechu wzrokiem, słuchem i czuciem. Pochyl się nad poszkodowanym, tak by wyczuć ruch wydychanego powietrza, jednocześnie obserwując ruchy klatki piersiowej.",R.drawable.push," KROK 2 "),
    KROK3(3,"Wykonaj 30 uciśnięć klatki piersiowej (splecione, wyprostowane w łokciach ręce ułuż na środku, uciskaj w tempie co najmniej 100 uciśnięć na minutę, tak aby ugięła się ona na głębokość co najmniej 5 cm) ",R.drawable.breathgive," KROK 3 "),
    KROK4(4,"Po wykonaniu 30 uciśnięć klatki piersiowej, wykonaj 2 oddechy ratownicze. Szczelnie obejmij swoimi ustami usta poszkodowanego i wdmuchnij tyle powietrza, aby uniosła sięklatka pierdsiowa poszkodowanego. ",R.drawable.breath," KROK 4 "),
    KROK5(5,"Ułóż poszkodowanego w bezpiecznej pozycji i poczekaj na lekarza",R.drawable.side," KROK 5 ");


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
