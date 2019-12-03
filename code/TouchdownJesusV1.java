package org.firstinspires.ftc.teamcode.Season19and20.code;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="TouchdownJesusV1", group="Test")
public class TouchdownJesusV1 extends OpMode {


    private static final double STRAFE_POWER = 0.7;
    HWMapTest robot = new HWMapTest();


    @Override
    public void init() {
        robot.init(hardwareMap);
        robot.servoTest.setPosition(0);
    }

    @Override
    public void loop() {
        Telemetry();
        double left;
        double right;

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        if(gamepad1.x) {
            robot.SpinRight.setPower(-0.5);
            robot.SpinLeft.setPower(0.5);
        }
        else if(gamepad1.b) {
            robot.SpinRight.setPower(0.5);
            robot.SpinLeft.setPower(-0.5);
        }

        if(gamepad1.left_bumper){
            robot.GrabRight.setPosition(0.5);
            robot.GrabLeft.setPosition(-0.3);
        }
        if(gamepad1.right_bumper){
            robot.GrabRight.setPosition(-0.3);
            robot.GrabLeft.setPosition(0.5);
        }

        if(gamepad1.y){
            robot.ArmRight.setPower(-0.2);
            robot.ArmLeft.setPower(0.2);
        }
        else if(gamepad1.a){
            robot.ArmRight.setPower(0.2);
            robot.ArmLeft.setPower(-0.2);
        }

        telemetry.addData("Motor Power", robot.hexMotor.getPower());
        telemetry.addData("Servo Position", robot.servoTest.getPosition());
        telemetry.addData("Status", "Running");
        telemetry.update();

        left = -gamepad1.left_stick_y;
        right = -gamepad1.right_stick_y;

        if (gamepad1.dpad_right) {
            //Strafe Right
            robot.FrontRight.setPower(STRAFE_POWER * 1.1);
            robot.RearRight.setPower(-STRAFE_POWER);

            robot.FrontLeft.setPower(STRAFE_POWER);
            robot.RearLeft.setPower(-STRAFE_POWER * 1.1);
        } else if (gamepad1.dpad_left) {
            //Strafe Left
            robot.FrontRight.setPower(-STRAFE_POWER * 1.1);
            robot.RearRight.setPower(STRAFE_POWER);

            robot.FrontLeft.setPower(-STRAFE_POWER);
            robot.RearLeft.setPower(STRAFE_POWER * 1.1);
        } else {
            robot.FrontLeft.setPower(left);
            robot.RearLeft.setPower(left);

            robot.FrontRight.setPower(-right);
            robot.RearRight.setPower(-right);
        }
    }
    public void Movent(){
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
