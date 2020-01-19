package org.firstinspires.ftc.teamcode.Season19and20.code;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.vuforia.Vuforia;

import java.util.ArrayList;
import java.util.List;

@Disabled
@Autonomous(name = "AutoSkystone", group = "Autonomous")

public class AutoSkystone extends LinearOpMode {

    private static int firstUp = 10;
    private boolean strafeCancel = false;
    private boolean targetVisible = false;
    boolean[] skyStoneLetters = {false, false, false, false, false, false};
    public List<VuforiaTrackable> allTrackables = new ArrayList<VuforiaTrackable>();
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
        allTrackables.addAll(targetsSkyStone);

        robot.ArmLeft.setTargetPosition(0);
        robot.ArmRight.setTargetPosition(0);

        waitForStart();
        DriveStraightDistance(1120, 0.5);
        sleep(3000);
        Strafe(1120, 0.5);
        StrafeSpecial(-2240, -0.25);
        for(int k = 0; k < 6;  k++){
            if(skyStoneLetters[k] == true){
                telemetry.addData("The skystone "+k+" from the left is ", skyStoneLetters[k]);
                telemetry.update();
            }
        }
        sleep(3000);
    }

    void DriveStraight(double power){
        if(strafeCancel == true){
            robot.FrontRight.setPower(power - 0.2);
            robot.FrontLeft.setPower(-power);
            robot.RearRight.setPower(power);
            robot.RearLeft.setPower(-power - 0.2);
        }
        else {
            robot.FrontRight.setPower(power);
            robot.FrontLeft.setPower(-power);
            robot.RearRight.setPower(power);
            robot.RearLeft.setPower(-power);
        }
    }

    void StopDriving (){
        DriveStraight(0);
    }

    void DriveStraightDistance(int distance, double power){
        telemetry.addData("Driving", "Yes");
        robot.FrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.FrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.RearRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.RearLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.FrontRight.setTargetPosition(distance);
        robot.FrontLeft.setTargetPosition(-distance);
        robot.RearRight.setTargetPosition(distance);
        robot.RearLeft.setTargetPosition(-distance);

        robot.FrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.FrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.RearRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.RearLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        DriveStraight(power);
        while((robot.FrontRight.isBusy() || robot.RearLeft.isBusy() || robot.RearRight.isBusy() || robot.FrontLeft.isBusy()) && opModeIsActive()){
            idle();
        }

        StopDriving();
        robot.FrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.FrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.RearRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.RearLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    void Turn (int distance, double power){
        telemetry.addData("Driving", "Yes");
        robot.FrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.FrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.RearRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.RearLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.FrontRight.setTargetPosition(distance);
        robot.FrontLeft.setTargetPosition(distance);
        robot.RearRight.setTargetPosition(distance);
        robot.RearLeft.setTargetPosition(distance);

        robot.FrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.FrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.RearRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.RearLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        DriveStraight(power);
        while((robot.FrontRight.isBusy() || robot.RearLeft.isBusy() || robot.RearRight.isBusy() || robot.FrontLeft.isBusy()) && opModeIsActive()){
            idle();
        }

        StopDriving();
        robot.FrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.FrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.RearRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.RearLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    //left is negative, right is positive
    void Strafe (int distance, double power){
        strafeCancel = true;
        telemetry.addData("Driving", "Yes");
        robot.FrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.FrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.RearRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.RearLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.FrontRight.setTargetPosition(-distance);
        robot.FrontLeft.setTargetPosition(-distance);
        robot.RearRight.setTargetPosition(distance);
        robot.RearLeft.setTargetPosition(distance);

        robot.FrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.FrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.RearRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.RearLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        DriveStraight(power);
        while((robot.FrontRight.isBusy() || robot.RearLeft.isBusy() || robot.RearRight.isBusy() || robot.FrontLeft.isBusy()) && opModeIsActive()){
            idle();
        }

        StopDriving();
        robot.FrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.FrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.RearRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.RearLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        strafeCancel = false;
    }

    void StrafeSpecial (int distance, double power){
        strafeCancel = true;
        telemetry.addData("Driving", "Yes");
        robot.FrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.FrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.RearRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.RearLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.FrontRight.setTargetPosition(-distance);
        robot.FrontLeft.setTargetPosition(-distance);
        robot.RearRight.setTargetPosition(distance);
        robot.RearLeft.setTargetPosition(distance);

        robot.FrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.FrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.RearRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.RearLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        DriveStraight(power);
        while((robot.FrontRight.isBusy() || robot.RearLeft.isBusy() || robot.RearRight.isBusy() || robot.FrontLeft.isBusy()) && opModeIsActive()){
            VuforiaSearch(distance);
        }

        StopDriving();
        robot.FrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.FrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.RearRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.RearLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        strafeCancel = false;
    }

    //NeveRest 40 Gearmotor has 280 ppr (Wheels) 1120 for full rotation
    //NeveRest 20 Gearmotor has 140 ppr (Arm) 560 for full rotation
    void Flip (){
        robot.ArmLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.ArmRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.ArmLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.ArmLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        for(int i = 140; i <= 560; i+=140){
            robot.ArmLeft.setTargetPosition(i);
            robot.ArmRight.setTargetPosition(i);
            robot.ArmLeft.setPower(0.25);
            robot.ArmRight.setPower(0.25);
            while(opModeIsActive() && robot.ArmLeft.isBusy() || robot.ArmRight.isBusy()){
                telemetry.addData("encoder-ArmLeft", robot.ArmLeft.getCurrentPosition() + "  busy=" + robot.ArmLeft.isBusy());
                telemetry.addData("encoder-ArmRight", robot.ArmRight.getCurrentPosition() + "  busy=" + robot.ArmRight.isBusy());
                telemetry.update();
                idle();
            }
            robot.ArmLeft.setPower(0);
            robot.ArmRight.setPower(0);
            sleep(1000);
        }
    }
    void VuforiaSearch(int distance2) {
        for (int j = 1; j <= 6; j++) {
            while(robot.FrontLeft.getCurrentPosition() < (j/6)*distance2){
                if (((VuforiaTrackableDefaultListener) allTrackables.get(0).getListener()).isVisible()) {
                    if(j == 0){
                        skyStoneLetters[0] =  true;
                    }
                    if(j == 1){
                        skyStoneLetters[1] =  true;
                    }
                    if(j == 2){
                        skyStoneLetters[2] =  true;
                    }
                    if(j == 3){
                        skyStoneLetters[3] =  true;
                    }
                    if(j == 4){
                        skyStoneLetters[4] =  true;
                    }
                    if(j == 5){
                        skyStoneLetters[5] =  true;
                    }
                }
            }
        }
    }
}