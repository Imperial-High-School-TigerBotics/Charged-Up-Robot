package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.BaseConstants;
import frc.robot.Constants.DpadConstants;

public class Extender extends SubsystemBase{
    /** Creates a new Arm */
    private CANSparkMax extender;

    public Extender() {
        extender = new CANSparkMax(BaseConstants.extenderID, MotorType.kBrushless);
    }

    @Override
    public void periodic() {
        // Called once per scheduler run
    }

    public void extender( XboxController controller, double speed){
        int angle = controller.getPOV();
    if( angle <= (DpadConstants.LEFT_DPAD + DpadConstants.DELTA_ANGLE) && angle >= (DpadConstants.LEFT_DPAD - DpadConstants.DELTA_ANGLE))
      extender.set(speed);
    else if( angle <= (DpadConstants.RIGHT_DPAD + DpadConstants.DELTA_ANGLE) && angle >= (DpadConstants.RIGHT_DPAD - DpadConstants.DELTA_ANGLE))
      extender.set(-speed);
    else if( angle <= 9 && angle >= -11)
      extender.set(0);
    }
}

