package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by robot on 9/24/2018.
 */

public class Hardware9330 {

    public ElapsedTime runtime = new ElapsedTime();

   //drive motor
    public static DcMotor frontLeftMotor;
    public static DcMotor frontRightMotor;
    public static DcMotor rearLeftMotor;
    public static DcMotor rearRightMotor;
    //climber
    public static DcMotor climberLiftMotor;
    public static Servo climberClawServo;
    //pickup
    public static DcMotor pickupMotor;

    public Hardware9330() {
    }

    public void init(HardwareMap hwMap) {
        //Drive Motors init
        frontLeftMotor = hwMap.dcMotor.get("frontLeftMotor");
        frontRightMotor = hwMap.dcMotor.get("frontRightMotor");
        rearLeftMotor = hwMap.dcMotor.get("rearLeftMotor");
        rearRightMotor = hwMap.dcMotor.get("rearRightMotor");
        //Climber init
        climberLiftMotor = hwMap.dcMotor.get("climberLiftMotor");
        climberClawServo = hwMap.servo.get("climberClawServo");
        //Pickup Motor init
        pickupMotor = hwMap.dcMotor.get("pickupMotor");
        //Drive Motor direction set
        frontLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        rearLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        rearRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        //Climber direction set
        climberLiftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        climberClawServo.setDirection(Servo.Direction.FORWARD);
        //Pickup Motor direction set
        pickupMotor.setDirection(DcMotorSimple.Direction.FORWARD);
    }

}
