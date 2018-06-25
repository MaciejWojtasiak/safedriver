package com.example.patrycja1.safedriver;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

/**
 * This class is a collection of first aid instructions available.
 * Here you will find, among others, field getters of each of the objects and the method returning the object with the given id number.
 * @author Patrycja Mirkowska
 */

public enum HelpObjects {
    /**
     * set of help instuction
     * ever object contains id, instruction, image and info
     */

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
    /** What does?
     * @return int value which is a object id in set
     */
    public int getId() {
        return id;
    }
    /** What does?
     * @return String value which is a help instruction
     */
    public String getInstruction() {
        return instruction;
    }
    /** What does?
     * @return String value which is info abaut number of instruction in set
     */
    public String getInfo() {
        return info;
    }
    /** What does?
     * @return int value which is id number od help image
     */
    public int gethelpImageId() {
        return helpImageId;
    }
    /** What does?
     * the function needs the corresponding id number,
     * then checks the file and returns the object with the specified id number
     * How it works?
     * using for each iterates over the elements of the set and returns the object with the given id number
     * To use this method you must set?
     * @param identify which is a object id in set
     * @return HelpObjects value which is a current instruction of set
     */
    public static HelpObjects findHelpById(int identify){
        for (HelpObjects helpObjects:HelpObjects.values()){
            if(helpObjects.getId()==identify){
                return helpObjects;
            }
        }
        return null;
    }
}
