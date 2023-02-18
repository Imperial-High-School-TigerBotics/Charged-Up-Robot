package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.BaseConstants;

public class Wrist extends SubsystemBase{
    /** Creates a new Arm */
    private CANSparkMax wrist;

    public Wrist() {
        wrist = new CANSparkMax(BaseConstants.wristID, MotorType.kBrushless);
    }

    @Override
    public void periodic() {
        // Called once per scheduler run
    }

    public void wrist( XboxController controller, double speed){
        if( controller.getAButton()){
            wrist.set(speed);
        }else{
            wrist.set(0);
        }

        if( controller.getBButton()){
            wrist.set(-speed);
        }else{
            wrist.set(0);
        }
    }
}