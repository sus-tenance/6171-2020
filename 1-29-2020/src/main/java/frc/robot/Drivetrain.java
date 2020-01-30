package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.drive.MecanumDrive;

import frc.robot.returntypes.Robotmap;

public class Drivetrain {

    //Left side motors
    private CANSparkMax m_frontLeftMotor = new CANSparkMax(Robotmap.m_frontLeftMotorID, MotorType.kBrushless);
    private CANSparkMax m_rearLeftMotor = new CANSparkMax(Robotmap.m_rearLeftMotorID, MotorType.kBrushless);
    //Right side motors
    private CANSparkMax m_frontRightMotor = new CANSparkMax(Robotmap.m_frontRightMotorID, MotorType.kBrushless);
    private CANSparkMax m_rearRightMotor = new CANSparkMax(Robotmap.m_rearRightMotorID, MotorType.kBrushless);

    //Mecanum Drivetrain
    private MecanumDrive m_robotDrive = new MecanumDrive(m_frontLeftMotor, m_rearLeftMotor, m_frontRightMotor, m_rearRightMotor);

    public void MecanumDrivetrain(double getDriveRightY, double getDriveRightX, double getDriveLeftX) {
        //Drives the robot with controller values passed from Robot.java
        m_robotDrive.driveCartesian(getDriveRightX, getDriveLeftX, getDriveRightX);
    }

    public void ResetMotors() {
        //Restores each motor's orginal state to prevent jankified invertations
        m_frontLeftMotor.restoreFactoryDefaults();
        m_rearLeftMotor.restoreFactoryDefaults();
        m_frontRightMotor.restoreFactoryDefaults();
        m_rearRightMotor.restoreFactoryDefaults();
    }
}