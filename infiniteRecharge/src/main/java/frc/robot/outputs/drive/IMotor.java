package frc.robot.outputs.drive;

import edu.wpi.first.wpilibj.SpeedController;
import frc.robot.models.enums.*;

public interface IMotor
{
    public void SetIdleMode(RestMode restMode);

    public void IsInverted(boolean isInverted);

    public void Reset();

    public void SetPower(double power);

    public SpeedController GetSpeedController();
}