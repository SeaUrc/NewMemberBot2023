package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.RollyClaw;

public class WristCmd extends CommandBase {

    private final RollyClaw mRollyClaw = RollyClaw.getInstance();
    private final DoubleSupplier rightJoyY;

    public WristCmd(DoubleSupplier rightJoyY) {
        this.rightJoyY = rightJoyY;
    }

    @Override
    public void execute() {
        double wristSpeed = rightJoyY.getAsDouble() * 0.1;

        mRollyClaw.setWristSpeed(wristSpeed);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
