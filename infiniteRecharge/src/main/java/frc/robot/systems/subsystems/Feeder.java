package frc.robot.systems.subsystems;

import frc.robot.systems.drive.IMotor;

public class Feeder
{
    private IMotor _feederMotor;

    private double _power = 0.5;

    public Feeder(IMotor feederMotor)
    {
        _feederMotor = feederMotor;
    }

    public void Feed()
    {
        _feederMotor.SetPower(_power);
    }
}