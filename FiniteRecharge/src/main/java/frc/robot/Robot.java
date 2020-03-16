/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.input.vision.Limelight;
import frc.robot.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "Custom Auton";
  private static final String kAutonForward = "Auton Forwards";
  private static final String kAutonBackward = "Auton Backwards";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  private ArcadeDrivetrain _drivetrain;
  private AutoIndexing _indexer;
  private Feeder _feeder;
  private Hopper _hopper;
  private Intake _intake;
  private Shooter _shooter;
  private Limelight _limelight;

  private Autonomous _auton;
  private Teleoperated _teleop;
  
  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("Custom Auton", kCustomAuto);
    m_chooser.addOption("Auton Forwards", kAutonForward);
    m_chooser.addOption("Auton Backwards", kAutonBackward);
    SmartDashboard.putData("Auto choices", m_chooser);

    _drivetrain = new ArcadeDrivetrain();
    _indexer = new AutoIndexing(_feeder);
    _feeder = new Feeder();
    _hopper = new Hopper();
    _intake = new Intake();
    _shooter = new Shooter();
    _limelight = new Limelight();

    _auton = new Autonomous(_drivetrain, _feeder, _shooter);
    _teleop = new Teleoperated(_drivetrain, _indexer, _feeder, _hopper, _intake, _shooter, _limelight);
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
      case kAutonForward:
      _auton.RunAutonomousForwards();
        break;
      case kAutonBackward:
      _auton.RunAutonomousBackwards();
        break;
      case kDefaultAuto:
      default:
      _auton.RunAutonomousBackwards();
        break;
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    _teleop.RunTeleop();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
