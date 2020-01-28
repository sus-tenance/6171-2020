package frc.robot.subsystems;

import java.util.ArrayList;

import io.github.pseudoresonance.pixy2api.*;
import io.github.pseudoresonance.pixy2api.links.SPILink;
import io.github.pseudoresonance.pixy2api.Pixy2CCC.Block;

import frc.robot.subsystems.MecWithGyro;


public class PixyIntake {
   
    private static Pixy2 pixy;

    private static MecWithGyro pixyDrive;

    public void PixyInit () {
        pixy = Pixy2.createInstance(new SPILink());
        pixy.init(1);   //  SPI chip select: CS0 is used by the gyro, so we shall use CS1 for Pixy2.
            //  We can solder header pins to break out the SPI pins we need from the back of the Gyro.
    }

    public void PixyMain (boolean getXButton) {
        if (getXButton) {
            
        }
    }

    public static Block getBiggestBlock() { //  Modified from https://github.com/PseudoResonance/Pixy2JavaAPI/wiki/Using-the-API#finding-the-biggest-target-in-the-frame
		// Gets the number of "blocks", identified targets, that match signature 1 on the Pixy2,
		// does not wait for new data if none is available,
		// and limits the number of returned blocks to 25, for a slight increase in efficiency
		int blockCount = pixy.getCCC().getBlocks(false, Pixy2CCC.CCC_SIG1, 25);
		System.out.println("Found " + blockCount + " blocks!"); // Reports number of blocks found
		if (blockCount <= 0) {
			return null; // If blocks were not found, stop processing
		}
		ArrayList<Block> blocks = pixy.getCCC().getBlocks(); // Gets a list of all blocks found by the Pixy2
		Block largestBlock = null;
		for (Block block : blocks) {
			if (largestBlock == null) {
				largestBlock = block;   //  Choose the first recognized block
			} else if (block.getWidth()*block.getHeight() > largestBlock.getWidth()*largestBlock.getHeight()) {
				largestBlock = block;
			}
		}
		return largestBlock;
	}
}