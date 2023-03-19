package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.TopFlap;

public class TopFlapCmd extends CommandBase{
    private TopFlap topFlap;

    public TopFlapCmd(TopFlap topFlap){
        this.topFlap = topFlap;
        addRequirements(topFlap);
    }

    @Override
    public void initialize(){
        topFlap.resetTopFlapCoder();
    }
    public void execute(){
        if(topFlap.APressed()){
            topFlap.moveToMaxPosition();
        }else if(topFlap.BPressed()){
            topFlap.moveToMinPosition();
        }
    }

    public void end() {
    }
}
