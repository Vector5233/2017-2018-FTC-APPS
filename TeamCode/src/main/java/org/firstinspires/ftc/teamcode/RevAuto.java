package org.firstinspires.ftc.teamcode;

/**
 * Created by CCA on 10/27/2017.
 */

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by CCA on 10/26/2017.
 */
@Autonomous(name = "RevAuto")

public class RevAuto extends LinearOpMode {

    DcMotor frontLeft = null;
    DcMotor liftMotor = null;
    DcMotor frontRight = null;
    DcMotor backLeft = null;
    DcMotor backRight = null;
    ColorSensor colorSensor = null;
    float red, green, blue;
    Servo leftGrab, rightGrab = null;
    Servo jewelKnocker = null;
    Drive drive;

    float Lt, Rt;
    final double RIGHTGrab_OPEN = 1.0;
    final double RIGHTGrab_CLOSE = 0.4; //used to be 0.46
    final double LEFTGrab_OPEN = 0;
    final double LEFTGrab_CLOSE = 0.6; //used to be 0.54
    final double SPROCKET_RATIO = 2.0/3.0;
    final double TICKS_PER_INCH = SPROCKET_RATIO*(1120.0/(2*2*3.14159));

    double JEWEL_UP = 0;
    double JEWEL_DOWN = 0+0.091;

    double ForwardPower = 1.0;

    ModernRoboticsI2cGyro gyro;

    @Override
    public void runOpMode() throws InterruptedException {

        initialization();
        waitForStart();
        /*drive.StrafeLeftDistance(0.5,12);
        sleep(5000);
        drive.StrafeRightDistance(0.5,12);
        sleep(5000);
        drive.StopDriving(); //redundant
        drive.DriveForwardDistance(0.5,12);
        sleep(5000);
        drive.DriveBackwardDistance(0.5,12);
        sleep(5000);
        drive.TurnLeftDegree(0.5,90);
        sleep(5000);
        drive.TurnRightDegree(0.5,90);*/

        DeliverGlyph();
    }
/*Time magic number: 1000 = 1 second*/


    public void initialization () {
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        backRight = hardwareMap.dcMotor.get("backRight");
        liftMotor = hardwareMap.dcMotor.get ("liftMotor");


        colorSensor = hardwareMap.colorSensor.get("color");
        jewelKnocker = hardwareMap.servo.get("jewel");
        gyro=hardwareMap.get(ModernRoboticsI2cGyro.class, "gyro");
        //jewelKnocker.setPosition(jewelKnocker_Raised);
        RaiseJewelKnocker();

        rightGrab = hardwareMap.servo.get("rightGrab");
        leftGrab = hardwareMap.servo.get("leftGrab");
        rightGrab.setPosition(RIGHTGrab_CLOSE);
        leftGrab.setPosition(LEFTGrab_CLOSE);

        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.FORWARD);
        liftMotor.setDirection(DcMotor.Direction.FORWARD);

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        drive = new Drive(frontLeft,frontRight,backLeft,backRight, gyro, this);
    }

    public void DeliverGlyph() {

        drive.DriveForwardDistance(0.5, 3.5);
        leftGrab.setPosition(LEFTGrab_OPEN);
        rightGrab.setPosition(RIGHTGrab_OPEN);
        drive.DriveForwardDistance(0.5, 1.5);
        drive.StopDriving();
    }




    public void LowerJewelKnocker(){

        jewelKnocker.setPosition(JEWEL_DOWN);

    }

    public void RaiseJewelKnocker(){
        jewelKnocker.setPosition(JEWEL_UP);

    }




}


