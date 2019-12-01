package org.firstinspires.ftc.teamcode.Season19and20.code;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "hexMotorPositions", group = "Autonomous")

public class hexMotorPositions extends LinearOpMode {

    private double a = 0;
    private static int firstUp = 10;
    HWMapTest robot = new HWMapTest();

    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);
        robot.hexMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.hexMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();
        telemetry.addData("hexMotor Position", robot.hexMotor.getCurrentPosition());
        telemetry.update();
        InchUp();
        sleep(1000);
    }

    void InchUp(){
        boolean check = false;
        while (check == false) {
            if (robot.hexMotor.getCurrentPosition() < firstUp){
                robot.hexMotor.setPower(0.3);
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