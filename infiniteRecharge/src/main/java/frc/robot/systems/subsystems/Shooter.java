package frc.robot.systems.subsystems;

import frc.robot.systems.drive.IMotor;

public class Shooter
{
    private IMotor _shootLeftMotor, _shootRighMotor;

    private double _power = 0.75;

    public Shooter(IMotor shootLeftMotor, IMotor shootRightMotor)
    {
        _shootLeftMotor = shootLeftMotor;
        _shootRighMotor = shootRightMotor;

        _shootRighMotor.IsInverted(true);
    }

    public void Shoot()
    {
        _shootLeftMotor.SetPower(_power);
        _shootRighMotor.SetPower(_power);
    }
}