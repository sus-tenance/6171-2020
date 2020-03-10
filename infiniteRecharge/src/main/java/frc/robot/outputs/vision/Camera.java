package frc.robot.outputs.vision;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;

public class Camera
{
    private UsbCamera _camera;
    
    /**
     * Constructs a new camera object. To see its feed, use the CameraServer dropdown in SmartDashoard, right click the corresponding camera and select Show As: Camera Stream.
     * 
     * @param port The USB port the camera is on. 0 and 1 are on the RIO but a USB hub probably works to offer more.
     */
    public Camera(int port)
    {
        _camera = CameraServer.getInstance().startAutomaticCapture(port);
        _camera.setResolution(240, 240);
        _camera.setFPS(30);
    }

    /**
     * Constructs a new camera object. To see its feed, use the CameraServer dropdown in SmartDashoard, right click the corresponding camera and select Show As: Camera Stream.
     * 
     * @param port The USB port the camera is on. 0 and 1 are on the RIO but a USB hub probably works to offer more.
     * @param resWidth resolution width value, 320 is good but lower things to save bandwidth if necessary.
     * @param resHeight resolution height value, 240 is good
     * @param fps frames per second to refresh, 30 is good
     */
    public Camera(int port, int resWidth, int resHeight, int fps)
    {
        _camera = CameraServer.getInstance().startAutomaticCapture(port);
        _camera.setResolution(resWidth, resHeight);
        _camera.setFPS(fps);
    }
}

