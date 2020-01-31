package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.returntypes.AccelerometerValue;
import frc.robot.returntypes.Robotmap;

public class Manipulator {

    private CANSparkMax m_inMotor = new CANSparkMax(Robotmap.m_mnipulatorINID, MotorType.kBrushless);
    private CANSparkMax m_outMotor = new CANSparkMax(Robotmap.m_mnipulatorOUTID, MotorType.kBrushless);

    private AccelerometerValue m_accelerometer = new AccelerometerValue();
    private boolean m_canShoot;

    //This should be called in Robot.java when we would like to intake or shoot
    public void ManipulatorMethod(boolean boxButtonA, boolean boxButtonB) {
        if (boxButtonA) {
            m_inMotor.set(-1);
        }
        else if (boxButtonB && m_canShoot) {
            m_outMotor.set(1);
        }
        else m_outMotor.set(0); m_inMotor.set(0);
    }

    //This should be called in the time after we move off the init line in Autonomous.java
    public void AutonomousShoot() {
        m_canShoot = m_accelerometer.CanShoot();
        if (m_canShoot) {
            m_outMotor.set(1);
        }
        else m_outMotor.set(0);
    }
}
