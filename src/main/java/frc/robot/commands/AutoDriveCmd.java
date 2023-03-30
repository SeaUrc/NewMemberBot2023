package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class AutoDriveCmd extends CommandBase{

    private Drivetrain drivetrain = new Drivetrain();

    double leftSpeed;
    double rightSpeed;
    double driveTime;
    double initTime;

    public AutoDriveCmd(Drivetrain drivetrain, double leftSpeed, double rightSpeed, double driveTime){
        this.drivetrain = drivetrain;
        this.leftSpeed = leftSpeed;
        this.rightSpeed = rightSpeed;
        this.driveTime = driveTime;
    }

    @Override
    public void initialize() {
        initTime = Timer.getFPGATimestamp();
    }

    @Override
    public void execute() {
        double currentTime = Timer.getFPGATimestamp();
        if(currentTime - initTime < driveTime){
            drivetrain.setMotors(leftSpeed, rightSpeed);
        }
        else{
            drivetrain.setMotors(0, 0);
        }
    }
}
