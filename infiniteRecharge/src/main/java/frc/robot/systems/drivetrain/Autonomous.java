package frc.robot.systems.drivetrain;

import edu.wpi.first.wpilibj.Timer;
import frc.robot.models.DriveAdjust;
import frc.robot.outputs.vision.Limelight;
import frc.robot.systems.subsystems.Feeder;
import frc.robot.systems.subsystems.Shooter;

public class Autonomous
{
    private ArcadeDrive _robotDrive;

    private Timer _timer = new Timer();
    private Timer _adjustTimer = new Timer();
    private Limelight _limelight = new Limelight();
    private Shooter  _shoot;
    private Feeder _feed;

    private double _tx;
    private double _time;

    public Autonomous(ArcadeDrive robotDrive, Shooter shoot, Feeder feed)
    {
        _robotDrive = robotDrive;
        _shoot = shoot;
        _feed = feed;
    }

    public void Drive(DriveAdjust driveAdjust)
    {
        _robotDrive.Drive(driveAdjust);
    }
}

