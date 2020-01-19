package org.firstinspires.ftc.teamcode.Season19and20.code;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
@Disabled
@TeleOp(name="TesterForTime", group="Test")
public class TesterForTime extends OpMode {
    HWMapTest robot = new HWMapTest();
    int a = 99999;
    public void init(){
        robot.init(hardwareMap);
        robot.hexMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.hexMotor.setTargetPosition(0);
        robot.hexMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    public void loop() {
        if (gamepad1.a) {
            a = robot.hexMotor.getCurrentPosition();
            robot.hexMotor.setPower(0.75);
        }
        if(a != 99999 && robot.hexMotor.getCurrentPosition() == a+360){
            robot.hexMotor.setPower(0);
        }
    }
}
