package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {

    // creates motor objects

    private static Drivetrain mInstance;

    public static Drivetrain getInstance() {
        if (mInstance == null) {
            mInstance = new Drivetrain();
        }
        return mInstance;
    }

    private final CANSparkMax leftMotorMaster = new CANSparkMax(Constants.leftMotorMaster_ID, MotorType.kBrushless);
    private final CANSparkMax leftMotorSlave = new CANSparkMax(Constants.leftMotorSlave_ID, MotorType.kBrushless);
    private final CANSparkMax rightMotorMaster = new CANSparkMax(Constants.rightMotorMaster_ID, MotorType.kBrushless);
    private final CANSparkMax rightMotorSlave = new CANSparkMax(Constants.rightMotorSlave_ID, MotorType.kBrushless);

    public Drivetrain() {
        // Idle Settings
        leftMotorMaster.setIdleMode(IdleMode.kBrake);
        leftMotorSlave.setIdleMode(IdleMode.kBrake);
        rightMotorMaster.setIdleMode(IdleMode.kBrake);
        rightMotorSlave.setIdleMode(IdleMode.kBrake);

        // invert left and right motors Ankur
        // rightMotorMaster.setInverted(false);
        // rightMotorSlave.setInverted(false);
        // leftMotorMaster.setInverted(true);
        // leftMotorSlave.setInverted(true);

        rightMotorMaster.setInverted(true);
        rightMotorSlave.setInverted(true);
        leftMotorMaster.setInverted(false);
        leftMotorSlave.setInverted(false);

        // slave settings
        leftMotorSlave.follow(leftMotorMaster);
        rightMotorSlave.follow(rightMotorMaster);
    }

    public void setMotors(double leftSpeed, double rightSpeed) {
        leftMotorMaster.set(leftSpeed);
        rightMotorMaster.set(rightSpeed);
    }
}
