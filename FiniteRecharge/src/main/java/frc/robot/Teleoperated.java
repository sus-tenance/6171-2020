package frc.robot;

import frc.robot.input.OI;
import frc.robot.input.odometry.PID;
import frc.robot.input.vision.Limelight;
import frc.robot.models.DriveAdjust;
import frc.robot.subsystems.*;

/**
 * @author Jacob Lewis
 * @author Josu ppshootaman
 * @author lannpha man
 */
public class Teleoperated
{
    private ArcadeDrivetrain _drivetrain;
    private AutoIndexing _indexer;
    private Feeder _feeder;
    private Hopper _hopper;
    private Intake _intake;
    private Shooter _shooter;
    private Limelight _limelight;
    private OI _driveController;
    private OI _operatorController;

    /**
     * Constructs a new TeleOp object.
     * @param drivetrain the drivetrain
     * @param indexer the indexer
     * @param feeder the feeder
     * @param hopper the hopper
     * @param intake the intake
     * @param shooter the shooter
     * @param limelight the limelight you fool have you not grasped the concept by this point
     */
    public Teleoperated(ArcadeDrivetrain drivetrain, AutoIndexing indexer, Feeder feeder, Hopper hopper, Intake intake, Shooter shooter, Limelight limelight)
    {
        _drivetrain = drivetrain;
        _indexer = indexer;
        _feeder = feeder;
        _hopper = hopper;
        _intake = intake;
        _shooter = shooter;
        _limelight = limelight;
        _driveController = new OI(0);
        _operatorController = new OI(1);
    }

    /**
     * oh no! catch it!
     */
    public void RunTeleop()
    {
        //driver control
        _drivetrain.Drive(_driveController);

        //hopper control
        if (_operatorController.dpad() == 180.0)
            _hopper.Reverse();       
        else if (_operatorController.dpad() == 90.0)
            _hopper.Start();
        else
            _hopper.Stop();

        // collector control
        if (_driveController.RButton())
            _intake.Start();
        else if (_driveController.LButton())
            _intake.Reverse();
        else
            _intake.StopMotor();

        // feeder control
        if (_operatorController.getY())
            _feeder.Feed();
        else if (_operatorController.getA())
            _feeder.Reverse();
        else if (_operatorController.getB())
            _feeder.FeedSlow();
        else
            _indexer.moveBalls();

        if (_operatorController.getDriveRightTrigger() > 0) 
        {
            if (!_operatorController.RButton())
                _shooter.Shoot(.28);
            else if (_operatorController.RButton())
                _shooter.Shoot(.32);

            // init line shot (10 foot)
            if (_shooter.returnVelocity() > 1250 && _shooter.returnVelocity() < 1500 && !_operatorController.RButton())
            {
                _shooter.Stop();
                _feeder.Feed();
            }
            // trench shot (18 foot)
            else if (_shooter.returnVelocity() > 1645 && _operatorController.RButton())
            {
                _shooter.Stop();
                _feeder.Feed();
            } 
            else if (!_operatorController.getA() || !_operatorController.getY())
                _feeder.StopFeed();
            
        }
        else _shooter.Idle();

        if (_driveController.getDriveLeftTrigger() == 1 && (Math.abs(_driveController.getDriveLeftY()) == 0) && (Math.abs(_driveController.getDriveRightX()) == 0)) 
        {
            _shooter.Shoot();
            DriveAdjust driveAdjust = PID.CalculateDrive(-_limelight.getTx(), _limelight.getTy());
            _drivetrain.Drive(driveAdjust);
        }
    }
}