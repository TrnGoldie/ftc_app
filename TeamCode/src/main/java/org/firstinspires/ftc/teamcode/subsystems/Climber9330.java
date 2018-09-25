package org.firstinspires.ftc.teamcode.subsystems;

import org.firstinspires.ftc.teamcode.Hardware9330;

/**
 * Created by robot on 9/24/2018.
 */

public class Climber9330 {

    private static final int CLAW_OPEN = 0;
    private static final int CLAW_CLOSE = 0;
    private static final int LIFT_SPEED = 0;
    private static final int LIFT_DURATION = 0;
    private Hardware9330 hwMap = null;

    public Climber9330(Hardware9330 hwMap) {
        this.hwMap = hwMap;
    }

    public void openClaw() {
        hwMap.climberClawServo.setPosition(CLAW_OPEN);
    }

    public void closeClaw() {
        hwMap.climberClawServo.setPosition(CLAW_CLOSE);
    }

    public void extendArm() {
        double targetTime = hwMap.runtime.seconds() + LIFT_DURATION;
        while (hwMap.runtime.seconds() < targetTime) {
            hwMap.climberLiftMotor.setPower(LIFT_SPEED);
        }
        hwMap.climberLiftMotor.setPower(0);
    }

    public void contractArm() {
        double targetTime = hwMap.runtime.seconds() + LIFT_DURATION;
        while (hwMap.runtime.seconds() < targetTime) {
            hwMap.climberLiftMotor.setPower(-LIFT_SPEED);
        }
        hwMap.climberLiftMotor.setPower(0);
    }
}
