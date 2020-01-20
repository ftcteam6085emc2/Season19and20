package org.firstinspires.ftc.teamcode.Season19and20.code;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "EncoderCheck", group = "Autonomous")

public class EncoderCheck extends LinearOpMode {
    HWMapTouchdown robot = new HWMapTouchdown();

    public void runOpMode() {
        robot.init(hardwareMap);
        robot.ArmLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        robot.ArmRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        robot.ArmLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.ArmRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
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
        }
        for (int i = -225; i >= -900; i -= 225) {
            robot.ArmLeft.setTargetPosition(i);
            robot.ArmRight.setTargetPosition(-i);
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
        }
    }
}
