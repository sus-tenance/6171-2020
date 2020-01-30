
package frc.robot;

import frc.robot.subsystems.MecWithGyro;
import frc.robot.subsystems.Shooting;
import frc.robot.subsystems.Limelight;
import edu.wpi.first.wpilibj.Timer;

public class Auton {
    private double limelightGetXTolerance = 3; //  Both these tolerance values are in degrees.
    private double gyroTolerance = 3;   //  Consider a ± sign in front of them...
    
    private MecWithGyro autoDrive;
    private Shooting autoShooter;
    private Limelight autoLimelight;
    private Timer timer;

    public void AutonomousStartTimer() {
        timer = new Timer();
        timer.reset();
        timer.start();
    }

    public void AutonomousMain(MecWithGyro drivetrainObject, Shooting shooterObject, Limelight limelightObject) { //  This method runs autonomous functions.
        autoDrive = drivetrainObject;
        autoShooter = shooterObject;
        autoLimelight = limelightObject;
        
        if (timer.get() <= 5 && Math.abs(autoLimelight.getX()) > limelightGetXTolerance){
            autoDrive.autonMoveCartesian(0, 1, autoLimelight.getX()/-27);    //  getX ranges from -27 to 27 degrees. This faces the robot to the LL target.
        }
        else if (timer.get() <= 10) {
            autoDrive.autonMoveCartesian(0, 0, autoLimelight.getX()/-27);   //  Continues adjusting for its last bit.
            autoShooter.shootWithLL(true);    //  Shoots for the next three seconds.
        }
        else if (timer.get() <= 15 && Math.abs(autoDrive.autonGetGyro()) > gyroTolerance){
            autoShooter.shootWithLL(false);
            autoDrive.autonMoveCartesian(0, 0, autoDrive.autonGetGyro()/360);   //  getGyro's circle has 360 degrees going clockwise.  This makes the robot face the wall.
        }
    }
}
/*
if (timer.get() <= 3 && Math.abs(autoLimelight.getX()) > limelightGetXTolerance)
    autoDrive.autonMoveCartesian(0, -1, 0); //  Back past the init line
*  
    autoDrive.autonMoveCartesian(0, 0, autoLimelight.getX()/-27);    //  getX ranges from -27 to 27 degrees. This faces the robot to the LL target.

else if (timer.get() <= 6) {
    autoDrive.autonMoveCartesian(0, 0, autoLimelight.getX()/-27);   //  Continues adjusting for its last bit.
    autoShooter.shootWithLL();    //  Shoots for the next three seconds.
}
else if (timer.get() <= 9 && Math.abs(autoDrive.autonGetGyro()) > gyroTolerance)
    

else if (timer.get() <= 15)
    autoDrive.autonMoveCartesian(0, 0, autoDrive.autonGetGyro()/360);   //  getGyro's circle has 360 degrees going clockwise.  This makes the robot face the wall.
*/
    