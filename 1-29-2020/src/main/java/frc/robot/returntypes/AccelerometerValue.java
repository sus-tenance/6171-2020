package frc.robot.returntypes;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;

public class AccelerometerValue {
    
    private BuiltInAccelerometer m_accelerometer = new BuiltInAccelerometer();

    private boolean m_moving;

    public boolean AccelerometerValueReturn() {
        if (Math.abs(m_accelerometer.getX()) > .5 && Math.abs(m_accelerometer.getY()) > .5) {
            m_moving = false;
        }
        else if ((Math.abs(m_accelerometer.getX()) <= .5 && Math.abs(m_accelerometer.getY()) <= .5)) {
            m_moving = true;
        }
        return m_moving;
    }
}