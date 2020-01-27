package frc.robot.subsystems;

import frc.robot.RobotMap;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;

import edu.wpi.first.wpilibj.drive.MecanumDrive;

public class MecWithGyro {
    private CANSparkMax m_frontRightMotor;
    private CANSparkMax m_rearRightMotor;
    private CANSparkMax m_frontLeftMotor;
    private CANSparkMax m_rearLeftMotor;

    private ADXRS450_Gyro m_gyro;

    private MecanumDrive m_robotDrive;

    //  private double _strafe, _drive, _turn;  //  Uncomment these to use manual mecanum math

    //  private double _lf, _lb, _rf, _rb;

    private final double _POWERMAX = 0.1;

    public void MecanumINIT() {
        m_frontRightMotor = new CANSparkMax(RobotMap.frontRightMotorID, MotorType.kBrushless);
        m_rearRightMotor = new CANSparkMax(RobotMap.rearRightMotorID, MotorType.kBrushless);
        m_frontLeftMotor = new CANSparkMax(RobotMap.frontLeftMotorID, MotorType.kBrushless);
        m_rearLeftMotor = new CANSparkMax(RobotMap.rearLeftMotorID, MotorType.kBrushless);

        m_frontRightMotor.setInverted(true);
        m_rearRightMotor.setInverted(true);

        m_robotDrive = new MecanumDrive(m_frontRightMotor, m_rearRightMotor, m_frontLeftMotor, m_rearLeftMotor);
        m_robotDrive.setMaxOutput(_POWERMAX);

        m_gyro = new ADXRS450_Gyro();
        m_gyro.calibrate();
    }

    public void MecanumMAIN(double getDriveRightY, double getDriveRightX, double getDriveLeftX) {
        m_robotDrive.driveCartesian(getDriveRightY, getDriveRightX, getDriveLeftX, m_gyro.getAngle());

        /*  This is manual mecanum math if you'd like...
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
        */

        }

        public void autonMoveCartesian (double ySpeed, double xSpeed, double zRotation) {
            m_robotDrive.driveCartesian(ySpeed, xSpeed, zRotation, 0.0);
        }

        public double autonGetGyro () {
            return m_gyro.getAngle();
        }
    }
