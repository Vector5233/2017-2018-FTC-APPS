package org.firstinspires.ftc.teamcode; /**
 * Created by CCA on 10/26/2017.
 */


import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.teamcode.Drive;

import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.FORWARD;
import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.REVERSE;


@Autonomous(name="RaymondAutonomousOpMode", group = "myGroup")
public class RaymondAutonomousOpMode extends LinearOpMode {
    Servo jewelKnocker = null;
    DcMotor frontLeft, frontRight, backLeft, backRight = null;
    ColorSensor colorSensor = null;
    Drive drive;
    float red, green, blue;
    ModernRoboticsI2cGyro gyro;
    double JEWEL_UP = 0;
    double JEWEL_DOWN = 0+0.091;

    public void initialize() {
        colorSensor = hardwareMap.colorSensor.get("color");
        jewelKnocker = hardwareMap.servo.get("jewel");
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        backRight = hardwareMap.dcMotor.get("backRight");

        frontLeft.setDirection(REVERSE);
        frontRight.setDirection(FORWARD);
        backLeft.setDirection(REVERSE);
        backRight.setDirection(FORWARD);

        jewelKnocker.setPosition(JEWEL_UP);

        drive = new Drive(frontLeft, frontRight, backLeft, backRight, gyro, this);
    }

    public void runOpMode() throws InterruptedException {
        initialize();
        waitForStart();
        jewelKnocker.setPosition(JEWEL_DOWN);
        sleep(2000);
        telemetry.addData("Red: ", colorSensor.red());
        telemetry.addData("Blue: ", colorSensor.blue());
        telemetry.update();
        if (colorSensor.red() > colorSensor.blue()) {
            drive.TurnRightDegree(0.3,21);
            jewelKnocker.setPosition(JEWEL_UP);
            drive.TurnLeftDegree(0.3,21);
        } else {
            drive.TurnLeftDegree(0.3,21);
            jewelKnocker.setPosition(JEWEL_UP);
            drive.TurnRightDegree(0.3,21);
        }
    }

}


