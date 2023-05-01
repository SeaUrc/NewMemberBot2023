package frc.robot;

import com.revrobotics.CANSparkMax.IdleMode;

public class Constants {
    
    // declare motor ports
    public static final byte leftMotorMaster_ID = 1;
    public static final byte leftMotorSlave_ID = 2;
    public static final byte rightMotorMaster_ID = 3;
    public static final byte rightMotorSlave_ID = 4;
    
    // auto settings
    public static final double autoLeftSpeed = 0.3;
    public static final double autoRightSpeed = 0.3;
    public static final double autoDriveTime = 2.0; // seconds
    
    // driving settings
    public static final double accellerationRate = 0.08; // 0.08 worked well
    public static final double turnSpeed = 0.3;
    public static final double speedTurningInfluence = 0.6;
    public static final IdleMode motorState = IdleMode.kBrake;
}
