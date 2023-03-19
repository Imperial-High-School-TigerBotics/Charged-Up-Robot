package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.BaseConstants;

public class Wrist extends SubsystemBase{
    /** Creates a new Arm */
    private TalonFX wrist;

    public Wrist() {
        wrist = new TalonFX(BaseConstants.wristID);
        wrist.setNeutralMode(NeutralMode.Brake);
        wrist.neutralOutput();
    }

    @Override
    public void periodic() {
        // Called once per scheduler run
    }

    public void wrist( XboxController controller, double speed){
        if( controller.getRightBumper()){
            wrist.set(ControlMode.PercentOutput, 0.3);
        }else if(controller.getLeftBumper()){
            wrist.set(ControlMode.PercentOutput, -0.3);
        }else{
            wrist.neutralOutput();
        }
    }
}