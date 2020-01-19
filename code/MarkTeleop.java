package org.firstinspires.ftc.teamcode.Season19and20.code;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Season19and20.code.HardwarePushbot;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
@Disabled
@TeleOp(name="MarkTeleop")
public class MarkTeleop extends OpMode {


    private static final double STRAFE_POWER = 0.7;
    HardwarePushbot robot   = new HardwarePushbot();   // Use a Pushbot's hardware
    private ElapsedTime     runtime = new ElapsedTime();


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

        telemetry.addData("Status", "Initialized");
        telemetry.update();


            if(gamepad1.y) {
                linearSlideDrive(1.0, 500, 5.0, true);
            }
            else if (gamepad1.a) {
                linearSlideDrive(1.0, 0, 5.0, false);
            }

            if(gamepad1.x){
                robot.servoClaw.setPosition(-1);
                telemetry.addData("Servo", robot.servoClaw.getPosition());
            }
            if(gamepad1.b){
                robot.servoClaw.setPosition(1);
                telemetry.addData("Servo", robot.servoClaw.getPosition());
            }
            //telemetry.addData("Motor Power", robot.hexMotor.getPower());
            //telemetry.addData("Servo Position", robot.servoTest.getPosition());
            //telemetry.addData("Status", "Running");
            telemetry.update();

        // Run wheels in tank mode (note: The joystick goes negative when pushed forwards, so negate it)
        //Left Side
        robot.leftDrive.setPower(left);
        robot.leftBack.setPower(left);

        //Right Side
        robot.rightDrive.setPower(right);
        robot.rightBack.setPower(right);

        //Strafe Right
        /*if (gamepad1.dpad_right) {
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
        }*/
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
        telemetry.addData("Front Right Encoder Position", robot.rightDrive.getCurrentPosition());
        telemetry.addData("Rear Right Encoder Position", robot.rightBack.getCurrentPosition());
        telemetry.addData("Front Left Encoder Position", robot.leftDrive.getCurrentPosition());
        telemetry.addData("Rear Left Encoder Position", robot.leftBack.getCurrentPosition());
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
        telemetry.addData("hexMotor", "In Linear Slide Function");
        telemetry.update();
        
        while ((runtime.seconds() < timeoutS) &&
              (robot.hexMotor.isBusy())) {

                // Display it for the driver.
                telemetry.addData("hexMotor",  "Power %7f Target: %7d", power,  robot.hexMotor.getCurrentPosition());
                telemetry.update();
                //sleep(250);
        } 

        // apply brake hopefully the motor will stay
        robot.hexMotor.setPower(0.0);
        //sleep(250);         
    }    
}
