package frc.robot.systems.subsystems;

import frc.robot.systems.drive.IMotor;

public class Feeder
{
    private IMotor _feederMotor;

    private double _power = 1;

    public Feeder(IMotor feederMotor)
    {
        _feederMotor = feederMotor;
    }

    public void Feed()
    {
        _feederMotor.SetPower(-_power);
    }

    public void Feed(double power)
    {
        _feederMotor.SetPower(power);
    }

    public void StopMotor()
    {
        _feederMotor.SetPower(0);
    }
}