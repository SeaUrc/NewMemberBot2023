package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {

    // creates motor objects
    private final CANSparkMax leftMotorMaster = new CANSparkMax(Constants.leftMotor1Port, MotorType.kBrushless);
    private final CANSparkMax leftMotorSlave = new CANSparkMax(Constants.leftMotor2Port, MotorType.kBrushless);
    private final CANSparkMax rightMotorMaster = new CANSparkMax(Constants.rightMotor1Port, MotorType.kBrushless);
    private final CANSparkMax rightMotorSlave = new CANSparkMax(Constants.rightMotor2Port, MotorType.kBrushless);

    public Drivetrain() {
        // invert left and right motors
        leftMotorMaster.setInverted(false);
        rightMotorMaster.setInverted(true);

        // slave settings
        leftMotorSlave.follow(leftMotorMaster);
        rightMotorSlave.follow(rightMotorMaster);

        leftMotorSlave.setInverted(leftMotorMaster.getInverted());
        rightMotorSlave.setInverted(rightMotorMaster.getInverted());
    }

    public void setMotors(double leftSpeed, double rightSpeed) {
        leftMotorMaster.set(leftSpeed);
        rightMotorMaster.set(rightSpeed);
    }
}
