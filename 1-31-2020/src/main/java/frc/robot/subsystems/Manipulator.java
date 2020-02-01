package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import frc.robot.returntypes.Robotmap;

public class Manipulator {

    private TalonSRX m_outMotor = new TalonSRX(Robotmap.m_mnipulatorOUTID);
    //private TalonSRX m_inMotor = new TalonSRX(Robotmap.m_mnipulatorINID);

    private boolean m_canShoot;

    //This should be called in Robot.java when we would like to intake or shoot
    public void ManipulatorMethod(boolean boxButtonA, boolean boxButtonB) {
        if (boxButtonA) {
            m_outMotor.set(ControlMode.PercentOutput, -1.0);
        }
        else if (boxButtonB && m_canShoot) {
            //m_inMotor.set(ControlMode.PercentOutput, -1.0);
        }
        else m_outMotor.set(ControlMode.PercentOutput, -1.0); //m_inMotor.set(ControlMode.PercentOutput, -1.0);
    }
    

    //This should be called in the time after we move off the init line in Autonomous.java
    public void AutonomousShoot() {
        m_outMotor.set(ControlMode.PercentOutput, -1);
        /*
        m_canShoot = m_accelerometer.CanShoot();
        if (m_canShoot) {
            m_testMotor.set(ControlMode.PercentOutput, -1.0);
        }
        else m_testMotor.set(ControlMode.PercentOutput, 0.0);
        */
    }    
}
