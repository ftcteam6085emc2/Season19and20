package org.firstinspires.ftc.teamcode.Season19and20.code;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Disabled
@Autonomous(name = "AutoTouchdownSkystonesRed", group = "Autonomous")

public class AutoTouchdownSkystonesRed extends LinearOpMode {

    private boolean strafeCancel = false;
    private int currentPos = 0;
    HWMapTouchdown robot = new HWMapTouchdown();

    public void runOpMode() {
        robot.init(hardwareMap);
        robot.FrontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        robot.FrontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        robot.RearRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        robot.RearLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        robot.ArmLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        robot.ArmRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        robot.SpinLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        robot.SpinRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        robot.ArmLeft.setTargetPosition(0);
        robot.ArmRight.setTargetPosition(0);
        robot.GrabLeft.setPosition(0.18);
        robot.GrabRight.setPosition(0.18);

        // 1 tile = 23.5 inches
        waitForStart();
        robot.SpinRight.setPower(1.0);
        robot.SpinLeft.setPower(-1.0);
        DriveStraightDistance(3000, 0.8); //46 inches
        Turn(-1000, -0.8);
        DriveStraightDistance(1500, 0.8);
        sleep(100);
        robot.GrabRight.setPosition(-0.1);
        robot.GrabLeft.setPosition(-0.1);
        sleep(100);
        DriveStraightDistance(-1500, 0.8);
        Turn(1000, 0.8);
        DriveStraightDistance(-3360, -0.8); //12 inches
        Turn(-1600, -0.5);
        DriveStraightDistance(-7000, -0.8); //3.5 tiles
        Flip();
        RevFlip();
        DriveStraightDistance(2800, 0.8); //1 rotation less than 3.5 tiles
    }

    private void DriveStraight(double power){
        /*if(strafeCancel){
            robot.FrontRight.setPower(power - 0.2);
            robot.FrontLeft.setPower(-power);
            robot.RearRight.setPower(power);
            robot.RearLeft.setPower(-power - 0.2);
        }
        else {*/
        robot.FrontRight.setPower(-power);
        robot.FrontLeft.setPower(power);
        robot.RearRight.setPower(-power);
        robot.RearLeft.setPower(power);
        //}
    }

    private void StopDriving (){
        DriveStraight(0);
    }

    private void DriveStraightDistance(int distance, double power){
        telemetry.addData("Driving", "Yes");
        robot.FrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.FrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.RearRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.RearLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.FrontRight.setTargetPosition(-distance);
        robot.FrontLeft.setTargetPosition(distance);
        robot.RearRight.setTargetPosition(-distance);
        robot.RearLeft.setTargetPosition(distance);

        robot.FrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.FrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.RearRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.RearLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        DriveStraight(power);
        while((robot.FrontRight.isBusy() && robot.RearLeft.isBusy() && robot.RearRight.isBusy() && robot.FrontLeft.isBusy()) && opModeIsActive()){
            idle();
        }

        StopDriving();
        robot.FrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.FrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.RearRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.RearLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    private void Turn (int distance, double power){
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
        while((robot.FrontRight.isBusy() && robot.RearLeft.isBusy() && robot.RearRight.isBusy() && robot.FrontLeft.isBusy()) && opModeIsActive()){
            idle();
        }

        StopDriving();
        robot.FrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.FrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.RearRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.RearLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    /*left is negative, right is positive
    private void Strafe (int distance, double power){
        strafeCancel = true;
        telemetry.addData("Driving", "Yes");
        robot.FrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.FrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.RearRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.RearLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.FrontRight.setTargetPosition(distance);
        robot.FrontLeft.setTargetPosition(distance);
        robot.RearRight.setTargetPosition(-distance);
        robot.RearLeft.setTargetPosition(-distance);

        robot.FrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.FrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.RearRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.RearLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        robot.FrontRight.setPower(power);
        robot.FrontLeft.setPower(power);
        robot.RearRight.setPower(-power);
        robot.RearLeft.setPower(-power);

        while((robot.FrontRight.isBusy() || robot.RearLeft.isBusy() || robot.RearRight.isBusy() || robot.FrontLeft.isBusy()) && opModeIsActive()){
            idle();
        }

        StopDriving();
        robot.FrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.FrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.RearRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.RearLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        strafeCancel = false;
    }*/

    //NeveRest 40 Gearmotor has 280 ppr (Wheels) 1120 for full rotation
    //NeveRest 20 Gearmotor has 140 ppr (Arm) 560 for full rotation
    private void Flip (){
        robot.ArmLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.ArmRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        if(currentPos == 0) {
            for (int i = 225; i <= 900; i += 225) {
                robot.ArmLeft.setTargetPosition(i);
                robot.ArmRight.setTargetPosition(-i);
                robot.ArmLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.ArmLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.ArmLeft.setPower(0.25);
                robot.ArmRight.setPower(-0.25);
                while (robot.ArmLeft.isBusy() && robot.ArmRight.isBusy()) {
                    telemetry.addData("encoder-ArmLeft", robot.ArmLeft.getCurrentPosition() + "  busy=" + robot.ArmLeft.isBusy());
                    telemetry.addData("encoder-ArmRight", robot.ArmRight.getCurrentPosition() + "  busy=" + robot.ArmRight.isBusy());
                    telemetry.update();
                }
                robot.ArmLeft.setPower(0);
                robot.ArmRight.setPower(0);
                sleep(100);
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
                while (robot.ArmLeft.isBusy() && robot.ArmRight.isBusy()) {
                    telemetry.addData("encoder-ArmLeft", robot.ArmLeft.getCurrentPosition() + "  busy=" + robot.ArmLeft.isBusy());
                    telemetry.addData("encoder-ArmRight", robot.ArmRight.getCurrentPosition() + "  busy=" + robot.ArmRight.isBusy());
                    telemetry.update();
                }
                robot.ArmLeft.setPower(0);
                robot.ArmRight.setPower(0);
            }
        }
        robot.GrabRight.setPosition(0.18);
        robot.GrabLeft.setPosition(0.18);
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
            for (int i = -225; i >= -900; i -= 225) {
                robot.ArmLeft.setTargetPosition(i);
                robot.ArmRight.setTargetPosition(-i);
                robot.ArmLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.ArmLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.ArmLeft.setPower(-0.25);
                robot.ArmRight.setPower(0.25);
                while (robot.ArmLeft.isBusy() && robot.ArmRight.isBusy()) {
                    telemetry.addData("encoder-ArmLeft", robot.ArmLeft.getCurrentPosition() + "  busy=" + robot.ArmLeft.isBusy());
                    telemetry.addData("encoder-ArmRight", robot.ArmRight.getCurrentPosition() + "  busy=" + robot.ArmRight.isBusy());
                    telemetry.update();
                }
                robot.ArmLeft.setPower(0);
                robot.ArmRight.setPower(0);
                sleep(100);
                robot.GrabRight.setPosition(-0.1);
                robot.GrabLeft.setPosition(-0.1);
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
                while (robot.ArmLeft.isBusy() && robot.ArmRight.isBusy()) {
                    telemetry.addData("encoder-ArmLeft", robot.ArmLeft.getCurrentPosition() + "  busy=" + robot.ArmLeft.isBusy());
                    telemetry.addData("encoder-ArmRight", robot.ArmRight.getCurrentPosition() + "  busy=" + robot.ArmRight.isBusy());
                    telemetry.update();
                }
                robot.ArmLeft.setPower(0);
                robot.ArmRight.setPower(0);
            }
        }
        robot.GrabRight.setPosition(0.18);
        robot.GrabLeft.setPosition(0.18);
        robot.GrabLeft.setPosition(0.18);
        currentPos = 0;
        robot.ArmLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.ArmRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    /*private void Center (){
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
    }*/
}