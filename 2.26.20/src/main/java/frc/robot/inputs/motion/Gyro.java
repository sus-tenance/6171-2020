package frc.robot.inputs.motion;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;

public class Gyro
{
    private ADXRS450_Gyro mGyro = new ADXRS450_Gyro();

    public void calibrate()
    {
        mGyro.calibrate();
    }

    public double getAngle()
    {
        return mGyro.getAngle();
    }
}