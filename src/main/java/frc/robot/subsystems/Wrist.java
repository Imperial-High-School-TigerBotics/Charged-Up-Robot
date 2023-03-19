package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Wrist extends SubsystemBase {
    private TalonFX wristMotor;
    public XboxController controller;
    private double kF;
    private double kP;
    private int maxPosition;
    private int minPosition;

    public Wrist(int motorID, double kF, double kP, XboxController controller, int maxPosition, int minPosition) {
        this.wristMotor = new TalonFX(motorID);
        this.controller = controller;
        this.kF = kF;
        this.kP = kP;
        this.maxPosition = maxPosition;
        this.minPosition = minPosition;

        this.wristMotor.setNeutralMode(NeutralMode.Brake);
        this.wristMotor.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
        this.wristMotor.configClosedloopRamp(0.2);

        resetWristPos();
    }

    public void moveWrist(boolean leftBumper, boolean rightBumper) {
        double outputValue = 0;
        if (leftBumper) {
            outputValue = 0.5; // Set outputValue to a positive value to move the arm up
        } else if (rightBumper) {
            outputValue = -0.5; // Set outputValue to a negative value to move the arm down
        }

        double currentPosition = wristMotor.getSelectedSensorPosition();
        if (currentPosition >= maxPosition && outputValue > 0) {
            outputValue = 0;
        } else if (currentPosition <= minPosition && outputValue < 0) {
            outputValue = 0;
        }

        wristMotor.set(ControlMode.PercentOutput, outputValue);
    }

    public void resetWristPos(){
        wristMotor.setSelectedSensorPosition(0);
    }

    public void moveToSetpoint(double setpoint) {
        double feedforwardValue = kF * setpoint;
        double feedbackValue = kP * (setpoint - wristMotor.getSelectedSensorPosition());
        double outputValue = feedforwardValue + feedbackValue;
    
        double currentPosition = wristMotor.getSelectedSensorPosition();
        if (currentPosition >= maxPosition && outputValue > 0) {
            outputValue = 0;
        } else if (currentPosition <= minPosition && outputValue < 0) {
            outputValue = 0;
        }
    
        wristMotor.set(ControlMode.PercentOutput, outputValue);
    }

    public void stopWrist() {
        wristMotor.set(ControlMode.PercentOutput, 0.0);
    }
    
    @Override
    public void periodic() {
        SmartDashboard.putNumber("WristPOS", wristMotor.getSelectedSensorPosition());
    }
}
