package frc.robot.input.odometry;

import com.kauailabs.navx.frc.AHRS;

import frc.robot.mapping.Robotmap;

//  Implement new mehtods as you want by looking at AHRS source file (ctrl-click this -> AHRS), have fun 😁

/**
 * @author Jacob Lewis
 * @author Josh ppshootaman
 */
public class NavX extends AHRS
{
    /**
     * Constructs a NavX with the default port.
     */
    public NavX()
    {
        super(Robotmap.navxPort);
        this.reset();
        this.calibrate();
    }

    public double GetAngle()
    {
        return this.getAngle();
    }

    public void Calibrate()
    {
        this.calibrate();
    }
}