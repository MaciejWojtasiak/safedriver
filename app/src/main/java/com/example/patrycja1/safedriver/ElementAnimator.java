package com.example.patrycja1.safedriver;

import android.content.Context;
import android.view.View;
import android.view.animation.AnimationUtils;

/**
 * Is a class intended for element animation.
 * You can find animation methods in it, ie rotate, zoom in, leave on the right.
 * @author Patrycja Mirkowska
 */

public class ElementAnimator  {

    public ElementAnimator(){
        super();
    }
    /** What does?
     * rotate view's element
     * How it works?
     * an animation that uses XML, creates the final variable android.view.animation.Animation
     * and assigns the appropriate XML file then starts the animation on the View object
     * To use this method you must set?
     * @param view , which is an element to animation
     * @param context, which is an application context
     */
    public void rotateElement(View view, Context context){
        final android.view.animation.Animation rotate = AnimationUtils.loadAnimation(context, R.anim.rotate);
        view.startAnimation(rotate);
    }

    /** What does?
     * come from the left side
     * How it works?
     * an animation that uses XML, creates the final variable android.view.animation.Animation
     * and assigns the appropriate XML file then starts the animation on the View object
     * To use this method you must set?
     * @param view , which is an element to animation
     * @param context, which is an application context
     */
    public void goLeftElement(View view, Context context){
        final android.view.animation.Animation goLeft = AnimationUtils.loadAnimation(context, R.anim.goleft);
        view.startAnimation(goLeft);
    }
    /** What does?
     * come from the right side
     * How it works?
     * an animation that uses XML, creates the final variable android.view.animation.Animation
     * and assigns the appropriate XML file then starts the animation on the View object
     * To use this method you must set?
     * @param view , which is an element to animation
     * @param context, which is an application context
     */
    public void goRightElement(View view,Context context){
        final android.view.animation.Animation goRight = AnimationUtils.loadAnimation(context, R.anim.goright);
        view.startAnimation(goRight);
    }
    /** What does?
     * chande alpha view's element
     * How it works?
     * an animation that uses XML, creates the final variable android.view.animation.Animation
     * and assigns the appropriate XML file then starts the animation on the View object
     * To use this method you must set?
     * @param view , which is an element to animation
     * @param context, which is an application context
     */
    public void setAlphaElement(View view,Context context){
        final android.view.animation.Animation setAlpha = AnimationUtils.loadAnimation(context, R.anim.setalpha);
        view.startAnimation(setAlpha);
    }
    /** What does?
     * enlarge view's element
     * How it works?
     * an animation that uses XML, creates the final variable android.view.animation.Animation
     * and assigns the appropriate XML file then starts the animation on the View object
     * To use this method you must set?
     * @param view , which is an element to animation
     * @param context, which is an application context
     */
    public void toGreatherElement(View view,Context context){
        final android.view.animation.Animation toGreather = AnimationUtils.loadAnimation(context, R.anim.togreather);
        view.startAnimation(toGreather);
    }
}
