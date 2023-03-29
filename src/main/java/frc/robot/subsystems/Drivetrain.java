package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {

    // creates motor objects
    private final WPI_TalonSRX leftMotorMaster = new WPI_TalonSRX(Constants.leftMotor1Port);
    private final WPI_VictorSPX leftMotorSlave = new WPI_VictorSPX(Constants.leftMotor2Port);
    private final WPI_TalonSRX rightMotorMaster = new WPI_TalonSRX(Constants.rightMotor1Port);
    private final WPI_VictorSPX rightMotorSlave = new WPI_VictorSPX(Constants.rightMotor2Port);

    public Drivetrain() {
        // invert left and right motors
        leftMotorMaster.setInverted(false);
        rightMotorMaster.setInverted(true);

        // slave settings
        leftMotorSlave.follow(leftMotorMaster);
        rightMotorSlave.follow(rightMotorMaster);

        leftMotorSlave.setInverted(InvertType.FollowMaster);
        rightMotorSlave.setInverted(InvertType.FollowMaster);
    }

    public void setMotors(double leftSpeed, double rightSpeed) {
        leftMotorMaster.set(leftSpeed);
        rightMotorMaster.set(rightSpeed);
    }
}
