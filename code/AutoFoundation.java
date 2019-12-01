package org.firstinspires.ftc.teamcode.Season19and20.code;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "AutoFoundationONBOT", group = "Autonomous")

public class AutoFoundation extends LinearOpMode {

    private double a = 0;
    private static int firstUp = 10;
    HWMapTest robot = new HWMapTest();

    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);
        robot.hexMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.hexMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();
        Inch(true);
        sleep(3000);
        DriveStraightDistance(2500, 0.6);
        sleep(3000);
        Inch(false);
        sleep(3000);
        DriveStraightDistance(-2500, -0.6);
    }

    void DriveStraight(double power){
        robot.FrontRight.setPower(-power);
        robot.FrontLeft.setPower(power);
        robot.RearRight.setPower(-power);
        robot.RearLeft.setPower(power);
    }

    void StopDriving (){
        DriveStraight(0);
    }

    void DriveStraightDistance(int distance, double power){
        telemetry.addData("Driving", "Yes");
        robot.FrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.FrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.RearRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.RearLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.FrontRight.setTargetPosition(-distance);
        robot.FrontLeft.setTargetPosition(distance);
        robot.RearRight.setTargetPosition(-distance);
        robot.RearLeft.setTargetPosition(distance);

        robot.FrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.FrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.RearRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.RearLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        DriveStraight(power);
        a = 0;
        while((robot.FrontRight.isBusy() || robot.RearLeft.isBusy() || robot.RearRight.isBusy() || robot.FrontLeft.isBusy()) && a < 1500){
            if(((distance + 20) > robot.FrontRight.getCurrentPosition()) && (robot.FrontRight.getCurrentPosition() > (distance - 20))){
                a = 1500;
            }
        }

        StopDriving();
        robot.FrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.FrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.RearRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.RearLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    void Turn (int distance, double power){
        telemetry.addData("Driving", "Yes");
        robot.FrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.FrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.RearRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.RearLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.FrontRight.setTargetPosition(distance);
        robot.FrontLeft.setTargetPosition(distance);
        robot.RearRight.setTargetPosition(distance);
        robot.RearLeft.setTargetPosition(distance);

        robot.FrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.FrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.RearRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.RearLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        DriveStraight(power);
        a = 0;
        while((robot.FrontRight.isBusy() || robot.RearLeft.isBusy() || robot.RearRight.isBusy() || robot.FrontLeft.isBusy()) && a < 1500) {
            if(((distance + 20) > robot.FrontRight.getCurrentPosition()) && (robot.FrontRight.getCurrentPosition() > (distance - 20))){
                a = 1500;
            }
        }

        StopDriving();
        robot.FrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.FrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.RearRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.RearLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    void Strafe (int distance, double power){
        telemetry.addData("Driving", "Yes");
        robot.FrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.FrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.RearRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.RearLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.FrontRight.setTargetPosition(-distance);
        robot.FrontLeft.setTargetPosition(-distance);
        robot.RearRight.setTargetPosition(distance);
        robot.RearLeft.setTargetPosition(distance);

        robot.FrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.FrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.RearRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.RearLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        DriveStraight(power);
        a = 0;
        while((robot.FrontRight.isBusy() || robot.RearLeft.isBusy() || robot.RearRight.isBusy() || robot.FrontLeft.isBusy()) && a < 1500){
            if(((distance + 20) > robot.FrontRight.getCurrentPosition()) && (robot.FrontRight.getCurrentPosition() > (distance - 20))){
                a = 1500;
            }
        }

        StopDriving();
        robot.FrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.FrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.RearRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.RearLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }


    void Inch(boolean positive){
        boolean check = false;
        if(positive == true) {
            while (check == false && !isStopRequested()) {
                if (robot.hexMotor.getCurrentPosition() < firstUp) {
                    robot.hexMotor.setPower(0.3);
                } else {
                    robot.hexMotor.setPower(0);
                    check = true;
                }
            }
            telemetry.addData("hexMotor Position", robot.hexMotor.getCurrentPosition());
            telemetry.update();
        }

        else if(positive == false && !isStopRequested()){
            while (check == false) {
                if (robot.hexMotor.getCurrentPosition() > 0) {
                    robot.hexMotor.setPower(-0.3);
                } else {
                    robot.hexMotor.setPower(0);
                    check = true;
                }
                telemetry.addData("hexMotor Position", robot.hexMotor.getCurrentPosition());
                telemetry.update();
            }
        }
    }
}