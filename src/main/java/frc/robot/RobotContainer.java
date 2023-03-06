package frc.robot;

import java.util.ArrayList;
import java.util.HashMap;
import com.pathplanner.lib.PathConstraints;
import com.pathplanner.lib.PathPlanner;
import com.pathplanner.lib.PathPlannerTrajectory;
import com.pathplanner.lib.auto.PIDConstants;
import com.pathplanner.lib.auto.SwerveAutoBuilder;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.OIConstants;

import frc.robot.commands.ArmCmd;
import frc.robot.commands.ClawCmd;
import frc.robot.commands.ExtenderCmd;
import frc.robot.commands.SwerveJoystickCmd;
import frc.robot.commands.WristCmd;
import frc.robot.commands.ClawPneumaticsCmd;

import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Claw;
import frc.robot.subsystems.Extender;
import frc.robot.subsystems.SwerveSubsystem;
import frc.robot.subsystems.Wrist;
import frc.robot.subsystems.ClawPneumatics;

public class RobotContainer {

    private final SwerveSubsystem swerveSubsystem = new SwerveSubsystem();

    private final Arm arm;
    private final ArmCmd armCmd;

    private final Claw claw;
    private final ClawCmd clawCmd;

    private final ClawPneumatics clawPneumatics;
    private final ClawPneumaticsCmd clawPneumaticsCmd;

    private final Extender extender;
    private final ExtenderCmd extenderCmd;

    private final Wrist wrist;
    private final WristCmd wristCmd;


    private final Joystick driverJoystick = new Joystick(OIConstants.kDriverControllerPort);

    public static XboxController driverController = new XboxController(OIConstants.kDriverControllerPort);
    public static XboxController controlsController = new XboxController(OIConstants.kControlsControllerPort);

    public RobotContainer() {
        swerveSubsystem.setDefaultCommand(new SwerveJoystickCmd(
                swerveSubsystem,
                () -> -driverJoystick.getRawAxis(OIConstants.kDriverYAxis),
                () -> driverJoystick.getRawAxis(OIConstants.kDriverXAxis),
                () -> driverJoystick.getRawAxis(OIConstants.kDriverRotAxis),
                () -> !driverJoystick.getRawButton(OIConstants.kDriverFieldOrientedButtonIdx)));


        arm = new Arm();
        armCmd = new ArmCmd(arm);
        arm.setDefaultCommand(armCmd);

        extender = new Extender();
        extenderCmd = new ExtenderCmd(extender);
        extender.setDefaultCommand(extenderCmd);

        wrist = new Wrist();
        wristCmd = new WristCmd(wrist);
        wrist.setDefaultCommand(wristCmd);

        claw = new Claw();
        clawCmd = new ClawCmd(claw);
        claw.setDefaultCommand(clawCmd);

        clawPneumatics = new ClawPneumatics();
        clawPneumaticsCmd = new ClawPneumaticsCmd(clawPneumatics);
        clawPneumatics.setDefaultCommand(clawPneumaticsCmd);

        configureButtonBindings();
    }

    private void configureButtonBindings() {
        new JoystickButton(driverJoystick, 1).onTrue(Commands.runOnce(() -> swerveSubsystem.zeroHeading()));
    }

     public Command getAutonomousCommand() {

        //Path Planner Auto

        // This will load the file "(Path Name without .path)" and generate it with a max velocity of 4 m/s and a max acceleration of 3 m/s^2
        // for every path in the group
        ArrayList<PathPlannerTrajectory> pathGroup = (ArrayList<PathPlannerTrajectory>) PathPlanner.loadPathGroup("Spin Line", new PathConstraints(2, 1));

        // This is just an example event map. It would be better to have a constant, global event map
        // in your code that will be used by all path following commands.
        HashMap<String, Command> eventMap = new HashMap<>();
        eventMap.put("marker1", new PrintCommand("Passed marker 1"));
        eventMap.put("marker1", new InstantCommand(clawPneumatics::ClawAuto));
        eventMap.put("marker2", new PrintCommand("Passed marker 2"));
        eventMap.put("marker3", new PrintCommand("Passed marker 3"));
        
        //eventMap.put("intakeDown", new IntakeDown()); //Once marker is passed, command will execute

        // Create the AutoBuilder. This only needs to be created once when robot code starts, not every time you want to create an auto command. A good place to put this is in RobotContainer along with your subsystems.
        SwerveAutoBuilder autoBuilder = new SwerveAutoBuilder(
        swerveSubsystem::getPose, // Pose2d supplier
        swerveSubsystem::resetOdometry, // Pose2d consumer, used to reset odometry at the beginning of auto
        swerveSubsystem.kinemetics(), // SwerveDriveKinematics
        new PIDConstants(5.0, 0.0, 0.0), // Originally: 5.0 | PID constants to correct for translation error (used to create the X and Y PID controllers)
        new PIDConstants(0.5, 0.0, 0.0), // PID constants to correct for rotation error (used to create the rotation controller)
        swerveSubsystem::setModuleStates, // Module states consumer used to output to the drive subsystem
        eventMap,
        true, // Should the path be automatically mirrored depending on alliance color. Optional, defaults to true
        swerveSubsystem// The drive subsystem. Used to properly set the requirements of path following commands
        );

        Command fullAuto = autoBuilder.fullAuto(pathGroup);
        return fullAuto;

     }
        /*
        Old Auto
        // 1. Create trajectory settings
        TrajectoryConfig trajectoryConfig = new TrajectoryConfig(
                AutoConstants.kMaxSpeedMetersPerSecond,
                AutoConstants.kMaxAccelerationMetersPerSecondSquared)
                        .setKinematics(DriveConstants.kDriveKinematics);

        // 2. Generate trajectory
        Trajectory trajectory = TrajectoryGenerator.generateTrajectory(
                new Pose2d(0, 0, new Rotation2d(0)),
                List.of(
                        new Translation2d(1, 0),
                        new Translation2d(1, -1)),
                new Pose2d(2, -1, Rotation2d.fromDegrees(180)),
                trajectoryConfig);

        // 3. Define PID controllers for tracking trajectory
        PIDController xController = new PIDController(AutoConstants.kPXController, 0, 0);
        PIDController yController = new PIDController(AutoConstants.kPYController, 0, 0);
        ProfiledPIDController thetaController = new ProfiledPIDController(
                AutoConstants.kPThetaController, 0, 0, AutoConstants.kThetaControllerConstraints);
        thetaController.enableContinuousInput(-Math.PI, Math.PI);

        // 4. Construct command to follow trajectory
        SwerveControllerCommand swerveControllerCommand = new SwerveControllerCommand(
                trajectory,
                swerveSubsystem::getPose,
                DriveConstants.kDriveKinematics,
                xController,
                yController,
                thetaController,
                swerveSubsystem::setModuleStates,
                swerveSubsystem);

        // 5. Add some init and wrap-up, and return everything
        return new SequentialCommandGroup(
                new InstantCommand(() -> swerveSubsystem.resetOdometry(trajectory.getInitialPose())),
                swerveControllerCommand,
                new InstantCommand(() -> swerveSubsystem.stopModules()));
    }*/
}