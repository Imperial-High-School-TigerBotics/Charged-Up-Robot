package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.BaseConstants;
import frc.robot.Constants.DpadConstants;

public class Arm extends SubsystemBase{
    /** Creates a new Arm */
    private TalonFX arm;
    static double armPOS;

    public Arm() {
        arm = new TalonFX(BaseConstants.armID);

        arm.set(ControlMode.PercentOutput, 0.0);
        arm.setNeutralMode(NeutralMode.Brake);

        arm.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
        arm.setSelectedSensorPosition(0);

        arm.configForwardSoftLimitThreshold(271505);

        arm.configReverseSoftLimitThreshold(0);

        arm.configForwardSoftLimitEnable(true);
        arm.configReverseSoftLimitEnable(true);

    }

    @Override
    public void periodic() {
        // Called once per scheduler run
        armPOS = arm.getSelectedSensorPosition();
        SmartDashboard.putNumber("Arm Position", armPOS);
    }

    public void arm( XboxController controller, double speed){
        int angle = controller.getPOV();
    if( angle == (DpadConstants.GamePad_UP_DPAD ))
      arm.set(ControlMode.PercentOutput, speed);
    else if( angle <= (DpadConstants.DOWN_DPAD + DpadConstants.DELTA_ANGLE) && angle >= (DpadConstants.DOWN_DPAD - DpadConstants.DELTA_ANGLE))
      arm.set(ControlMode.PercentOutput, -speed);
    else if( angle <= 9 && angle >= -11)
      arm.set(ControlMode.PercentOutput, 0);
    }

  }


