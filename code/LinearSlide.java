package org.firstinspires.ftc.teamcode.Season19and20.code;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@Disabled
@TeleOp

public class LinearSlide extends LinearOpMode {
    private DcMotor hexMotor;

    @Override
    public void runOpMode() {
        DcMotor hexMotor = hardwareMap.get(DcMotor.class, "hexMotor");

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)

        double tgtPower = 0.1;
        hexMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        while (opModeIsActive()) {
            //tgtPower = -this.gamepad1.left_stick_y;
            //hexMotor.setPower(tgtPower);
            // check to see if we need to move the servo.

            if(gamepad1.y) {
                // slide up.
                hexMotor.setTargetPosition(1000);
                hexMotor.setPower(0.75);
                hexMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            } else if (gamepad1.a) {
                // slide down
                hexMotor.setTargetPosition(-1000);
                hexMotor.setPower(-0.75);
                hexMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            }

            int position = hexMotor.getCurrentPosition();
            telemetry.addData("Encoder Position", position);

            telemetry.addData("Target Power", tgtPower);
            telemetry.addData("Motor Power", hexMotor.getPower());
            telemetry.addData("Status", "Running");
            telemetry.update();

        }
    }
}
