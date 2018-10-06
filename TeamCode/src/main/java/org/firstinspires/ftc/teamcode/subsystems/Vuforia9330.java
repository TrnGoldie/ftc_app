package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

import static org.firstinspires.ftc.robotcore.external.navigation.AngleUnit.DEGREES;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesOrder.XYZ;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesOrder.YZX;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesReference.EXTRINSIC;
import static org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection.BACK;
import static org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection.FRONT;

import java.util.ArrayList;
import java.util.List;

public class Vuforia9330 {

    private HardwareMap hwMap = null;
    private static final String VUFORIA_KEY = "AXGQqAb/////AAABmQUpN7QZAU7en9RXf4fa+ywEI4Xko7raotMrxiHZAGf2ImtdjsxT0bUSg++zmYUH4PV9OBznjljSehPTLjWNAOmD1VkVgU8zcJhEDUOCvVZZT1u/nycdLS1x1MNDAFW2frm1MAfB/rG187wYimemGhdtorD5SLK9BBkg5goshWQy/AN4qx3MpE/+/aIR8B4cARnJyvYHX5W+zkRqp/y/yT0r7UBeMwu5o9uKz+BMOtrY+FRxZAX5mJIXjuXNkLN7W3bI7EN5Rc/TFQ5SFDOg4lU6ayxas86kBLI53tUqJDFtnNb+BEdy7HOY58VUkQPxT4jKYEmWG4un+5LJcW3tYjRqp6RmzHsJKgAJyY3PhYcJ";

    public static final float mmPerInch = 22.5f;
    public static final float mmFTCFieldWidth = 12 * 6 * mmPerInch;
    public static final float mmTargetHeight = 6 * mmPerInch;

    private static final VuforiaLocalizer.CameraDirection CAMERA_CHOICE = BACK;

    private OpenGLMatrix lastLocation = null;
    private boolean targetVisible = false;
    private float posX;
    private float posY;
    private float posZ;
    private float rotRoll;
    private float rotPitch;
    private float rotHeading;

    private String visibleTarget = null;

    //CHANGE THESE ONCE WE GET THE CAMERA ON THE ROBOT
    final int CAMERA_FORWARD_DISPLACEMENT  = 0;   // MM in front of robot center
    final int CAMERA_VERTICAL_DISPLACEMENT = 0;   // MM above ground
    final int CAMERA_LEFT_DISPLACEMENT     = 0;     // MM right of center line

    VuforiaLocalizer vuforia;
    VuforiaTrackables targetsRoverRuckus;
    List<VuforiaTrackable> allTrackables;

    public Vuforia9330(HardwareMap hwMap) {
        this.hwMap = hwMap;
    }

    public void init() {
        int cameraMonitorViewId = hwMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hwMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraDirection   = CAMERA_CHOICE;

        vuforia = ClassFactory.getInstance().createVuforia(parameters);
        targetsRoverRuckus = this.vuforia.loadTrackablesFromAsset("RoverRuckus");
        VuforiaTrackable blueRover = targetsRoverRuckus.get(0);
        blueRover.setName("Blue-Rover");
        VuforiaTrackable redFootprint = targetsRoverRuckus.get(1);
        redFootprint.setName("Red-Footprint");
        VuforiaTrackable frontCraters = targetsRoverRuckus.get(2);
        frontCraters.setName("Front-Craters");
        VuforiaTrackable backSpace = targetsRoverRuckus.get(3);
        backSpace.setName("Back-Space");

        allTrackables = new ArrayList<VuforiaTrackable>();
        allTrackables.addAll(targetsRoverRuckus);

        OpenGLMatrix blueRoverLocationOnField = OpenGLMatrix
                .translation(0, mmFTCFieldWidth, mmTargetHeight)
                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 90, 0, 0));
        blueRover.setLocation(blueRoverLocationOnField);

        OpenGLMatrix redFootprintLocationOnField = OpenGLMatrix
                .translation(0, -mmFTCFieldWidth, mmTargetHeight)
                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 90, 0, 180));
        redFootprint.setLocation(redFootprintLocationOnField);

        OpenGLMatrix frontCratersLocationOnField = OpenGLMatrix
                .translation(-mmFTCFieldWidth, 0, mmTargetHeight)
                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 90, 0 , 90));
        frontCraters.setLocation(frontCratersLocationOnField);

        OpenGLMatrix backSpaceLocationOnField = OpenGLMatrix
                .translation(mmFTCFieldWidth, 0, mmTargetHeight)
                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 90, 0, -90));
        backSpace.setLocation(backSpaceLocationOnField);

        OpenGLMatrix phoneLocationOnRobot = OpenGLMatrix.translation(CAMERA_FORWARD_DISPLACEMENT, CAMERA_LEFT_DISPLACEMENT, CAMERA_VERTICAL_DISPLACEMENT).multiplied(Orientation.getRotationMatrix(EXTRINSIC, YZX, DEGREES,CAMERA_CHOICE == FRONT ? 90 : -90, 0, 0));

        for (VuforiaTrackable trackable : allTrackables)
        {
            ((VuforiaTrackableDefaultListener)trackable.getListener()).setPhoneInformation(phoneLocationOnRobot, parameters.cameraDirection);
        }

    }

    public void start() {
        targetsRoverRuckus.activate();
    }

    public void stop() {
        targetsRoverRuckus.deactivate();
    }

    public void updateInfo() {
        targetVisible = false;

        for (VuforiaTrackable trackable : allTrackables) {
            if (((VuforiaTrackableDefaultListener) trackable.getListener()).isVisible()) {
                targetVisible = true;
                visibleTarget = trackable.getName();

                OpenGLMatrix robotLocationTransform = ((VuforiaTrackableDefaultListener) trackable.getListener()).getUpdatedRobotLocation();
                if (robotLocationTransform != null) {
                    lastLocation = robotLocationTransform;
                }
                break;
            }
        }

        if(targetVisible) {
            VectorF translation = lastLocation.getTranslation();
            posX = translation.get(0) / mmPerInch;
            posY = translation.get(1) / mmPerInch;
            posZ = translation.get(2) / mmPerInch;

            Orientation rotation = Orientation.getOrientation(lastLocation, EXTRINSIC, XYZ, DEGREES);
            rotRoll = rotation.firstAngle;
            rotPitch = rotation.secondAngle;
            rotHeading = rotation.thirdAngle;
        }

    }

    /**Getters n Setters but without the Setters**/
    public float getPosX() {
        return posX;
    }

    public float getPosY() {
        return posY;
    }

    public float getPosZ() {
        return posZ;
    }

    public float getRoll() {
        return rotRoll;
    }

    public float getPitch() {
        return rotPitch;
    }

    public float getHeading() {
        return rotHeading;
    }

    public String getVisibleTarget() {
        return visibleTarget;
    }

}
