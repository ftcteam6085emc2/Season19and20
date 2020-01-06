package org.firstinspires.ftc.teamcode.Season19and20.code;

import android.media.AudioManager;
import android.media.MediaPlayer;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import java.io.IOException;

@TeleOp(name="TouchdownJesusInversed", group="Test")
public class TouchdownJesusInversed extends OpMode {

    private int currentPos = 0;
    private double armPower = 0.3;
    private boolean SpinCheck = false;
    HWMapTouchdown robot = new HWMapTouchdown();
    private MediaPlayer mediaPlayer = new MediaPlayer();


    @Override
    public void init() {
        robot.init(hardwareMap);
        robot.FrontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        robot.FrontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        robot.RearRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        robot.RearLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        robot.ArmLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        robot.ArmRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        robot.SpinLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        robot.SpinRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        robot.ArmLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.ArmRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.ArmLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.ArmRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    @Override
    public void loop() {
        Telemetry();
        double left1 = -gamepad1.left_stick_y; //these are reversed for kevin
        double right1 = gamepad1.right_stick_y;
        double leftx1 = -gamepad1.left_stick_x;
        double rightx1 = -gamepad1.right_stick_x;

        if(gamepad1.start){
            robot.FoundationServo.setPosition(0.5);
        }
        if(gamepad1.back){
            robot.FoundationServo.setPosition(-0.5);
        }
        if(gamepad2.dpad_down){
            SpinCheck = !SpinCheck;
        }
        if(gamepad2.x) {
            robot.SpinRight.setPower(-1);
            robot.SpinLeft.setPower(1);
        }
        else if(gamepad2.b) {
            robot.SpinRight.setPower(1);
            robot.SpinLeft.setPower(-1);
        }
        else if (!SpinCheck) {
            robot.SpinRight.setPower(0);
            robot.SpinLeft.setPower(0);
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
            robot.ArmRight.setPower(armPower);
            robot.ArmLeft.setPower(-armPower);
        }
        else if(gamepad2.a){
            robot.ArmRight.setPower(-armPower);
            robot.ArmLeft.setPower(armPower);
        }
        else {
            robot.ArmRight.setPower(0);
            robot.ArmLeft.setPower(0);
        }
        if(gamepad2.dpad_left){
            RevFlip();
        }
        if(gamepad2.dpad_right){
            Flip();
        }
        if(gamepad2.dpad_up){
            Center();
        }

        if(gamepad1.left_trigger > 0 || gamepad1.right_trigger > 0){
            robot.FrontLeft.setPower(left1+leftx1);
            robot.RearLeft.setPower(left1-leftx1);

            robot.FrontRight.setPower(right1+rightx1);
            robot.RearRight.setPower(right1-rightx1);
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

    private void Flip (){
        robot.ArmLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.ArmRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        if(currentPos == 0) {
            for (int i = 225; i <= 1100; i += 225) {
                robot.ArmLeft.setTargetPosition(i);
                robot.ArmRight.setTargetPosition(-i);
                robot.ArmLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.ArmLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.ArmLeft.setPower(0.25);
                robot.ArmRight.setPower(-0.25);
                while (robot.ArmLeft.isBusy() || robot.ArmRight.isBusy()) {
                    telemetry.addData("encoder-ArmLeft", robot.ArmLeft.getCurrentPosition() + "  busy=" + robot.ArmLeft.isBusy());
                    telemetry.addData("encoder-ArmRight", robot.ArmRight.getCurrentPosition() + "  busy=" + robot.ArmRight.isBusy());
                    telemetry.update();
                }
                robot.ArmLeft.setPower(0);
                robot.ArmRight.setPower(0);
                robot.ArmLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.ArmRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            }
        }
        else if(currentPos == 1){
            for (int i = 225; i <= 550; i += 225) {
                robot.ArmLeft.setTargetPosition(i);
                robot.ArmRight.setTargetPosition(-i);
                robot.ArmLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.ArmLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.ArmLeft.setPower(0.25);
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
        currentPos = 2;
        robot.ArmLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.ArmRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    private void RevFlip() {
        robot.ArmLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.ArmRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        if(currentPos == 2) {
            for (int i = -225; i >= -1100; i -= 225) {
                robot.ArmLeft.setTargetPosition(i);
                robot.ArmRight.setTargetPosition(-i);
                robot.ArmLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.ArmLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.ArmLeft.setPower(-0.25);
                robot.ArmRight.setPower(0.25);
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
                robot.ArmLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.ArmRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            }
        }
        else if(currentPos == 1){
            for (int i = -225; i >= -550; i -= 225) {
                robot.ArmLeft.setTargetPosition(i);
                robot.ArmRight.setTargetPosition(-i);
                robot.ArmLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.ArmLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.ArmLeft.setPower(-0.25);
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
        currentPos = 0;
        robot.ArmLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.ArmRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    private void Center (){
        robot.ArmLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.ArmRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        if(currentPos == 0) {
            for (int i = 225; i <= 550; i += 225) {
                robot.ArmLeft.setTargetPosition(i);
                robot.ArmRight.setTargetPosition(-i);
                robot.ArmLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.ArmLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.ArmLeft.setPower(0.25);
                robot.ArmRight.setPower(-0.25);
                while (robot.ArmLeft.isBusy() || robot.ArmRight.isBusy()) {
                    telemetry.addData("encoder-ArmLeft", robot.ArmLeft.getCurrentPosition() + "  busy=" + robot.ArmLeft.isBusy());
                    telemetry.addData("encoder-ArmRight", robot.ArmRight.getCurrentPosition() + "  busy=" + robot.ArmRight.isBusy());
                    telemetry.update();
                }
                robot.ArmLeft.setPower(0);
                robot.ArmRight.setPower(0);
                robot.ArmLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.ArmRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            }
        }
        else if(currentPos == 2){
            for (int i = -225; i >= -550; i -= 225) {
                robot.ArmLeft.setTargetPosition(i);
                robot.ArmRight.setTargetPosition(-i);
                robot.ArmLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.ArmLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.ArmLeft.setPower(-0.25);
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
        currentPos = 1;
        robot.ArmLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.ArmRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    private void Pierre () {
        robot.ArmLeft.setTargetPosition(550);
        robot.ArmRight.setTargetPosition(-550);
        robot.ArmLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.ArmLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        if(currentPos == 0) {
            robot.ArmLeft.setPower(0.2);
            robot.ArmRight.setPower(-0.2);
        }
        if(currentPos == 2){
            robot.ArmLeft.setPower(-0.2);
            robot.ArmRight.setPower(0.2);
        }
        while (robot.ArmLeft.isBusy() && robot.ArmRight.isBusy()) {
            telemetry.addData("encoder-ArmLeft", robot.ArmLeft.getCurrentPosition() + "  busy=" + robot.ArmLeft.isBusy());
            telemetry.addData("encoder-ArmRight", robot.ArmRight.getCurrentPosition() + "  busy=" + robot.ArmRight.isBusy());
            telemetry.update();
        }
        robot.ArmLeft.setPower(0);
        robot.ArmRight.setPower(0);
        robot.ArmLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.ArmRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    private void Telemetry () {
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
