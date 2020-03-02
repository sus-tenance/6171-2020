package frc.robot.outputs.motion;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.models.*;

public class PID
{

    private double p;
    private double i;
    private double d;
    private double f;
    private int error;
    boolean Apushed = false;

    public static DriveAdjust CalculateDrive(double tx, double ty)
    {
        double kpAim = -0.035; //Proportional constant for Aiming
        double kpDistance = -0.15; //Proportional Constant for Distance
        double minAimThreshold = 0.06; //TODO This should be slightly less than the Minimum Power needed to spin robot 
        double minDistanceThreshold = 0.1; //TODO This should be slightly less than the Minimum Power needed to advance/retreat robot 

        double headingError = -tx;
        double distanceError = -ty; //Calibrate limelight from destination

        double aimAdjust = 0.0;
        double distanceAdjust = 0.0;

        if (tx > 1.0)
        {
            aimAdjust = kpAim * headingError + minAimThreshold; //Seems like +/- Thresholds are flipped
        }
        else if (tx < 1.0)
        {
            aimAdjust = kpAim * headingError - minAimThreshold;
        }
        
        if (ty > 0.2)
        {
            distanceAdjust = -1 * kpDistance * distanceError + minDistanceThreshold; //Seems like +/- Thresholds are flipped      
        }
        else if (ty < 0.2)
        {
            distanceAdjust = -1* kpDistance * distanceError - minDistanceThreshold;
        }

        DriveAdjust driveAdjust = new DriveAdjust();
        driveAdjust.setAimAdjust(aimAdjust);
        driveAdjust.setDistanceAdjust(distanceAdjust);

        SmartDashboard.putNumber("distanceAdjust", distanceAdjust);
        SmartDashboard.putNumber("aimAdjust", aimAdjust);

        return driveAdjust;
    }

    public int GetError()
    {   
        error = 0;
        return error;
    }

    public void SetP(int value)
    {
        p=value;
    }
    public void SetI(int value)
    {
        i=value;
    }
    public void SetD(int value)
    {
        d=value;
    }
    public void SetF(int value)
    {
        f=value;
    }

    public double GetP()
    {
        return p;
    }
    public double GetI()
    {
        return i;
    }
    public double GetD()
    {
        return d;
    }
    public double GetF()
    {
        return f;
    }
}