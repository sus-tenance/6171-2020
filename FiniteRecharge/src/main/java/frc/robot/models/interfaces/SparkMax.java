package frc.robot.models.interfaces;

import com.revrobotics.CANSparkMax;

import frc.robot.models.enums.RestMode;

/**
 * @author Jacob Lewis
 * @author Josh ppshootaman
 */
public class SparkMax extends CANSparkMax implements IMotor
{

    /**
     * Constructs a new SparkMax with a default RestMode Coast.
     * 
     * @param deviceID The device ID of the SparkMax controller.
     */
    public SparkMax(int deviceID) 
    {
        super(deviceID, MotorType.kBrushless);
    }

    /**
     * Constructs a new SparkMax with a default RestMode Coast.
     * 
     * @param deviceID The device ID of the SparkMax controller.
     * @param type The motor type. (kBrushed or kBrushless)
     */
    public SparkMax(int deviceID, MotorType type) 
    {
        super(deviceID, type);
        this.setIdleMode(IdleMode.kCoast);
    }

    /**
     * Constructs a new SparkMax.
     * 
     * @param deviceID The device ID of the SparkMax controller.
     * @param restMode What to do when the motor has no signal. (Brake or Coast)
     */
    public SparkMax(int deviceID, RestMode restMode) 
    {
        super(deviceID, MotorType.kBrushless);
        if (restMode == RestMode.Brake) 
            this.setIdleMode(IdleMode.kBrake);
        if (restMode == RestMode.Coast)
            this.setIdleMode(IdleMode.kCoast);
        this.Reset();
    }

    /**
     * Constructs a new SparkMax.
     * 
     * @param deviceID The device ID of the SparkMax controller.
     * @param type The motor type. (kBrushed or kBrushless)
     * @param restMode What to do when the motor has no signal. (Brake or Coast)
     */
    public SparkMax(int deviceID, MotorType type, RestMode restMode) 
    {
        super(deviceID, type);
        if (restMode == RestMode.Brake) 
            this.setIdleMode(IdleMode.kBrake);
        if (restMode == RestMode.Coast)
            this.setIdleMode(IdleMode.kCoast);
        this.Reset();
    }

    /**
     * Sets the IdleMode for the SparkMax.
     * 
     * @param restMode What to do when the motor has no signal. (Brake or Coast)
     */
    @Override
    public void SetIdleMode(RestMode restMode)
    {
        if (restMode == RestMode.Brake) 
            this.setIdleMode(IdleMode.kBrake);
        if (restMode == RestMode.Coast)
            this.setIdleMode(IdleMode.kCoast);
    }

    /**
     * sets power duh
     */
    @Override
    public void SetPower(double power) 
    {
        this.set(power);
    }

    /**
     * Resets the SparkMax to the factory defaults.
     */
    @Override
    public void Reset() 
    {
        this.restoreFactoryDefaults();
    }

    /**
     * Inverts the motor.
     * 
     * @param setInverted To invert (true) or not to invert (false), that is the question
     */
    @Override
    public void SetInverted(boolean setInverted) {
        this.setInverted(setInverted);
    }

    /**
     * Stops the motor.
     */
    @Override
    public void StopMotor() {
        this.stopMotor();
    }

    /**
     * 指定されたモーターコントローラーに従います。
     * @param _spark The motor controller to follow.
     */
    public void Follow(CANSparkMax _spark)
    {
        this.follow(_spark);
    }

}