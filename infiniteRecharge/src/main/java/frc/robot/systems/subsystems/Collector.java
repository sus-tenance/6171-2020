package frc.robot.systems.subsystems;

import frc.robot.systems.drive.IMotor;

public class Collector
{
    private IMotor _CollectMotor;

    private final double _power = 1;

    public Collector(IMotor CollectMotor)
    {
        _CollectMotor = CollectMotor;
    }

    public void Collect()
    {
        _CollectMotor.SetPower(_power);
    } 

    public void ReverseCollector()
    {
        _CollectMotor.SetPower(-_power);
    }
}