package frc.robot.subsystems;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import io.github.pseudoresonance.pixy2api.*;
import io.github.pseudoresonance.pixy2api.links.SPILink;
import io.github.pseudoresonance.pixy2api.Pixy2CCC.Block;

import frc.robot.subsystems.MecWithGyro;


public class PixyIntake {
	
	private static final int SPICHIPSELECT = 1;
	private double pixyTolerance = 3;
	public static boolean isCamera = false;
	public static int state = -1;
   
    private static Pixy2 pixy;

    private static MecWithGyro pixyDrive;

    public void PixyInit () {
        pixy = Pixy2.createInstance(new SPILink());
        pixy.init(SPICHIPSELECT);   //  SPI chip select: CS0 is used by the gyro, so we shall use CS1 for Pixy2.
            //  We can solder header pins to break out the SPI pins we need from the back of the Gyro.
    }

    public void PixyMain (boolean getXButton) {
		Block biggestBlock = getBiggestBlock();
		if (getXButton && Math.abs(biggestBlock.getX()) > pixyTolerance) pixyDrive.autonMoveCartesian(biggestBlock.getX()/0, biggestBlock.getY()/0, biggestBlock.getX()/0);	//	This would play out weird in movenment. I also don't know what the getX ranges from to scale it... 0 for placeholder.
    }

    public static Block getBiggestBlock() { //  Modified from https://github.com/PseudoResonance/Pixy2JavaAPI/wiki/Using-the-API#finding-the-biggest-target-in-the-frame
		// Gets the number of "blocks", identified targets, that match signature 1 on the Pixy2,
		// does not wait for new data if none is available,
		// and limits the number of returned blocks to 25, for a slight increase in efficiency
		if (!isCamera)
			state = pixy.init(SPICHIPSELECT);
		isCamera = state >= 0;
		SmartDashboard.putBoolean("Camera", isCamera);
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
		SmartDashboard.putNumber("Largest Block X", largestBlock.getX());
		SmartDashboard.putNumber("Largest Block Y", largestBlock.getY());
		return largestBlock;
	}
}