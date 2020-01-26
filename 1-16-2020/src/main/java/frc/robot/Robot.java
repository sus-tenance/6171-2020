/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.OI;
import frc.robot.subsystems.MecWithGyro;
import frc.robot.subsystems.Shooting;
import frc.robot.subsystems.Limelight;
import edu.wpi.first.wpilibj.Timer;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
  
  private OI m_oi;
  private MecWithGyro m_drive;
  private Shooting m_shooter;
  private Limelight m_limelight;
  private Timer m_timer;

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);

    m_oi = new OI();
    m_drive = new MecWithGyro();
    m_shooter = new Shooting();
    m_limelight = new Limelight();
    m_timer = new Timer();
    m_drive.MecanumINIT();
    m_shooter.ShootingINIT();
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
      case kDefaultAuto:
      default:
        // Put default auto code here
        m_autonomous();
        break;
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    m_drive.MecanumMAIN(m_oi.getDriveRightX(), m_oi.getDriveRightY(), m_oi.getDriveLeftX());
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }

  private void m_autonomous() { //  This method we made runs autonomous functions.
   /*
    * PSEUDOCODE:
    * 
    * get gyro angle at start for north ref
    * 
    * if tx is positive
    *  turn right until 0
    * else if tx is negative
    *  turn left until 0
    * 
    * shoot
    * 
    * while gyro angle is not equal to what the starting angle was
    *  turn in the opposite direction that you used to turn to shoot
    * 
    * back up a bit
    * 
    * done
    * 
    */
    double initialAngle = m_drive.autonGetGyro();
    String firstTurn = "";

    while(applyDeadband(m_limelight.getX(), 3) != 0){
      if (applyDeadband(m_limelight.getX(), 3) > 0) {
        m_drive.autonTurnLeft();
        firstTurn = "Left";
      } else if (applyDeadband(m_limelight.getX(), 3) < 0) {
        m_drive.autonTurnRight();
        firstTurn = "Right";
      }
    }

    m_timer.start();
    while(m_timer.get() < 5)
      m_shooter.autonShooting();
    m_timer.stop();

    while(applyDeadband(m_drive.autonGetGyro(), 10) != initialAngle) {
      switch(firstTurn){
        case "Left":
          m_drive.autonTurnRight();
          break;
        case "Right":
          m_drive.autonTurnLeft();
          break;
      }
    }

    m_timer.start();
    while(m_timer.get() < 5)
      m_drive.autonMoveBack();     
    m_timer.stop();

  }

  /** *** COPIED FROM WPILIB: https://github.com/wpilibsuite/allwpilib/blob/master/wpilibj/src/main/java/edu/wpi/first/wpilibj/drive/RobotDriveBase.java ***
	 * Returns 0.0 if the given value is within the specified range around zero. The remaining range
	 * between the deadband and 1.0 is scaled from 0.0 to 1.0.
	 *
	 * @param value    value to clip
	 * @param deadband range around zero
	 */
	protected double applyDeadband(final double value, final double deadband) {
		if (Math.abs(value) > deadband) {
			if (value > 0.0) {
				return (value - deadband) / (1.0 - deadband);
			} else {
				return (value + deadband) / (1.0 - deadband);
			}
			} else {
				return 0.0;
			}
	}
}
