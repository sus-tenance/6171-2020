package frc.robot.systems.subsystems;

import frc.robot.systems.drive.IMotor;

public class Shooter
{
    private IMotor _shootLeftMotor, _shootRightMotor;

    private double _power = 0.31;

    public Shooter(IMotor shootLeftMotor, IMotor shootRightMotor)
    {
        _shootLeftMotor = shootLeftMotor;
        _shootRightMotor = shootRightMotor;

        _shootRightMotor.IsInverted(true);
    }

    public void Shoot()
    {
        _shootLeftMotor.SetPower(_power);
        _shootRightMotor.SetPower(_power);
    }

    public void Shoot(double speed)
    {
        _shootLeftMotor.SetPower(speed);
        _shootRightMotor.SetPower(speed);
    }

    public void StopMotors()
    {
        _shootLeftMotor.SetPower(0);
        _shootRightMotor.SetPower(0);
    }

    public void ResetShooter()
    {
        _shootLeftMotor.Reset();
        _shootRightMotor.Reset();
    }
}