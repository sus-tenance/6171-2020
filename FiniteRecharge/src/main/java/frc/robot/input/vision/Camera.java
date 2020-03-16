package frc.robot.input.vision;

import edu.wpi.cscore.UsbCamera;

/**
 * @author Jacob Lewis
 * @author Josh ppshootaman
 */
public class Camera extends UsbCamera
{

    /**
     * Constructs a new camera object. To see its feed, use the CameraServer dropdown in SmartDashoard, right click the corresponding camera and select Show As: Camera Stream.
     * with our default resolution of 240x240 and 30 FPS.
     * Use the init() method in robotInit to begin stream.
     * 
     * @param port The USB port the camera is on. 0 and 1 are on the RIO but a USB hub probably works to offer more.
     * 
     */
    public Camera(String name, int port)
    {
        super(name, port);
        this.setResolution(240, 240);
        this.setFPS(30);
    }
    
    /**
     * Constructs a new camera object. To see its feed, use the CameraServer dropdown in SmartDashoard, right click the corresponding camera and select Show As: Camera Stream.
     * with our default resolution of 240x240 and 30 FPS.
     * Use the init() method in robotInit to begin stream.
     * 
     * @param port The USB port the camera is on. 0 and 1 are on the RIO but a USB hub probably works to offer more.
     * @param resWidth resolution width value, 320 is good but lower things to save bandwidth if necessary.
     * @param resHeight resolution height value, 240 is good
     * @param fps frames per second to refresh, 30 is good 
     */
    public Camera(String name, int port, int resWidth, int resHeight, int fps)
    {
        super(name, port);
        this.setResolution(resWidth, resHeight);
        this.setFPS(fps);
    }

}

