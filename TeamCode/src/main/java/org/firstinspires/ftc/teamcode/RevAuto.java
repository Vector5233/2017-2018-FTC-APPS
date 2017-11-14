package org.firstinspires.ftc.teamcode;

/**
 * Created by CCA on 10/27/2017.
 */

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import java.util.concurrent.SynchronousQueue;

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

    float Lt, Rt;
    final double RIGHTGrab_OPEN = 1.0;
    final double RIGHTGrab_CLOSE = 0.4; //used to be 0.46
    final double LEFTGrab_OPEN = 0;
    final double LEFTGrab_CLOSE = 0.6; //used to be 0.54
    final double SPROCKET_RATIO = 2.0/3.0;
    final double TICKS_PER_INCH = SPROCKET_RATIO*(1120.0/(2*2*3.14159));

    double RaiseArm = 1.0;
    double LowerArm = 0.0;

    double ForwardPower = 1.0;

    @Override
    public void runOpMode() throws InterruptedException {

        initialization();
        waitForStart();
        StrifeLeftDistance(0.5,12);
        sleep(10000);
        StrifeRightDistance(0.5,12);
        sleep(10000);
        StopDriving(); //redundant
        telemetry.update();
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
        //jewelKnocker.setPosition(jewelKnocker_Raised);
        RaiseJewelKnocker();

        rightGrab = hardwareMap.servo.get("rightGrab");
        leftGrab = hardwareMap.servo.get("leftGrab");
        rightGrab.setPosition(RIGHTGrab_OPEN);
        leftGrab.setPosition(LEFTGrab_OPEN);

        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.FORWARD);
        liftMotor.setDirection(DcMotor.Direction.FORWARD);

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void TurnLeftDegree(double power, double degree) throws InterruptedException {

        frontLeft.setPower(-power);
        frontRight.setPower(power);
        backLeft.setPower(-power);
        backRight.setPower(power);

    }

    public void TurnRightDegree(double power, double degree) throws InterruptedException {

        frontLeft.setPower(power);
        frontRight.setPower(-power);
        backLeft.setPower(power);
        backRight.setPower(-power);
    }

    public void StrifeRightDistance(double power, double distance){
// distance in inches
        //conjecture instead of moving 12", wheels will go 12"*cos(45)= 8.5"
        int ticks = (int)(distance * TICKS_PER_INCH);
        if (power>0.65){power = 0.65;}

        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        frontLeft.setTargetPosition(ticks);
        frontRight.setTargetPosition(-ticks);
        backLeft.setTargetPosition(-ticks);
        backRight.setTargetPosition(ticks);

        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontLeft.setPower(power);
        frontRight.setPower(power);
        backLeft.setPower(power);
        backRight.setPower(power);

        while (frontRight.isBusy() &&frontLeft.isBusy());

        StopDriving();
    }
    public void StrifeLeftDistance(double power, double distance){
// distance in inches
        //conjecture instead of moving 12", wheels will go 12"*cos(45)= 8.5"
        int ticks = (int)(distance * TICKS_PER_INCH);
        if (power>0.65){power = 0.65;}

        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        frontLeft.setTargetPosition(-ticks);
        frontRight.setTargetPosition(ticks);
        backLeft.setTargetPosition(ticks);
        backRight.setTargetPosition(-ticks);

        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontLeft.setPower(power);
        frontRight.setPower(power);
        backLeft.setPower(power);
        backRight.setPower(power);

        while (frontRight.isBusy() &&frontLeft.isBusy());

        StopDriving();
    }


    public void DriveForwardDistance (double power, double distance){
        // distance in inches
        //FR,FL,BR,BL, Back motors are slower to stop
        int ticks = (int)(distance * TICKS_PER_INCH);
        if (power>0.65){power = 0.65;}

        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        frontLeft.setTargetPosition(ticks);
        frontRight.setTargetPosition(ticks);
        backRight.setTargetPosition(ticks);
        backLeft.setTargetPosition(ticks);

        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontLeft.setPower(power);
        frontRight.setPower(power);
        backLeft.setPower(power);
        backRight.setPower(power);

        while (frontRight.isBusy() &&frontLeft.isBusy());

        StopDriving();
    }

    public void DriveForwardAlternate(double power, double distance) {
        int ticks = (int) (distance * TICKS_PER_INCH);

        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        frontLeft.setPower(power);
        frontRight.setPower(power);
        backLeft.setPower(power);
        backRight.setPower(power);

        while (frontLeft.getCurrentPosition() <= ticks  && opModeIsActive()) {
            idle();
        }

        StopDriving();

    }

    public void DriveBackwardDistance(double power, double distance) throws InterruptedException {
// distance in inches
        int ticks = (int)(distance * TICKS_PER_INCH);
        if (power>0.65){power = 0.65;}

        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        frontLeft.setTargetPosition(-ticks);
        frontRight.setTargetPosition(-ticks);
        backLeft.setTargetPosition(-ticks);
        backRight.setTargetPosition(-ticks);

        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontLeft.setPower(power);
        frontRight.setPower(power);
        backLeft.setPower(power);
        backRight.setPower(power);

        while (frontRight.isBusy() &&frontLeft.isBusy());

        StopDriving();
    }

    public void StopDriving(){

        frontLeft.setPower(0.0);
        frontRight.setPower(0.0);
        backLeft.setPower(0.0);
        backRight.setPower(0.0);
    }

    public void DriveForward (double power) throws InterruptedException {

        frontLeft.setPower(power);
        frontRight.setPower(power);
        backLeft.setPower(power);
        backRight.setPower(power);
    }


    public void LowerJewelKnocker(){

        jewelKnocker.setPosition(LowerArm);

    }

    public void RaiseJewelKnocker(){
        jewelKnocker.setPosition(RaiseArm);

    }




}


