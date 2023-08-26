package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.BaseConstants;
import frc.robot.Constants.CurrentLimits;
import frc.robot.Constants.SpeedScaleFactors;

public class TopFlap extends SubsystemBase{
    private CANSparkMax TopFlap;
    private RelativeEncoder TopFlapCoder;
    private XboxController controller;
    private double maxPosition;
    private double minPosition;
    

    public TopFlap(XboxController controller){
        this.controller = controller;
        this.maxPosition = BaseConstants.TopFlapMax;
        this.maxPosition = BaseConstants.TopFlapMin;

        this.TopFlap = new CANSparkMax(BaseConstants.TopFlap, MotorType.kBrushless);
        TopFlap.setIdleMode(IdleMode.kBrake);

        TopFlap.setSmartCurrentLimit(CurrentLimits.TopFlapAMPLimit);

        this.TopFlapCoder = TopFlap.getEncoder();
        TopFlapCoder.setPosition(0);
        }

    public void moveToMaxPosition(){
        if(TopFlapCoder.getPosition() < maxPosition){
            TopFlap.set(SpeedScaleFactors.topFlapSpeed);
        }else if(TopFlapCoder.getPosition() >= maxPosition){
            TopFlap.set(0);
        }
    }

    public void moveToMinPosition(){
        if(TopFlapCoder.getPosition() > minPosition){
            TopFlap.set(-SpeedScaleFactors.topFlapSpeed);
        }else if(TopFlapCoder.getPosition() <= minPosition){
            TopFlap.set(0);
        }
    }

    public void movePositive(){
        TopFlap.set(0.1);
    }

    public void moveNegative(){
        TopFlap.set(-0.1);
    }

    public void stopMotor(){
        TopFlap.set(0.0);
    }

    public void resetTopFlapCoder(){
        TopFlapCoder.setPosition(0);
    }

    public boolean LBPressed(){
        return controller.getLeftBumper();
    }
    public boolean RBPressed(){
        return controller.getRightBumper();
    }
    
    @Override
    public void periodic(){
        SmartDashboard.putNumber("TopFlap Encoder", TopFlapCoder.getPosition());
    }
}