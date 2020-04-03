package frc.robot.input;

import edu.wpi.first.wpilibj.Spark;
import frc.robot.mapping.Robotmap;

//  http://www.revrobotics.com/content/docs/REV-11-1105-UM.pdf page 8 has the guide on why we use a Spark

/**
 * @author Jacob Lewis
 * @author Josh ppshootaman
 */
public class BlinkinNeo extends Spark
{
    public BlinkinNeo() {
        super(Robotmap.blinkinNeopixel);
        this.set(.77);  //  .77 is green from a blinkin
    }    
}
