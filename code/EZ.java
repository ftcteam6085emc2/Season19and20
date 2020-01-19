package org.firstinspires.ftc.teamcode.Season19and20.code;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "Forward", group = "Autonomous")

public class EZ extends LinearOpMode{
    HWMapTouchdown robot = new HWMapTouchdown();
    public void runOpMode() {
        robot.init(hardwareMap);
        robot.RearRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        robot.RearLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        robot.ArmLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        robot.ArmRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        waitForStart();

        DriveStraightDistance(500, 0.5);
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
        while((robot.FrontRight.isBusy() || robot.RearLeft.isBusy() || robot.RearRight.isBusy() || robot.FrontLeft.isBusy()) && opModeIsActive()){
            idle();
        }

        DriveStraight(0);
        robot.FrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.FrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.RearRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.RearLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
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
}
