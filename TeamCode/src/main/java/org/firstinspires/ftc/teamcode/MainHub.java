package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.motors.RevRoboticsCoreHexMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


public class MainHub {
    public static DcMotor left_motor = null;
    public static DcMotor right_motor = null;

   // public static RevRoboticsCoreHexMotor core_motor = null;

    static Servo servo = null;


    double ticksPerRotation = 28;
    double motorRotationPerShaftRotation = 19/2;
    double WheelRotationPerShaftRotation = 2;
    double DistancePerWheelRotation = 11.87;
    double ticksPerInch = (ticksPerRotation*motorRotationPerShaftRotation)/(WheelRotationPerShaftRotation/DistancePerWheelRotation);

    public void init(HardwareMap ahwMap) {
        hwMap = ahwMap;
        left_motor = ahwMap.get(DcMotor.class, "left_motor");
        right_motor = ahwMap.get(DcMotor.class, "right_motor");
      //  core_motor = ahwMap.get(RevRoboticsCoreHexMotor.class, "core_motor");
        servo = ahwMap.get(Servo.class, "servo");

        left_motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        right_motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        left_motor.setDirection(DcMotor.Direction.FORWARD); // may need to change one of these to reverse?
        right_motor.setDirection(DcMotor.Direction.FORWARD);

        left_motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        right_motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    private static HardwareMap hwMap = null;
    private ElapsedTime period = new ElapsedTime();

    public MainHub() {}

    public void setpower(double x) {
        left_motor.setPower(x);
        right_motor.setPower(x);

    }

    public void drive(double inches, LinearOpMode opMode) {
        left_motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        right_motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        double distance = inches*ticksPerInch;
        int encoderDrivingTarget = (int)distance;
        left_motor.setTargetPosition(encoderDrivingTarget);
        right_motor.setTargetPosition(encoderDrivingTarget);
        setpower(0.5);
        left_motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        right_motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while((left_motor.isBusy() || right_motor.isBusy() && opMode.opModeIsActive())) {}

        setpower(0);

    }

    public void Stop() {
        setpower(0.0);

    }
}
