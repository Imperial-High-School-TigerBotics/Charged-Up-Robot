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
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.BaseConstants;
import frc.robot.Constants.OIConstants;

import frc.robot.commands.ArmCmd;
import frc.robot.commands.ExtenderCmd;
import frc.robot.commands.IntakeCmd;
import frc.robot.commands.SwerveJoystickCmd;
import frc.robot.commands.TopFlapCmd;
import frc.robot.commands.WristCmd;

import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Extender;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.SwerveSubsystem;
import frc.robot.subsystems.TopFlap;
import frc.robot.subsystems.Wrist;

public class RobotContainer {

    private final SwerveSubsystem swerveSubsystem = new SwerveSubsystem();

    private final Arm arm;
    private final ArmCmd armCmd;

    private final Extender extender;
    private final ExtenderCmd extenderCmd;

    private final Wrist wrist;
    private final WristCmd wristCmd;

    private final Intake intake;
    private final IntakeCmd intakeCmd;

    private final TopFlap topFlap;
    private final TopFlapCmd topFlapCmd;


    private final Joystick driverJoystick = new Joystick(OIConstants.kDriverControllerPort);

    public static XboxController driverController = new XboxController(OIConstants.kDriverControllerPort);
    public static XboxController controlsController = new XboxController(OIConstants.kControlsControllerPort);
    public static XboxController testController = new XboxController(OIConstants.kTestControllerPort);

    public RobotContainer() {
        swerveSubsystem.setDefaultCommand(new SwerveJoystickCmd(
                swerveSubsystem,
                () -> -driverJoystick.getRawAxis(OIConstants.kDriverYAxis),
                () -> driverJoystick.getRawAxis(OIConstants.kDriverXAxis),
                () -> driverJoystick.getRawAxis(OIConstants.kDriverRotAxis),
                () -> !driverJoystick.getRawButton(OIConstants.kDriverFieldOrientedButtonIdx)));


        arm = new Arm(BaseConstants.armID, 1, 0.0000000025, controlsController, 360000, 0);
        armCmd = new ArmCmd(arm);
        arm.setDefaultCommand(armCmd);

        extender = new Extender(BaseConstants.extenderID, 1, 0.0000001, controlsController, 145000, 0); // Max & Mins need adjustments
        extenderCmd = new ExtenderCmd(extender);
        extender.setDefaultCommand(extenderCmd);

        wrist = new Wrist(BaseConstants.wristID, 1, 0.00000001, controlsController, 1000, -5000); //Wrist max and min still need to be tested
        wristCmd = new WristCmd(wrist);
        wrist.setDefaultCommand(wristCmd);

        intake = new Intake(controlsController);
        intakeCmd = new IntakeCmd(intake);
        intake.setDefaultCommand(intakeCmd);

        topFlap = new TopFlap(controlsController);
        topFlapCmd = new TopFlapCmd(topFlap);
        topFlap.setDefaultCommand(topFlapCmd);

        configureButtonBindings();
    }

    private void configureButtonBindings() {
        //Driver Controller
        new JoystickButton(driverJoystick, 1).onTrue(Commands.runOnce(() -> swerveSubsystem.zeroHeading()));

        //Test Controller
        new JoystickButton(testController, 2).onTrue(getConeNode3Cmd()); //Should prefectly position arm, extender, intake, and top flap to drop cone in the third node
    }

        Command getConeNode3Cmd(){
        SequentialCommandGroup group = new SequentialCommandGroup();
                group.addCommands(new WaitCommand(5), arm.moveToSetpoint(10000));
                return group;
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
        eventMap.put("marker2", new PrintCommand("Passed marker 2"));
        eventMap.put("marker3", new PrintCommand("Passed marker 3"));
        
        //eventMap.put("intakeDown", new IntakeDown()); //Once marker is passed, command will execute

        // Create the AutoBuilder. This only needs to be created once when robot code starts, not every time you want to create an auto command. A good place to put this is in RobotContainer along with your subsystems.
        SwerveAutoBuilder autoBuilder = new SwerveAutoBuilder(
        swerveSubsystem::getPose, // Pose2d supplier
        swerveSubsystem::resetOdometry, // Pose2d consumer, used to reset odometry at the beginning of auto
        swerveSubsystem.kinemetics(), // SwerveDriveKinematics
        new PIDConstants(0.65, 0.0, 0.0), // Originally: 5.0 | PID constants to correct for translation error (used to create the X and Y PID controllers)
        new PIDConstants(0.75, 0.0, 0.0), // PID constants to correct for rotation error (used to create the rotation controller)
        swerveSubsystem::setModuleStates, // Module states consumer used to output to the drive subsystem
        eventMap,
        true, // Should the path be automatically mirrored depending on alliance color. Optional, defaults to true
        swerveSubsystem// The drive subsystem. Used to properly set the requirements of path following commands
        );

        Command fullAuto = autoBuilder.fullAuto(pathGroup);
        return fullAuto;

     }
}