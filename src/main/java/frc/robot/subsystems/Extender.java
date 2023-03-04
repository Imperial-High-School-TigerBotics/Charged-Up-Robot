package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.BaseConstants;
import frc.robot.Constants.DpadConstants;

public class Extender extends SubsystemBase{
    /** Creates a new Arm */
    private TalonFX extender;

    static double extenderPOS;

    public Extender() {
        extender = new TalonFX(BaseConstants.extenderID);

        extender.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
        extender.setSelectedSensorPosition(0);

        extender.configForwardSoftLimitThreshold(185000);

        extender.configReverseSoftLimitThreshold(5000);

        extender.configForwardSoftLimitEnable(true);
        extender.configReverseSoftLimitEnable(true);
    }

    @Override
    public void periodic() {
        // Called once per scheduler run
        extenderPOS = extender.getSelectedSensorPosition();

        SmartDashboard.putNumber("Extender Position", extenderPOS);
      }

    public void extender( XboxController controller, double speed){
        int angle = controller.getPOV();
    if( angle <= (DpadConstants.LEFT_DPAD + DpadConstants.DELTA_ANGLE) && angle >= (DpadConstants.LEFT_DPAD - DpadConstants.DELTA_ANGLE))
      extender.set(ControlMode.PercentOutput, speed);
    else if( angle <= (DpadConstants.RIGHT_DPAD + DpadConstants.DELTA_ANGLE) && angle >= (DpadConstants.RIGHT_DPAD - DpadConstants.DELTA_ANGLE))
      extender.set(ControlMode.PercentOutput, -speed);
    else if( angle <= 9 && angle >= -11)
      extender.set(ControlMode.PercentOutput, 0);
    }
}

