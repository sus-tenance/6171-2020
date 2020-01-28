package frc.robot.subsystems;

import frc.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Shooting {

    private TalonSRX _shootyMotor;
    private Limelight _limelight;
    private double distanceToTarget;

    public void ShootingINIT() {
        _shootyMotor = new TalonSRX(RobotMap.shootyMotorID);
    }

    public void ShootingMAIN(boolean getXButton) {
        if (getXButton) shootWithLL(true);
        else shootWithLL(false);
    }
    
    public void shootWithLL (boolean toggle) {
        /*
            The values below should be determined experimentally.

            We'll need to figure out what distance means in motor values.

            Also, for future reference: TA is exponentially related to distance from
            target... not linearly, and it's reportedly inaccurate. Use TY instead, that's okay.

            And this: https://docs.limelightvision.io/en/latest/cs_estimating_distance.html#using-a-fixed-angle-camera
            
            d = (actualHeightOfTarget-LLHeightFromFloor) / tan(ty + angleBetweenLLAndFloor)

            For us, actualHeightOfTarget = 98.18 in to the 3 pointer shot
            and LLHeightFromFloor and angleBetweenLLAndFloor are based on the robot.
        */

        distanceToTarget = (0.00 - 0.00) / Math.tan(_limelight.getY() - 0.00);  //  0.00 for placeholders
        if (toggle) _shootyMotor.set(ControlMode.Velocity, 0.00*distanceToTarget); //  0.00 for placeholder.
            /*
              Need to find relationship between actual distance and motors.
              This is easy though, like Mechanical can take care of
              finding out what distance needs what power. Hopefully it's linear,
              if not, we can deal with that in code...
            */
    }
}