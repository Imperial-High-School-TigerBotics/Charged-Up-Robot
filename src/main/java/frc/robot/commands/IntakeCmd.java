package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class IntakeCmd extends CommandBase{
    private Intake intake;

    public IntakeCmd(Intake intake){
        this.intake = intake;
        addRequirements(intake);
    }

    @Override
    public void execute(){
        if(intake.getX()){ //Picks up Cubes & Drops Cones
            intake.intake();
        }else if(intake.getY()){ //Picks up Cones & Drops Cubes
            intake.outtake();
        }else{
            intake.stop();
        }
    }
}
