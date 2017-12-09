package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Hardware9330;
import org.firstinspires.ftc.teamcode.subsystems.Clamps9330;
import org.firstinspires.ftc.teamcode.subsystems.Drive9330;
import org.firstinspires.ftc.teamcode.subsystems.Gyro9330;

/**
 * Created by robot on 11/13/2017.
 */
@Autonomous(name = "DriveToCryptoboxAuto9330", group = "OpMode")
public class DriveToCryptoboxAuto9330 extends LinearOpMode {

    Drive9330 drive;
    Clamps9330 clamps;
    Hardware9330 robotMap = new Hardware9330();
    Gyro9330 gyro;
    int points[][] = {{10, 10}, {20, 20}};

    @Override
    public void runOpMode() throws InterruptedException {
        //This is exclusively for Right(from the perspective of the drivers) on the Red alliance
        telemetry.addData("Info", "Initializing. Please wait.");

        robotMap.init(hardwareMap);
        drive = new Drive9330(robotMap);
        clamps = new Clamps9330(robotMap);
        gyro = new Gyro9330(robotMap);

        telemetry.addData("Info", "Initialized. Press start when ready.");
        waitForStart();

        drive.driveDistance(-24, true);
        drive.gyroTurn(90, 30, true, true);
        drive.driveDistance(8, true);


    }
}
