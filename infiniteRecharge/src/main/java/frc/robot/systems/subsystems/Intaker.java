package frc.robot.systems.subsystems;

import frc.robot.systems.drive.IMotor;

public class Intaker
{
    private IMotor _intakeMotor;

    public Intaker(IMotor intakeMotor)
    {
        _intakeMotor = intakeMotor;
    }

    public void Intake()
    {
        _intakeMotor.SetPower(1);
    } 
}