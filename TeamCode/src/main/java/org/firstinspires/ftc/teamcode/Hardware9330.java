package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by robot on 9/24/2018.
 */

public class Hardware9330 {

    private HardwareMap hwMap = null;
    public ElapsedTime runtime = new ElapsedTime();

    public static DcMotor frontLeftMotor;
    public static DcMotor frontRightMotor;
    public static DcMotor rearLeftMotor;
    public static DcMotor rearRightMotor;

    public Hardware9330() {
    }

    public void init(HardwareMap hwMap) {
        frontLeftMotor = hwMap.dcMotor.get("frontLeftMotor");
        frontRightMotor = hwMap.dcMotor.get("frontRightMotor");
        rearLeftMotor = hwMap.dcMotor.get("rearLeftMotor");
        rearRightMotor = hwMap.dcMotor.get("rearRightMotor");

        frontLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        rearLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        rearRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    }

}
