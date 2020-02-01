package frc.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Autonomous {

    private Drivetrain m_mecanum;

    private Timer m_timer = new Timer();

    private int m_time;

    public Autonomous(Drivetrain train) {
        m_mecanum = train;
    }
    
    public void StartAutonomousTimer() {
        m_timer.reset();
        m_timer.start();
    }

    public void AutonomousMode() {
        m_time = (int)Math.round(m_timer.get());
        SmartDashboard.putNumber("Match Time:", m_time);
        if (m_timer.get() <= 2) {
            m_mecanum.MecanumDrivetrain(0.0, -0.3, 0.0);
        }
        else if (m_timer.get() <= 15 && m_timer.get() > 3) {
            m_mecanum.MecanumAutonomous();
        }
    }
    


}