package frc.robot.models.interfaces;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import frc.robot.models.enums.RestMode;

/**
 * @author Jacob Lewis
 * @author Josh ppshootaman
 */
public class Talon extends WPI_TalonSRX implements IMotor 
{

    /**
     * Constructs a new Talon.
     * 
     * @param deviceNumber The ID number of the motor controller.
     */
    public Talon(int deviceNumber)
    {
        super(deviceNumber);
        this.Reset();
    }

    /**
     * Sets the idle mode for the talon.
     * 
     * @param restMode What to do when the motor has no signal. (Brake or Coast)
     */
    @Override
    public void SetIdleMode(RestMode restMode)
    {
        if (restMode == RestMode.Brake)
            this.setNeutralMode(NeutralMode.Brake);
        if (restMode == RestMode.Coast)
            this.setNeutralMode(NeutralMode.Coast);
    }

    /**
     * Inverts the motor.
     * 
     * @param setInverted To invert (true) or not to invert (false), that is the question
     */
    @Override
    public void SetInverted(boolean setInverted)
    {
        this.setInverted(setInverted);
    }

    /**
     * sets power duh
     */
    @Override
    public void SetPower(double power)
    {
        this.set(ControlMode.PercentOutput, power);
    }

    /**
     * Resets the Talon to the factory defaults.
     */
    @Override
    public void Reset()
    {
        this.configFactoryDefault();
    }

    /**
     * Stops the motor.
     */
    @Override
    public void StopMotor()
    {
        this.stopMotor();
    }

    /**
     * 指定されたモーターコントローラーに従います。
     * @param _talon The motor controller to follow.
     */
    public void Follow(WPI_TalonSRX _talon)
    {
        this.follow(_talon);
    }

}