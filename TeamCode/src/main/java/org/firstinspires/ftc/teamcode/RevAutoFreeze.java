package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by CCA on 10/30/2017.
 */


@Autonomous(name = "RevAutoFreeze")
 public class RevAutoFreeze extends LinearOpMode {

        DcMotor frontLeft;
        DcMotor liftMotor= null;
        DcMotor frontRight= null;
        DcMotor backLeft= null;
        DcMotor backRight= null;
        ColorSensor colorSensor = null;
        float red, green, blue;
        Servo leftGrab,rightGrab = null;
        Servo jewelKnocker = null;

        float Lt,Rt;
        final double RIGHT_OPEN = 1.0;
        final double RIGHT_CLOSE = 0.46;
        final double LEFT_OPEN = 0;
        final double LEFT_CLOSE = 0.54;


        @Override
        public void runOpMode(){

            frontLeft = hardwareMap.dcMotor.get("frontLeft");
            frontRight = hardwareMap.dcMotor.get("frontRight");
            backLeft = hardwareMap.dcMotor.get("backLeft");
            backRight = hardwareMap.dcMotor.get("backRight");

            colorSensor = hardwareMap.colorSensor.get("color");
            jewelKnocker = hardwareMap.servo.get("jewel");
            jewelKnocker.setPosition(0.1);

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

            waitForStart();

            while (opModeIsActive()){

                telemetry.update();
                idle();

            }
        }

    }
