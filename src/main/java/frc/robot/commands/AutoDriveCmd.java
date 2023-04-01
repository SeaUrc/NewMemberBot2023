package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class AutoDriveCmd extends CommandBase{

    private Drivetrain mDrivetrain = new Drivetrain();

    double leftTargetSpeed;
    double rightTargetSpeed;
    double leftCurrentSpeed;
    double rightCurrentSpeed;
    double driveTime;
    double initTime;

    public AutoDriveCmd(Drivetrain mDrivetrain, double leftTargetSpeed, double rightTargetSpeed, double driveTime){
        this.mDrivetrain = mDrivetrain;
        this.leftTargetSpeed = leftTargetSpeed;
        this.rightTargetSpeed = rightTargetSpeed;
        this.driveTime = driveTime;
        addRequirements(mDrivetrain);
    }

    @Override
    public void initialize() {
        initTime = Timer.getFPGATimestamp();
        leftCurrentSpeed = 0;
        rightCurrentSpeed = 0;
    }

    @Override
    public void execute() {
        double currentTime = Timer.getFPGATimestamp();
        if(currentTime - initTime < driveTime) {
            mDrivetrain.setMotors(leftCurrentSpeed, rightCurrentSpeed);
        } else {
            mDrivetrain.setMotors(0, 0);
        }
    }
}
