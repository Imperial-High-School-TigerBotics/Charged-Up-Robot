/*package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.fasterxml.jackson.databind.JsonSerializable.Base;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.BaseConstants;

public class Hand extends SubsystemBase {
    private TalonFX handFlap;
    private TalonFX handPickUp;
    private boolean isHandDown = false;
    //private boolean handMoving = false;

    private int handTime = -1;
    private int handPartExecution = 0;

    public Hand() {
        // The flap that snaps up and down
        handFlap = new TalonFX(BaseConstants.handFlapID);
        handFlap.setNeutralMode(NeutralMode.Brake);
        handFlap.neutralOutput();

        // The thing that actually sucks in the cube
        handPickUp = new TalonFX(BaseConstants.handPickUpID);
        handPickUp.setNeutralMode(NeutralMode.Brake);
        handPickUp.neutralOutput();
    }

    @Override
    public void periodic() {
        // Called once per scheduler run
    }

    public void handFlap(XboxController controller, double speed) {
        if (controller.getRightBumperPressed()) {
            isHandDown = !isHandDown;
            handPartExecution = 0;

            //handMoving = true;
            handTime = 20 - handTime; // very small amount of time, change this
            
            // start moving the hand flap
            // !change the output values from 0.3!
            if (isHandDown) {
                handFlap.set(ControlMode.PercentOutput, 0.3);
            } else {
                handPickUp.neutralOutput();
                handFlap.set(ControlMode.PercentOutput, -0.3);
            }
        } else if (handTime > 0) {
            // waiting period dead space
            // hand flap is still moving
            handTime--;
        } else if (handTime == 0 && handPartExecution == 0) {
            // stop moving the hand flap
            handFlap.neutralOutput();

            // if the flap is going down, start sucking in the ball
            if (isHandDown) {
                handTime = 20;
                handPartExecution++;
                handPickUp.set(ControlMode.PercentOutput, 0.3);
            }
        } else if (handTime == 0 && handPartExecution == 1 && isHandDown) {
            // if the flap is going down, stop sucking and hope the ball stays in place.
            handPickUp.neutralOutput();
            handTime = -1;
        }
    }
}*/