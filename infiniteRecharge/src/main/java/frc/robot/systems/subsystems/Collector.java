package frc.robot.systems.subsystems;

import frc.robot.systems.drive.IMotor;

public class Collector
{
    private IMotor _CollecterMotor;

    private final double _power = .7;

    public Collector(IMotor CollecterMotor)
    {
        _CollecterMotor = CollecterMotor;

        _CollecterMotor.IsInverted(true);
    }

    public void Collect()
    {
        _CollecterMotor.SetPower(_power);
    } 

    public void ReverseCollector()
    {
        _CollecterMotor.SetPower(-_power*.5);
    }

    public void StopMotor()
    {
        _CollecterMotor.SetPower(0);
    }
}