package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.MainHub.left_motor;
import static org.firstinspires.ftc.teamcode.MainHub.right_motor;
import static org.firstinspires.ftc.teamcode.MainHub.servo;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


@TeleOp(name="Teleop", group="Linear Opmode")
public class Teleop extends LinearOpMode {
    MainHub robot = new MainHub();

    private final ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        robot.init(hardwareMap);

        servo.setPosition(0.0);
        waitForStart();
        runtime.reset();

        // run until the driver presses STOP
        while (opModeIsActive()) {
            double leftMotorPower;
            double rightMotorPower;
            double armLiftPower;


            double drive = -gamepad1.left_stick_y;
            double turn = gamepad1.right_stick_x;
            double strafe = gamepad1.left_stick_x;

            boolean sloMo1 = gamepad1.right_bumper;
            boolean xbutton = gamepad1.x;
            boolean ybutton = gamepad1.y;
            boolean abutton = gamepad1.a;
            boolean bbutton = gamepad1.b;

            // button to lift arm with the hex motor?
            double armLift = gamepad1.right_stick_y;

            boolean dpadRight = gamepad1.dpad_right;
            boolean dpadLeft = gamepad1.dpad_left;
            boolean dpadUp = gamepad1.dpad_up;
            boolean dpadDown = gamepad1.dpad_down;

            armLiftPower = Range.clip(armLift, -1.0, 1.0);

            if (sloMo1) {
                // might need to change the signs in the "drive turn strafe" part
                leftMotorPower = Range.clip(drive + turn + strafe, -.5, .5);
                rightMotorPower = Range.clip(drive - turn - strafe, -.5, .5);

            }
            else {
                leftMotorPower = Range.clip(drive + turn + strafe, -1.0, 1.0);
                rightMotorPower = Range.clip(drive - turn - strafe, -1.0, 1.0);
            }

            // send power to motors to make wheels move
            left_motor.setPower(leftMotorPower);
            right_motor.setPower(rightMotorPower);

            // armLift.setPower(armLiftPower); - not sure how to set the power for the hex motor?
            /*
            if (dpadUp) {
                armLift.setPosition(1.0);
            }
*/

            telemetry.addData("Status", "Run Time: " +  runtime.toString());
            telemetry.addData("Motors", "left ( %.2f), right (%.2f)", leftMotorPower, rightMotorPower);

            telemetry.addData("Right Motor", right_motor.getCurrentPosition());
            telemetry.addData("Left Motor", left_motor.getCurrentPosition());

            telemetry.update();

        }
    }
}
