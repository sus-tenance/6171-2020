package frc.robot.systems.subsystems;

import frc.robot.systems.drive.IMotor;

public class Climb
{
    private IMotor _slide, _winchL, _winchR;
    public Climb(IMotor slideMotor, IMotor winchLeft, IMotor winchRight)
    {
        _slide = slideMotor;
        _winchL = winchLeft;
        _winchR = winchRight;
    }

    public void SlideUp()
    {
        _slide.SetPower(0.25);
    }

    public void SlideDown()
    {
        _slide.SetPower(-0.25);
    }

    public void Unravel()
    {
        _winchL.SetPower(0.25);
        _winchR.SetPower(-0.25);
    } 

    public void Ravel()
    {
        _winchL.SetPower(-0.25);
        _winchR.SetPower(0.25);
    }

    public void StopMotors()
    {
        _slide.SetPower(0);
        _winchL.SetPower(0);
        _winchR.SetPower(0);
    }
}