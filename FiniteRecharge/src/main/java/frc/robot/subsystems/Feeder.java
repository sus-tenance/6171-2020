package frc.robot.subsystems;

import frc.robot.models.interfaces.Talon;
import frc.robot.mapping.Robotmap;

/**
 * @author Jacob Lewis
 * @author Josh ppshootaman
 */
public class Feeder extends Talon
{    
    /**
     * フィーダーを構築する
     * @param deviceNumber デバイスIDが必要です
     */
    public Feeder() 
    {
        super(Robotmap._feederMotorID);
        this.SetInverted(true);
    }

    private static final double POWER = .4;
    private static final double SLOWPOWER = .2;
    private static final double REVERSEPOWER = -.4;

    public void Feed()
    {
        this.Feed(POWER);
    }

    public void FeedSlow()
    {
        this.Feed(SLOWPOWER);
    }

    public void Feed(double power)
    {
        this.SetPower(power);
    }

    public void Reverse()
    {
        this.Feed(REVERSEPOWER);
    }

    public void Reverse(double power)
    {
        this.Feed(-power);
    }

    public void StopFeed()
    {
        this.StopMotor();
    }
}