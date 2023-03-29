package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class ArcadeDriveCmd extends CommandBase {
    
    private final Drivetrain drivetrain;
    private final Supplier<Double> speedFunc, turnFunc;

    public ArcadeDriveCmd(Drivetrain drivetrain, Supplier<Double> speedFunc,  Supplier<Double> turnFunc) {
        this.drivetrain = drivetrain;
        this.speedFunc = speedFunc;
        this.turnFunc = turnFunc;
        addRequirements(drivetrain);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        Double realTimeSpeed = speedFunc.get();
        Double realTimeTurn = turnFunc.get();

        Double leftSpeed = realTimeSpeed + realTimeTurn;
        Double rightSpeed = realTimeSpeed - realTimeTurn;
        drivetrain.setMotors(leftSpeed, rightSpeed);
    }

    @Override
    public void end(boolean interrupted) {
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
