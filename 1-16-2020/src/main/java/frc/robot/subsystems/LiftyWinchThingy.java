package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import frc.robot.RobotMap;

public class LiftyWinchThingy {
    private TalonSRX _liftyMotor;

    public void LiftyWinchThingyINIT() {
        _liftyMotor = new TalonSRX(RobotMap.liftyMotorID);
    }

    public void LiftyWinchThingyMAIN(boolean getYButton) {    //  pass m_oi.getYButton to this
        if (getYButton) _liftyMotor.set(ControlMode.PercentOutput, 1);
        else _liftyMotor.set(ControlMode.PercentOutput, 0);
    }
}