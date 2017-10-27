package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;


/**
 * Created by CCA on 8/16/2017.
 */

@TeleOp(name="RevRoboticsOp", group = "myGroup")
public class RevRoboticsOp extends OpMode {


    DcMotor frontLeft, frontRight, backLeft, backRight, liftMotor;
    ColorSensor colorSensor;
    float red, green, blue;
    CRServo leftGrab,rightGrab;
    Servo jewelKnocker;
    float Lt,Rt;
    final double RIGHT_OPEN_POWER = 1.0;
    final double RIGHT_CLOSE_POWER = -1.0;
    final double LEFT_OPEN_POWER = -1.0;
    final double LEFT_CLOSE_POWER = 1.0;
    final double NEUTRAL = 0.0;

    public void init() {
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        backRight = hardwareMap.dcMotor.get("backRight");

        colorSensor = hardwareMap.colorSensor.get("color");
        jewelKnocker = hardwareMap.servo.get("jewel");
        jewelKnocker.setPosition(0.1);

        liftMotor = frontLeft;

        rightGrab = hardwareMap.crservo.get("rightGrab");
        leftGrab = hardwareMap.crservo.get("leftGrab");
        rightGrab.setPower(NEUTRAL);
        leftGrab.setPower(NEUTRAL);

        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.FORWARD);

        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
    public void loop () {

        red = colorSensor.red();
        green = colorSensor.green();
        blue = colorSensor.blue();
        /*
        Left.setPower(gamepad1.left_stick_y);
        Right.setPower(gamepad1.right_stick_y);
        */


        frontLeft.setPower(gamepad1.left_stick_y-gamepad1.left_stick_x+gamepad1.right_stick_x);
        frontRight.setPower(gamepad1.right_stick_y+gamepad1.right_stick_x-gamepad1.right_stick_x);
        backLeft.setPower(gamepad1.left_stick_y+gamepad1.left_stick_x-gamepad1.right_stick_x);
        backRight.setPower(gamepad1.right_stick_y-gamepad1.right_stick_x+gamepad1.right_stick_x);


        telemetry.addData("Red: ", red);
        telemetry.addData("Green: ", green);
        telemetry.addData("Blue: ", blue);
        telemetry.update();

        // trivial change
        if (gamepad1.a) {
            jewelKnocker.setPosition(0.0);
        }
        else if (gamepad1.b) {
            jewelKnocker.setPosition(1.0);
        }


        /*
        Lt = gamepad1.left_trigger;
        //Left trigger actions
        if (gamepad1.left_trigger == 1.0) {
            leftGrab.setPosition(1.0);
        }
        else {
            leftGrab.setPosition(0.5);
        }

        //Left bumper actions
        if (gamepad1.left_bumper)  {
            leftGrab.setPosition(-1.0);
        }
        else {
            leftGrab.setPosition(0.5);
        }

        //Right trigger actions
        if (gamepad1.right_trigger == 1.0) {
            rightGrab.setPosition(0);
        }
        else {
            rightGrab.setPosition(0.5);
        }

        //Right bumper actions
        if (gamepad1.right_bumper) {
            rightGrab.setPosition(1.0);
        }
        else {
            rightGrab.setPosition(0.5);
        }

        rightGrab.setPosition(0.5);

        //Lift motor actions
        if (gamepad1.dpad_up) {
            liftMotor.setPower(0.5);
        }
        else {
            liftMotor.setPower(0);
        }

        if (gamepad1.dpad_down) {
            liftMotor.setPower(-0.5);
        }
        else {
            liftMotor.setPower(0);
        }

       */

        if (gamepad1.right_trigger == 1.0) {
            rightGrab.setPower(RIGHT_OPEN_POWER);
            leftGrab.setPower(LEFT_OPEN_POWER);
        }
        else {
            rightGrab.setPower(NEUTRAL);
            leftGrab.setPower(NEUTRAL);
        }

        if (gamepad1.left_trigger == 1.0) {
            rightGrab.setPower(RIGHT_CLOSE_POWER);
            leftGrab.setPower(LEFT_CLOSE_POWER);
        }
        else {
            rightGrab.setPower(NEUTRAL);
            leftGrab.setPower(NEUTRAL);
        }

        if (gamepad1.dpad_up) {
            liftMotor.setPower(1.0);
        }
        else {
            liftMotor.setPower(0);
        }

        if (gamepad1.dpad_down) {
            liftMotor.setPower(0);
        }
        else {
            liftMotor.setPower(0);
        }



    }






}
