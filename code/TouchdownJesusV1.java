package org.firstinspires.ftc.teamcode.Season19and20.code;

import android.media.AudioManager;
import android.media.MediaPlayer;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.R;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import java.io.IOException;

@TeleOp(name="TouchdownJesusV1", group="Test")
public class TouchdownJesusV1 extends OpMode {


    private static final double STRAFE_POWER = 0.7;
    private double ArmPower = 0.4;
    private double SpinPower = 1.0;
    private double Multiplier = 1.0;
    private int currentPos = 0;
    private boolean SpinCheck = false;
    private boolean Swap1 = false;
    private boolean Swap2 = false;
    private boolean FULLPOWER = false;
    private boolean halfpower = false;
    HWMapTouchdown robot = new HWMapTouchdown();
    MediaPlayer mediaPlayer = new MediaPlayer();


    @Override
    public void init() {
        robot.init(hardwareMap);
        robot.FrontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        robot.FrontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        robot.RearRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        robot.RearLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        String url = "https://www.youtube.com/watch?v=cuOEv1jCivg"; // your URL here
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mediaPlayer.setDataSource(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loop() {
        Telemetry();
        double left1 = -gamepad1.left_stick_y;
        double right1 = gamepad1.right_stick_y;
        double leftx1 = -gamepad1.left_stick_x;
        double rightx1 = -gamepad1.right_stick_x;
        if(FULLPOWER == true){
            Multiplier = 10;
        }
        else if(halfpower == true){
            Multiplier = 0.1;
        }
        else{
            Multiplier = 1;
        }

        if(gamepad1.dpad_down && Swap1 == false){
            SpinCheck = true;
            Swap1 = true;
        }
        if(gamepad1.dpad_down && Swap1 == true){
            SpinCheck = false;
            Swap1 = false;
        }
        if(gamepad1.x) {
            robot.SpinRight.setPower(-SpinPower);
            robot.SpinLeft.setPower(SpinPower);
        }
        else if(gamepad1.b) {
            robot.SpinRight.setPower(SpinPower);
            robot.SpinLeft.setPower(-SpinPower);
        }
        else if (SpinCheck == false) {
            robot.SpinLeft.setPower(0);
            robot.SpinRight.setPower(0);
        }

        if(gamepad1.left_bumper){
            robot.GrabRight.setPosition(0.6);
            robot.GrabLeft.setPosition(0.6);
        }
        if(gamepad1.right_bumper){
            robot.GrabRight.setPosition(0.2);
            robot.GrabLeft.setPosition(0.2);
        }

        if(gamepad1.y){
            robot.ArmRight.setPower(ArmPower);
            robot.ArmLeft.setPower(-ArmPower);
        }
        else if(gamepad1.a){
            robot.ArmRight.setPower(-ArmPower);
            robot.ArmLeft.setPower(ArmPower);
        }
        else {
            robot.ArmRight.setPower(0);
            robot.ArmLeft.setPower(0);
        }
        if(gamepad1.dpad_left){
            RevFlip();
        }
        if(gamepad1.dpad_right){
            Flip();
        }
        if(gamepad1.dpad_up){
            Center();
        }
        if(gamepad1.start && Swap2 == false){
            FULLPOWER = true;
            Swap2 = true;
        }
        else if(gamepad1.start && Swap2 == true){
            FULLPOWER = false;
            Swap2 = false;
        }
        if(gamepad1.back && Swap2 == false){
            halfpower = true;
            Swap2 = true;
        }
        else if(gamepad1.back && Swap2 == true){
            halfpower = false;
            Swap2 = false;
        }
        if(gamepad1.left_stick_button){
            try {
                mediaPlayer.prepare();
                mediaPlayer.start();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        double left2 = -gamepad2.left_stick_y;
        double right2 = gamepad2.right_stick_y;
        double leftx2 = -gamepad2.left_stick_x;
        double rightx2 = -gamepad2.right_stick_x;

        if(gamepad2.dpad_down && Swap1 == false){
            SpinCheck = true;
            Swap1 = true;
        }
        if(gamepad2.dpad_down && Swap1 == true){
            SpinCheck = false;
            Swap1 = false;
        }
        if(gamepad2.x) {
            robot.SpinRight.setPower(-SpinPower);
            robot.SpinLeft.setPower(SpinPower);
        }
        else if(gamepad2.b) {
            robot.SpinRight.setPower(SpinPower);
            robot.SpinLeft.setPower(-SpinPower);
        }
        else if (SpinCheck == false) {
            robot.SpinLeft.setPower(0);
            robot.SpinRight.setPower(0);
        }

        if(gamepad2.left_bumper){
            robot.GrabRight.setPosition(0.6);
            robot.GrabLeft.setPosition(0.6);
        }
        if(gamepad2.right_bumper){
            robot.GrabRight.setPosition(0.2);
            robot.GrabLeft.setPosition(0.2);
        }

        if(gamepad2.y){
            robot.ArmRight.setPower(ArmPower);
            robot.ArmLeft.setPower(-ArmPower);
        }
        else if(gamepad2.a){
            robot.ArmRight.setPower(-ArmPower);
            robot.ArmLeft.setPower(ArmPower);
        }
        else {
            robot.ArmRight.setPower(0);
            robot.ArmLeft.setPower(0);
        }
        if(gamepad2.start && Swap2 == false){
            FULLPOWER = true;
            Swap2 = true;
        }
        else if(gamepad2.start && Swap2 == true){
            FULLPOWER = false;
            Swap2 = false;
        }
        if(gamepad2.back && Swap2 == false){
            halfpower = true;
            Swap2 = true;
        }
        else if(gamepad2.back && Swap2 == true){
            halfpower = false;
            Swap2 = false;
        }
        if(gamepad2.left_stick_button){
            try {
                mediaPlayer.prepare();
                mediaPlayer.start();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        if(gamepad1.left_trigger > 0 || gamepad1.right_trigger > 0){
            robot.FrontLeft.setPower(left1-leftx1);
            robot.RearLeft.setPower(left1+leftx1);

            robot.FrontRight.setPower(right1-rightx1);
            robot.RearRight.setPower(right1+rightx1);
        }
        else {
            robot.FrontLeft.setPower(left1);
            robot.RearLeft.setPower(left1);

            robot.FrontRight.setPower(right1);
            robot.RearRight.setPower(right1);
        }
            /*if (gamepad1.left_trigger > 0 || gamepad1.right_trigger > 0) {
                robot.FrontLeft.setPower((left1 + leftx1)*Multiplier);
                robot.RearLeft.setPower((left1 - leftx1)*Multiplier);

                robot.FrontRight.setPower((right1 - rightx1)*Multiplier);
                robot.RearRight.setPower((right1 + rightx1)*Multiplier);
            } else {
                robot.FrontLeft.setPower(left1*Multiplier);
                robot.RearLeft.setPower(left1*Multiplier);

                robot.FrontRight.setPower(-right1*Multiplier);
                robot.RearRight.setPower(-right1*Multiplier);
            }
            if (gamepad2.left_trigger > 0 || gamepad2.right_trigger > 0) {
                robot.FrontLeft.setPower((left2 + leftx2)*Multiplier);
                robot.RearLeft.setPower((left2 - leftx2)*Multiplier);

                robot.FrontRight.setPower((right2 - rightx2)*Multiplier);
                robot.RearRight.setPower((right2 + rightx2)*Multiplier);
            } else {
                robot.FrontLeft.setPower(left2*Multiplier);
                robot.RearLeft.setPower(left2*Multiplier);

                robot.FrontRight.setPower(-right2*Multiplier);
                robot.RearRight.setPower(-right2*Multiplier);
            }*/

    }
    /*public void Movent(){
        robot.hexMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.hexMotor.setTargetPosition(0);
        robot.hexMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        int pos = robot.hexMotor.getCurrentPosition();
        robot.hexMotor.setTargetPosition(pos);
        if (robot.hexMotor.getCurrentPosition() > pos) {
            robot.hexMotor.setPower(-0.75);
        } else if (robot.hexMotor.getCurrentPosition() < pos) {
            robot.hexMotor.setPower(0.75);
        } else if (robot.hexMotor.getCurrentPosition() > pos - 50 || robot.hexMotor.getCurrentPosition() < pos + 50) {
            robot.hexMotor.setPower(0);
        }
    }*/
    void Flip (){
        robot.ArmLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.ArmRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.ArmLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.ArmLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        if(currentPos == 0) {
            for (int i = 140; i <= 560; i += 140) {
                robot.ArmLeft.setTargetPosition(i);
                robot.ArmRight.setTargetPosition(i);
                robot.ArmLeft.setPower(0.25);
                robot.ArmRight.setPower(0.25);
                while (robot.ArmLeft.isBusy() || robot.ArmRight.isBusy()) {
                    telemetry.addData("encoder-ArmLeft", robot.ArmLeft.getCurrentPosition() + "  busy=" + robot.ArmLeft.isBusy());
                    telemetry.addData("encoder-ArmRight", robot.ArmRight.getCurrentPosition() + "  busy=" + robot.ArmRight.isBusy());
                    telemetry.update();
                }
                robot.ArmLeft.setPower(0);
                robot.ArmRight.setPower(0);
            }
        }
        else if(currentPos == 1){
            for (int i = 140; i <= 280; i += 140) {
                robot.ArmLeft.setTargetPosition(i);
                robot.ArmRight.setTargetPosition(i);
                robot.ArmLeft.setPower(0.25);
                robot.ArmRight.setPower(0.25);
                while (robot.ArmLeft.isBusy() || robot.ArmRight.isBusy()) {
                    telemetry.addData("encoder-ArmLeft", robot.ArmLeft.getCurrentPosition() + "  busy=" + robot.ArmLeft.isBusy());
                    telemetry.addData("encoder-ArmRight", robot.ArmRight.getCurrentPosition() + "  busy=" + robot.ArmRight.isBusy());
                    telemetry.update();
                }
                robot.ArmLeft.setPower(0);
                robot.ArmRight.setPower(0);
            }
        }
        robot.GrabRight.setPosition(0.6);
        robot.GrabLeft.setPosition(0.6);
        robot.SpinRight.setPower(0);
        robot.SpinLeft.setPower(0);
        currentPos = 2;
    }

    void RevFlip() {
        robot.ArmLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.ArmRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.ArmLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.ArmLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        if(currentPos == 2) {
            for (int i = -140; i >= -560; i -= 140) {
                robot.ArmLeft.setTargetPosition(i);
                robot.ArmRight.setTargetPosition(i);
                robot.ArmLeft.setPower(-0.25);
                robot.ArmRight.setPower(-0.25);
                while (robot.ArmLeft.isBusy() || robot.ArmRight.isBusy()) {
                    telemetry.addData("encoder-ArmLeft", robot.ArmLeft.getCurrentPosition() + "  busy=" + robot.ArmLeft.isBusy());
                    telemetry.addData("encoder-ArmRight", robot.ArmRight.getCurrentPosition() + "  busy=" + robot.ArmRight.isBusy());
                    telemetry.update();
                }
                robot.ArmLeft.setPower(0);
                robot.ArmRight.setPower(0);
                if (i == -280) {
                    robot.GrabRight.setPosition(0.2);
                    robot.GrabLeft.setPosition(0.2);
                }
            }
        }
        else if(currentPos == 1){
            for (int i = -140; i >= -280; i -= 140) {
                robot.ArmLeft.setTargetPosition(i);
                robot.ArmRight.setTargetPosition(i);
                robot.ArmLeft.setPower(-0.25);
                robot.ArmRight.setPower(-0.25);
                while (robot.ArmLeft.isBusy() || robot.ArmRight.isBusy()) {
                    telemetry.addData("encoder-ArmLeft", robot.ArmLeft.getCurrentPosition() + "  busy=" + robot.ArmLeft.isBusy());
                    telemetry.addData("encoder-ArmRight", robot.ArmRight.getCurrentPosition() + "  busy=" + robot.ArmRight.isBusy());
                    telemetry.update();
                }
                robot.ArmLeft.setPower(0);
                robot.ArmRight.setPower(0);
            }
        }
        robot.GrabRight.setPosition(0.6);
        robot.GrabLeft.setPosition(0.6);
        currentPos = 0;
    }
    void Center (){
        robot.ArmLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.ArmRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.ArmLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.ArmLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        if(currentPos == 0) {
            for (int i = 140; i <= 280; i += 140) {
                robot.ArmLeft.setTargetPosition(i);
                robot.ArmRight.setTargetPosition(i);
                robot.ArmLeft.setPower(0.25);
                robot.ArmRight.setPower(0.25);
                while (robot.ArmLeft.isBusy() || robot.ArmRight.isBusy()) {
                    telemetry.addData("encoder-ArmLeft", robot.ArmLeft.getCurrentPosition() + "  busy=" + robot.ArmLeft.isBusy());
                    telemetry.addData("encoder-ArmRight", robot.ArmRight.getCurrentPosition() + "  busy=" + robot.ArmRight.isBusy());
                    telemetry.update();
                }
                robot.ArmLeft.setPower(0);
                robot.ArmRight.setPower(0);
            }
        }
        else if(currentPos == 2){
            for (int i = -140; i <= -280; i -= 140) {
                robot.ArmLeft.setTargetPosition(i);
                robot.ArmRight.setTargetPosition(i);
                robot.ArmLeft.setPower(-0.25);
                robot.ArmRight.setPower(-0.25);
                while (robot.ArmLeft.isBusy() || robot.ArmRight.isBusy()) {
                    telemetry.addData("encoder-ArmLeft", robot.ArmLeft.getCurrentPosition() + "  busy=" + robot.ArmLeft.isBusy());
                    telemetry.addData("encoder-ArmRight", robot.ArmRight.getCurrentPosition() + "  busy=" + robot.ArmRight.isBusy());
                    telemetry.update();
                }
                robot.ArmLeft.setPower(0);
                robot.ArmRight.setPower(0);
            }
        }
        robot.GrabRight.setPosition(0.6);
        robot.GrabLeft.setPosition(0.6);
        robot.SpinRight.setPower(0);
        robot.SpinLeft.setPower(0);
        currentPos = 1;
    }

    public void Telemetry () {
        telemetry.addData("FR_Power", "%.2f", robot.FrontRight.getPower());
        telemetry.addData("RR_Power", "%.2f", robot.RearRight.getPower());
        telemetry.addData("FL_Power", "%.2f", robot.FrontLeft.getPower());
        telemetry.addData("RL_Power", "%.2f", robot.RearLeft.getPower());
        telemetry.addData("Front Right Encoder Position", robot.FrontRight.getCurrentPosition());
        telemetry.addData("Rear Right Encoder Position", robot.RearRight.getCurrentPosition());
        telemetry.addData("Front Left Encoder Position", robot.FrontLeft.getCurrentPosition());
        telemetry.addData("Rear Left Encoder Position", robot.RearLeft.getCurrentPosition());
        telemetry.update();
    }
}
