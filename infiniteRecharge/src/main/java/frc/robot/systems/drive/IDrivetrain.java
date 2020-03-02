package frc.robot.systems.drive;

import frc.robot.models.enums.*;
import frc.robot.outputs.motion.OI;
import frc.robot.outputs.vision.Limelight;

public interface IDrivetrain
{
    public void SetDriverType(DriverType driverType);

    public DriverType GetDriverType();

    public void Drive(OI oi);

    public void Drive(Limelight limelight);
}