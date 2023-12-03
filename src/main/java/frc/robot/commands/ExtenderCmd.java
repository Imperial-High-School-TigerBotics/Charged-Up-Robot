package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Extender;

public class ExtenderCmd extends CommandBase {
    private Extender extender;

    public ExtenderCmd(Extender extender) {
        this.extender = extender;
        addRequirements(this.extender);
    }

    @Override
    public void initialize() {
       // Timer.delay(5);
    }

    @Override
    public void execute() {
         if(extender.isJoystickMoved()) {
            extender.moveExtender(extender.controller.getRawAxis(0));
        } else {
            extender.stopExtender();
        }

        if(extender.buttonPressed()){
            extender.resetExtenderPos();
        }
       // extender.moveToSetpoint(70000);
    }

    @Override
    public void end(boolean interrupted) {
        extender.stopExtender();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
