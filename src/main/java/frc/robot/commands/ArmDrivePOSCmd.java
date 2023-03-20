package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Extender;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.TopFlap;
import frc.robot.subsystems.Wrist;


public class ArmDrivePOSCmd extends CommandBase{
    private Arm arm;
    private Extender extender;
    private Intake intake;
    private TopFlap topFlap;
    private Wrist wrist;

    public ArmDrivePOSCmd(Arm arm, Extender extender, Intake intake, TopFlap topFlap, Wrist wrist){
        this.arm = arm;
        addRequirements(this.arm);

        this.extender = extender;
        addRequirements(this.extender);

        this.intake = intake;
        addRequirements(intake);

        this.topFlap = topFlap;
        addRequirements(topFlap);

        this.wrist = wrist;
        addRequirements(wrist);
    }

    @Override
    public void initialize(){}

    @Override
    public void execute(){
        arm.moveToSetpoint(1000);
        new WaitCommand(5);
        extender.moveToSetpoint(1000);
        new WaitCommand(5);
        wrist.moveToSetpoint(-1000);
    }

    @Override
    public void end(boolean interrupted){}
    @Override
    public boolean isFinished() {
        return false;
    }
}