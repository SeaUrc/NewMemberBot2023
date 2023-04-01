package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import frc.robot.Constants;

public class ArcadeDriveCmd extends CommandBase {
    
    private final Drivetrain mDrivetrain = Drivetrain.getInstance();
    private final DoubleSupplier leftTrigFunc, rightTrigFunc, turnFunc;

    public ArcadeDriveCmd(Drivetrain mDrivetrain, DoubleSupplier leftTrigFunc, DoubleSupplier rightTrigFunc,  DoubleSupplier turnFunc) {
        this.leftTrigFunc = leftTrigFunc;
        this.rightTrigFunc = rightTrigFunc;
        this.turnFunc = turnFunc;
        addRequirements(mDrivetrain);
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
        realTimeTurn *= (Math.abs(realTimeSpeed) * Constants.speedTurningInfluence) + Constants.turnSpeed;

        double leftSpeed = realTimeSpeed + realTimeTurn;
        double rightSpeed = realTimeSpeed - realTimeTurn;

        mDrivetrain.setMotors(leftSpeed, rightSpeed);

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
}
