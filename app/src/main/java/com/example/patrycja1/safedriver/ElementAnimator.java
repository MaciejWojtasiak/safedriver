package com.example.patrycja1.safedriver;

import android.content.Context;
import android.view.View;
import android.view.animation.AnimationUtils;

/**
 * Created by Patrycja1 on 30.04.2018.
 */

public class ElementAnimator  {

    public ElementAnimator(){
        super();
    }

    public void rotateElement(View view, Context context){
        //if you want rotate view's element use this method
        //set view field and application context
        final android.view.animation.Animation rotate = AnimationUtils.loadAnimation(context, R.anim.rotate);
        view.startAnimation(rotate);
    }


    public void goLeftElement(View view, Context context){
        //if you want to view's element to come from the left side, use this method
        //set view field and application context
        final android.view.animation.Animation goLeft = AnimationUtils.loadAnimation(context, R.anim.goleft);
        view.startAnimation(goLeft);
    }

    public void goRightElement(View view,Context context){
        //if you want to view's element to come from the right side, use this method
        //set view field and application context
        final android.view.animation.Animation goRight = AnimationUtils.loadAnimation(context, R.anim.goright);
        view.startAnimation(goRight);
    }

    public void setAlphaElement(View view,Context context){
        //if you want chande alpha view's element use this method
        //set view field and application context
        final android.view.animation.Animation setAlpha = AnimationUtils.loadAnimation(context, R.anim.setalpha);
        view.startAnimation(setAlpha);
    }

    public void toGreatherElement(View view,Context context){
        //if you want enlarge view's element use this method
        //set view field and application context
        final android.view.animation.Animation toGreather = AnimationUtils.loadAnimation(context, R.anim.togreather);
        view.startAnimation(toGreather);
    }
}
