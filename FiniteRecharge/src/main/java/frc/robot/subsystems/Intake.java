package frc.robot.subsystems;

import frc.robot.models.interfaces.Talon;
import frc.robot.mapping.Robotmap;

/**
 * @author Jacob Lewis
 * @author Josh ppshootaman
 */
public class Intake extends Talon
{
    private static final double POWER = .7;

    /**
     * 摂取量を構築する
     */
    public Intake()
    {
        super(Robotmap._intakeMotorID);
        this.SetInverted(true);
    }

    public void Start()
    {
        this.SetPower(POWER);
    } 

    public void Reverse()
    {
        this.SetPower(-POWER * .5);
    }

    public void StopMotor()
    {
        this.StopMotor();
    }
}