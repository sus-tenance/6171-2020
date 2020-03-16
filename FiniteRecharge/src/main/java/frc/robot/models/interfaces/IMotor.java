package frc.robot.models.interfaces;

import frc.robot.models.enums.RestMode;

/**
 * Goddamn be careful when spelling with uppercase and lowercase letters
 * @author Joshu PPShooterMan
 * @author Jacob Lewice Stikcye
 */
public interface IMotor
{
    public void SetIdleMode(RestMode restMode);

    public void SetInverted(boolean setInverted);

    public void SetPower(double power);

    public void StopMotor();

    public void Reset();
}