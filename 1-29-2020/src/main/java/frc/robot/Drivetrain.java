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
    private MecanumDrive m_robotDrive = new MecanumDrive(m_frontLeftMotor, m_rearLeftMotor, m_frontRightMotor, m_rearRightMotor);

    private Manipulator m_autonomousShooting = new Manipulator();

    private Limelight m_limelight = new Limelight();
    private static double m_POWER = 0.02;

    public void MecanumDrivetrain(double getDriveRightY, double getDriveRightX, double getDriveLeftX) {
        //Drives the robot with controller values passed from Robot.java
        setBrake(true);
        m_robotDrive.driveCartesian(getDriveRightX, getDriveLeftX, getDriveRightX);
    }

    public void MecanumAutonomous() {
        double tx = m_limelight.getX();
        SmartDashboard.putNumber("Drivetrain tx", tx);
        if (tx>4 || tx<-4) { //Horizontal offset from crosshair to target (+10 degrees and -10 degress)
            m_frontRightMotor.set(-tx*m_POWER);
            m_rearRightMotor.set(-tx*m_POWER);
            m_frontLeftMotor.set(tx*m_POWER);
            m_rearLeftMotor.set(tx*m_POWER);
        }
        else m_autonomousShooting.AutonomousShoot();
        
    }

    public void ResetMotors() {
        //Restores each motor's orginal state to prevent jankified invertations
        m_frontLeftMotor.restoreFactoryDefaults();
        m_rearLeftMotor.restoreFactoryDefaults();
        m_frontRightMotor.restoreFactoryDefaults();
        m_rearRightMotor.restoreFactoryDefaults();
    }

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