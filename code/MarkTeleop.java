package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="MarkTeleop")
public class MarkTeleop extends OpMode {

    private static final double STRAFE_POWER = 0.7;
    HardwarePushbot         robot   = new HardwarePushbot();   // Use a Pushbot's hardware
    private ElapsedTime     runtime = new ElapsedTime();
    int linearSlideMax = 600;
    int linearSlideMin = 100;
    int linearSlideAdjust = 0; // 0 adjust linear slide min, 1 adjust linear slide max

    @Override
    public void init() {
        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);
        robot.rightDrive.setDirection(DcMotor.Direction.FORWARD);
        //robot.FrontRight.setTargetPosition(32);
        //robot.RearRight.setTargetPosition(32);
        //robot.FrontLeft.setTargetPosition(32);
        //robot.RearLeft.setTargetPosition(32);
        robot.servoClaw.setPosition(0);
        // Send telemetry message to signify robot waiting;
    }

    @Override
    public void loop() {
        Telemetry();
        double left = -gamepad1.left_stick_y;
        double right = gamepad1.right_stick_y;
        double leftx = -gamepad1.left_stick_x;
        double rightx = -gamepad1.right_stick_x;

        double Forward;
        double Backward;

        //telemetry.addData("Status", "Initialized");
        //telemetry.update();


        if(gamepad1.y) {
            linearSlideDrive(1.0, linearSlideMax, 5.0, true);
        }
        else if (gamepad1.a) {
            linearSlideDrive(1.0, linearSlideMin, 5.0, false);
        }

        if(gamepad1.x){
            robot.servoClaw.setPosition(-1);
            telemetry.addData("Servo", robot.servoClaw.getPosition());
        }
        
        if(gamepad1.b){
            robot.servoClaw.setPosition(1);
            telemetry.addData("Servo", robot.servoClaw.getPosition());
        }
        
        if (gamepad1.left_bumper) {
            linearSlideAdjust = 0;
        }
        
        if (gamepad1.right_bumper) {
            linearSlideAdjust = 1;
        }
        
        if (gamepad1.dpad_up) {
            if (linearSlideAdjust == 0) {
                linearSlideMin += 1;
            } else {
                linearSlideMax += 1;
            }
        }
        
        if (gamepad1.dpad_down) {
            if (linearSlideAdjust == 0) {
                linearSlideMin -= 1;
            } else {
                linearSlideMax -= 1;
            }
        }
        //telemetry.addData("Motor Power", robot.hexMotor.getPower());
        //telemetry.addData("Servo Position", robot.servoTest.getPosition());
        //telemetry.addData("Status", "Running");
        //telemetry.update();

        // Run wheels in tank mode (note: The joystick goes negative when pushed forwards, so negate it)
        //Left Side
        robot.leftDrive.setPower(left);
        robot.leftBack.setPower(left);

        //Right Side
        robot.rightDrive.setPower(right);
        robot.rightBack.setPower(right);

        //Strafe Right
        if (gamepad1.dpad_right) {
            //Right Side
            robot.rightDrive.setPower(STRAFE_POWER * 1.1);
            robot.rightBack.setPower(-STRAFE_POWER);

            //Left Side
            robot.leftDrive.setPower(STRAFE_POWER);
            robot.leftBack.setPower(-STRAFE_POWER * 1.1);
        } else if (gamepad1.dpad_left) {
        
            //Strafe Right

            //Right Side
            robot.rightDrive.setPower(-STRAFE_POWER * 1.1);
            robot.rightBack.setPower(STRAFE_POWER);

            //Left Side
            robot.leftDrive.setPower(-STRAFE_POWER);
            robot.leftBack.setPower(STRAFE_POWER * 1.1);
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
        telemetry.addData("FR_Power", "%.2f", robot.rightDrive.getPower());
        telemetry.addData("RR_Power", "%.2f", robot.rightBack.getPower());
        telemetry.addData("FL_Power", "%.2f", robot.leftDrive.getPower());
        telemetry.addData("RL_Power", "%.2f", robot.leftBack.getPower());
        //telemetry.addData("Front Right Encoder Position", robot.rightDrive.getCurrentPosition());
        //telemetry.addData("Rear Right Encoder Position", robot.rightBack.getCurrentPosition());
        //telemetry.addData("Front Left Encoder Position", robot.leftDrive.getCurrentPosition());
        //telemetry.addData("Rear Left Encoder Position", robot.leftBack.getCurrentPosition());
        if (linearSlideAdjust == 0) {
            telemetry.addData("Linear Slide Adjust Min", "min: %d max: %d", linearSlideMin, linearSlideMax);
        } else {
            telemetry.addData("Linear Slide Adjust Max", "min: %d max: %d", linearSlideMin, linearSlideMax);
        }
        telemetry.update();
    }
    
    public void linearSlideDrive(double power,
                             int targetPosition,
                             double timeoutS,
                             boolean brake) {
        if (brake == true) {
            robot.hexMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        } else {
            robot.hexMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        }
        robot.hexMotor.setTargetPosition(targetPosition);
        robot.hexMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.hexMotor.setPower(power);
        runtime.reset();
        //telemetry.addData("hexMotor", "In Linear Slide Function");
        //telemetry.update();
        
        while ((runtime.seconds() < timeoutS) &&
              (robot.hexMotor.isBusy())) {

                // Display it for the driver.
                //telemetry.addData("hexMotor",  "Power %7f Target: %7d", power,  robot.hexMotor.getCurrentPosition());
                //telemetry.update();
                //sleep(250);
        } 

        // apply brake hopefully the motor will stay
        robot.hexMotor.setPower(0.0);
        //sleep(250);         
    }    
}
