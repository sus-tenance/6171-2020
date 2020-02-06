package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.returntypes.Limelight;
import frc.robot.returntypes.Robotmap;
import frc.robot.subsystems.Manipulator;

public class Drivetrain {
    //Left side motors
		private CANSparkMax m_frontLeftMotor = new CANSparkMax(Robotmap.m_frontLeftMotorID, MotorType.kBrushless);
		private CANSparkMax m_rearLeftMotor = new CANSparkMax(Robotmap.m_rearLeftMotorID, MotorType.kBrushless);
    //Right side motors
		private CANSparkMax m_frontRightMotor = new CANSparkMax(Robotmap.m_frontRightMotorID, MotorType.kBrushless);
		private CANSparkMax m_rearRightMotor = new CANSparkMax(Robotmap.m_rearRightMotorID, MotorType.kBrushless);
    //Mecanum Drivetrain
		private MecanumDrive m_robotDrive = new MecanumDrive(m_frontRightMotor, m_rearRightMotor ,m_frontLeftMotor, m_rearLeftMotor);
	//Manipulator
		private Manipulator m_autonomousShooting = new Manipulator();
	//Limelight
		private Limelight m_limelight = new Limelight();
	//Constants
		private final static double m_POWER = 0.02;

	/*
	* Sets the motors of the mecanum drive based on passed joystick values.
	* @param getDriveRightX the value of the right joystick's horizontal axis.
	* @param getDriveRightY the value of the right joystick's vertical axis.
	* @param getDriveLeftX the value of the leftjoystick's horizontal axis.
	*/
    public void MecanumDrivetrain(double getDriveRightX, double getDriveRightY, double getDriveLeftX) {
        //Drives the robot with controller values passed from Robot.java
        //setBrake(true);
        m_robotDrive.setMaxOutput(.5);
        m_robotDrive.driveCartesian(getDriveRightX, getDriveRightY, getDriveLeftX);
    }

	/*
	* Points the robot towards the limelight target with an accuracy of +/- 4 degrees, and then shoots ball
	* Precondition: There should be a valid limelight target
	*/
    public void MecanumAutonomous() {
        double tx = m_limelight.getX();
        SmartDashboard.putNumber("Drivetrain tx", tx);
        if ((tx>4 || tx<-4)&& tx!=0) { //Horizontal offset from crosshair to target (+10 degrees and -10 degress)
            
            m_robotDrive.setMaxOutput(.3);
            m_frontRightMotor.set(-tx*m_POWER);
            m_rearRightMotor.set(tx*m_POWER);
            m_frontLeftMotor.set(tx*m_POWER);
            m_rearLeftMotor.set(-tx*m_POWER);
            
        }
        else m_autonomousShooting.AutonomousShoot();
        
    }
    
	/*
	* Resets the onboard memory of the CANSparkMax controllers
	*/
    public void ResetMotors() {
        //Restores each motor's orginal state to prevent jankified invertations
        m_frontLeftMotor.restoreFactoryDefaults();
        m_rearLeftMotor.restoreFactoryDefaults();
        m_frontRightMotor.restoreFactoryDefaults();
        m_rearRightMotor.restoreFactoryDefaults();
    }

	/*
	* Sets the brake mode of the 4 motors given a boolean-- true for brake, false for coast
	* @param trueorfalse whether or not the motors should be put in brake mode
	*/
    public void setBrake(boolean trueorfalse) {
        //Set the motor mode to either Brake or Coast
        if (trueorfalse == true) {
            m_frontLeftMotor.setIdleMode(IdleMode.kBrake);
            m_rearLeftMotor.setIdleMode(IdleMode.kBrake);
            m_frontRightMotor.setIdleMode(IdleMode.kBrake);
            m_rearRightMotor.setIdleMode(IdleMode.kBrake);
        }
        else {
            m_frontLeftMotor.setIdleMode(IdleMode.kCoast);
            m_rearLeftMotor.setIdleMode(IdleMode.kCoast);
            m_frontRightMotor.setIdleMode(IdleMode.kCoast);
            m_rearRightMotor.setIdleMode(IdleMode.kCoast);
        }
    }
}