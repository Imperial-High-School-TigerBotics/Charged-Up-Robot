package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.BaseConstants;
import frc.robot.Constants.SpeedScaleFactors;

public class Intake extends SubsystemBase{
    private TalonFX IntakeMotor;
    private XboxController controller;

    public Intake(XboxController controller){
        this.IntakeMotor = new TalonFX(BaseConstants.Intake);
        this.controller = controller;

        IntakeMotor.set(ControlMode.PercentOutput, 0.0);
        IntakeMotor.setNeutralMode(NeutralMode.Brake);
    }

    public void intake(){
        IntakeMotor.set(ControlMode.PercentOutput, SpeedScaleFactors.intake);
    }
    public void outtake(){
        IntakeMotor.set(ControlMode.PercentOutput, -SpeedScaleFactors.intake);
    }
    public void stop(){
        IntakeMotor.set(ControlMode.PercentOutput, 0.0);
    }
    public boolean getX(){
        return controller.getXButton();
    }
    public boolean getY(){
        return controller.getYButton();
    }
}
