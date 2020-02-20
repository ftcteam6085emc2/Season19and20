package org.firstinspires.ftc.teamcode.Season19and20.code;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="DUCK", group="Outreach")
public class Duck extends OpMode {
    public boolean slow = true;
    public boolean slowCheck = false;

    DuckHM robot = new DuckHM();

    @Override
    public void init() {robot.init(hardwareMap);}

    @Override
    public void loop() {
        double left;
        double right;

        if(gamepad1.start && !slowCheck){
            slow = !slow;
            slowCheck = true;
        }
        else if (!gamepad1.start){
            slowCheck = false;
        }

        left = gamepad1.left_stick_y;
        right = -gamepad1.right_stick_y;
        if(!slow) {
            robot.leftMotor.setPower(left);
            robot.rightMotor.setPower(right);
        }
        else{
            robot.leftMotor.setPower(left/2);
            robot.rightMotor.setPower(right/2);
        }
        telemetry.addData("left", "%.2f", left);
        telemetry.addData("right", "%.2f", right);
    }
}