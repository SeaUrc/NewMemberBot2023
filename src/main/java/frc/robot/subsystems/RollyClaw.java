package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class RollyClaw extends SubsystemBase{
    
    private static RollyClaw mInstance;

    public static RollyClaw getInstance() {
        if (mInstance == null) {
            mInstance = new RollyClaw();
        }
        return mInstance;
    }

    // declare motor ports
    private final WPI_TalonFX wristMotor = new WPI_TalonFX(Constants.wristMotor_ID);
    private final WPI_TalonFX rollerMotor = new WPI_TalonFX(Constants.rollerMotor_ID);

    public RollyClaw() {
        // sets brake mode
        wristMotor.setNeutralMode(NeutralMode.Brake);
        rollerMotor.setNeutralMode(NeutralMode.Brake);

        // inverts motors
        wristMotor.setInverted(false);
        rollerMotor.setInverted(false);
    }

    public void setRollerSpeed(double rollerSpeed) {
        rollerMotor.set(rollerSpeed);
    }

    public void setWristSpeed(double wristSpeed) {
        wristMotor.set(wristSpeed);
    }

}
