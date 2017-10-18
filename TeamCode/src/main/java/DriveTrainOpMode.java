import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by CCA on 10/18/2017.
 */
@TeleOp

public class DriveTrainOpMode extends LinearOpMode {
    @Override
    private DcMotor motorTest;
    private Servo servoTest;
    public void runOpMode() throws InterruptedException {
    motorTest=hardwareMap.get(DcMotor.class, motorTest);
    }
}
