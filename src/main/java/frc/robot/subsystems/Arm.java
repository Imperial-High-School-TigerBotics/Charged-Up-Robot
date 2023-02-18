package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.BaseConstants;
import frc.robot.Constants.DpadConstants;

public class Arm extends SubsystemBase{
    /** Creates a new Arm */
    private CANSparkMax arm;

    public Arm() {
        arm = new CANSparkMax(BaseConstants.armID, MotorType.kBrushless);
    }

    @Override
    public void periodic() {
        // Called once per scheduler run
    }

    public void arm( XboxController controller, double speed){
        int angle = controller.getPOV();
    if( angle <= (DpadConstants.UP_DPAD + DpadConstants.DELTA_ANGLE) && angle >= (DpadConstants.UP_DPAD - DpadConstants.DELTA_ANGLE))
      arm.set(speed);
    else if( angle <= (DpadConstants.DOWN_DPAD + DpadConstants.DELTA_ANGLE) && angle >= (DpadConstants.DOWN_DPAD - DpadConstants.DELTA_ANGLE))
      arm.set(-speed);
    else if( angle <= 9 && angle >= -11)
      arm.set(0);
    }
}

