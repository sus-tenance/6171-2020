
package frc.robot;

import frc.robot.subsystems.MecWithGyro;
import frc.robot.subsystems.Shooting;
import frc.robot.subsystems.Limelight;
import edu.wpi.first.wpilibj.Timer;

public class Auton {
    
    private double limelightGetXTolerance = 10;
    private double gyroTolerance = 10;
    private MecWithGyro autoDrive;
    private Shooting autoShooter;
    private Limelight autoLimelight;
    private Timer _timer;

    public void Autonomous(MecWithGyro drivetrainObject, Shooting shooterObject, Limelight limelightObject) { //  This method runs autonomous functions.
        autoDrive = drivetrainObject;
        autoShooter = shooterObject;
        autoLimelight = limelightObject;
    
        while (Math.abs(autoLimelight.getX()) > limelightGetXTolerance) {
            autoDrive.autonMoveCartesian(0, 0, autoLimelight.getX()/-27);    //  getX ranges from -27 to 27 degrees
        }

        _timer.start();
        while(_timer.get() < 5) autoShooter.autonShooting();
        _timer.stop();
        _timer.reset();
    
        while(Math.abs(autoDrive.autonGetGyro()) > gyroTolerance) {
            autoDrive.autonMoveCartesian(0, 0, autoDrive.autonGetGyro()/360);   //  getGyro is 0 to 360 in a clockwise circle, negative is ccw
        }
    
        _timer.start();
        while(_timer.get() < 5) autoDrive.autonMoveCartesian(0, -1, 0);
        _timer.stop();    
    }    
}