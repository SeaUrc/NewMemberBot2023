package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.AutoDriveCmd;
import frc.robot.subsystems.Drivetrain;
import frc.robot.commands.ArcadeDriveCmd;

public class RobotContainer {
  private final Drivetrain mDrivetrain = Drivetrain.getInstance();
  private final XboxController xDrive = new XboxController(0);

  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
    new ArcadeDriveCmd(
      mDrivetrain,
      () -> xDrive.getLeftTriggerAxis(), // left trigger
      () -> xDrive.getRightTriggerAxis(), // right trigger
      () -> xDrive.getLeftX()); // turn axis
  }

  public Command getAutonomousCommand() {
    return new AutoDriveCmd(mDrivetrain, Constants.autoLeftSpeed, Constants.autoRightSpeed, Constants.autoDriveTime);
  }
}
