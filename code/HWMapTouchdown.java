package org.firstinspires.ftc.teamcode.Season19and20.code;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

//This is NOT an OpMode

public class HWMapTouchdown {
    /* Public OpMode members. */
    public DcMotor  FrontLeft   = null;
    public DcMotor  FrontRight  = null;
    public DcMotor  RearLeft  = null;
    public DcMotor  RearRight  = null;
    public DcMotor SpinLeft = null;
    public DcMotor SpinRight = null;
    public DcMotor ArmLeft = null;
    public DcMotor ArmRight = null;

    public Servo GrabLeft = null;
    public Servo GrabRight = null;

    /* local OpMode members. */
    HardwareMap hwMap           =  null;
    private ElapsedTime period  = new ElapsedTime();

    /* Constructor */
    public HWMapTouchdown(){

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors
        FrontLeft   = hwMap.dcMotor.get("FrontLeft");
        FrontRight  = hwMap.dcMotor.get("FrontRight");
        RearLeft = hwMap.dcMotor.get("RearLeft");
        RearRight = hwMap.dcMotor.get("RearRight");
        SpinLeft = hwMap.get(DcMotor.class, "SpinLeft");
        SpinRight = hwMap.get(DcMotor.class, "SpinRight");
        ArmLeft = hwMap.get(DcMotor.class, "ArmLeft");
        ArmRight = hwMap.get(DcMotor.class, "ArmRight");

        GrabLeft = hwMap.get(Servo.class, "GrabLeft");
        GrabRight = hwMap.get(Servo.class, "GrabRight");

        FrontLeft.setPower(0);
        FrontRight.setPower(0);
        RearLeft.setPower(0);
        RearRight.setPower(0);

        GrabLeft.setPosition(0);
        GrabRight.setPosition(0);
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