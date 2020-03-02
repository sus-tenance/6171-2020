package frc.robot.systems.subsystems;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.outputs.vision.Limelight;
import frc.robot.systems.drive.IMotor;

public class Shooter
{

    private Limelight _limelight = new Limelight();
    private Timer _timer = new Timer();
    private SpeedControllerGroup _shootyGroup;

    private double tx = _limelight.GetTx();

    public Shooter(IMotor shootLeftMotor, IMotor shootRightMotor)
    {
        
    }

    public void Shoot()
    {

    }

    public void Shoot(Limelight limelight)
    {
        if ((tx>2 || tx<-2)&& tx!=0)
        {
            _timer.start();
        }
    }
}