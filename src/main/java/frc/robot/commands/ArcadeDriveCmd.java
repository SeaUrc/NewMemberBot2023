package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class ArcadeDriveCmd extends CommandBase {
    
    private final Drivetrain drivetrain;
    private final Supplier<Double> leftTrigFunc, rightTrigFunc, turnFunc;

    public ArcadeDriveCmd(Drivetrain drivetrain, Supplier<Double> leftTrigFunc, Supplier<Double> rightTrigFunc,  Supplier<Double> turnFunc) {
        this.drivetrain = drivetrain;
        this.leftTrigFunc = leftTrigFunc;
        this.rightTrigFunc = rightTrigFunc;
        this.turnFunc = turnFunc;
        addRequirements(drivetrain);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        double realTimeLeftTrig = leftTrigFunc.get();
        double realTimeRightTrig = rightTrigFunc.get();
        double realTimeTurn = turnFunc.get();

        double realTimeSpeed = realTimeRightTrig - realTimeLeftTrig;
        double leftSpeed = realTimeSpeed + realTimeTurn;
        double rightSpeed = realTimeSpeed - realTimeTurn;
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
