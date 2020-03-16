package frc.robot;

import edu.wpi.first.wpilibj.Timer;
import frc.robot.subsystems.*;

/**
 * @author Jacob Lewis
 * @author Joshua ppshootaman
 * @author urmom
 */
public class Autonomous
{
    private Shooter _shooter;
    private Feeder _feeder;
    private ArcadeDrivetrain _drivetrain;
    private Timer _McTimer = new Timer();
    
    private double power = 0.27;
    private double _desiredVelocity = 1230;

    public Autonomous(ArcadeDrivetrain drivetrain, Feeder feeder, Shooter shooter)
    {
        _drivetrain = drivetrain;
        _feeder = feeder;
        _shooter = shooter;
    }

    /**
     * plays the game for us in the first 15 seconds of the game
     * robot moves, but NO ONE TOUCHES A BUTTON YOOOo 👌
     * this should be called via loop
     */
    public void RunAutonomousBackwards()
    {  
        _shooter.Shoot(power);
        if (_shooter.returnVelocity() > _desiredVelocity)
        {
          _feeder.Feed(-.7);
        }
        if (_McTimer.get() > 8 && _McTimer.get() < 12)
        {
          _drivetrain.Drive(0.5, 0.0);
        }
        else if (_McTimer.get() > 12)
        {
          _drivetrain.Drive(0.0, 0.0);
        }
    }

    public void RunAutonomousForwards()
    {  
        _shooter.Shoot(power);
        if (_shooter.returnVelocity() > _desiredVelocity)
        {
          _feeder.Feed(-.7);
        }
        if (_McTimer.get() > 8 && _McTimer.get() < 12)
        {
          _drivetrain.Drive(-0.5, 0.0);
        }
        else if (_McTimer.get() > 12)
        {
          _drivetrain.Drive(0.0, 0.0);
        }
    }

    /**
     * starts the timer for autonomous 🐱‍👤
     */
    public void StartAutonomousTimer()
    {
        _McTimer.start();
    }
}
