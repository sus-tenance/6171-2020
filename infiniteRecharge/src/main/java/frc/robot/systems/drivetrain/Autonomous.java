package frc.robot.systems.drivetrain;

import frc.robot.outputs.vision.Limelight;
import frc.robot.systems.subsystems.Shooter;

public class Autonomous
{
    private ArcadeDrive _robotDrive;

    private Limelight _limelight = new Limelight();
    private Shooter  _shoot;

    private double _tx;

    public Autonomous(ArcadeDrive robotDrive, Shooter shoot)
    {
        _robotDrive = robotDrive;
        _shoot = shoot;
    }

    public void Drive()
    {
        _tx = _limelight.GetTx();

        if ((_tx > 2) && (_tx < 2))
        {
            _shoot.Shoot();
        }
        else
        {
            _robotDrive.Drive(0.0, 0.3);
        }
    }
}

