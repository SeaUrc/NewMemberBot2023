package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.ArcadeDriveAnkur;
import frc.robot.commands.ArcadeDriveCmd;
import frc.robot.subsystems.Drivetrain;

public class RobotContainer {
  private final Drivetrain drivetrain = Drivetrain.getInstance();

  // private final Joystick joystick = new Joystick(0);
  private final XboxController xDrive = new XboxController(0);
  private final XboxController manip = new XboxController(1);

  public RobotContainer() {
      
    drivetrain.setDefaultCommand(
      new ArcadeDriveAnkur(
        () -> xDrive.getLeftY(),
        () -> xDrive.getRightX()
      )
    );

    configureBindings();
  }

    private void configureBindings() {
      drivetrain.removeDefaultCommand();

      drivetrain.setDefaultCommand(
        new ArcadeDriveCmd(
          drivetrain,
          () -> xDrive.getLeftTriggerAxis(), // left trigger
          () -> xDrive.getRightTriggerAxis(), // right trigger
          () -> xDrive.getLeftX())); // turn axis

      new JoystickButton(manip, XboxController.Button.kA.value).onTrue(new InstantCommand(() -> xDrive.setRumble(RumbleType.kBothRumble, 1)));
      new JoystickButton(manip, XboxController.Button.kB.value).onTrue(new InstantCommand(() -> xDrive.setRumble(RumbleType.kBothRumble, 0)));

    }

    public Command getAutonomousCommand() {
      return Commands.print("No autonomous command configured");
    }
}
