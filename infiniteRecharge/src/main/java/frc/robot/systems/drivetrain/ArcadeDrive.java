package frc.robot.systems.drivetrain;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.models.*;
import frc.robot.outputs.motion.OI;

public class ArcadeDrive
{
    private DifferentialDrive _robotDrive;
    public ArcadeDrive(SpeedControllerGroup leftGroup, SpeedControllerGroup rightGroup)
    {
        _robotDrive = new DifferentialDrive(leftGroup, rightGroup);
        _robotDrive.setMaxOutput(.80);
    }

    /*
    private DriverType _driverType;

    public DriverType GetDriverType()
    {
        return _driverType;
    }
    public void SetDriverType(DriverType driverType)
    {
        _driverType = DriverType.Human;

        if (_driverType == DriverType.Human) StartHumanDriver();
            else StartLimelightDriver();
    }
    */

    /*
    private void StartHumanDriver()
    {
        this._robotDrive.setMaxOutput(0.4);
        this._robotDrive.setDeadband(0.5);
    }

    private void StartLimelightDriver()
    {
        this._robotDrive.setMaxOutput(0.3);
    }
    */

    
    /**
     * drives the robot with the controller 
     * @param oi is the controller :^)
     */
    public void Drive(OI oi)
    {
        
        double precision = oi.getDriveRightTrigger() * .5;
        if (precision > 0) 
        {
            _robotDrive.arcadeDrive(oi.getDriveLeftY() * precision, oi.getDriveRightX() * precision);
        }
        else 
        {
            _robotDrive.arcadeDrive(oi.getDriveLeftY(), -oi.getDriveRightX() * 1.5);
        }
    }

    /**
     * used to aim/drive to a target
     * @param driveAdjust the driveadjust
     */
    public void Drive(DriveAdjust driveAdjust)
    {
        _robotDrive.arcadeDrive(0, driveAdjust.getAimAdjust());
    }

    /**
     * makes custom drive speed settings for arcade drive
     * @param driveSpeed the forward/backward speed
     * @param turnSpeed the left/right speed
     */
    public void Drive(double driveSpeed, double turnSpeed)
    {
        _robotDrive.arcadeDrive(driveSpeed, turnSpeed);
    }

    /*
    public void Drive(Limelight limelight)
    {
        double kPAim = -0.035;
        double kPDistance = -0.15;
        double minAimThreshold = 0.06;
        double minDistanchThreshold = 0.1;

        boolean tv = limelight.GetTv();
        double tx = limelight.GetTx();
        double ty = limelight.GetTy();

        double headingError = -tx;
        double distanceError = -ty;

        double aimAdjust = 0.0;
        double distanceAdjust = 0.0;

        if (!tv)
        {
            aimAdjust = 0.3f;
        }
        else
        {
            SmartDashboard.putNumber("aimAdjust", aimAdjust);
            if (tx > 1.0)
            {
                aimAdjust = kPAim * headingError + minAimThreshold;
            }
            else if (tx < 1.0)
            {
                aimAdjust = kPAim * headingError - minAimThreshold;
            }

            SmartDashboard.putNumber("distanceAdjust", distanceAdjust);
            if (ty > 0.2)
            {
                distanceAdjust = -1 * kPDistance * distanceError + minDistanchThreshold;
            }
            else if (ty < 0.2)
            {
                distanceAdjust = -1 * kPDistance * distanceError - minDistanchThreshold;
            }

            _robotDrive.arcadeDrive(distanceAdjust, aimAdjust);
        }
    }
    */
}   