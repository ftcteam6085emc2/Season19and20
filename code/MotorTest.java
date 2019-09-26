package org.firstinspires.ftc.teamcode.Season19and20;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AngularVelocity;

@TeleOp

public class MotorTest extends LinearOpMode {
    private Gyroscope imu;
    private DcMotor hexMotor;
    private DigitalChannel digitalTouch;
    private DistanceSensor sensorColorRange;
    private Servo servoTest;


    @Override
    public void runOpMode() {
        imu = hardwareMap.get(Gyroscope.class, "imu");
        DcMotor hexMotor = hardwareMap.get(DcMotor.class, "hexMotor");
        //digitalTouch = hardwareMap.get(DigitalChannel.class, "digitalTouch");
        //sensorColorRange = hardwareMap.get(DistanceSensor.class, "sensorColorRange");
        servoTest = hardwareMap.get(Servo.class, "servoTest");

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)

        AngularVelocity degrees;
        double tgtPower = 0;
        while (opModeIsActive()) {
            degrees = imu.getAngularVelocity(AngleUnit.DEGREES);
            tgtPower = -this.gamepad1.left_stick_y;
            hexMotor.setPower(tgtPower);
            // check to see if we need to move the servo.
            if(gamepad1.y) {
                // move to 0 degrees.
                hexMotor.setPower(-0.5);
            }// else if (gamepad1.x || gamepad1.b) {
                // move to 90 degrees.
             //   hexMotor.setPower(0.1);
             else if (gamepad1.a) {
                // move to 180 degrees.
                hexMotor.setPower(0.5);
            }
            telemetry.addData("Target Power", tgtPower);
            telemetry.addData("Motor Power", hexMotor.getPower());
            telemetry.addData("Status", "Running");
            telemetry.addData("imu", Float.toString(degrees.xRotationRate));
            telemetry.update();

        }
    }
}
