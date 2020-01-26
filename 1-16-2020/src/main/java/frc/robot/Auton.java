
package frc.robot;

import frc.robot.subsystems.MecWithGyro;
import frc.robot.subsystems.Shooting;
import frc.robot.subsystems.Limelight;
import edu.wpi.first.wpilibj.Timer;

public class Auton {
    
    private MecWithGyro autoDrive;
    private Shooting autoShooter;
    private Limelight autoLimelight;
    private Timer _timer;

    private double initialAngle;
    private String firstTurn;

    public void Autonomous(MecWithGyro drivetrainObject, Shooting shooterObject, Limelight limelightObject) { //  This method runs autonomous functions.
        
        autoDrive = drivetrainObject;
        autoShooter = shooterObject;
        autoLimelight = limelightObject;

        /*
        * PSEUDOCODE:
        * 
        * get gyro angle at start for north ref
        * 
        * if tx is positive
        *  turn right until 0
        * else if tx is negative
        *  turn left until 0
        * 
        * shoot
        * 
        * while gyro angle is not equal to what the starting angle was
        *  turn in the opposite direction that you used to turn to shoot
        * 
        * back up a bit
        * 
        * done
        * 
        */

        initialAngle = autoDrive.autonGetGyro();
        firstTurn = "";
    
        while(applyDeadband(autoLimelight.getX(), 3) != 0){
        if (applyDeadband(autoLimelight.getX(), 3) > 0) {
            autoDrive.autonTurnLeft();
            firstTurn = "Left";
        } else if (applyDeadband(autoLimelight.getX(), 3) < 0) {
            autoDrive.autonTurnRight();
            firstTurn = "Right";
        }
        }
    
        _timer.start();
        while(_timer.get() < 5)
        autoShooter.autonShooting();
        _timer.stop();
    
        while(applyDeadband(autoDrive.autonGetGyro(), 10) != initialAngle) {
        switch(firstTurn){
            case "Left":
            autoDrive.autonTurnRight();
            break;
            case "Right":
            autoDrive.autonTurnLeft();
            break;
        }
        }
    
        _timer.start();
        while(_timer.get() < 5)
        autoDrive.autonMoveBack();     
        _timer.stop();
    
    }

    /** *** COPIED FROM WPILIB: https://github.com/wpilibsuite/allwpilib/blob/master/wpilibj/src/main/java/edu/wpi/first/wpilibj/drive/RobotDriveBase.java ***
	 * Returns 0.0 if the given value is within the specified range around zero. The remaining range
	 * between the deadband and 1.0 is scaled from 0.0 to 1.0.
	 *
	 * @param value    value to clip
	 * @param deadband range around zero
	 */
	protected double applyDeadband(final double value, final double deadband) {
		if (Math.abs(value) > deadband) {
			if (value > 0.0) {
				return (value - deadband) / (1.0 - deadband);
			} else {
				return (value + deadband) / (1.0 - deadband);
			}
			} else {
				return 0.0;
			}
	}
    
}