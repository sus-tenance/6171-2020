package frc.robot.subsystems;

import io.github.pseudoresonance.pixy2api.*;
import io.github.pseudoresonance.pixy2api.links.*;


public class PixyIntake {
   
    private Pixy2 pixy;

    public void PixyInit () {
        pixy = Pixy2.createInstance(new SPILink());
        pixy.init(1);   //  SPI chip select: CS0 is used by the gyro, so we shall use CS1 for Pixy2.
    }

    public void PixyMain () {

    }
}