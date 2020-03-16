package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.Ultrasonic.*;

import frc.robot.mapping.Robotmap;

/**
 * @author Jacob Lewis
 * @author Joshua ppshootaman
 * @author Lance Pham
 */
public class AutoIndexing
{

    /*  |        OO
        |       OOOO
        |       OOOO
        |        OO
        | _four
        |
        | _three
        |
        | _two
        |
        |__________________
               _one

    */
    private Ultrasonic _one = new Ultrasonic(Robotmap.sonars[0][0], Robotmap.sonars[0][1], Unit.kInches);    //  at the base of the feed
    private Ultrasonic _two = new Ultrasonic(Robotmap.sonars[1][0], Robotmap.sonars[1][1], Unit.kInches);    //  lowest one in it

    private static final double _kYesBallPresentDistanceIswithinourspecifieddistancetoreconizeitasaballwithinthesensorwewant = 0.5;   //  inches

    private Feeder _feeder;

    /**
     * オートインデックス
     * @param feeder フィーダーが必要です
     */
    public AutoIndexing(Feeder feeder)
    {
        _feeder = feeder;
        _one.setAutomaticMode(true);
    }

    /**
     * TOUCHES balls hehe
     * "ball reader"
     */
    public void moveBalls()
    {
        if (isBallPresent(_one)) 
            while (!isBallPresent(_two))
                _feeder.Feed();
        else _feeder.StopMotor();                                               // Y E S 😉
    }

    /**
     * @param sensor is the sensor ??? are you dumb??
     * @return (true) if when now is there ball has there been in was is p r e s e n t 😐 (or is it 🐱‍👤))
     */
    private boolean isBallPresent(Ultrasonic sensor)
    {
        return (sensor.getRangeInches() < _kYesBallPresentDistanceIswithinourspecifieddistancetoreconizeitasaballwithinthesensorwewant);
    }
}