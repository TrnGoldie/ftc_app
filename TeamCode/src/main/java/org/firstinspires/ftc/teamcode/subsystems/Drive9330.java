package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Hardware9330;

/**
 * Created by robot on 9/25/2017.
 */

public class Drive9330 {
    double Diameter = 3.78;
    double Circumference = 3.1415 * Diameter;
    int PPR = 560;
    int oneInchOfMovement = (int)Math.round(PPR / Circumference);

    private Hardware9330 hwMap = null;
    Telemetry telemetry = null;
    double encoderDriveSpeed = 0.4;
    int turnError = 1 ;
    int straightError = 2;
    Gyro9330 gyro;
    int dist;
    int degree;

    public Drive9330(Hardware9330 robotMap) {
        hwMap = robotMap;
        gyro = new Gyro9330(robotMap);
        gyro.init();
    }

    public Drive9330(Hardware9330 robotMap, Telemetry _telemetry, Gyro9330 _gyro) {
        telemetry = _telemetry;
        hwMap = robotMap;
        gyro = _gyro;
        gyro.init();
    }

    public void driveForward(double speed) { //Speed mush, MUSH! be between 0 and 100
        Hardware9330.leftMotor.setPower(speed);
        Hardware9330.rightMotor.setPower(speed);
    }

    public void turnLeft(double speed, boolean allWheel) {
        if (!allWheel) {
            Hardware9330.leftMotor.setPower(0);
            Hardware9330.rightMotor.setPower(speed);
        }
        //Speed mush be between 0 and 100
        Hardware9330.leftMotor.setPower(-speed);
        Hardware9330.rightMotor.setPower(speed);

    }

    public void turnRight(double speed, boolean allWheel) { //Speed mush be between 0 and 100
        if (!allWheel) {
            Hardware9330.leftMotor.setPower(speed);
            Hardware9330.rightMotor.setPower(0);
        }
        Hardware9330.leftMotor.setPower(speed);
        Hardware9330.rightMotor.setPower(-speed);
    }

    public void stopDrive() {
        Hardware9330.leftMotor.setPower(0);
        Hardware9330.rightMotor.setPower(0);
    }

    public void driveDistance(int inches, boolean useGyro) {
        Hardware9330.rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Hardware9330.leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        Hardware9330.rightMotor.setTargetPosition(inches * oneInchOfMovement);
        Hardware9330.leftMotor.setTargetPosition(inches * oneInchOfMovement);

        Hardware9330.rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Hardware9330.leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        Hardware9330.rightMotor.setPower(encoderDriveSpeed);
        Hardware9330.leftMotor.setPower(encoderDriveSpeed);

        double startPos = gyro.getYaw();

        while(Hardware9330.rightMotor.isBusy() || Hardware9330.leftMotor.isBusy()) {
            if(useGyro) {
                if (gyro.getYaw() < startPos - straightError|| gyro.getYaw() > startPos + straightError) {
                    Hardware9330.rightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                    Hardware9330.leftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                    gyroTurn((float) startPos, 0.2, true);
                    Hardware9330.rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    Hardware9330.leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                }
            }
        }

        Hardware9330.rightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Hardware9330.leftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Hardware9330.rightMotor.setPower(0);
        Hardware9330.leftMotor.setPower(0);
    }

    public void gyroTurn(float _degrees, double speed, boolean allWheel) {
       boolean isBackwards = false;
       float degrees = _degrees;
       if(_degrees < 0) {
           isBackwards = true;
           degrees = Math.abs(_degrees);
       }
       float minAngle = degrees - turnError + (float)gyro.getYaw();
       float maxAngle = degrees + turnError + (float)gyro.getYaw();
        while (gyro.getYaw() < minAngle || gyro.getYaw() > maxAngle) {
            if (telemetry != null) {
                telemetry.addData("Gyro angle", gyro.getYaw());
                telemetry.update();
            }
            if (gyro.getYaw() < minAngle) {
                if(isBackwards) {
                    turnRight(speed, allWheel);
                }else{
                    turnLeft(speed, allWheel);
                }
            } else {
               if(isBackwards) {
                   turnLeft(speed, allWheel);
               }else{
                   turnRight(speed, allWheel);
               }
            }
        }
    }

    /*
    public void Drive(int[][] points) {
        for (int i = 0; i < points.length; i++) {
            dist = points[i][0];
            degree = points[i][1];
            turnLeft(degree, true);
            driveDistance(dist);
        }
    }
    */
}
