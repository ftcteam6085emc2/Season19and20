package org.firstinspires.ftc.teamcode.Season19and20.code;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;

@Disabled
@Autonomous(name="Auto2019Attempt1", group="Autonomous")

public class Auto2019Attempt1 extends LinearOpMode {
    double i = 0;
    double n = 0;
    double a = 0;
    private static final double servoPos1 = 0.3;
    HWMapTest robot = new HWMapTest();
    VuMarkIdentificationV3 vuf = new VuMarkIdentificationV3();
    ColorSensor colorSensor;
    ElapsedTime timer = new ElapsedTime();

    public static final String TAG = "Vuforia VuMark";

    OpenGLMatrix lastLocation = null;

    VuforiaLocalizer vuforia;
    public int cameraMonitorViewId =  hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());

    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);

        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        parameters.vuforiaLicenseKey = "ASxYq8z/////AAAAGfKVNNE3jU+gvP8mNaYt5P10PoBkHejK3PS1xher3fPIXyBxpFvFC/HLUBT/nThQuxl0zY6zI7EtPsk/CVOqgKa5YlyulrjYVZ/P8T/dhPyFyqml9UEBixPKcgNPSeu8xd2q1oUMvxYm33tuX/flCOtgWO2tLjgpTlMK7BM0hn2hNK8BgylLqSchG7aIdsr909swav2LqY1ZAa4qml4LoQwSkvQ1SzSSC7egkEkmWK4+U/lDnD4Wly++WqvKpP5fUWF69bW5c4/wCyww99UBlUISrMBuR8hoRZnckvqYopzE3m4oU8m2DLFjEODGxf13bfYQ0VHSvVseqLsjUkWJHp8NW7Vb79sUdb4kNqXYS7HO";

        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.FRONT;
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);

        /*robot.FrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.RearLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.RearRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.FrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);*/
        //timer.reset();
        waitForStart();

        //Begin Program
        //code here
        DriveStraight(0.5);
        vuf.VuforiaCheck();
        StopDriving();
    }

    /*void ColorSense() {
        Telemetry();
        float hsvValues[] = {0F, 0F, 0F};

        final float values[] = hsvValues;

        int relativeLayoutId = hardwareMap.appContext.getResources().getIdentifier("RelativeLayout", "id", hardwareMap.appContext.getPackageName());
        final View relativeLayout = ((Activity) hardwareMap.appContext).findViewById(relativeLayoutId);

        colorSensor = hardwareMap.get(ColorSensor.class, "colorSensor");


        Color.RGBToHSV(colorSensor.red() * 8, colorSensor.green() * 8, colorSensor.blue() * 8, hsvValues);
        telemetry.addData("Clear", colorSensor.alpha());
        telemetry.addData("Red  ", colorSensor.red());
        telemetry.addData("Green", colorSensor.green());
        telemetry.addData("Blue ", colorSensor.blue());
        telemetry.addData("Hue", hsvValues[0]);

        relativeLayout.post(new Runnable() {
            public void run() {
                    relativeLayout.setBackgroundColor(Color.HSVToColor(0xff, values));
            }
        });

        telemetry.update();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (colorSensor.blue() >= 1) {
            telemetry.addData("BLUE BLUE BLUE", colorSensor.blue());
            //do whatever
        } else if (colorSensor.red() >= 1) {
            telemetry.addData("RED RED RED", colorSensor.red());
            //do whatever
        }

    }*/


    void DriveStraight(double power){
        robot.FrontRight.setPower(-power);
        robot.FrontLeft.setPower(power);
        robot.RearRight.setPower(-power);
        robot.RearLeft.setPower(power);
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
        robot.FrontLeft.setTargetPosition(distance);
        robot.RearRight.setTargetPosition(distance);
        robot.RearLeft.setTargetPosition(-distance);

        robot.FrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.FrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.RearRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.RearLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        DriveStraight(power);
        a = 0;
        while((robot.FrontRight.isBusy() || robot.RearLeft.isBusy() || robot.RearRight.isBusy() || robot.FrontLeft.isBusy()) && a < 1500){
            Telemetry();
            if(((distance + 20) > robot.FrontRight.getCurrentPosition()) && (robot.FrontRight.getCurrentPosition() > (distance - 20))){
                a = 1500;
                Telemetry();
            }
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

        robot.FrontRight.setTargetPosition(-distance);
        robot.FrontLeft.setTargetPosition(distance);
        robot.RearRight.setTargetPosition(-distance);
        robot.RearLeft.setTargetPosition(-distance);

        robot.FrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.FrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.RearRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.RearLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        DriveStraight(power);
        a = 0;
        while((robot.FrontRight.isBusy() || robot.RearLeft.isBusy() || robot.RearRight.isBusy() || robot.FrontLeft.isBusy()) && a < 1500) {
            Telemetry();
            if(((distance + 20) > robot.FrontRight.getCurrentPosition()) && (robot.FrontRight.getCurrentPosition() > (distance - 20))){
                a = 1500;
                Telemetry();
            }
        }

        StopDriving();
        robot.FrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.FrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.RearRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.RearLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    void Strafe (int distance, double power){
        telemetry.addData("Driving", "Yes");
        robot.FrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.FrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.RearRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.RearLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.FrontRight.setTargetPosition(distance);
        robot.FrontLeft.setTargetPosition(-distance);
        robot.RearRight.setTargetPosition(-distance);
        robot.RearLeft.setTargetPosition(-distance);

        robot.FrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.FrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.RearRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.RearLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        DriveStraight(power);
        a = 0;
        while((robot.FrontRight.isBusy() || robot.RearLeft.isBusy() || robot.RearRight.isBusy() || robot.FrontLeft.isBusy()) && a < 1500){
            Telemetry();
            if(((distance + 20) > robot.FrontRight.getCurrentPosition()) && (robot.FrontRight.getCurrentPosition() > (distance - 20))){
                a = 1500;
                Telemetry();
            }
        }

        StopDriving();
        robot.FrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.FrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.RearRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.RearLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    void ReturnToStart(int distance, double power){
        telemetry.addData("Driving", "Yes");

        robot.FrontRight.setTargetPosition(distance);
        robot.FrontLeft.setTargetPosition(distance);
        robot.RearRight.setTargetPosition(distance);
        robot.RearLeft.setTargetPosition(-distance);

        robot.FrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.FrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.RearRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.RearLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        DriveStraight(power);
        a = 0;
        while((robot.FrontRight.isBusy() || robot.RearLeft.isBusy() || robot.RearRight.isBusy() || robot.FrontLeft.isBusy()) && a < 1500){
            Telemetry();
            if(((distance + 20) > robot.FrontRight.getCurrentPosition()) && (robot.FrontRight.getCurrentPosition() > (distance - 20))){
                a = 1500;
                Telemetry();
            }
        }

        StopDriving();
        robot.FrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.FrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.RearRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.RearLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    /*void VuforiaSearch  (){
        while(n < 1){
            RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);
            if (vuMark != RelicRecoveryVuMark.UNKNOWN) {

                if (vuMark == RelicRecoveryVuMark.RIGHT) {
                    telemetry.addData("VuMark", "RIGHT visible", vuMark);
                    DriveStraightDistance(1000, 0.3);
                    sleep(500);
                    DriveStraightDistance(2550, 0.4);
                    n=1;
                } else {
                    if (vuMark == RelicRecoveryVuMark.LEFT) {
                        telemetry.addData("VuMark", "LEFT visible", vuMark);
                        DriveStraightDistance(1000, 0.3);
                        sleep(500);
                        DriveStraightDistance(1300, 0.4);
                        n=1;
                    } else {
                        if (vuMark == RelicRecoveryVuMark.CENTER) {
                            telemetry.addData("VuMark", "CENTER visible", vuMark);
                            DriveStraightDistance(1000, 0.3);
                            sleep(500);
                            DriveStraightDistance(2000, 0.4);
                            n=1;
                        } else {
                            telemetry.addData("VuMark", "NONE visible", vuMark);
                        }
                    }
                }
                telemetry.update();
            }
        }
    }*/

    void Telemetry () {
        telemetry.addData("FR_Power", "%.2f",robot.FrontRight.getPower());
        telemetry.addData("RR_Power", "%.2f",robot.RearRight.getPower());
        telemetry.addData("FL_Power", "%.2f",robot.FrontLeft.getPower());
        telemetry.addData("RL_Power", "%.2f",robot.RearLeft.getPower());
        telemetry.addData("Front Right Encoder Position", robot.FrontRight.getCurrentPosition());
        telemetry.addData("Front Right Encoder Target", robot.FrontRight.getTargetPosition());
        telemetry.addData("Rear Right Encoder Position", robot.RearRight.getCurrentPosition());
        telemetry.addData("Rear Right Encoder Target", robot.RearRight.getTargetPosition());
        telemetry.addData("Front Left Encoder Position", robot.FrontLeft.getCurrentPosition());
        telemetry.addData("Front Left Encoder Target", robot.FrontLeft.getTargetPosition());
        telemetry.addData("Rear Left Encoder Position", robot.RearLeft.getCurrentPosition());
        telemetry.addData("Rear Left Encoder Target", robot.RearLeft.getTargetPosition());
        telemetry.update();
    }
}