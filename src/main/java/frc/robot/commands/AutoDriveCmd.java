package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class AutoDriveCmd extends CommandBase{

    private Drivetrain mDrivetrain = new Drivetrain();

    double targetSpeed;
    double currentSpeed;
    double driveTime;
    double initTime;

    public AutoDriveCmd(Drivetrain drivetrain, double targetSpeed, double driveTime){
        this.mDrivetrain = drivetrain;
        this.targetSpeed = targetSpeed;
        this.driveTime = driveTime;
    }

    @Override
    public void initialize() {
        initTime = Timer.getFPGATimestamp();
        currentSpeed = 0;
    }

    @Override
    public void execute() {
        double currentTime = Timer.getFPGATimestamp();
        if(currentTime - initTime < driveTime){
            mDrivetrain.setMotors(currentSpeed, currentSpeed);
        }
        else{
            mDrivetrain.setMotors(0, 0);
        }
    }
}
