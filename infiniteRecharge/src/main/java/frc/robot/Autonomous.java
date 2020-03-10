package frc.robot;

import edu.wpi.first.wpilibj.Timer;

import com.revrobotics.CANEncoder;

import frc.robot.systems.drivetrain.ArcadeDrive;
import frc.robot.systems.subsystems.*;


public class Autonomous
{
    private Shooter _Shooter;
    private Feeder _Feeder;
    private ArcadeDrive _Drivetrain;
    private Timer _AutonTimer = new Timer();
    private CANEncoder _ShootingEncoder;
    
    private double power = 0.27;
    private double _DesiredVelocity = 1230;
    
    /**
     * Instantitialates systems for auton operation
     * @param hopper bucket 😈
     * @param shooter gun 🎉
     * @param feeder vacuum 🙈
     * @param drivetrain wheels 😎(☞ﾟヮﾟ)☞ ☜(ﾟヮﾟ☜)
     */
    public Autonomous(Shooter shooter, Feeder feeder, ArcadeDrive drivetrain, CANEncoder shootingencoder)
    {
        _Shooter = shooter;
        _Feeder = feeder;
        _Drivetrain = drivetrain;
        _ShootingEncoder = shootingencoder;
        _AutonTimer.reset();
        _Feeder.Feed(0.0);
    }
    
    /**
     * plays the game for us in the first 15 seconds of the game
     * robot moves, but NO ONE TOUCHES A BUTTON YOOOo 👌
     * this should be called via loop
     */
    public void RunAutonomousBackwards()
    {  
        _Shooter.Shoot(power);
        if (_ShootingEncoder.getVelocity() > _DesiredVelocity)
        {
          _Feeder.Feed(-.7);
        }
        if (_AutonTimer.get() > 8 && _AutonTimer.get() < 12)
        {
          _Drivetrain.Drive(0.5, 0.0);
        }
        else if (_AutonTimer.get() > 12)
        {
          _Drivetrain.Drive(0.0, 0.0);
        }
    }

    public void RunAutonomousForwards()
    {  
        _Shooter.Shoot(power);
        if (_ShootingEncoder.getVelocity() > _DesiredVelocity)
        {
          _Feeder.Feed(-.7);
        }
        if (_AutonTimer.get() > 8 && _AutonTimer.get() < 12)
        {
          _Drivetrain.Drive(-0.5, 0.0);
        }
        else if (_AutonTimer.get() > 12)
        {
          _Drivetrain.Drive(0.0, 0.0);
        }
    }

    /**
     * starts the timer for autonomous 🐱‍👤
     */
    public void StartAutonomousTimer()
    {
        _AutonTimer.start();
    }
}