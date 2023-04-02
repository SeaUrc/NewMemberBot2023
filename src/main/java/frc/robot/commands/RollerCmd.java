package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import java.util.function.DoubleSupplier;
import frc.robot.subsystems.RollyClaw;

public class RollerCmd extends CommandBase {

    private final RollyClaw mRollyClaw = RollyClaw.getInstance();
    private final DoubleSupplier leftJoyY;

    public RollerCmd(DoubleSupplier leftJoyY) {
        this.leftJoyY = leftJoyY;
    }

    @Override
    public void execute() {
        double rollerSpeed = leftJoyY.getAsDouble();

        mRollyClaw.setRollerSpeed(rollerSpeed);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
