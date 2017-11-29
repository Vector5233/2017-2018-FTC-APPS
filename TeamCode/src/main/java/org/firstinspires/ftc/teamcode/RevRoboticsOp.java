package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.Gyroscope;
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
    ModernRoboticsI2cGyro gyro;
    float Lt,Rt;

    final double RIGHTGrab_OPEN = 0.8;
    final double RIGHTGrab_CLOSE = 0.4; //used to be 0.46
    final double LEFTGrab_OPEN = 0.2;
    final double LEFTGrab_CLOSE = 0.6; //used to be 0.54

    final double JEWEL_UP = 0;
    final double JEWEL_DOWN = 0+0.091; //new number: 0.222

    public void init() {
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        backRight = hardwareMap.dcMotor.get("backRight");
        liftMotor = hardwareMap.dcMotor.get("liftMotor");
        gyro = hardwareMap.get(ModernRoboticsI2cGyro.class, "gyro");

        //colorSensor = hardwareMap.colorSensor.get("color");
        jewelKnocker = hardwareMap.servo.get("jewel");
        jewelKnocker.setPosition(JEWEL_DOWN);  //JEWEL_UP

        rightGrab = hardwareMap.servo.get("rightGrab");
        leftGrab = hardwareMap.servo.get("leftGrab");
        rightGrab.setPosition(RIGHTGrab_OPEN);
        leftGrab.setPosition(LEFTGrab_OPEN);

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

        gyro.calibrate();
        while (gyro.isCalibrating()) {
            ;
        }
    }
    public void loop () {
// 2 Drive: movement + tasks
        /*red = colorSensor.red();
        green = colorSensor.green();
        blue = colorSensor.blue();
        */


        frontLeft.setPower(-gamepad1.left_stick_y+gamepad1.left_stick_x+gamepad1.right_stick_x);
        frontRight.setPower(-gamepad1.left_stick_y-gamepad1.left_stick_x-gamepad1.right_stick_x);
        backLeft.setPower(-gamepad1.left_stick_y-gamepad1.left_stick_x+gamepad1.right_stick_x);
        backRight.setPower(-gamepad1.left_stick_y+gamepad1.left_stick_x-gamepad1.right_stick_x);

        /*telemetry.addData("Red: ", red);
        telemetry.addData("Green: ", green);
        telemetry.addData("Blue: ", blue);*/
        telemetry.addData("gyro: ",gyro.getIntegratedZValue());
        telemetry.addData("Front Left:", gamepad1.left_stick_y-gamepad1.left_stick_x-gamepad1.right_stick_x);
        telemetry.addData("Back Right:", gamepad1.left_stick_y-gamepad1.left_stick_x+gamepad1.right_stick_x);

        // trivial change
        if (gamepad2.a) {
            jewelKnocker.setPosition(JEWEL_DOWN);
        }
        else if (gamepad2.b) {
            jewelKnocker.setPosition(JEWEL_UP);
        }


        // trivial change
        if (gamepad2.dpad_up) {
            liftMotor.setPower(1.0);
        }
        else if (gamepad2.dpad_down) {
            liftMotor.setPower(-1.0);
        }
        else {
            liftMotor.setPower(0);
        }


        if (gamepad2.left_bumper) {
            rightGrab.setPosition(RIGHTGrab_OPEN);
            leftGrab.setPosition(LEFTGrab_OPEN);
        }

        if (gamepad2.right_bumper) {
            rightGrab.setPosition(RIGHTGrab_CLOSE);
            leftGrab.setPosition(LEFTGrab_CLOSE);
        }

        telemetry.update();


    }






}
