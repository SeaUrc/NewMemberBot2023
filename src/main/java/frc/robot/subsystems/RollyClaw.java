package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class RollyClaw extends SubsystemBase{
    
    private static RollyClaw mInstance;

    public static RollyClaw getInstance() {
        if (mInstance == null) {
            mInstance = new RollyClaw();
        }
        return mInstance;
    }

    public RollyClaw() {
        
    }
}
