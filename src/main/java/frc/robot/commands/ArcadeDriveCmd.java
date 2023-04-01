package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class ArcadeDriveCmd extends CommandBase {
    
    private final Drivetrain drivetrain = Drivetrain.getInstance();
    private final DoubleSupplier leftTrigFunc, rightTrigFunc, turnFunc;
    public double realTimeSpeed;

    public ArcadeDriveCmd(Drivetrain drivetrain, DoubleSupplier leftTrigFunc, DoubleSupplier rightTrigFunc,  DoubleSupplier turnFunc) {
        this.leftTrigFunc = leftTrigFunc;
        this.rightTrigFunc = rightTrigFunc;
        this.turnFunc = turnFunc;
        addRequirements(drivetrain);
    }

    @Override
    public void initialize() {
        realTimeSpeed = 0;
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

        realTimeSpeed = accellerate(realTimeRightTrig - realTimeLeftTrig, realTimeSpeed, .1);

        // sets turning sensativity
        realTimeTurn *= Math.abs(realTimeSpeed) + 0.3;

        double leftSpeed = limitValue(realTimeSpeed + realTimeTurn, -1, 1);
        double rightSpeed = limitValue(realTimeSpeed - realTimeTurn, -1, 1);

        drivetrain.setMotors(leftSpeed, rightSpeed);

        SmartDashboard.putNumber("Left Trigger", realTimeLeftTrig);
        SmartDashboard.putNumber("Right Trigger", realTimeRightTrig);
        SmartDashboard.putNumber("Turn", realTimeTurn);
        SmartDashboard.putNumber("Left Speed", leftSpeed);
        SmartDashboard.putNumber("Right Speed", rightSpeed);
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

    // accellerates speed to current speed value
    private double accellerate(double speedTarget, double currentSpeed, double accellerationRate){
        if(currentSpeed < speedTarget) {
            return currentSpeed + accellerationRate;
        } else if(currentSpeed > speedTarget) {
            return currentSpeed - accellerationRate;
        }
        return currentSpeed;
    }
}
