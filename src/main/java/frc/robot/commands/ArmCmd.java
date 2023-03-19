package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Arm;

public class ArmCmd extends CommandBase {
    private Arm arm;

    public ArmCmd(Arm arm) {
        this.arm = arm;
        addRequirements(this.arm);
    }

    @Override
    public void initialize() {
        arm.resetArmPos();
    }

    @Override
    public void execute() {
        if (arm.isJoystickMoved()) {
            arm.moveArm(arm.controller.getRawAxis(1));
        } else {
            arm.stopArm();
        }

    }

    @Override
    public void end(boolean interrupted) {
        arm.stopArm();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
