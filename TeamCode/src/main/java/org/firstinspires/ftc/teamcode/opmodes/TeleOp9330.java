package org.firstinspires.ftc.teamcode.opmodes;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.teamcode.Hardware9330;

@TeleOp(name = "TeleOp9330", group = "Opmode")  // @Autonomous(...) is the other common choice
//@Disabled
public class TeleOp9330 extends OpMode {
    Hardware9330 robotMap = new Hardware9330();

    float yPower = 0;
    float spinPower = 0;

    public void init() {
        robotMap.init(hardwareMap);
    }

    public void loop() {
        yPower = Math.round(gamepad1.left_stick_y* .8);
        spinPower = Math.round(gamepad1.right_stick_x * .8);

        //Set powers of the motors
        Hardware9330.leftFront.setPower(yPower + spinPower);
        Hardware9330.leftBack.setPower(yPower + spinPower);
        Hardware9330.rightFront.setPower(yPower - spinPower);
        Hardware9330.rightBack.setPower(yPower - spinPower);

        telemetry.update();
    }
}
