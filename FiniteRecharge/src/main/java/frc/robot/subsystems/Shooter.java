package frc.robot.subsystems;

import com.revrobotics.CANEncoder;

import frc.robot.mapping.Robotmap;
import frc.robot.models.enums.RestMode;
import frc.robot.models.interfaces.SparkMax;

public class Shooter
{
    private SparkMax _shooterLeftMotor, _shooterRightMotor;
    private CANEncoder _shootingEncoder;
    private static final double POWER = 0.31;
    private static final double IDLEPOWER = 0.2;

    /**
     * シューターを構築します
     */
    public Shooter()
    {
        this._shooterLeftMotor = new SparkMax(Robotmap._shooterLeftMotorID, RestMode.Coast);
        this._shooterRightMotor = new SparkMax(Robotmap._shooterRightMotorID, RestMode.Coast);

        _shooterRightMotor.SetInverted(true);
        _shooterRightMotor.Follow(_shooterLeftMotor);

        _shootingEncoder = new CANEncoder(_shooterLeftMotor);
    }

    public void Shoot()
    {
        _shooterLeftMotor.SetPower(POWER);
    }

    public void Idle()
    {
        _shooterLeftMotor.SetPower(IDLEPOWER);
    }

    public void Shoot(double power)
    {
        _shooterLeftMotor.SetPower(power);
    }

    /**
     * 
     * @return the rpm of the shooter.
     */
    public double returnVelocity()
    {
        return _shootingEncoder.getVelocity();
    }

    public void Stop()
    {
        _shooterLeftMotor.StopMotor();
    }
    
}