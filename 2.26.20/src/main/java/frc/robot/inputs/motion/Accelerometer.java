package frc.robot.inputs.motion;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;

public class Accelerometer {
    
    private BuiltInAccelerometer m_accelerometer = new BuiltInAccelerometer();

    private boolean m_canShoot;

    private double m_absX;
    private double m_absY;

    public boolean CanShoot() {

        m_absX = Math.abs(m_accelerometer.getX());
        m_absY = Math.abs(m_accelerometer.getY());

        //If robot is moving m_canShoot is false
        if (m_absX > .5 || m_absY > .5) {
            m_canShoot = false;
        }
        //If robot is not moving m_canShoot is true
        else if ((m_absX <= 0 && m_absY <= 0)) {
            m_canShoot = true;
        }
        return m_canShoot;
    }
}