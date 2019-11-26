package org.firstinspires.ftc.teamcode.Season19and20.code;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "hexMotorPositions", group = "Autonomous")

public class hexMotorPositions extends LinearOpMode {

    private boolean check = false;
    private static int firstUp = 500;
    HWMapTest robot = new HWMapTest();

    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);

        waitForStart();
        telemetry.addData("hexMotor Position", robot.hexMotor.getCurrentPosition());
        telemetry.update();

        sleep(1000);
        InchUp();
    }

    void InchUp(){
        while (check == false) {
            if (robot.hexMotor.getCurrentPosition() < firstUp){
                robot.hexMotor.setPower(0.1);
            }
            else{
                robot.hexMotor.setPower(0);
                check = true;
            }
            telemetry.addData("hexMotor Position", robot.hexMotor.getCurrentPosition());
            telemetry.update();
        }
    }
}