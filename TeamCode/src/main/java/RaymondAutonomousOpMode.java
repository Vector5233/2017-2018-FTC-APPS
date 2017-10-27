/**
 * Created by CCA on 10/26/2017.
 */


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class RaymondAutonomousOpMode extends LinearOpMode {
    Servo jewelKnocker;
    DcMotor frontleft, frontright, backleft, backright;
    ColorSensor jewelSensor;
    float red, green, blue;

    public void runOpMode() {
        jewelSensor = hardwareMap.colorSensor.get("color");
        frontleft = hardwareMap.dcMotor.get("frontLeft");
        frontright = hardwareMap.dcMotor.get("frontRight");
    };

}
