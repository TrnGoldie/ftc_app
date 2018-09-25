package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Hardware9330;
import org.firstinspires.ftc.teamcode.subsystems.Climber9330;
import org.firstinspires.ftc.teamcode.subsystems.Vuforia9330;

/**
 * Created by robot on 9/24/2018.
 */

@Autonomous(name="Autonomous", group="Vuforia")
//@Disabled
public class Autonomous9330 extends LinearOpMode {

    /* Declare OpMode members. */
    private Hardware9330 hwMap = new Hardware9330();
    private Vuforia9330 vuforia = new Vuforia9330(hardwareMap);
    private Climber9330 climber = new Climber9330(hwMap);

    private static final double TURN_SPEED = 0.5;

    @Override
    public void runOpMode() {

        hwMap.init(hardwareMap);
        vuforia.init();
        vuforia.start();
        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Ready to run");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        hwMap.runtime.reset();

        // Drop from Lander
        climber.extendArm();
        climber.openClaw();
        climber.contractArm();
        climber.closeClaw();

        while (!vuforia.targetVisible) {
            vuforia.updateInfo();

            hwMap.frontLeftMotor.setPower(-TURN_SPEED);
            hwMap.frontRightMotor.setPower(TURN_SPEED);
            hwMap.rearLeftMotor.setPower(-TURN_SPEED);
            hwMap.rearRightMotor.setPower(TURN_SPEED);
        }

        hwMap.frontLeftMotor.setPower(0);
        hwMap.frontRightMotor.setPower(0);
        hwMap.rearLeftMotor.setPower(0);
        hwMap.rearRightMotor.setPower(0);

        vuforia.stop();

        telemetry.addData("I DID IT!!!", String.format("Visible Target was %s", vuforia.visibleTarget));
        telemetry.update();
    }
}
