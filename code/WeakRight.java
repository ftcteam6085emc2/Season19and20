package org.firstinspires.ftc.teamcode.Season19and20.code;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "WeakRight", group = "Autonomous")

public class WeakRight extends LinearOpMode {

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
        robot.FrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.FrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.RearRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.RearLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.FrontRight.setTargetPosition(0);
        robot.FrontLeft.setTargetPosition(0);
        robot.RearRight.setTargetPosition(0);
        robot.RearLeft.setTargetPosition(0);
        robot.FrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.FrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.RearRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.RearLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.ArmLeft.setTargetPosition(0);
        robot.ArmRight.setTargetPosition(0);
        robot.GrabRight.setPosition(0.1);
        robot.GrabLeft.setPosition(0.18);

        // 1 tile = 23.5 inches
        waitForStart();
        DriveStraightDistance(2500, 0.8);
        Strafe(-1000, -0.8);
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
        robot.FrontRight.setTargetPosition(robot.FrontRight.getCurrentPosition()-distance);
        robot.FrontLeft.setTargetPosition(robot.FrontLeft.getCurrentPosition()+distance);
        robot.RearRight.setTargetPosition(robot.RearRight.getCurrentPosition()-distance);
        robot.RearLeft.setTargetPosition(robot.RearLeft.getCurrentPosition()+distance);

        DriveStraight(power);
        while((robot.FrontRight.isBusy() && robot.RearLeft.isBusy() && robot.RearRight.isBusy() && robot.FrontLeft.isBusy()) && opModeIsActive()){
            idle();
        }

        StopDriving();
    }

    private void Strafe (int distance, double power){
        telemetry.addData("Driving", "Yes");
        robot.FrontRight.setTargetPosition(robot.FrontRight.getCurrentPosition()+distance);
        robot.FrontLeft.setTargetPosition(robot.FrontLeft.getCurrentPosition()+distance);
        robot.RearRight.setTargetPosition(robot.RearRight.getCurrentPosition()-distance);
        robot.RearLeft.setTargetPosition(robot.RearLeft.getCurrentPosition()-distance);

        robot.FrontRight.setPower(power);
        robot.FrontLeft.setPower(power);
        robot.RearRight.setPower(-power);
        robot.RearLeft.setPower(-power);

        while((robot.FrontRight.isBusy() && robot.RearLeft.isBusy() && robot.RearRight.isBusy() && robot.FrontLeft.isBusy()) && opModeIsActive()){
            idle();
        }

        StopDriving();
    }
    //NeveRest 40 Gearmotor has 280 ppr (Wheels) 1120 for full rotation
    //NeveRest 20 Gearmotor has 140 ppr (Arm) 560 for full rotation
}