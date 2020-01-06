
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

/**
 * This file illustrates the concept of driving a path based on encoder counts.
 * It uses the common Pushbot hardware class to define the drive on the robot.
 * The code is structured as a LinearOpMode
 *
 * The code REQUIRES that you DO have encoders on the wheels,
 *   otherwise you would use: PushbotAutoDriveByTime;
 *
 *  This code ALSO requires that the drive Motors have been configured such that a positive
 *  power command moves them forwards, and causes the encoders to count UP.
 *
 *   The desired path in this example is:
 *   - Drive forward for 48 inches
 *   - Spin right for 12 Inches
 *   - Drive Backwards for 24 inches
 *   - Stop and close the claw.
 *
 *  The code is written using a method called: encoderDrive(speed, leftInches, rightInches, timeoutS)
 *  that performs the actual movement.
 *  This methods assumes that each movement is relative to the last stopping place.
 *  There are other ways to perform encoder based moves, but this method is probably the simplest.
 *  This code uses the RUN_TO_POSITION mode to enable the Motor controllers to generate the run profile
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@Autonomous(name="Foundation: Movement", group="Pushbot")

public class FoundationMovement extends LinearOpMode {

    /* Declare OpMode members. */
    HardwarePushbot         robot   = new HardwarePushbot();   // Use a Pushbot's hardware
    private ElapsedTime     runtime = new ElapsedTime();

    static final double     COUNTS_PER_MOTOR_REV    = 685 ;    // eg: TETRIX Motor Encoder
    static final double     DRIVE_GEAR_REDUCTION    = 1.0 ;     // This is < 1.0 if geared UP
    static final double     WHEEL_DIAMETER_INCHES   = 3.0 ;     // For figuring circumference
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
                                                      (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double     DRIVE_SPEED             = 0.6;
    static final double     TURN_SPEED              = 0.5;

    boolean USE_DISTANCE_SENSOR = false;

    @Override
    public void runOpMode() {

        /*
         * Initialize the drive system variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Resetting Encoders");    //
        telemetry.addData("Status", "try to strafe by Sam");    //        
        telemetry.update();  
        sleep(1000);

        robot.leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.hexMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.hexMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        robot.leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.hexMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        
        robot.rightBack.setDirection(DcMotor.Direction.REVERSE);
        // Send telemetry message to indicate successful Encoder reset
        telemetry.addData("Path0",  "Starting at %7d :%7d :%7d :%7d", 
                          robot.leftDrive.getCurrentPosition(),
                          robot.rightDrive.getCurrentPosition(),
                          robot.rightBack.getCurrentPosition(),
                          robot.leftBack.getCurrentPosition());
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        
        // Step through each leg of the path,
        // Note: Reverse movement is obtained by setting a negative distance (not speed)
        encoderDrive(DRIVE_SPEED,  10,  10, 3.0);  // S1: Forward 15 Inches with 10 Sec timeout
        encoderDrive(TURN_SPEED,   19, -19, 4.0);  // S2: Turn 90 degrees with 4 Sec timeout
        encoderDrive(DRIVE_SPEED,  88,  88, 5.0);  // S1: Forward 88 Inches with 10 Sec timeout
        // Lift arm up
        linearSlideDrive(1.0, 300, 5.0, true);
        
        encoderDrive(TURN_SPEED,   -19, 19, 4.0);  // S2: Turn 90 degrees with 4 Sec timeout
        if (USE_DISTANCE_SENSOR == false) {
        	encoderDrive(DRIVE_SPEED,  27,  27, 4.0);  // Drive towards foundation
        } else {
        	proportionalDrive();
        }
        
        linearSlideDrive(1.0, 0, 5, false);
        encoderDrive(DRIVE_SPEED,  -35,  -35, 4.0); // S1: Forward 15 Inches with 10 Sec timeout
        
        // Lift arm up
        linearSlideDrive(1.0, 300, 5.0, true);
        encoderDrive(TURN_SPEED,   19, -19, 4.0);  // S2: Turn 90 degrees with 4 Sec timeout
        linearSlideDrive(1.0, 0, 5.0, false);
        encoderDrive(DRIVE_SPEED,  -50,  -50, 4.0); // S1: Forward 15 Inches with 10 Sec timeout

        sleep(1000);     // pause for servos to move

        telemetry.addData("Path", "Complete");
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
        
        while (opModeIsActive() &&
              (runtime.seconds() < timeoutS) &&
              (robot.hexMotor.isBusy())) {

                // Display it for the driver.
                telemetry.addData("hexMotor",  "Power %7f Target: %7d", power,  robot.hexMotor.getCurrentPosition());
                telemetry.update();
                sleep(250);
        } 

        // apply brake hopefully the motor will stay
        robot.hexMotor.setPower(0.0);
        //sleep(250);         
    }

    /*
     *  Method to perfmorm a relative move, based on encoder counts.
     *  Encoders are not reset as the move is based on the current position.
     *  Move will stop if any of three conditions occur:
     *  1) Move gets to the desired position
     *  2) Move runs out of time
     *  3) Driver stops the opmode running.
     */
    public void encoderDrive(double speed,
                             double leftInches, double rightInches,
                             double timeoutS) {
        int newLeftTarget;
        int newRightTarget;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newLeftTarget = robot.leftDrive.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            newRightTarget = robot.rightDrive.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
            newRightTarget = robot.rightBack.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
            newLeftTarget = robot.leftBack.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            robot.leftDrive.setTargetPosition(newLeftTarget);
            robot.rightDrive.setTargetPosition(newRightTarget);
            robot.rightBack.setTargetPosition(newRightTarget);
            robot.leftBack.setTargetPosition(newLeftTarget);  
            
            // Turn On RUN_TO_POSITION
            robot.leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            // reset the timeout time and start motion.
            runtime.reset();
            robot.leftDrive.setPower(Math.abs(speed));
            robot.rightDrive.setPower(Math.abs(speed));
            robot.rightBack.setPower(Math.abs(speed));
            robot.leftBack.setPower(Math.abs(speed));
            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (opModeIsActive() &&
              (runtime.seconds() < timeoutS) &&
              (robot.leftDrive.isBusy() && robot.rightDrive.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path1",  "Running to %7d :%7d", newLeftTarget,  newRightTarget);
                telemetry.addData("Path2",  "Running at %7d :%7d",
                                            robot.leftDrive.getCurrentPosition(),
                                            robot.rightDrive.getCurrentPosition());
                telemetry.update();
            }

            // Stop all motion;
            robot.leftDrive.setPower(0);
            robot.rightDrive.setPower(0);
            robot.rightBack.setPower(0);
            robot.leftBack.setPower(0);
            
            // Turn off RUN_TO_POSITION
            robot.leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            //  sleep(250);   // optional pause after each move
        }
    }

    /*
     *  Method to perfmorm a relative move, based on encoder counts.
     *  Encoders are not reset as the move is based on the current position.
     *  Move will stop if any of three conditions occur:
     *  1) Move gets to the desired position
     *  2) Move runs out of time
     *  3) Driver stops the opmode running.
     */
    public void strafeDrive(double speed,
                             double distance,
                             double timeoutS,
                             String direction) {
        int newLeftTarget;
        int newRightTarget;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {
            newLeftTarget = robot.leftDrive.getCurrentPosition() + (int)(distance * COUNTS_PER_INCH);
            newRightTarget = robot.rightDrive.getCurrentPosition() + (int)(distance * COUNTS_PER_INCH);
            newRightTarget = robot.rightBack.getCurrentPosition() + (int)(distance * COUNTS_PER_INCH);
            newLeftTarget = robot.leftBack.getCurrentPosition() + (int)(distance * COUNTS_PER_INCH);

            // Determine new target position, and pass to motor controller
            if (direction == "right") {
                robot.leftDrive.setTargetPosition(newLeftTarget);
                robot.rightDrive.setTargetPosition(-newRightTarget);
                robot.rightBack.setTargetPosition(newRightTarget);
                robot.leftBack.setTargetPosition(-newLeftTarget);  
                
            } else {
                robot.leftDrive.setTargetPosition(-newLeftTarget);
                robot.rightDrive.setTargetPosition(newRightTarget);
                robot.rightBack.setTargetPosition(-newRightTarget);
                robot.leftBack.setTargetPosition(newLeftTarget);  
            }

            // Turn On RUN_TO_POSITION
            robot.leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            // reset the timeout time and start motion.
            runtime.reset();
            robot.leftDrive.setPower(Math.abs(speed));
            robot.rightDrive.setPower(Math.abs(speed));
            robot.rightBack.setPower(Math.abs(speed));
            robot.leftBack.setPower(Math.abs(speed));
            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (opModeIsActive() &&
              (runtime.seconds() < timeoutS) &&
              (robot.leftDrive.isBusy() && robot.rightDrive.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path1",  "Running to %7d :%7d", newLeftTarget,  newRightTarget);
                telemetry.addData("Path2",  "Running at %7d :%7d",
                                            robot.leftDrive.getCurrentPosition(),
                                            robot.rightDrive.getCurrentPosition());
                telemetry.update();
            }

            // Stop all motion;
            robot.leftDrive.setPower(0);
            robot.rightDrive.setPower(0);
            robot.rightBack.setPower(0);
            robot.leftBack.setPower(0);
            
            // Turn off RUN_TO_POSITION
            robot.leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            //  sleep(250);   // optional pause after each move
        }
    }

    /*
     *  Method to perfmorm a proportionalDrive with distance sensor.
     */
    public void proportionalDrive() {    
        // Turn off RUN_TO_POSITION
        robot.leftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.rightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.rightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.leftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    	while (1 == 1) {
    		range = robot.sensorRange.getDistance(DistanceUnit.INCH);
    		if (range <= 0.1) {
    			robot.leftDrive.setPower(0);
    			robot.rightDrive.setPower(0);
    			robot.leftBack.setPower(0);
    			robot.rightBack.setPower(0);    			
    			break; // we have arrived!
    		} else {
    			speed = range * 1.0;
    			if (speed > 1) {
    				speed = 1;
    			} else if (speed < 0) {
    				speed = 0;
    			}
    			robot.leftDrive.setPower(speed);
    			robot.rightDrive.setPower(speed);
    			robot.leftBack.setPower(speed);
    			robot.rightBack.setPower(speed);
    		}
    	}
        robot.leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);    	
    }
}
