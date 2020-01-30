package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.returntypes.Robotmap;

public class Manipulator {

    private CANSparkMax m_inMotor = new CANSparkMax(Robotmap.m_mnipulatorINID, MotorType.kBrushless);
    private CANSparkMax m_outMotor = new CANSparkMax(Robotmap.m_mnipulatorOUTID, MotorType.kBrushless);

    public void BallIn(boolean boxButtonA, boolean canShoot) {
        if (boxButtonA && canShoot) {
            m_inMotor.set(1);
        }
        else m_inMotor.set(0);
    }

    public void BallOut(boolean boxButtonB, boolean canShoot) {
        if (boxButtonB && canShoot) {
            m_outMotor.set(1);
        }
        else m_inMotor.set(0);
    }
}