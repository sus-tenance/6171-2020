package frc.robot.systems.drive;

import frc.robot.models.enums.*;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class SparkMax implements IMotor
{
    private CANSparkMax _sparkMax;

    /** Requires CanId, Default values are Brushless, Coast, Not Inverted
     * The motor config will be reset to default.  */
    public SparkMax(int canId)
    {    
        this(canId, MotorCommutation.Brushless);        
    }

    public SparkMax(int canId, MotorCommutation motorCommutation)
    {    
        this(canId, motorCommutation, RestMode.Coast);
    }

    public SparkMax(int canId, MotorCommutation motorCommutation, RestMode restMode)
    {     
        this(canId, motorCommutation, restMode, false);      
    }

    public SparkMax(int canId, MotorCommutation motorCommutation, RestMode restMode, boolean isInverted)
    {  
        _sparkMax = new CANSparkMax(canId, MotorType.kBrushless);
        _sparkMax.restoreFactoryDefaults();

        if(motorCommutation == MotorCommutation.Brushed)
            _sparkMax = new CANSparkMax(canId, MotorType.kBrushed);
        if(motorCommutation == MotorCommutation.Brushless)
            _sparkMax = new CANSparkMax(canId, MotorType.kBrushless);
        
        if(restMode == RestMode.Coast)
            _sparkMax.setIdleMode(IdleMode.kCoast);
        if(restMode == RestMode.Brake)  
            _sparkMax.setIdleMode(IdleMode.kBrake);

        _sparkMax.setInverted(isInverted);
    }

    public void SetIdleMode(RestMode restMode)
    {
        if(restMode == RestMode.Coast)
            _sparkMax.setIdleMode(IdleMode.kCoast);
        if(restMode == RestMode.Brake)  
            _sparkMax.setIdleMode(IdleMode.kBrake);
    }

    public void IsInverted(boolean isInverted)
    {
        _sparkMax.setInverted(isInverted);
    }

    public void Reset()
    {
        _sparkMax.restoreFactoryDefaults();
    }

    public void SetPower(double power)
    {
        _sparkMax.set(power);
    }

    public CANSparkMax GetSparkMax()
    {
        return _sparkMax;
    }

    public TalonSRX GetTalon()
    {
        return null;
    }
}