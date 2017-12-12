package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Hardware9330;

/**
 * Created by robot on 10/13/2017.
 */

public class RelicPickup9330 {
    //We will need to add 2 extra servos
    private boolean isHandClosed;
    private boolean isWristUp;
    private Hardware9330 hwMap;
    int armPosition = 0;
    int armMAX = 150;
    int armMIN = 0;


    static final double OPEN_POS = 0.0;
    static final double CLOSED_POS = 0.6;

    static final double LOW_POS = 0.0;
    static final double HIGH_POS = 0.5;

    public RelicPickup9330(Hardware9330 robotMap) {
        hwMap = robotMap;
        isHandClosed = false;
        isWristUp = false;

        robotMap.relicArmMotor.setTargetPosition(0);
        robotMap.relicArmMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robotMap.relicArmMotor.setPower(0.2);
    }

    public void moveArm(int amount) {
        if (armPosition+amount > armMIN && armPosition+amount < armMAX) {
            armPosition += amount;
            hwMap.relicArmMotor.setTargetPosition(armPosition);
        }
    }

    public void openHand() {
        if (isHandClosed) {
            isHandClosed = false;
            hwMap.relicHandServo.setPosition(OPEN_POS);
        }
    }

    public void closeHand() {
        if (!isHandClosed) {
            isHandClosed = true;
            hwMap.relicHandServo.setPosition(CLOSED_POS);
        }
    }

    public void raiseWrist() {
        if (!isWristUp) {
            isWristUp = true;
            hwMap.relicWristServo.setPosition(HIGH_POS);
        }
    }

    public void lowerWrist() {
        if (isWristUp) {
            isWristUp = false;
            hwMap.relicWristServo.setPosition(LOW_POS);

        }
    }

    public void toggleHand() {
        if (isHandClosed == false) closeHand();
        else openHand();
    }

    public void toggleWrist() {
        if (isWristUp == false) raiseWrist();
        else lowerWrist();
    }
}
