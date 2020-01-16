package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpiutil.math.MathUtil;
import frc.robot.OI;

public class mMecanumDrive {
    private OI oi;
    private CANSparkMax m_frontRightMotor;
    private static final int m_frontRightMotorID = 0;
    private CANSparkMax m_rearRightMotor;
    private static final int m_rearRightMotorID = 1;
    private CANSparkMax m_frontLeftMotor;
    private static final int m_frontLeftMotorID = 2;
    private CANSparkMax m_rearLeftMotor;
    private static final int m_rearLeftMotorID = 3;

    //private MecanumDrive m_robotDrive;

    private double _strafe, _drive, _turn;

    private double _lf, _lb, _rf, _rb;

    private final double _POWERMAX = 0.3;

    public void MecanumINIT() {     
        oi = new OI();
        m_frontRightMotor = new CANSparkMax(m_frontRightMotorID, MotorType.kBrushless);
        m_rearRightMotor = new CANSparkMax(m_rearRightMotorID, MotorType.kBrushless);
        m_frontLeftMotor = new CANSparkMax(m_frontLeftMotorID, MotorType.kBrushless);
        m_rearLeftMotor = new CANSparkMax(m_rearLeftMotorID, MotorType.kBrushless);

        //m_robotDrive = new MecanumDrive(m_frontRightMotor, m_rearRightMotor, m_frontLeftMotor, m_rearLeftMotor);
    }
    
    public void MecanumMAIN() {
        //  m_robotDrive.driveCartesian(oi.getDriveRightY(), oi.getDriveRightX(), oi.getDriveLeftX());
        _strafe = oi.getDriveRightX();
        _drive = oi.getDriveRightY();
        _turn = oi.getDriveLeftX();

        _lf = MathUtil.clamp(_drive + _turn + _strafe, -1.0, 1.0);
        _lb = MathUtil.clamp(_drive + _turn - _strafe, -1.0, 1.0);
        _rf = MathUtil.clamp(_drive - _turn - _strafe, -1.0, 1.0);
        _rb = MathUtil.clamp(_drive - _turn + _strafe, -1.0, 1.0);

        m_frontRightMotor.set(_rf*_POWERMAX);
        m_rearRightMotor.set(_rb*_POWERMAX);
        m_frontLeftMotor.set(_lf*_POWERMAX);
        m_rearLeftMotor.set(_lb*_POWERMAX);
        }
    }