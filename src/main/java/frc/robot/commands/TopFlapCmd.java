package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
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
        if(topFlap.LBPressed()){
            topFlap.movePositive();
        }else if(topFlap.RBPressed()){
            topFlap.moveNegative();
        }else{
            topFlap.stopMotor();
        }
    }

    public void end() {
    }
}
