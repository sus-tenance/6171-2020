package frc.robot.systems.subsystems;

import edu.wpi.first.wpilibj.SpeedController;

public class Intaker
{
    private SpeedController _intakeMotor;

    public Intaker(SpeedController intakeMotor)
    {
        _intakeMotor = intakeMotor;
    }

    public void Intake()
    {
        _intakeMotor.set(1);
    } 
}