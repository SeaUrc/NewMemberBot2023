package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.RollyClaw;

public class WristCmd extends CommandBase {
    private final RollyClaw wrist = RollyClaw.getInstance();
    
}
