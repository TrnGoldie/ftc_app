package org.firstinspires.ftc.teamcode.subsystems;

import org.firstinspires.ftc.teamcode.Hardware9330;

public class Drive9330 {
    private Hardware9330 hwMap = null;

    public Drive9330(Hardware9330 robotMap) {
        hwMap = robotMap;
    }

    public void driveForward(double speed) { //Speed must be between -100 and 100
        Hardware9330.leftFront.setPower(speed);
        Hardware9330.leftBack.setPower(speed);
        Hardware9330.rightFront.setPower(speed);
        Hardware9330.rightBack.setPower(speed);
    }

    public void turnLeft(double speed) {
        Hardware9330.leftFront.setPower(-speed);
        Hardware9330.leftBack.setPower(-speed);
        Hardware9330.rightFront.setPower(speed);
        Hardware9330.rightBack.setPower(speed);
    }

    public void turnRight(double speed) {
        Hardware9330.leftFront.setPower(speed);
        Hardware9330.leftBack.setPower(speed);
        Hardware9330.rightFront.setPower(-speed);
        Hardware9330.rightBack.setPower(-speed);
    }

    public void stopDrive() {
        Hardware9330.leftFront.setPower(0);
        Hardware9330.leftBack.setPower(0);
        Hardware9330.rightFront.setPower(0);
        Hardware9330.rightBack.setPower(0);
    }

}
