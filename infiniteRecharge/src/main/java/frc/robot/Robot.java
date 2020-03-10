/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.revrobotics.CANEncoder;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.outputs.motion.OI;
import frc.robot.outputs.vision.Camera;
import frc.robot.outputs.vision.Limelight;
import frc.robot.mapping.Robotmap;
import frc.robot.models.enums.RestMode;
import frc.robot.systems.drive.IMotor;
import frc.robot.systems.drive.SparkMax;
import frc.robot.systems.drive.Talon;
import frc.robot.systems.drivetrain.ArcadeDrive;
import frc.robot.systems.subsystems.Climb;
import frc.robot.systems.subsystems.Collector;
import frc.robot.systems.subsystems.Feeder;
import frc.robot.systems.subsystems.Hopper;
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
  private static final String kForwardAuto = "Auto to go forward";
  private static final String kBackwardAuto = "Auto to go backward";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  //#region motors
  
  //drivetrain
  private IMotor _FrontLeftMotor = new SparkMax(Robotmap._frontLeftMotorID);
  private IMotor _RearLeftMotor = new SparkMax(Robotmap._rearLeftMotorID);
  private IMotor _FrontRightMotor = new SparkMax(Robotmap._frontRightMotorID);
  private IMotor _RearRightMotor = new SparkMax(Robotmap._rearRightMotorID);

  private SpeedControllerGroup _LeftMotorGroup = new SpeedControllerGroup(_FrontLeftMotor.GetSparkMax(), _RearLeftMotor.GetSparkMax());
  private SpeedControllerGroup _RightMotorGroup = new SpeedControllerGroup(_FrontRightMotor.GetSparkMax(), _RearRightMotor.GetSparkMax());
  //private MecanumDrivetrain _Drivetrain = new MecanumDrivetrain(mFrontLeft, mRearLeft, mFrontRight, mRearRight);
  private ArcadeDrive _Drivetrain = new ArcadeDrive(_LeftMotorGroup, _RightMotorGroup);

  //shooter
  private IMotor _ShootingOne = new SparkMax(Robotmap._shooterLeftMotorID);
  private IMotor _ShootingTwo = new SparkMax(Robotmap._shooterRightMotorID);
  private CANEncoder _ShootingEncoder = new CANEncoder(_ShootingOne.GetSparkMax());
  
  private Shooter _Shoot = new Shooter(_ShootingOne, _ShootingTwo);

  //intake 
  private IMotor _CollectorMotor = new Talon(Robotmap._intakeMotorID);

  private Collector _Collector = new Collector(_CollectorMotor);

  //hopper
  private IMotor _HopperLeftMotor = new Talon(Robotmap._hopperLeftMotorID);
  private IMotor _HopperRightMotor = new Talon(Robotmap._hopperRightMotorID);

  private Hopper _Hopper = new Hopper(_HopperLeftMotor, _HopperRightMotor);

   //feeder
  private IMotor _FeederMotor = new Talon(Robotmap._feederMotorID);

  private Feeder _Feeder = new Feeder(_FeederMotor);

  //climb and winch
  private IMotor _WinchLeftMotor = new SparkMax(Robotmap._winchLeftMotor);
  private IMotor _WinchRightMotor = new SparkMax(Robotmap._winchRightMotor);
  private IMotor _SlideMotor = new Talon(Robotmap._slideMotor);

  private Climb _Climb = new Climb(_SlideMotor, _WinchLeftMotor, _WinchRightMotor);
  //#endregion motors

  //#region utilities
  private OI _DriveController;
  private OI _OperatorController;
  private Limelight _Limelight;
  private Camera _FeedCamera;
  //#endregion utilities

  //#region game stuff
  private Autonomous _Autonomous = new Autonomous(_Shoot, _Feeder, _Drivetrain, _ShootingEncoder);
  private Teleop _Teleop = new Teleop(_Shoot, _Feeder, _Collector, _Hopper, _Drivetrain, _ShootingEncoder, _DriveController, _OperatorController);
  //#endregion game stuff
  
  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    //auton chooser
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
    
    //vision
    _Limelight = new Limelight();
    _FeedCamera = new Camera(0);

    //#region motor stuff
    _FrontLeftMotor.SetIdleMode(RestMode.Brake);
    _RearLeftMotor.SetIdleMode(RestMode.Brake);
    _FrontRightMotor.SetIdleMode(RestMode.Brake);
    _RearRightMotor.SetIdleMode(RestMode.Brake);
    _ShootingOne.Reset();
    _ShootingTwo.Reset();
    _FeederMotor.Reset();
    //#endregion motor stuff
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
    SmartDashboard.putNumber("shooter rpm", _ShootingEncoder.getVelocity());
    _Limelight.Update();
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
    m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: maxOutput" + m_autoSelected);
    
    _Autonomous.StartAutonomousTimer();
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
      case kForwardAuto:
      _Autonomous.RunAutonomousForwards();
        break;
      case kBackwardAuto:
      _Autonomous.RunAutonomousBackwards();
        break;
      case kDefaultAuto:
      default:
        _Autonomous.RunAutonomousBackwards();
      break;
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() 
  {
    _Teleop.RunTeleop();
  }
  

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
