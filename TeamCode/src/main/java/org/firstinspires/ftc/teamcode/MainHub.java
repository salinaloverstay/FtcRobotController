package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


public class MainHub {
    public static DcMotor left_motor = null;
    public static DcMotor right_motor = null;

    static Servo servo = null;

    public void init(HardwareMap ahwMap) {
        hwMap = ahwMap;
        left_motor = ahwMap.get(DcMotor.class, "left_motor");
        right_motor = ahwMap.get(DcMotor.class, "right_motor");
        servo = ahwMap.get(Servo.class, "servo");

        left_motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        right_motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        left_motor.setDirection(DcMotor.Direction.FORWARD); // may need to change one of these to reverse?
        right_motor.setDirection(DcMotor.Direction.FORWARD);
    }

    private static HardwareMap hwMap = null;
    private ElapsedTime period = new ElapsedTime();

    public MainHub() {}

    public void setpower(double x) {
        left_motor.setPower(x);
        right_motor.setPower(x);
    }

    public void Stop() {
        setpower(0.0);

    }
}
