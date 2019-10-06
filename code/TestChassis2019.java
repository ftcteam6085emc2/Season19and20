package org.firstinspires.ftc.teamcode.Season19and20;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Season18and19.HWMapTest;

@TeleOp(name="TestChassis2019", group="Test")
public class TestChassis2019 extends OpMode {


    private static final double STRAFE_POWER = 0.7;
    HWMapTest robot = new HWMapTest();

    @Override
    public void init() {
        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        //robot.FrontRight.setTargetPosition(32);
        //robot.RearRight.setTargetPosition(32);
        //robot.FrontLeft.setTargetPosition(32);
        //robot.RearLeft.setTargetPosition(32);

        robot.init(hardwareMap);
        // Send telemetry message to signify robot waiting;
        telemetry.addData("Waiting");    //
    }

    @Override
    public void loop() {
        Telemetry();
        double left;
        double right;
        double Forward;
        double Backward;

        // Run wheels in tank mode (note: The joystick goes negative when pushed forwards, so negate it)
        left = -gamepad1.left_stick_y;
        right = -gamepad1.right_stick_y;

        //Strafe Right
        if (gamepad1.dpad_right) {
            //Right Side
            robot.FrontRight.setPower(STRAFE_POWER * 1.1);
            robot.RearRight.setPower(-STRAFE_POWER);

            //Left Side
            robot.FrontLeft.setPower(STRAFE_POWER);
            robot.RearLeft.setPower(-STRAFE_POWER * 1.1);
        } else if (gamepad1.dpad_left) {
            //Strafe Right

            //Right Side
            robot.FrontRight.setPower(-STRAFE_POWER * 1.1);
            robot.RearRight.setPower(STRAFE_POWER);

            //Left Side
            robot.FrontLeft.setPower(-STRAFE_POWER);
            robot.RearLeft.setPower(STRAFE_POWER * 1.1);
        } else {
            //Strafe Left

            //Left Side
            robot.FrontLeft.setPower(left);
            robot.RearLeft.setPower(left);

            //Right Side
            robot.FrontRight.setPower(-right);
            robot.RearRight.setPower(-right);
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
