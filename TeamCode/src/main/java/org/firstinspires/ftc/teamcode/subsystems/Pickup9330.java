package org.firstinspires.ftc.teamcode.subsystems;

import org.firstinspires.ftc.teamcode.Hardware9330;

public class Pickup9330 {

    private final int MAX_SPIN = 1;
    private final int MIN_SPIN = 0;
    private Hardware9330 hwMap = null;

    public Pickup9330(Hardware9330 hwMap){
        this.hwMap = hwMap;
    }

    public void SpinStart(){
        hwMap.pickupMotor.setPower(MAX_SPIN);
    }

    public void SpinStop(){
        hwMap.pickupMotor.setPower(MIN_SPIN);
    }
}
