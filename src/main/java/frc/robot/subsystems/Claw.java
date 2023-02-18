package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.BaseConstants;
import frc.robot.Constants.DpadConstants;

public class Claw extends SubsystemBase{
    /** Creates a new Arm */
    private CANSparkMax claw1;
    private CANSparkMax claw2;

    public Claw() {
        claw1 = new CANSparkMax(BaseConstants.claw1ID, MotorType.kBrushless);
        claw2 = new CANSparkMax(BaseConstants.claw2ID, MotorType.kBrushless);
    }

    @Override
    public void periodic() {
        // Called once per scheduler run
    }

    public void claw( XboxController controller, double speed){
        if( controller.getXButton()){
            claw1.set(speed);
            claw2.set(-speed);
        }else{
            claw1.set(0);
            claw2.set(0);
        }

        if( controller.getYButton()){
            claw1.set(-speed);
            claw2.set(speed);
        }else{
            claw1.set(0);
            claw2.set(0);
        }
    }
}