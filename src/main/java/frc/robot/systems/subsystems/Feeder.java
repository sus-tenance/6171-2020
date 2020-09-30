package frc.robot.systems.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import frc.robot.systems.drive.IMotor;
import frc.robot.systems.drive.Talon;

import frc.robot.mapping.Robotmap;

// public class Feeder
// {
//     private IMotor _feederMotor;

//     private double _power = .25;

//     public Feeder(IMotor feederMotor)
//     {
//         _feederMotor = feederMotor;
//     }

//     public void Feed()
//     {
//         _feederMotor.SetPower(-_power);
//     }

//     public void Feed(double power)
//     {
//         _feederMotor.SetPower(power);
//     }

//     public void StopMotor()
//     {
//         _feederMotor.SetPower(0);
//     }
// }

// public class Feeder extends TalonSRX
// {    
//     public Feeder(int deviceNumber) {
//         super(deviceNumber);
//     }

//     private static final double _power = .25;

//     public void Feed()
//     {
//         this.set(ControlMode.PercentOutput, -_power);
//     }

//     public void Feed(double power)
//     {
//         this.set(ControlMode.PercentOutput, power);
//     }

//     public void StopMotor()
//     {
//         this.set(ControlMode.PercentOutput, 0);
//     }
// }

public class Feeder extends Talon
{    
    public Feeder(int deviceNumber) {
        super(deviceNumber);
    }

    private static final double _power = .25;

    public void Feed()
    {
        this.SetPower(-_power);
    }

    public void Feed(double power)
    {
        this.SetPower(power);
    }

    public void StopFeed()
    {
        this.SetPower(0);
    }
}