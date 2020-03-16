package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.input.OI;
import frc.robot.mapping.Robotmap;
import frc.robot.models.DriveAdjust;
import frc.robot.models.enums.RestMode;
import frc.robot.models.interfaces.SparkMax;

/**
 * @author Jacob Lewis
 * @author Josh ppshootaman
 */
public class ArcadeDrivetrain 
{
    private SparkMax _frontLeftMotor = new SparkMax(Robotmap._frontLeftMotorID, RestMode.Coast);
    private SparkMax _rearLeftMotor = new SparkMax(Robotmap._rearLeftMotorID, RestMode.Coast);
    private SparkMax _frontRightMotor = new SparkMax(Robotmap._frontRightMotorID, RestMode.Coast);
    private SparkMax _rearRightMotor = new SparkMax(Robotmap._rearRightMotorID, RestMode.Coast);

    private SpeedControllerGroup _leftGroup = new SpeedControllerGroup(_frontLeftMotor, _rearLeftMotor);
    private SpeedControllerGroup _rightGroup = new SpeedControllerGroup(_frontRightMotor, _rearRightMotor);

    private DifferentialDrive _drivetrain;

    private static final double kDrivetrainMaxOutput = 0.8;
    private static final double kPrecisionTriggerSensitivity = 0.5;

    /**
     * ブルームブルーム
     */
    public ArcadeDrivetrain() {
        _drivetrain = new DifferentialDrive(_leftGroup, _rightGroup);
        _drivetrain.setMaxOutput(kDrivetrainMaxOutput);
    }

    public void Drive(OI oi)
    {
        double precision = oi.getDriveRightTrigger() * kPrecisionTriggerSensitivity;
        if (precision > 0)
            this.Drive(oi.getDriveLeftY() * precision, oi.getDriveRightX() * precision);
        else
            this.Drive(oi.getDriveLeftY(), oi.getDriveRightX());
    }

    public void Drive(DriveAdjust driveAdjust)
    {
        this.Drive(0, driveAdjust.getAimAdjust());
    }

    public void Drive(double driveSpeed, double turnSpeed)
    {
        _drivetrain.arcadeDrive(driveSpeed, turnSpeed);
    }

}