package org.firstinspires.ftc.teamcode.Season19and20.code;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

//This is NOT an OpMode

public class HWMapTest
{
    /* Public OpMode members. */
    public DcMotor  FrontLeft   = null;
    public DcMotor  FrontRight  = null;
    public DcMotor  RearLeft  = null;
    public DcMotor  RearRight  = null;
    public DcMotor hexMotor = null;
    public Servo servoTest = null;

    /* local OpMode members. */
    HardwareMap hwMap           =  null;
    private ElapsedTime period  = new ElapsedTime();

    /* Constructor */
    public HWMapTest(){

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors
        FrontLeft   = hwMap.dcMotor.get("FrontLeft");
        //rightMotor.setDirection(DcMotor.Direction.FORWARD);
        FrontRight  = hwMap.dcMotor.get("FrontRight");
        RearLeft = hwMap.dcMotor.get("RearLeft");
        RearRight = hwMap.dcMotor.get("RearRight");
        //RearRight.setDirection(DcMotor.Direction.FORWARD);
        hexMotor = hwMap.get(DcMotor.class, "hexMotor");
        servoTest = hwMap.get(Servo.class, "servoTest");

        FrontLeft.setPower(0);
        FrontRight.setPower(0);
        RearLeft.setPower(0);
        RearRight.setPower(0);

        //Set position of servos

    }

    /***
     *
     * waitForTick implements a periodic delay. However, this acts like a metronome with a regular
     * periodic tick.  This is used to compensate for varying processing times for each cycle.
     * The function looks at the elapsed cycle time, and sleeps for the remaining time interval.
     *
     * @param periodMs  Length of wait cycle in mSec.
     */
    public void waitForTick(long periodMs) {

        long  remaining = periodMs - (long)period.milliseconds();

        // sleep for the remaining portion of the regular cycle period.
        if (remaining > 0) {
            try {
                Thread.sleep(remaining);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Reset the cycle clock for the next pass.
        period.reset();
    }


}