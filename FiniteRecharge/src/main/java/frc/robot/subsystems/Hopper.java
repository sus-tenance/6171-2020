package frc.robot.subsystems;

import frc.robot.mapping.Robotmap;
import frc.robot.models.interfaces.Talon;

/**
 * @author Jacob Lewis
 * @author Josh ppshootaman
 */
public class Hopper
{
    private Talon _hopperLeftMotor, _hopperRightMotor;
    private final double POWER = .5;

    /**
     * ホッパーを構築します。
     */
    public Hopper()
    {
        this._hopperLeftMotor = new Talon(Robotmap._hopperLeftMotorID);
        this._hopperRightMotor = new Talon(Robotmap._hopperRightMotorID);

        _hopperRightMotor.SetInverted(true);
        _hopperRightMotor.Follow(_hopperLeftMotor);
    }

    /**
     *  H O P S.
     */
    public void Start()
    {
        _hopperLeftMotor.SetPower(POWER);
    }

    /**
     *  H O P S, but with newly added P O W E R.
     * @param power power, but like, you do it.
     */
    public void Start(double power)
    {
        _hopperLeftMotor.SetPower(power);
    }

    /**
     * R E V E R S E.
     */
    public void Reverse()
    {
        _hopperLeftMotor.SetPower(-POWER);
    }

    /**
     * R E V E R S E, but also with newly added P O W E R.
     * @param power power, but like, you do it.
     */
    public void Reverse(double power)
    {
        _hopperLeftMotor.SetPower(-power);
    }

    /**
     * did you even read the method name lol.
     */
    public void Stop()
    {
        _hopperLeftMotor.SetPower(0.0);
    }
    
}