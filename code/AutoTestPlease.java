package org.firstinspires.ftc.teamcode.Season19and20.code;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

import java.util.ArrayList;
import java.util.List;

@Autonomous(name = "AutoTestPlease", group = "Autonomous")

public class AutoTestPlease extends LinearOpMode {

    double i = 0;
    double n = 0;
    double a = 0;
    private boolean targetVisible = false;
    public List<VuforiaTrackable> allTrackables = new ArrayList<VuforiaTrackable>();
    private static final double servoPos1 = 0.3;
    HWMapTest robot = new HWMapTest();

    VuforiaLocalizer vuforia;


    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        parameters.vuforiaLicenseKey = "ASxYq8z/////AAAAGfKVNNE3jU+gvP8mNaYt5P10PoBkHejK3PS1xher3fPIXyBxpFvFC/HLUBT/nThQuxl0zY6zI7EtPsk/CVOqgKa5YlyulrjYVZ/P8T/dhPyFyqml9UEBixPKcgNPSeu8xd2q1oUMvxYm33tuX/flCOtgWO2tLjgpTlMK7BM0hn2hNK8BgylLqSchG7aIdsr909swav2LqY1ZAa4qml4LoQwSkvQ1SzSSC7egkEkmWK4+U/lDnD4Wly++WqvKpP5fUWF69bW5c4/wCyww99UBlUISrMBuR8hoRZnckvqYopzE3m4oU8m2DLFjEODGxf13bfYQ0VHSvVseqLsjUkWJHp8NW7Vb79sUdb4kNqXYS7HO";

        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.FRONT;
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);

        VuforiaTrackables targetsSkyStone = this.vuforia.loadTrackablesFromAsset("Skystone");

        VuforiaTrackable stoneTarget = targetsSkyStone.get(0);
        stoneTarget.setName("Stone Target");
        VuforiaTrackable blueRearBridge = targetsSkyStone.get(1);
        blueRearBridge.setName("Blue Rear Bridge");
        VuforiaTrackable redRearBridge = targetsSkyStone.get(2);
        redRearBridge.setName("Red Rear Bridge");
        VuforiaTrackable redFrontBridge = targetsSkyStone.get(3);
        redFrontBridge.setName("Red Front Bridge");
        VuforiaTrackable blueFrontBridge = targetsSkyStone.get(4);
        blueFrontBridge.setName("Blue Front Bridge");
        VuforiaTrackable red1 = targetsSkyStone.get(5);
        red1.setName("Red Perimeter 1");
        VuforiaTrackable red2 = targetsSkyStone.get(6);
        red2.setName("Red Perimeter 2");
        VuforiaTrackable front1 = targetsSkyStone.get(7);
        front1.setName("Front Perimeter 1");
        VuforiaTrackable front2 = targetsSkyStone.get(8);
        front2.setName("Front Perimeter 2");
        VuforiaTrackable blue1 = targetsSkyStone.get(9);
        blue1.setName("Blue Perimeter 1");
        VuforiaTrackable blue2 = targetsSkyStone.get(10);
        blue2.setName("Blue Perimeter 2");
        VuforiaTrackable rear1 = targetsSkyStone.get(11);
        rear1.setName("Rear Perimeter 1");
        VuforiaTrackable rear2 = targetsSkyStone.get(12);
        rear2.setName("Rear Perimeter 2");

        // For convenience, gather together all the trackable objects in one easily-iterable collection */
        //List<VuforiaTrackable> allTrackables = new ArrayList<VuforiaTrackable>();  -moved to class for public access
        allTrackables.addAll(targetsSkyStone);

        waitForStart();

        DriveStraight(0.05);
        sleep(1000);
        StopDriving();
        VuforiaSearch();
        DriveStraight(0.05);
        sleep(1000);
        StopDriving();
    }


    void DriveStraight(double power) {
        robot.FrontRight.setPower(-power);
        robot.FrontLeft.setPower(power);
        robot.RearRight.setPower(-power);
        robot.RearLeft.setPower(power);
    }

    void StopDriving() {
        DriveStraight(0);
    }

    void VuforiaSearch() {
        while (opModeIsActive() && targetVisible == false) {
            for (VuforiaTrackable trackable : allTrackables) {
                if (((VuforiaTrackableDefaultListener) trackable.getListener()).isVisible()) {
                    targetVisible = true;
                }
            }
        }
    }

}