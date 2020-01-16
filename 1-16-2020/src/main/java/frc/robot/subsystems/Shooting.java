package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import frc.robot.OI;

public class Shooting {

    private OI oi;
    private TalonSRX _shootymotor;
    private static final int _shootymotorID = 0;

    private Limelight _limelight;

    public void ShootingINIT() {
        _shootymotor = new TalonSRX(_shootymotorID);
    }

    public void ShootingMAIN() {
        while (oi.getX()) {
            if (_limelight.getY()>10) {
                _shootymotor.set(ControlMode.PercentOutput, 1);
            }
            if (_limelight.getY()>6.6 && _limelight.getY()<10) {
                _shootymotor.set(ControlMode.PercentOutput, .6);
            }
            if (_limelight.getY()>3.3 && _limelight.getY()<6.6) {
                _shootymotor.set(ControlMode.PercentOutput, .3);
            }
        }
    }
}