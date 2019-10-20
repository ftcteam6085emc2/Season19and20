package org.firstinspires.ftc.teamcode.Season19and20;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp

public class MotorTestV2 extends LinearOpMode {
    private DcMotor hexMotor;
    private Servo servoTest;


    @Override
    public void runOpMode() {
        DcMotor hexMotor = hardwareMap.get(DcMotor.class, "hexMotor");
        servoTest = hardwareMap.get(Servo.class, "servoTest");

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        servoTest.setPosition(0);
        waitForStart();

        while (opModeIsActive()) {
            if(gamepad1.y) {
                hexMotor.setPower(-0.25);
            }
            else if (gamepad1.a) {
                hexMotor.setPower(0.25);
            }
            else {
                hexMotor.setPower(0);
            }
            if(gamepad1.left_bumper){
                servoTest.setPosition(0);
            }
            if(gamepad1.right_bumper){
                servoTest.setPosition(0.3);
            }
            if(gamepad1.dpad_right){
                servoTest.setPosition(-0.25);
            }
            if(gamepad1.dpad_left){
                servoTest.setPosition(0.1);
            }
            telemetry.addData("Motor Power", hexMotor.getPower());
            telemetry.addData("Servo Position", servoTest.getPosition());
            telemetry.addData("Status", "Running");
            telemetry.update();

        }
    }
}
