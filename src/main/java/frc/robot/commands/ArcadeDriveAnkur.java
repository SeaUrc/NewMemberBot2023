package frc.robot.commands;

import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Drivetrain;

public class ArcadeDriveAnkur extends CommandBase {
    private final Drivetrain mDrivetrain = Drivetrain.getInstance();

    private double leftAxis, rightAxis;
    private DoubleSupplier leftSupplier, rightSupplier;
    private double deadzone = 0.08;
    double leftMotor, rightMotor;

    public ArcadeDriveAnkur(DoubleSupplier left, DoubleSupplier right) {
        addRequirements(mDrivetrain);

        leftSupplier = left;
        rightSupplier = right;
    }

    public void initialize() {
        System.out.println("Ankurs Code");
    }
    
    public void execute() {
        leftAxis = leftSupplier.getAsDouble();
        rightAxis = rightSupplier.getAsDouble();

        if (Math.abs(leftAxis) <= 0.04) {
            leftAxis = 0.0;
        }
        if (Math.abs(rightAxis) <= 0.1) {
            rightAxis = 0.0;
        }

        if (rightAxis <= -deadzone) { //Left Turn
            leftMotor = (leftAxis) + (Constants.DRIVETRAIN_MOD * -rightAxis);
            rightMotor = (leftAxis) + (Constants.DRIVETRAIN_MOD * rightAxis);

        } else { //Right Turn
            leftMotor = (leftAxis) + (Constants.DRIVETRAIN_MOD * -rightAxis);
            rightMotor = (leftAxis) + (Constants.DRIVETRAIN_MOD * rightAxis);
        }

        mDrivetrain.setMotors(leftMotor, rightMotor);
    }
}
