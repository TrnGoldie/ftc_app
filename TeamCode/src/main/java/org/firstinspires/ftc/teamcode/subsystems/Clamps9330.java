package org.firstinspires.ftc.teamcode.subsystems;
import org.firstinspires.ftc.teamcode.Hardware9330;

/**
 * Created by robot on 9/25/2017.
 */

public class Clamps9330 {

    private boolean lowClampEngaged;
    private boolean highClampEngaged;

    //   Declaring Set positions for servo
    static final Double ENGAGED_POS = 0.8;
    static final Double RELEASE_POS = 0.25;
    //   accessing Hardware9330
    private Hardware9330 hwMap = null;
    //Constructor for brake class
    public Clamps9330(Hardware9330 robotMap){
        hwMap = robotMap;
        hwMap.lowGlyphClamp.setPosition(ENGAGED_POS);
        hwMap.highGlyphClamp.setPosition(ENGAGED_POS);
        lowClampEngaged = false;
        highClampEngaged = false;
    }
    //  Engaging the low clamp
    public void toggleLowClamp(){
        if(!lowClampEngaged){
            lowClampEngaged = true;
            hwMap.lowGlyphClamp.setPosition(ENGAGED_POS);
        } else {
            lowClampEngaged = false;
            hwMap.lowGlyphClamp.setPosition(RELEASE_POS);
        }
    }

    public void toggleHighClamp(){
        if(!highClampEngaged){
            highClampEngaged = true;
            //hwMap.highGlyphClamp.setPosition(ENGAGED_POS);
        } else {
            highClampEngaged = false;
            hwMap.highGlyphClamp.setPosition(RELEASE_POS);
        }
    }


    public void closeLowClamp(){
        if(!lowClampEngaged){
            lowClampEngaged = true;
            //hwMap.lowGlyphClamp.setPosition(ENGAGED_POS);
        }
    }
    // Releasing the low clamp
    public void openLowClamp(){
        if(lowClampEngaged){
            lowClampEngaged = false;
            //hwMap.lowGlyphClamp.setPosition(RELEASE_POS);
        }
    }
    //Engaging the high clamp
    public void closeHighClamp(){
        if(!highClampEngaged){
            highClampEngaged = true;
            //hwMap.highGlyphClamp.setPosition(ENGAGED_POS);
        }
    }
    // Releasing the high clamp
    public void openHighClamp(){
        if(highClampEngaged){
            highClampEngaged = false;
            //hwMap.highGlyphClamp.setPosition(RELEASE_POS);
        }
    }

    // query brake state
    public boolean islowClampEngaged() {
        return lowClampEngaged;
    }
}
