package frc.robot.outputs.drivetrain;

import frc.robot.inputs.motion.OI;
import frc.robot.models.enums.DriverType;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import frc.robot.outputs.drive.*;

public class MecanumDrivetrain {
    private MecanumDrive _robotDrive;

    public MecanumDrivetrain(IMotor frontLeft, IMotor rearLeft, IMotor frontRight, IMotor rearRight) {
        _robotDrive = new MecanumDrive(frontLeft.GetSpeedController(), rearLeft.GetSpeedController(),
                frontRight.GetSpeedController(), rearRight.GetSpeedController());
    }

    private DriverType _driverType;

    public DriverType GetDriverType() {
        return _driverType;
    }

    public void SetDriverType(DriverType driverType) {
        _driverType = driverType;

        if (_driverType == DriverType.Human)
            StartHumanDriver();
        else
            StartLimelightDriver();
    }

    private void StartHumanDriver() {
        this._robotDrive.setMaxOutput(1.0);
        this._robotDrive.setDeadband(.05);
    }

    private void StartLimelightDriver() {
        _robotDrive.setMaxOutput(.3);
        _robotDrive.setDeadband(.05);
    }

    public void Drive(OI oi) {
        _robotDrive.driveCartesian(-oi.getDriveLeftX(), oi.getDriveLeftY(), -oi.getDriveRightX());

    }
}