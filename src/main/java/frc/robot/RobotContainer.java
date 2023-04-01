package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.ArcadeDriveAnkur;
import frc.robot.commands.ArcadeDriveCmd;
import frc.robot.commands.AutoDriveCmd;
import frc.robot.subsystems.Drivetrain;

public class RobotContainer {
  private final Drivetrain mDrivetrain = Drivetrain.getInstance();
  private final XboxController xDrive = new XboxController(0);
  private final XboxController manip = new XboxController(1);

  public RobotContainer() {
      
    mDrivetrain.setDefaultCommand(
      new ArcadeDriveAnkur(
        () -> xDrive.getLeftY(),
        () -> xDrive.getRightX()
      )
    );

    configureBindings();
  }

    private void configureBindings() {
      mDrivetrain.removeDefaultCommand();

      mDrivetrain.setDefaultCommand(
        new ArcadeDriveCmd(
          mDrivetrain,
          () -> xDrive.getLeftTriggerAxis(), // left trigger
          () -> xDrive.getRightTriggerAxis(), // right trigger
          () -> xDrive.getLeftX())); // turn axis

      new JoystickButton(manip, XboxController.Button.kA.value).onTrue(new InstantCommand(() -> xDrive.setRumble(RumbleType.kBothRumble, 1)));
      new JoystickButton(manip, XboxController.Button.kB.value).onTrue(new InstantCommand(() -> xDrive.setRumble(RumbleType.kBothRumble, 0)));

    }

    public Command getAutonomousCommand() {
      return new AutoDriveCmd(mDrivetrain, Constants.autoLeftSpeed, Constants.autoRightSpeed, Constants.autoDriveTime);
    }
}
