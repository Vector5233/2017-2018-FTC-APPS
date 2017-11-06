package org.firstinspires.ftc.teamcode; /**
 * Created by CCA on 10/26/2017.
 */


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.FORWARD;
import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.REVERSE;



@Autonomous(name="RaymondAutonomousOpMode", group = "myGroup")
public class RaymondAutonomousOpMode extends LinearOpMode {
    Servo jewelKnocker=null;
    DcMotor frontLeft, frontRight, backLeft, backRight=null;
    ColorSensor jewelSensor=null;


    float red, green, blue;


    public void runOpMode() throws InterruptedException {
        initialize();
        waitForStart();
        jewelKnocker.setPosition(0.0);
        if(jewelSensor.red() == 3) {
            strafeRight(1,3);
            strafeLeft(1,3);
        }
        else {
            strafeLeft(1,3);
            strafeRight(1,3);
        }
        jewelKnocker.setPosition(1.0);
    }


    public void initialize() {
        jewelSensor = hardwareMap.colorSensor.get("color");
        jewelKnocker = hardwareMap.servo.get("jewel");
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        backRight = hardwareMap.dcMotor.get("backRight");

        frontLeft.setDirection(REVERSE);
        frontRight.setDirection(FORWARD);
        backLeft.setDirection(REVERSE);
        backRight.setDirection(FORWARD);

        jewelKnocker.setPosition(1.0);

        Servo jewel=null;
        DcMotor frontLeft, frontRight, backLeft, backRight=null;
        ColorSensor jewelSensor=null;
    }
}

public void strafeRight (double power,int distance){

    frontLeft.setPower(power,3);
    frontRight.setPower(power,3);

}

public void strafeLeft (double power,int distance){
    frontRight.setPower(power,);
    frontLeft.setPower();
}


