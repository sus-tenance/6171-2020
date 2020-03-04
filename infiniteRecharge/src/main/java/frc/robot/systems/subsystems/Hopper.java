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

    public void usehopper()
    {
        _hopperLeft.SetPower(_power);
        _hopperRight.SetPower(-_power);
    }
}