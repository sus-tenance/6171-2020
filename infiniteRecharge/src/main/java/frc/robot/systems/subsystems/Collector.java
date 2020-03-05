package frc.robot.systems.subsystems;

import frc.robot.systems.drive.IMotor;

public class Collector
{
    private IMotor _CollectMotor;

    public Collector(IMotor CollectMotor)
    {
        _CollectMotor = CollectMotor;
    }

    public void Collect()
    {
        _CollectMotor.SetPower(1);
    } 

    public void ReverseCollector()
    {

    }
}