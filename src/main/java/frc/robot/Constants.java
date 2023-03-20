package frc.robot;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.math.util.Units;

public final class Constants {

    public static final class GearRatios{
        public static final double armGearRatio = 1/240;
        public static final double extenderGearRatio = 1/27;
    }

    public static final class BaseConstants{

        public static final double Falcon_Ticks_Per_Rev = 2048;

        //Pigeon 2.0 Gyro
        public static final int PigeonID = 13;

        //Base IDs for arm, arm extender, and wrist
        public static final int armID = 16;


        public static final int extenderID = 15;
        public static final int wristID = 14;

        //Claw IDs
        public static final int Intake = 18;
        public static final int TopFlap = 17;
        public static final double TopFlapMax = 2;
        public static final double TopFlapMin = 0.1;

        //Pneumatic IDs
    }

    public static final class SpeedScaleFactors{
        public static final double SwerveMaxSpeed = 0.75; //Speed in percentage
        public static final double SwerveMaxTurnSpeed = 0.30;
        public static final double armSpeed = 0.5;
        public static final double extenderSpeed = 0.5;
        public static final double wristSpeed = 0.3;
        public static final double intake = 0.4;
        public static final double topFlapSpeed = 0.4;
    }

    public static final class CurrentLimits{
        public static final int TopFlapAMPLimit = 20;
        public static final int armAMPLimit = 30;
        public static final int extenderAMPLimit = 30; 
        public static final int wristAMPLimit = 35;
    }

    public static final class DpadConstants{
        public static final int RIGHT_DPAD = 90;
        public static final int DELTA_ANGLE = 10;
        public static final int LEFT_DPAD = 270;
        public static final int UP_DPAD = 360;
        public static final int GamePad_UP_DPAD = 0;
        public static final int DOWN_DPAD = 180;
    }


    public static final class ModuleConstants {
        public static final double kWheelDiameterMeters = Units.inchesToMeters(4);
        public static final double kDriveMotorGearRatio = 1 / 6.75;
        public static final double kTurningMotorGearRatio = 1 / 21.428;
        public static final double kDriveEncoderRot2Meter = kDriveMotorGearRatio * Math.PI * kWheelDiameterMeters;
        public static final double kTurningEncoderRot2Rad = kTurningMotorGearRatio * 2 * Math.PI;
        public static final double kDriveEncoderRPM2MeterPerSec = kDriveEncoderRot2Meter / 60;
        public static final double kTurningEncoderRPM2RadPerSec = kTurningEncoderRot2Rad / 60;
        public static final double kPTurning = 0.5;
    }

    public static final class DriveConstants {

        public static final double kTrackWidth = Units.inchesToMeters(24);
        // Distance between right and left wheels
        public static final double kWheelBase = Units.inchesToMeters(24);
        // Distance between front and back wheels
        public static final SwerveDriveKinematics kDriveKinematics = new SwerveDriveKinematics(
                new Translation2d(kWheelBase / 2, -kTrackWidth / 2),
                new Translation2d(kWheelBase / 2, kTrackWidth / 2),
                new Translation2d(-kWheelBase / 2, -kTrackWidth / 2),
                new Translation2d(-kWheelBase / 2, kTrackWidth / 2));

        public static final int kFrontLeftDriveMotorPort = 4; //Make sure motor ports match with robot (CAN)
        public static final int kBackLeftDriveMotorPort = 2;
        public static final int kFrontRightDriveMotorPort = 6;
        public static final int kBackRightDriveMotorPort = 8;

        public static final int kFrontLeftTurningMotorPort = 3;
        public static final int kBackLeftTurningMotorPort = 1;
        public static final int kFrontRightTurningMotorPort =  5;
        public static final int kBackRightTurningMotorPort = 7;

        public static final boolean kFrontLeftTurningEncoderReversed = false;
        public static final boolean kBackLeftTurningEncoderReversed = false;
        public static final boolean kFrontRightTurningEncoderReversed = false;
        public static final boolean kBackRightTurningEncoderReversed = false;

        public static final boolean kFrontLeftDriveEncoderReversed = false;
        public static final boolean kBackLeftDriveEncoderReversed = false;
        public static final boolean kFrontRightDriveEncoderReversed = false;
        public static final boolean kBackRightDriveEncoderReversed = false;

        public static final int kFrontLeftDriveAbsoluteEncoderPort = 11; //Make sure encoder ports match with robot (CAN)
        public static final int kBackLeftDriveAbsoluteEncoderPort = 12;
        public static final int kFrontRightDriveAbsoluteEncoderPort = 10;
        public static final int kBackRightDriveAbsoluteEncoderPort = 9;

        public static final boolean kFrontLeftDriveAbsoluteEncoderReversed = false;
        public static final boolean kBackLeftDriveAbsoluteEncoderReversed = false;
        public static final boolean kFrontRightDriveAbsoluteEncoderReversed = false;
        public static final boolean kBackRightDriveAbsoluteEncoderReversed = false;

        public static final double kFrontLeftDriveAbsoluteEncoderOffsetDeg = 127.353516;//11
        public static final double kBackLeftDriveAbsoluteEncoderOffsetDeg = 270.087891;//10
        public static final double kFrontRightDriveAbsoluteEncoderOffsetDeg = 273.603516;//12
        public static final double kBackRightDriveAbsoluteEncoderOffsetDeg = 125.595703;//9

        public static final double kPhysicalMaxSpeedMetersPerSecond = 4.4; // Original: 5
        public static final double kPhysicalMaxAngularSpeedRadiansPerSecond = 2 * 2 * Math.PI; // Original: 2 * 2 * Math.PI

        public static final double kTeleDriveMaxSpeedMetersPerSecond = kPhysicalMaxSpeedMetersPerSecond * SpeedScaleFactors.SwerveMaxSpeed;// / 4;
        public static final double kTeleDriveMaxAngularSpeedRadiansPerSecond = //
                kPhysicalMaxAngularSpeedRadiansPerSecond * SpeedScaleFactors.SwerveMaxSpeed;// / 4;
        public static final double kTeleDriveMaxAccelerationUnitsPerSecond = 3; //Original: 3
        public static final double kTeleDriveMaxAngularAccelerationUnitsPerSecond = 3; //Original 3
    }

    public static final class AutoConstants {
        public static final double kMaxSpeedMetersPerSecond = DriveConstants.kPhysicalMaxSpeedMetersPerSecond / 4;
        public static final double kMaxAngularSpeedRadiansPerSecond = //
                DriveConstants.kPhysicalMaxAngularSpeedRadiansPerSecond / 10;
        public static final double kMaxAccelerationMetersPerSecondSquared = 6;
        public static final double kMaxAngularAccelerationRadiansPerSecondSquared = Math.PI / 4;
        public static final double kPXController = 1.5;
        public static final double kPYController = 1.5;
        public static final double kPThetaController = 3;

        public static final TrapezoidProfile.Constraints kThetaControllerConstraints = //
                new TrapezoidProfile.Constraints(
                        kMaxAngularSpeedRadiansPerSecond,
                        kMaxAngularAccelerationRadiansPerSecondSquared);
    }

    public static final class OIConstants {
        public static final int kDriverControllerPort = 0;
        public static final int kControlsControllerPort = 1;
        public static final int kTestControllerPort = 2;

        public static final int kDriverYAxis = 1; //Make sure constants work for Logitech & Xbox controller
        public static final int kDriverXAxis = 0;
        public static final int kDriverRotAxis = 4;
        public static final int kDriverFieldOrientedButtonIdx = 1;

        public static final double kDeadband = 0.01;
    }
}