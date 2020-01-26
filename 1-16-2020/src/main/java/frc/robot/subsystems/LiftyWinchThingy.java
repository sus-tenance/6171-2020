package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class LiftyWinchThingy {
    private TalonSRX _liftyMotor;
    private static final int _liftyMotorID = 0;

    public void LiftyWinchThingyINIT() {
        _liftyMotor = new TalonSRX(_liftyMotorID);
    }

    public void LiftyWinchThingyMAIN(boolean getY) {    //  pass m_oi.getY to this
        if (getY) _liftyMotor.set(ControlMode.PercentOutput, 1);
        else _liftyMotor.set(ControlMode.PercentOutput, 0);
    }
}