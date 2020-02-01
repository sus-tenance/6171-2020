package frc.robot.returntypes;

import edu.wpi.first.networktables.*;


public class Limelight {

    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    
    private double x;
    private double y;
    private double a;
    private boolean v;

	public double getA() {
        NetworkTableEntry ta = table.getEntry("ta");
        a = ta.getDouble(0.0);
        return a;
    }

    public double getX() {
        NetworkTableEntry tx = table.getEntry("tx");
        x = tx.getDouble(0.0);
        return x;
    }

    public double getY() {
        NetworkTableEntry ty = table.getEntry("ty");
        y = ty.getDouble(0.0);
        return y;
    }

    public boolean getV() {
        NetworkTableEntry tv = table.getEntry("tv");
        v = tv.getBoolean(false);
        return v;
    }

    public void setLight(boolean _button) {
        if (_button) table.getEntry("ledMode").setNumber(3); else table.getEntry("ledMode").setNumber(0);
    }
}