package frc.robot.input.vision;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/*
 * Default Location - http://limelight.local:5801
 * Recommended Static Location - 
 * IP - 10.TE.AM.11
 * Subnet - 255.255.255.0
 * Gateway - 10.TE.AM.1
 * Recommended Static Stream Location - http://10.TE.AM.11:5800
 * Recommended Static Config Location - http://10.TE.AM.11:5801
 * 
 * 320x240 pipelines execute at 90fps <- This is usually correct
 * 960x720 pipelines execute at 22fps
 * 
 * tv - Whether the limelight has any valid targets (0 or 1)
 * tx - Horizontal Offset From Crosshair To Target (-27 degrees to 27 degrees)
 * ty - Vertical Offset From Crosshair To Target (-20.5 degrees to 20.5 degrees)
 * ta - Target Area (0% of image to 100% of image)
 * ts - The rotation or "skew" of your target
 * 
 * ledMode     Sets limelight’s LED state
 * 0 	        use the LED Mode set in the current pipeline
 * 1 	        force off
 * 2 	        force blink
 * 3 	        force on
 * 
 * camMode 	Sets limelight’s operation mode
 * 0 	        Vision processor
 * 1 	        Driver Camera (Increases exposure, disables vision processing)
 * 
 * pipeline 	Sets limelight’s current pipeline
 * 0 .. 9 	    Select pipeline 0..9
 * 
 * stream 	Sets limelight’s streaming mode
 * 0 	    Standard - Side-by-side streams if a webcam is attached to Limelight
 * 1 	    PiP Main - The secondary camera stream is placed in the lower-right corner of the primary camera stream
 * 2 	    PiP Secondary - The primary camera stream is placed in the lower-right corner of the secondary camera stream
 * 
 * NetworkTableInstance.getDefault().getTable("limelight").getEntry("<variablename>").setNumber(<value>); 
 */

/**
 * <pre>
 * "Has your ty been negative lately? 😏." - Josh
 * </pre>
 * 
 * @author Jacob Lewis
 * @author Josh ppshootaman
 * 
 * @param target my fucking eyes, good lord.
 * @version the one that hurts my eyes if i look at it.
 * @see <a href="https://i.imgur.com/q0L03h6.jpg">"Hey Jacob. You see that locker? Yeah me too. Enable the robot." - Zoheb </a>
 */
public class Limelight
{
    private NetworkTable _limelightTable;

    private double _limelightTa;
    private boolean _limelightTv;
    private double _limelightTx; 
    private double _limelightTy; 

    /**
     * Updates us with the freshest limelight data. 😎😎😎😎
     * should be run in loop
     */
    public void Update()
    {
        _limelightTable = NetworkTableInstance.getDefault().getTable("limelight");

        _limelightTa = _limelightTable.getEntry("ta").getDouble(0);
        _limelightTx = _limelightTable.getEntry("tx").getDouble(0);
        _limelightTy = _limelightTable.getEntry("ty").getDouble(0);
        _limelightTv = _limelightTable.getEntry("tv").getBoolean(false);

        updateSmartDashboard();
    }

    /**
     * D U H. updates smartdashboard with ta, tx, ty, and tv.
     */
    public void updateSmartDashboard()
    {
        SmartDashboard.putNumber("Limelight ta", _limelightTa);
        SmartDashboard.putNumber("Limelight tx", _limelightTx);
        SmartDashboard.putNumber("Limelight ty", _limelightTy);
        SmartDashboard.putBoolean("Limelight tv", _limelightTv);      
    }
    
    /**
     * 
     * @return percent of camera feed area taken up by the target.
     */
    public double getTa()
    {
        _limelightTa = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0);
        return _limelightTa;
    }

    /**
     * 
     * @return the horizontal offset from the target.
     */
    public double getTx()
    {
        _limelightTx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
        return _limelightTx;
    }

    /**
     * 
     * @return the vertical offset from the target.
     */
    public double getTy()
    {
        _limelightTy = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
        return _limelightTy;
    }

    /**
     * 
     * @return whether there is a valid target or not
     */
    public boolean getTv()
    {
        _limelightTv = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getBoolean(false);
        return _limelightTv;
    }

    /**
     * Because our ll overheats a lot, we use a NeoPixel for green lights. This turns off the limelight lights, duh.
     */
    public void lightsOut()
    {
        _limelightTable.getEntry("ledMode").setNumber(1);
    }

}    