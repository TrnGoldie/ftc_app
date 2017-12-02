package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Hardware9330;
import org.firstinspires.ftc.teamcode.subsystems.JewelArm9330;

/**
 * Created by robot on 10/16/2017.
 */
@Autonomous(name = "AutoDriveTest9330", group = "Opmode")
public class AutoDriveTest9330 extends LinearOpMode {
    Hardware9330 robotMap = new Hardware9330();
    Double Diameter = 3.78;
    Double Circumference = 3.1415 * Diameter;
    int PPR = 560;
    int oneInchOfMovement = (int)Math.round(PPR / Circumference);

    public void checkStop() {
        if (isStopRequested()) stop();
    }

    @Override
    public void runOpMode() throws InterruptedException {
        robotMap.init(hardwareMap);
        JewelArm9330 jewel = new JewelArm9330(robotMap);
        //Setting the mode of the motor

        robotMap.rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robotMap.leftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        waitForStart();

        //Goes forward
        telemetry.addData("Program", "Driving Forward");
        robotMap.rightMotor.setTargetPosition(12 * oneInchOfMovement);

        //Set the motor channel to run to the target position set above
        robotMap.rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //Set the motors to drive at the given speed to the position
        robotMap.rightMotor.setPower(0.5);
        robotMap.leftMotor.setPower(0.5);

        while(robotMap.rightMotor.isBusy()) {
            checkStop();
            telemetry.addData("Right encoder target", robotMap.rightMotor.getTargetPosition());
            telemetry.addData("Right encoder current", robotMap.rightMotor.getCurrentPosition());
            telemetry.update();
        }

        robotMap.rightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robotMap.rightMotor.setPower(0);
        robotMap.leftMotor.setPower(0);
        //Resetting Encoders
        stop();
    }

}

