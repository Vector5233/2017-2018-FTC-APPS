package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by CCA on 11/21/2017.
 */

public class DeliverGlyph extends Object {

    Servo leftGrab, rightGrab
    DcMotor frontLeft, frontRight, backLeft, backRight, liftMotor;
    LinearOpMode opmode;
    final double SPROCKET_RATIO = 2.0/3.0;
    final double TICKS_PER_INCH = SPROCKET_RATIO*(1120.0/(2*2*3.14159));

    final double RIGHTGrab_OPEN = 1.0;
    final double RIGHTGrab_CLOSE = 0.4; //used to be 0.46
    final double LEFTGrab_OPEN = 0;
    final double LEFTGrab_CLOSE = 0.6; //used to be 0.54
    final double SPROCKET_RATIO = 2.0/3.0;
}
