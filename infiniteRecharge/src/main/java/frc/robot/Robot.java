/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.revrobotics.CANEncoder;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
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
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  //#region motors
  /**
   * DRIVE MOTORS
   */
  private IMotor mFrontLeft = new SparkMax(Robotmap._frontLeftMotorID);
  private IMotor mRearLeft = new SparkMax(Robotmap._rearLeftMotorID);
  private IMotor mFrontRight = new SparkMax(Robotmap._frontRightMotorID);
  private IMotor mRearRight = new SparkMax(Robotmap._rearRightMotorID);

  private SpeedControllerGroup leftGroup = new SpeedControllerGroup(mFrontLeft.GetSparkMax(), mRearLeft.GetSparkMax());
  private SpeedControllerGroup rightGroup = new SpeedControllerGroup(mFrontRight.GetSparkMax(), mRearRight.GetSparkMax());
  //private MecanumDrivetrain _drivetrain = new MecanumDrivetrain(mFrontLeft, mRearLeft, mFrontRight, mRearRight);
  private ArcadeDrive _drivetrain = new ArcadeDrive(leftGroup, rightGroup);

  /**
   * SHOOTER MOTORS
   */
  private IMotor _ShootingOne = new SparkMax(Robotmap._shooterLeftMotorID);
  private IMotor _ShootingTwo = new SparkMax(Robotmap._shooterRightMotorID);
  private CANEncoder _shootingEncoder = new CANEncoder(_ShootingOne.GetSparkMax());
  
  private Shooter _shoot = new Shooter(_ShootingOne, _ShootingTwo);

  /**
   * INTAKE MOTOR
   */
  private IMotor _intakeMotor = new Talon(Robotmap._intakeMotorID);

  private Collector _intake = new Collector(_intakeMotor);

  /**
   * HOPPER MOTOR
   */
  private IMotor _hopperLeftMotor = new Talon(Robotmap._hopperLeftMotorID);
  private IMotor _hopperRightMotor = new Talon(Robotmap._hopperRightMotorID);

  private Hopper _hopper = new Hopper(_hopperLeftMotor, _hopperRightMotor);

   /**
    * FEEDER MOTOR
    */
    private IMotor _feederMotor = new Talon(Robotmap._feederMotorID);

    private Feeder _feeder = new Feeder(_feederMotor);

  /**
   * CLIMB/WINCH MOTORS
   */
  private IMotor _winchLeft = new SparkMax(Robotmap._winchLeftMotor);
  private IMotor _winchRight = new SparkMax(Robotmap._winchRightMotor);
  private IMotor _slide = new Talon(Robotmap._slideMotor);

  private Climb _climb = new Climb(_slide, _winchLeft, _winchRight);
  //#endregion motors

  private OI _driveController;
  private OI _operatorController;
  private Limelight _limelight;

  //Needed for autonomous
  private Timer _McTimer = new Timer();
  private final double power = 0.27;
  private final double _desiredVelocity = 1230;

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
    _driveController = new OI(0);
    _operatorController = new OI(1);

    _limelight = new Limelight();

    mFrontLeft.SetIdleMode(RestMode.Brake);
    mRearLeft.SetIdleMode(RestMode.Brake);
    mFrontRight.SetIdleMode(RestMode.Brake);
    mRearRight.SetIdleMode(RestMode.Brake);

    UsbCamera intakeCam = CameraServer.getInstance().startAutomaticCapture(0);
    intakeCam.setResolution(240, 240);
    intakeCam.setFPS(30);

    UsbCamera feedCam = CameraServer.getInstance().startAutomaticCapture(1);
    feedCam.setResolution(240, 240);
    feedCam.setFPS(30);

    SmartDashboard.getNumber("velocity", 0.0);

    _ShootingOne.Reset();
    _ShootingTwo.Reset();
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
    SmartDashboard.putNumber("shooter rpm", _shootingEncoder.getVelocity());
    _limelight.Update();
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
    System.out.println("Auto selected: maxOutput" + m_autoSelected);
    

    _McTimer.start();
    _feederMotor.Reset();
    _feeder.Feed(0.0);
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
      _shoot.Shoot(power);
        if (_shootingEncoder.getVelocity() > _desiredVelocity)
        {
          _feeder.Feed(-.7);
        }
        if (_McTimer.get() > 8 && _McTimer.get() < 12)
        {
          _drivetrain.Drive(0.5, 0.0);
        }
        else if (_McTimer.get() > 12)
        {
          _drivetrain.Drive(0.0, 0.0);
        }
        break;
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() 
  {
    //Driver
    _drivetrain.Drive(_driveController);

    //Operator
    if (_operatorController.dpad() == 180.0)
    {
      _hopper.ReverseHopper();
    }
    else if (_operatorController.dpad() == 90.0)
    {
      _hopper.Hop();
    }
    else
    {
      _hopper.StopMotors();
    }

    if (_driveController.RButton())
    {      
      _intake.Collect();    
    }
    else if (_driveController.LButton())
    {
      _intake.ReverseCollector();
    }
    else 
    {
      _intake.StopMotor();
    }

    if (_operatorController.getY())
    {
      _feeder.Feed(-0.4);
    }
    else if (_operatorController.getA())
    {
      _feeder.Feed(0.4);
    }
    else if (_operatorController.getB())
    {
      _feeder.Feed(-0.2);
    }
    else
    {
      _feeder.StopMotor();
    }

    // 10 FOOT SHOT1Z
    if (_operatorController.getDriveRightTrigger() > 0)
    {
      if (!_operatorController.RButton()) _shoot.Shoot(.28); else if (_operatorController.RButton()) _shoot.Shoot(.32);
      //init line (10 foot)
      if (_shootingEncoder.getVelocity() > 1250 && _shootingEncoder.getVelocity() < 1500 && !_operatorController.RButton())
      {
        _shoot.StopMotors();
        _feeder.Feed(-.4);
      }
      //trench (18 foot)
      else if (_shootingEncoder.getVelocity() > 1645 && _operatorController.RButton())
      {
        _shoot.StopMotors();
        _feeder.Feed(-.4);
      }
      else if (!_operatorController.getA() || !_operatorController.getY())
      {
        _feeder.Feed(0.0);
      }
    }
    else
    {
      _shoot.Shoot(.2);
    }

    if (_driveController.getDriveLeftTrigger() == 1 && (Math.abs(_driveController.getDriveLeftY()) == 0) && (Math.abs(_driveController.getDriveRightX()) == 0))
    {
      _shoot.Shoot(.31);
      DriveAdjust driveAdjust = PID.CalculateDrive(-_limelight.GetTx(), _limelight.GetTy());
    _drivetrain.Drive(driveAdjust);
    }
  }
  

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
    if (_shootingEncoder.getVelocity() < _desiredVelocity)
    {
      _shoot.Shoot(.3);
    }
    else
    {
      _shoot.StopMotors();
    }
  }
}
