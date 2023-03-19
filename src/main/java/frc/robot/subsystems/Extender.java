package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Extender extends SubsystemBase {
    private TalonFX extenderMotor;
    public XboxController controller;
    private double kF;
    private double kP;
    private int maxPosition;
    private int minPosition;

    public Extender(int motorID, double kF, double kP, XboxController controller, int maxPosition, int minPosition) {
        this.extenderMotor = new TalonFX(motorID);
        this.controller = controller;
        this.kF = kF;
        this.kP = kP;
        this.maxPosition = maxPosition;
        this.minPosition = minPosition;

        this.extenderMotor.setNeutralMode(NeutralMode.Brake);
        this.extenderMotor.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
        this.extenderMotor.configClosedloopRamp(0.2);
    }

    public void moveExtender(double joystickValue) {
        double feedforwardValue = kF * -joystickValue;
        double feedbackValue = kP * extenderMotor.getSelectedSensorPosition();
        double outputValue = feedforwardValue + feedbackValue;

        double currentPosition = extenderMotor.getSelectedSensorPosition();
        if (currentPosition >= maxPosition && outputValue > 0) {
            outputValue = 0;
        } else if (currentPosition <= minPosition && outputValue < 0) {
            outputValue = 0;
        }

        extenderMotor.set(ControlMode.PercentOutput, outputValue);
    }

    public void moveToSetpoint(double setpoint) {
        double feedforwardValue = kF * setpoint;
        double feedbackValue = kP * (setpoint - extenderMotor.getSelectedSensorPosition());
        double outputValue = feedforwardValue + feedbackValue;
    
        double currentPosition = extenderMotor.getSelectedSensorPosition();
        if (currentPosition >= maxPosition && outputValue > 0) {
            outputValue = 0;
        } else if (currentPosition <= minPosition && outputValue < 0) {
            outputValue = 0;
        }
    
        extenderMotor.set(ControlMode.PercentOutput, outputValue);
    }

    public void resetExtenderPos(){
        extenderMotor.setSelectedSensorPosition(0);
    }

    public boolean buttonPressed(){
        return controller.getAButton();
    }

    public void stopExtender() {
        extenderMotor.set(ControlMode.PercentOutput, 0.0);
    }

    public boolean isJoystickMoved() {
        return Math.abs(controller.getRawAxis(0)) > 0.1;
    }
    
    @Override
    public void periodic() {
        SmartDashboard.putNumber("ExtenderPOS", extenderMotor.getSelectedSensorPosition() );
    }
}