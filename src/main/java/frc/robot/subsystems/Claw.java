package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.BaseConstants;
import frc.robot.Constants.CurrentLimits;
public class Claw extends SubsystemBase{
    /** Creates a new Arm */
    private CANSparkMax claw1;
    private CANSparkMax claw2;

    public Claw() {
        claw1 = new CANSparkMax(BaseConstants.claw1ID, MotorType.kBrushless);
        claw2 = new CANSparkMax(BaseConstants.claw2ID, MotorType.kBrushless);

        claw1.setSmartCurrentLimit(CurrentLimits.clawAMPLimit);
        claw2.setSmartCurrentLimit(CurrentLimits.clawAMPLimit);
    }

    @Override
    public void periodic() {
        // Called once per scheduler run
    }

    public void claw( XboxController controller, double speed){
        if( controller.getXButton()){
            //claw1.set(speed);
            claw2.set(-speed);
        }else if( controller.getYButton()){
            //claw1.set(-speed);
            claw2.set(speed);
        }else{
            claw2.set(0);
        }
    }

}