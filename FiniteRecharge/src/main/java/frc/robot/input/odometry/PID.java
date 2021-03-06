package frc.robot.input.odometry;

import frc.robot.models.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
        double minAimThreshold = 0.5; //TODO This should be slightly less than the Minimum Power needed to spin robot 
        double minDistanceThreshold = 0.1; //TODO This should be slightly less than the Minimum Power needed to advance/retreat robot 

        double headingError = -tx;
        double distanceError = -ty; //Calibrate limelight from destination

        double aimAdjust = 0.0;
        double distanceAdjust = 0.0;

        //causes the robot to aim at a target near tx
        if (tx > 0.15)
        {
            aimAdjust = kpAim * headingError + minAimThreshold; //Seems like +/- Thresholds are flipped
        }
        else if (tx < 0.15)
        {
            aimAdjust = kpAim * headingError - minAimThreshold;
        }
        
        //causes the robot to drive to a target location near ty
        if (ty > 0.15)
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

    //actually useless lmao?
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