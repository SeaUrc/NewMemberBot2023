package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.commands.ArcadeDriveCmd;
import frc.robot.subsystems.Drivetrain;

public class RobotContainer {
  private final Drivetrain drivetrain = new Drivetrain();

  private final Joystick joystick = new Joystick(0);

  public RobotContainer() {

    drivetrain.setDefaultCommand(
      new ArcadeDriveCmd(
        drivetrain,
        () -> joystick.getRawAxis(Constants.leftTrigAxis), // left trigger
        () -> joystick.getRawAxis(Constants.rightTrigAxis), // right trigger
        () -> joystick.getRawAxis(Constants.turnJoyAxis))); // turn axis

    configureBindings();
    }

    private void configureBindings() {
    }

    public Command getAutonomousCommand() {
      return Commands.print("No autonomous command configured");
    }
}
