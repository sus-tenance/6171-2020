package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.OI;

public class Drive {
    private OI oi;
    private CANSparkMax m_frontRightMotor;
    private static final int m_frontRightMotorID = 0;
    private CANSparkMax m_rearRightMotor;
    private static final int m_rearRightMotorID = 1;
    private CANSparkMax m_frontLeftMotor;
    private static final int m_frontLeftMotorID = 2;
    private CANSparkMax m_rearLeftMotor;
    private static final int m_rearLeftMotorID = 3;

    private MecanumDrive m_robotDrive;

    private Limelight limelight;
    public final double POWER = 0.02;
	public final double DOWPOW = 0.12;

    public void MecanumINIT() {     
        oi = new OI();
        m_frontRightMotor = new CANSparkMax(m_frontRightMotorID, MotorType.kBrushless);
        m_rearRightMotor = new CANSparkMax(m_rearRightMotorID, MotorType.kBrushless);
        m_frontLeftMotor = new CANSparkMax(m_frontLeftMotorID, MotorType.kBrushless);
        m_rearLeftMotor = new CANSparkMax(m_rearLeftMotorID, MotorType.kBrushless);

        m_robotDrive = new MecanumDrive(m_frontRightMotor, m_rearRightMotor, m_frontLeftMotor, m_rearLeftMotor);
        m_robotDrive.setMaxOutput(.3);
    }

    public void MecanumMAIN() {
        m_robotDrive.driveCartesian(oi.getDriveRightY()*.5, oi.getDriveRightX()*.5, oi.getDriveLeftX());

        if (oi.getDriveRightTrigger()>1) {
            m_robotDrive.setMaxOutput(.3);
        }
        if (oi.getDriveLeftTrigger()>1) {
            m_robotDrive.setMaxOutput(.2);
        }
        }

        public void LLCenter() {
            double tx = limelight.getX();
            SmartDashboard.putNumber("Drivetrain tx", tx);
            if (tx>10 || tx<-10) { //Horizontal Offset From Crosshair To Target (+10 degrees and -10 degress)
                m_frontRightMotor.set(-tx*POWER);
                m_rearRightMotor.set(-tx*POWER);
                m_frontLeftMotor.set(tx*POWER);
                m_rearLeftMotor.set(tx*POWER);
            }
            else {
                SmartDashboard.putNumber("Drivetrain tx", tx);
                LLDrive();
            }
        }
    
        public void LLDrive() {
            double ta = limelight.getA();
            SmartDashboard.putNumber("Drivetrain ta", ta);
            if (ta< 2 && ta != 0) { 
                m_frontRightMotor.set(-DOWPOW/ta);
                m_rearRightMotor.set(-DOWPOW/ta);
                m_frontLeftMotor.set(-DOWPOW/ta);
                m_rearLeftMotor.set(-DOWPOW/ta);
            }
            else if (ta > 3.0) { 
                m_frontRightMotor.set(0.03*ta);
                m_rearRightMotor.set(0.03*ta);
                m_frontLeftMotor.set(0.03*ta);
                m_rearLeftMotor.set(0.03*ta);
            }
            else if (ta == 0) { 
                m_frontRightMotor.set(0);
                m_rearRightMotor.set(0);
                m_frontLeftMotor.set(0);
                m_rearLeftMotor.set(0);
            }
        }
    }