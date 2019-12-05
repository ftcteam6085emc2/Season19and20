package org.firstinspires.ftc.teamcode.Season19and20.code;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "hexMotorPositions", group = "Autonomous")

public class hexMotorPositions extends LinearOpMode {

    private double a = 0;
    private static int firstUp = 5;
    HWMapTest robot = new HWMapTest();

    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);
        waitForStart();

        telemetry.addData("hexMotor Position", robot.hexMotor.getCurrentPosition());
        telemetry.update();
        InchUp();
        sleep(1000);
    }

    void InchUp(){
        robot.hexMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        robot.hexMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.hexMotor.setTargetPosition(5);
        robot.hexMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.hexMotor.setPower(0.1);
    }
}