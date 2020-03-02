package frc.robot.systems.drivetrain;

import edu.wpi.first.wpilibj.Timer;

public class Autonomous
{
    private Timer _timer = new Timer();

    public void TimerStart()
    {
        _timer.start();
    }
}

