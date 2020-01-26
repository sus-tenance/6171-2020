package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import frc.robot.OI;

public class LiftyWinchThingy {

    private OI oi;
    private TalonSRX _liftyMotor;
    private static final int _liftyMotorID = 0;

    public void LiftyWinchThingyINIT() {
        _liftyMotor = new TalonSRX(_liftyMotorID);
    }

    public void LiftyWinchThingyMAIN() {
        if (oi.getY()) {
            _liftyMotor.set(ControlMode.PercentOutput, 1);
        } else {_liftyMotor.set(ControlMode.PercentOutput, 0);}
    }
}