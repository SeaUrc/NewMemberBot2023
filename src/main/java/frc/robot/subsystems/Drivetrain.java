package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {

    private static Drivetrain mInstance;

    public static Drivetrain getInstance() {
        if (mInstance == null) {
            mInstance = new Drivetrain();
        }
        return mInstance;
    }

    // declare motor ports
    private final CANSparkMax leftMotorMaster = new CANSparkMax(Constants.leftMotorMaster_ID, MotorType.kBrushless);
    private final CANSparkMax leftMotorSlave = new CANSparkMax(Constants.leftMotorSlave_ID, MotorType.kBrushless);
    private final CANSparkMax rightMotorMaster = new CANSparkMax(Constants.rightMotorMaster_ID, MotorType.kBrushless);
    private final CANSparkMax rightMotorSlave = new CANSparkMax(Constants.rightMotorSlave_ID, MotorType.kBrushless);

    private double leftCurrentSpeed;
    private double rightCurrentSpeed;

    public Drivetrain() {
        // idle Settings
        leftMotorMaster.setIdleMode(Constants.motorState);
        leftMotorSlave.setIdleMode(Constants.motorState);
        rightMotorMaster.setIdleMode(Constants.motorState);
        rightMotorSlave.setIdleMode(Constants.motorState);

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

        leftCurrentSpeed = 0;
        rightCurrentSpeed = 0;
    }

    public void setMotors(double leftTargetSpeed, double rightTargetSpeed) {
        leftCurrentSpeed = limitValue(accellerate(leftTargetSpeed, leftCurrentSpeed), -1, 1);
        rightCurrentSpeed = limitValue(accellerate(rightTargetSpeed, rightCurrentSpeed), -1, 1);

        leftMotorMaster.set(leftCurrentSpeed);
        rightMotorMaster.set(rightCurrentSpeed);
    }

    // accellerates speed to current speed value
    private double accellerate(double speedTarget, double currentSpeed){
        if(speedTarget == 0) {
            return 0;
        } else if(currentSpeed < speedTarget && (speedTarget - currentSpeed) >= Constants.accellerationRate) {
            return currentSpeed + Constants.accellerationRate;
        } else if(currentSpeed > speedTarget && (currentSpeed - speedTarget) >= Constants.accellerationRate) {
            return currentSpeed - Constants.accellerationRate;
        }
        return currentSpeed;
    }

    // limits a value between a max and min value
    private double limitValue(double value, double min, double max) {
        if(value < min) {
            return min;
        } else if(value > max) {
            return max;
        } else {
            return value;
        }
    }
}
