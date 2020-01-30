package frc.robot;

import edu.wpi.first.wpilibj.Timer;

public class Autonomous {

    private Drivetrain m_mecanum = new Drivetrain();

    private static int m_oneSecond = 14;
    private static int m_sixMore = 8;

    public void AutonomousMode() {
        if (Timer.getMatchTime() >= m_oneSecond) {
            m_mecanum.MecanumDrivetrain(0.0, -0.3, 0.0);
        }
        if (Timer.getMatchTime() >= m_sixMore) {
            m_mecanum.MecanumAutonomous();
        }
    }


}