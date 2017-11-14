package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;


/**
 * Created by CCA on 8/16/2017.
 */

@TeleOp(name="RevRoboticsOp", group = "myGroup")
public class RevRoboticsOp extends OpMode {


    DcMotor frontLeft, frontRight, backLeft, backRight, liftMotor;
    ColorSensor colorSensor;
    float red, green, blue;
    Servo leftGrab,rightGrab;
    Servo jewelKnocker;
    float Lt,Rt;

    final double RIGHT_OPEN = 1.0;
    final double RIGHT_CLOSE = 0.4;
    final double LEFT_OPEN = 0;
    final double LEFT_CLOSE = 0.6;

    public void init() {
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        backRight = hardwareMap.dcMotor.get("backRight");
        liftMotor = hardwareMap.dcMotor.get("liftMotor");

        colorSensor = hardwareMap.colorSensor.get("color");
        jewelKnocker = hardwareMap.servo.get("jewel");
        jewelKnocker.setPosition(1.0);

        rightGrab = hardwareMap.servo.get("rightGrab");
        leftGrab = hardwareMap.servo.get("leftGrab");
        rightGrab.setPosition(RIGHT_OPEN);
        leftGrab.setPosition(LEFT_OPEN);

        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.FORWARD);
        liftMotor.setDirection(DcMotor.Direction.FORWARD);

        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
    public void loop () {

        red = colorSensor.red();
        green = colorSensor.green();
        blue = colorSensor.blue();
        /*
        Left.setPower(gamepad1.left_stick_y);
        Right.setPower(gamepad1.right_stick_y);
        */


        frontLeft.setPower(-gamepad1.left_stick_y+gamepad1.left_stick_x+gamepad1.right_stick_x);
        frontRight.setPower(-gamepad1.left_stick_y-gamepad1.left_stick_x-gamepad1.right_stick_x);
        backLeft.setPower(-gamepad1.left_stick_y-gamepad1.left_stick_x+gamepad1.right_stick_x);
        backRight.setPower(-gamepad1.left_stick_y+gamepad1.left_stick_x-gamepad1.right_stick_x);

        telemetry.addData("Red: ", red);
        telemetry.addData("Green: ", green);
        telemetry.addData("Blue: ", blue);
        telemetry.addData("Front Left:", gamepad1.left_stick_y-gamepad1.left_stick_x-gamepad1.right_stick_x);
        telemetry.addData("Back Right:", gamepad1.left_stick_y-gamepad1.left_stick_x+gamepad1.right_stick_x);
        telemetry.update();

        // trivial change
        if (gamepad1.a) {
            jewelKnocker.setPosition(0.0);
        }
        else if (gamepad1.b) {
            jewelKnocker.setPosition(1.0);
        }


        // trivial change
        if (gamepad1.dpad_up) {
            liftMotor.setPower(1.0);
        }
        else if (gamepad1.dpad_down) {
            liftMotor.setPower(-1.0);
        }
        else {
            liftMotor.setPower(0);
        }


        if (gamepad1.left_bumper) {
            rightGrab.setPosition(RIGHT_OPEN);
            leftGrab.setPosition(LEFT_OPEN);
        }

        if (gamepad1.right_bumper) {
            rightGrab.setPosition(RIGHT_CLOSE);
            leftGrab.setPosition(LEFT_CLOSE);
        }

        telemetry.update();


    }






}
