package frc.robot.systems.subsystems;

import frc.robot.systems.drive.IMotor;

public class Hopper
{
    private IMotor _hopperLeft, _hopperRight;

    private double _power = .5;

    public Hopper(IMotor hopperLeft, IMotor hopperRight)
    {
        _hopperLeft = hopperLeft;
        _hopperRight = hopperRight;
    }

    public void Hop()
    {
        _hopperLeft.SetPower(_power);
        _hopperRight.SetPower(-_power);
    }

    public void ReverseHopper()
    {
        _hopperLeft.SetPower(-_power);
        _hopperRight.SetPower(_power);
    }

    public void StopMotors()
    {
        _hopperLeft.SetPower(0);
        _hopperRight.SetPower(0);
    }
}