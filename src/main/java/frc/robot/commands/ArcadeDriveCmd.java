package frc.robot.commands;

import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class ArcadeDriveCmd extends CommandBase {
    
    private final Drivetrain drivetrain;
    private final DoubleSupplier leftTrigFunc, rightTrigFunc, turnFunc;

    public ArcadeDriveCmd(Drivetrain drivetrain, DoubleSupplier leftTrigFunc, DoubleSupplier rightTrigFunc,  DoubleSupplier turnFunc) {
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
        double realTimeLeftTrig = leftTrigFunc.getAsDouble();
        double realTimeRightTrig = rightTrigFunc.getAsDouble();
        double realTimeTurn = turnFunc.getAsDouble();

        // deadzone for left joystick
        if(Math.abs(realTimeTurn) <= .1) {
            realTimeTurn = 0;
        }

        double realTimeSpeed = realTimeRightTrig - realTimeLeftTrig;

        // sets turning sensativity
        realTimeTurn *= realTimeSpeed + 0.3;

        double leftSpeed = limitValue(realTimeSpeed + realTimeTurn, -1, 1);
        double rightSpeed = limitValue(realTimeSpeed - realTimeTurn, -1, 1);

        drivetrain.setMotors(leftSpeed, rightSpeed);
    }

    @Override
    public void end(boolean interrupted) {
    }

    @Override
    public boolean isFinished() {
        return false;
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
