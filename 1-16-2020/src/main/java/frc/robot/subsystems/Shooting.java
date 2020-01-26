package frc.robot.subsystems;

import edu.wpi.first.wpiutil.math.MathUtil;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import frc.robot.OI;

public class Shooting {

    private OI oi;
    private TalonSRX _shootyMotor;
    private static final int _shootyMotorID = 0;

    private Limelight _limelight;

    public void ShootingINIT() {
        _shootyMotor = new TalonSRX(_shootyMotorID);
    }

    public void ShootingMAIN() {
        //if this is here it worked
        if (oi.getX()) {
            /*
            if (_limelight.getY() > 10) {
                _shootyMotor.set(ControlMode.PercentOutput, 1);
            }
            else if (_limelight.getY() > 6.6 && _limelight.getY() < 10) {
                _shootyMotor.set(ControlMode.PercentOutput, .6);
            }
            if (_limelight.getY() > 3.3 && _limelight.getY() < 6.6) {
                _shootyMotor.set(ControlMode.PercentOutput, .3);
            }*/

                //  So i have NO idea where the numbers (3.3, 10, 1, .6, and .3) for this came from, I'm assuming by guess. They should be determined experimentally.
            _shootyMotor.set(ControlMode.PercentOutput, map(MathUtil.clamp(_limelight.getY(), 3.3, 10), 3.3, 10, 0.3, 1));

        }
    }
    
    private double map(final double x, final double in_min, final double in_max, final double out_min, final double out_max) {  //  Copied from the Arduino map() function
        return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
    }
}