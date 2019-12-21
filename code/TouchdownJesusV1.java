package org.firstinspires.ftc.teamcode.Season19and20.code;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="TouchdownJesusV1", group="Test")
public class TouchdownJesusV1 extends OpMode {


    private static final double STRAFE_POWER = 0.7;
    private double ArmPower = 0.4;
    private double SpinPower = 1.0;
    boolean strafeCheck = false;
    HWMapTouchdown robot = new HWMapTouchdown();


    @Override
    public void init() {
        robot.init(hardwareMap);
        robot.FrontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        robot.FrontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        robot.RearRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        robot.RearLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    @Override
    public void loop() {
        Telemetry();
        double left;
        double right;

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        if(gamepad1.x) {
            robot.SpinRight.setPower(-SpinPower);
            robot.SpinLeft.setPower(SpinPower);
        }
        else if(gamepad1.b) {
            robot.SpinRight.setPower(SpinPower);
            robot.SpinLeft.setPower(-SpinPower);
        }
        else {
            robot.SpinLeft.setPower(0);
            robot.SpinRight.setPower(0);
        }

        if(gamepad1.left_bumper){
            robot.GrabRight.setPosition(0.7);
            robot.GrabLeft.setPosition(0.7);
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

        /*if (gamepad1.dpad_up) {
            ArmPower += 0.1;
        }
        if (gamepad1.dpad_down) {
            ArmPower -= 0.1;
        }*/
        left = -gamepad1.left_stick_y;
        right = -gamepad1.right_stick_y;

        if(gamepad1.left_trigger > 0 || gamepad1.right_trigger > 0){
            strafeCheck = true;
        }
        else {
            strafeCheck = false;
        }
        if (strafeCheck == true && (gamepad1.dpad_right || gamepad1.left_stick_x > 0 || gamepad1.right_stick_x > 0)) {
            //Strafe Right
            robot.FrontRight.setPower(STRAFE_POWER * 1.1);
            robot.RearRight.setPower(-STRAFE_POWER);

            robot.FrontLeft.setPower(STRAFE_POWER);
            robot.RearLeft.setPower(-STRAFE_POWER * 1.1);
        } else if (strafeCheck == true && (gamepad1.dpad_left || gamepad1.left_stick_x < 0 || gamepad1.right_stick_x < 0)) {
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
