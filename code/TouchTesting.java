package org.firstinspires.ftc.teamcode.Season19and20.code;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.TouchSensor;

//Configure touch sensor as digital device in slot 2 in digital devices
@Disabled
@TeleOp(name = "Sensor: Digital touch", group = "Sensor")
public class TouchTesting extends LinearOpMode {

    HWMapTouchdown robot = new HWMapTouchdown();

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        waitForStart();
        while (opModeIsActive()) {
            if (robot.touchSensor.isPressed() == true) {
                telemetry.addData("Digital Touch", "Is Pressed");
            } else {
                telemetry.addData("Digital Touch", "Is Not Pressed");
            }

            telemetry.update();
        }
    }
}
