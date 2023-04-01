package frc.robot;

import com.revrobotics.CANSparkMax.IdleMode;

public class Constants {
    
    // declare motor ports
    public static final byte leftMotorMaster_ID = 1;
    public static final byte leftMotorSlave_ID = 2;
    public static final byte rightMotorMaster_ID = 3;
    public static final byte rightMotorSlave_ID = 4;
    public static final double DRIVETRAIN_MOD = .8;
    
    public static final byte leftMotor1Port = 20;
    public static final byte leftMotor2Port = 21;
    public static final byte rightMotor1Port = 10;
    public static final byte rightMotor2Port = 11;

    // auto settings
    public static final double autoLeftSpeed = 0.3;
    public static final double autoRightSpeed = 0.3;
    public static final double autoDriveTime = 2.0; // seconds

    // driving settings
    public static final double accellerationRate = 0.08;
    public static final double turnSpeed = 0.3;
    public static final IdleMode motorState = IdleMode.kBrake;

}
