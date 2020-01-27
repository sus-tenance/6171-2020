package frc.robot.subsystems;

import frc.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Shooting {

    private TalonSRX _shootyMotor;
    private Limelight _limelight;

    public void ShootingINIT() {
        _shootyMotor = new TalonSRX(RobotMap.shootyMotorID);
    }

    public void ShootingMAIN(boolean getXButton) {
        if (getXButton) shootWithLL();
    }
    
    public void shootWithLL () {
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

            /*
                The values below should be determined experimentally.

                We'll need to figure out what distance means in motor values.

                Also, for future reference: TA is exponentially related to distance from
                target... not linearly, and it's reportedly inaccurate. Use TY instead, that's okay.

                And this: https://docs.limelightvision.io/en/latest/cs_estimating_distance.html#using-a-fixed-angle-camera
                
            */

        _shootyMotor.set(ControlMode.Velocity, map(_limelight.getY(), 3.3, 10, 0.3, 1));
    }

    private double map(final double x, final double in_min, final double in_max, final double out_min, final double out_max) {
        return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;    //  Copied from the Arduino map() function
    }
}