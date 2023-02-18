// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.Constants.BaseConstants;
import frc.robot.Constants.SpeedScaleFactors;
import frc.robot.subsystems.Wrist;

public class WristCmd extends CommandBase {
  /** Creates a new ShootBall. */
  private Wrist wrist;

  public WristCmd( Wrist l ) {
    // Use addRequirements() here to declare subsystem dependencies.
    wrist = l;
    addRequirements(wrist);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
   wrist.wrist(RobotContainer.controlsController, SpeedScaleFactors.wristSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}