package frc.robot.models;

public class DriveAdjust
{
    private  double _aimAdjust;

    public double getAimAdjust() 
    {
        return _aimAdjust;
    }

    public void setAimAdjust(double aimAdjust)  
    {
        this._aimAdjust = aimAdjust;
    }

    private  double _distanceAdjust;

    public double getDistanceAdjust() 
    {
        return _distanceAdjust;
    }

    public void setDistanceAdjust(double distanceAdjust)  
    {
        this._distanceAdjust = distanceAdjust;
    }
}