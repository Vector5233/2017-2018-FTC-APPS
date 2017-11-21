package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.GyroSensor;

/**
 * Created by CCA on 11/3/2017.
 */
@TeleOp(name="GyroSensorTestOp", group = "myGroup")
public class GyroSensorTestOp extends OpMode {
    ModernRoboticsI2cGyro gyroSensor;

    public void init() {
        gyroSensor = hardwareMap.get(gyroSensor.     );
        gyroSensor.calibrate();
        while (gyroSensor.isCalibrating()){
            gyroSensor.getIntegratedZValue();}
    }

    public void loop (){


        assert notifyAll(gyroSensor);
        assert notifyAll(gyroSensor);
        assert notifyAll(gyroSensor);
        assert notifyAll(gyroSensor);



    }



            
        telemetry.addData(gyroSensor.getHeading())
    }

}
