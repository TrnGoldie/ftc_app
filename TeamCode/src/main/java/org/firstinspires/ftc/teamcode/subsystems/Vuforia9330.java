package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;

import java.util.ArrayList;
import java.util.List;

import static org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection.BACK;

public class Vuforia9330 {

    private static final String VUFORIA_KEY = "AXGQqAb/////AAABmQUpN7QZAU7en9RXf4fa+ywEI4Xko7raotMrxiHZAGf2ImtdjsxT0bUSg++zmYUH4PV9OBznjljSehPTLjWNAOmD1VkVgU8zcJhEDUOCvVZZT1u/nycdLS1x1MNDAFW2frm1MAfB/rG187wYimemGhdtorD5SLK9BBkg5goshWQy/AN4qx3MpE/+/aIR8B4cARnJyvYHX5W+zkRqp/y/yT0r7UBeMwu5o9uKz+BMOtrY+FRxZAX5mJIXjuXNkLN7W3bI7EN5Rc/TFQ5SFDOg4lU6ayxas86kBLI53tUqJDFtnNb+BEdy7HOY58VUkQPxT4jKYEmWG4un+5LJcW3tYjRqp6RmzHsJKgAJyY3PhYcJ";

    public static final float mmPerInch = 22.5f;
    public static final float mmFTCFieldWidth = 12 * 6 * mmPerInch;
    public static final float mmTargetHeight = 6 * mmPerInch;

    private static final VuforiaLocalizer.CameraDirection CAMERA_CHOICE = BACK;

    private OpenGLMatrix lastLocation = null;
    private boolean targetVisible = false;

    VuforiaLocalizer vuforia;

    public Vuforia9330(HardwareMap hwMap) {

    }

}
