package frc.robot.systems.subsystems;

import frc.robot.systems.drive.IMotor;

public class Climb
{
    private IMotor _slide, _winchL, _winchR;

    private double _slidePower = 0.25;
    private double _winchPower = 0.25;

    public Climb(IMotor slideMotor, IMotor winchLeft, IMotor winchRight)
    {
        _slide = slideMotor;
        _winchL = winchLeft;
        _winchR = winchRight;

        _winchR.IsInverted(true);
    }

    public void SlideUp()
    {
        _slide.SetPower(_slidePower);
    }

    public void SlideDown()
    {
        _slide.SetPower(-_slidePower);
    }

    public void Unravel()
    {
        _winchL.SetPower(_winchPower);
        _winchR.SetPower(_winchPower);
    } 

    public void Ravel()
    {
        _winchL.SetPower(_winchPower);
        _winchR.SetPower(_winchPower);
    }

    public void StopMotors()
    {
        _slide.SetPower(0);
        _winchL.SetPower(0);
        _winchR.SetPower(0);
    }
}