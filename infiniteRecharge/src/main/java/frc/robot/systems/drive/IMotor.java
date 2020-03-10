package frc.robot.systems.drive;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;

import frc.robot.models.enums.*;

public interface IMotor
{
    public void SetIdleMode(RestMode restMode);

    public void IsInverted(boolean isInverted);

    public void Reset();

    public void SetPower(double power);

    public CANSparkMax GetSparkMax();

    public TalonSRX GetTalon();
}