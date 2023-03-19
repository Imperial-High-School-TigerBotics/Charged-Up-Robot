package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Arm extends SubsystemBase {
    private TalonFX armMotor;
    public XboxController controller;
    private double kF;
    private double kP;
    private int maxPosition;
    private int minPosition;

    public Arm(int motorID, double kF, double kP, XboxController controller, int maxPosition, int minPosition) {
        this.armMotor = new TalonFX(motorID);
        this.controller = controller;
        this.kF = kF;
        this.kP = kP;
        this.maxPosition = maxPosition;
        this.minPosition = minPosition;

        this.armMotor.setNeutralMode(NeutralMode.Brake);
        this.armMotor.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
        this.armMotor.configClosedloopRamp(0.2);
    }

    public void moveArm(double joystickValue) {
        double feedforwardValue = kF * -joystickValue;
        double feedbackValue = kP * armMotor.getSelectedSensorPosition();
        double outputValue = feedforwardValue + feedbackValue;

        double currentPosition = armMotor.getSelectedSensorPosition();
        if (currentPosition >= maxPosition && outputValue > 0) {
            outputValue = 0;
        } else if (currentPosition <= minPosition && outputValue < 0) {
            outputValue = 0;
        }

        armMotor.set(ControlMode.PercentOutput, outputValue);
    }

    public void resetArmPos(){
        armMotor.setSelectedSensorPosition(0);
    }

    public void stopArm() {
        armMotor.set(ControlMode.PercentOutput, 0.0);
    }

    public boolean isJoystickMoved() {
        return Math.abs(controller.getRawAxis(1)) > 0.1;
    }
    
    @Override
    public void periodic() {
        SmartDashboard.putNumber("ArmPos", armMotor.getSelectedSensorPosition());
    }
}