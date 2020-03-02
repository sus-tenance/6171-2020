package frc.robot.outputs.drive;

import frc.robot.models.enums.*;
import frc.robot.inputs.motion.OI;
import frc.robot.inputs.vision.Limelight;

public interface IDrivetrain
{
    public void SetDriverType(DriverType driverType);

    public DriverType GetDriverType();

    public void Drive(OI oi);

    public void Drive(Limelight limelight);
}