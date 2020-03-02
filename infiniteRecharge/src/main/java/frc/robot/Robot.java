/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.outputs.motion.OI;
import frc.robot.outputs.motion.PID;
import frc.robot.outputs.vision.Limelight;

import frc.robot.mapping.Robotmap;

import frc.robot.models.DriveAdjust;
import frc.robot.models.enums.DriverType;
import frc.robot.models.enums.RestMode;

import frc.robot.systems.drive.IMotor;
import frc.robot.systems.drive.SparkMax;
import frc.robot.systems.drive.Talon;
import frc.robot.systems.drivetrain.ArcadeDrive;
import frc.robot.systems.drivetrain.Autonomous;
import frc.robot.systems.subsystems.Climb;
import frc.robot.systems.subsystems.Intaker;
import frc.robot.systems.subsystems.Shooter;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  /**
   * DRIVE MOTORS
   */
  private IMotor mFrontLeft = new SparkMax(Robotmap._frontLeftMotorID);
  private IMotor mRearLeft = new SparkMax(Robotmap._rearLeftMotorID);
  private IMotor mFrontRight = new SparkMax(Robotmap._frontRightMotorID);
  private IMotor mRearRight = new SparkMax(Robotmap._rearRightMotorID);

  private SpeedControllerGroup leftGroup = new SpeedControllerGroup(mFrontLeft.GetSpeedController(), mRearLeft.GetSpeedController());
  private SpeedControllerGroup rightGroup = new SpeedControllerGroup(mFrontRight.GetSpeedController(), mRearRight.GetSpeedController());
  //private MecanumDrivetrain _drivetrain = new MecanumDrivetrain(mFrontLeft, mRearLeft, mFrontRight, mRearRight);
  private ArcadeDrive _drivetrain = new ArcadeDrive(leftGroup, rightGroup);

  /**
   * SHOOTER MOTORS
   */
  private IMotor _ShootingOne = new SparkMax(Robotmap._shooterLeftMotorID);
  private IMotor _ShootingTwo = new SparkMax(Robotmap._shooterRightMotorID);
  
  private Shooter _shoot = new Shooter(_ShootingOne, _ShootingTwo);

  /**
   * INTAKE MOTOR
   */
  private IMotor _intakeMotor = new Talon(Robotmap._intakeMotorID);

  private Intaker _intake = new Intaker(_intakeMotor.GetSpeedController());

  /**
   * CLIMB/WINCH MOTORS
   */
  private IMotor _winchLeft = new SparkMax(Robotmap._winchLeftMotor);
  private IMotor _winchRight = new SparkMax(Robotmap._winchRightMotor);
  private IMotor _slide = new Talon(Robotmap._slideMotor);

  private Climb _climb = new Climb(_slide, _winchLeft, _winchRight);

  private OI oi;
  private Limelight _limelight;
  private Autonomous _auton = new Autonomous();

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
    oi = new OI();
    _limelight = new Limelight();

    mFrontLeft.SetIdleMode(RestMode.Brake);
    mRearLeft.SetIdleMode(RestMode.Brake);
    mFrontRight.SetIdleMode(RestMode.Brake);
    mRearRight.SetIdleMode(RestMode.Brake);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);

    _auton.TimerStart();
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        DriveAdjust driveAdjust = PID.CalculateDrive(_limelight.GetTx(), _limelight.GetTy());
        _drivetrain.Drive(driveAdjust);
        break;
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    /**
     * Set the driver type.
     */

     /*
    if (oi.getX())
    {
      _drivetrain.SetDriverType(DriverType.Limelight);
    }
    else*/ if (oi.getB())
    {
      _drivetrain.SetDriverType(DriverType.Human);
    }

    _limelight.Update();

     /**
      * Drive the robot using limelight or the controller.
      */
    if (_drivetrain.GetDriverType() == DriverType.Limelight)
    {
      DriveAdjust driveAdjust = PID.CalculateDrive(_limelight.GetTx(), _limelight.GetTy());
      _drivetrain.Drive(driveAdjust);
    }
    else
    {
      _drivetrain.Drive(oi);
      /*
      if (oi.getY())
      {
        _climb.SlideUp();
        _climb.Unravel();
      }
      else if (oi.getA())
      {
        _climb.SlideDown();
      }
      else if (oi.getDriveRightTrigger() > 0)
      {
        _climb.Ravel();
      }
      else
      {
        _climb.StopMotors();
      }
      */
    }
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
