package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI 
{
	//constants
	private static final double oi_joystickDeadband = 0.2;	//	Modify this here to change the joystick deadband in getDrive and saitek methods.
	
	//Main xbox controller
	public static final int LEFT_X =0;
	public static final int LEFT_Y =1;
	public static final int LEFT_TRIGGER =2;
	public static final int RIGHT_TRIGGER =3;
	public static final int RIGHT_X = 4;
	public static final int RIGHT_Y =5;
	
	public static final int A_NUM =1;
	public static final int B_NUM =2;
	public static final int X_NUM =3;
	public static final int Y_NUM =4;
	
	public static final int BACK = 7;
	public static final int START =8;
	public static final int LEFT_JOYSTICK = 9;
	public static final int RIGHT_JOYSTICK =10;
	public static final int LB_NUM = 5;
	public static final int RB_NUM =6;

	//Manipulator Button Case
	public static final int M_1 = 1;
	public static final int M_2 = 2;
	public static final int M_3 = 3;
	public static final int M_4 = 4;
	public static final int M_5 = 11;
	public static final int M_6 = 6;
	public static final int M_7 = 7;
	public static final int M_8 = 8;
	public static final int M_9 = 9;
	public static final int M_10 = 10;

	//Buffalo Secondary Controller
	public static final int B_XAXIS = 0;
	public static final int B_YAXIS = 1;
	public static final int B_A = 0;
	public static final int B_B = 1;
	public static final int B_X = 2;
	public static final int B_Y = 3;
	public static final int B_LTRIGGER = 4;
	public static final int B_RTRIGGER = 5;
	public static final int B_SELECT = 6;
	public static final int B_START = 7;
	
	JoystickButton dX, dA, dB, dY, dLB, dRB, dBack, dStart, dLeftJoystick, dRightJoystick, m1, m2, m3, m4, m5, m6, m7, m8, m9, m10, bXAxis,
	bYAxis, bA, bB, bX, bY, bLTrigger, bRTrigger, bSelect, bStart;
	Joystick drive, manipulator, buffalo;
	Joystick saitek; 
	JoystickButton D1, D2, D3, D4, D5, D6, Dback, Dstart, DleftJoystick, DrightJoystick;

	public OI()
	{
		drive = new Joystick(0);
		dA = new JoystickButton(drive, A_NUM);
		dB = new JoystickButton(drive, B_NUM);
		dY = new JoystickButton(drive, Y_NUM);
		dX = new JoystickButton(drive, X_NUM);
		dLB = new JoystickButton(drive, LB_NUM);
		dRB = new JoystickButton(drive, RB_NUM);
		dBack = new JoystickButton(drive, BACK);
		dStart = new JoystickButton(drive, START);
		dLeftJoystick = new JoystickButton(drive, LEFT_JOYSTICK);
		dRightJoystick = new JoystickButton(drive, RIGHT_JOYSTICK);
		
		saitek = new Joystick(4);
		D1 = new JoystickButton(saitek, A_NUM);
		D2 = new JoystickButton(saitek, B_NUM);
		D3 = new JoystickButton(saitek, X_NUM);
		D4 = new JoystickButton(saitek, Y_NUM); //Does not work
		D5 = new JoystickButton(saitek, LB_NUM);
		D6 = new JoystickButton(saitek, RB_NUM);
		Dback = new JoystickButton(saitek, BACK); 
		Dstart = new JoystickButton(saitek, START);
		DleftJoystick = new JoystickButton(saitek, LEFT_JOYSTICK);
		
		manipulator = new Joystick(1);
		m1 = new JoystickButton(manipulator, M_1);
		m2 = new JoystickButton(manipulator, M_2);
		m3 = new JoystickButton(manipulator, M_3);
		m4 = new JoystickButton(manipulator, M_4);
		m5 = new JoystickButton(manipulator, M_5);
		m6 = new JoystickButton(manipulator, M_6);
		m7 = new JoystickButton(manipulator, M_7);
		m8 = new JoystickButton(manipulator, M_8);
		m9 = new JoystickButton(manipulator, M_9);
		m10 = new JoystickButton(manipulator, M_10);
		
		buffalo = new Joystick(2);
		bXAxis = new JoystickButton(buffalo, B_XAXIS);
		bYAxis = new JoystickButton(buffalo, B_YAXIS);
		bA = new JoystickButton(buffalo, B_A);
		bB = new JoystickButton(buffalo, B_B);
		bX = new JoystickButton(buffalo, B_X);
		bY = new JoystickButton(buffalo, B_Y);
		bLTrigger = new JoystickButton(buffalo, B_LTRIGGER);
		bRTrigger = new JoystickButton(buffalo, B_RTRIGGER);
		bSelect = new JoystickButton(buffalo, B_SELECT);
		bStart = new JoystickButton(buffalo, B_START);
	}
	//Return Methods for driving
	public double getDriveLeftY()
	{
		return joystickSmoother(applyDeadband(drive.getRawAxis(LEFT_Y), oi_joystickDeadband));
	}
	public double getDriveLeftX()
	{
		return joystickSmoother(applyDeadband(drive.getRawAxis(LEFT_X), oi_joystickDeadband));
	}
	public double getDriveRightX()
	{
		return joystickSmoother(applyDeadband(drive.getRawAxis(RIGHT_X), oi_joystickDeadband));
	}
	public double getDriveRightY()
	{
		return joystickSmoother(applyDeadband(drive.getRawAxis(RIGHT_Y), oi_joystickDeadband));
	}
	public double getDriveLeftTrigger()
	{
		return drive.getRawAxis(LEFT_TRIGGER);
	}
	public double getDriveRightTrigger()
	{
		return drive.getRawAxis(RIGHT_TRIGGER);
	}
	public boolean getA() {
		return drive.getRawButton(A_NUM);
	}
	public boolean getB() {
		return drive.getRawButton(B_NUM);
	}
	public boolean getX() {
		return drive.getRawButton(X_NUM);
	}
	public boolean getY() {
		return drive.getRawButton(Y_NUM);
	}
	public boolean getBoxA() {
		return manipulator.getRawButton(M_1);
	}
	public boolean getBoxB() {
		return manipulator.getRawButton(M_2);
	}
	public boolean getBoxC() {
		return manipulator.getRawButton(M_3);
	}
	public boolean getBoxD() {
		return manipulator.getRawButton(M_4);
	}
	public boolean getBoxE() {
		return manipulator.getRawButton(M_5);
	}
	public boolean getBoxF() {
		return manipulator.getRawButton(M_6);
	}
	public boolean getBoxG() {
		return manipulator.getRawButton(M_7);
	}
	public boolean getBoxH() {
		return manipulator.getRawButton(M_8);
	}
	public boolean getBoxI() {
		return manipulator.getRawButton(M_9);
	}
	public boolean getBoxJ() {
		return manipulator.getRawButton(M_10);
	}
	public boolean getBuffaloA() {
		return buffalo.getRawButton(B_A);
	}
	public boolean getBuffaloB() {
		return buffalo.getRawButton(B_B);
	}
	public boolean getBuffaloX() {
		return buffalo.getRawButton(B_X);
	}
	public boolean getBuffaloY() {
		return buffalo.getRawButton(B_Y);
	}
	public boolean getBuffaloRightTrigger() {
		return buffalo.getRawButton(B_RTRIGGER);
	}
	public boolean getBuffaloLeftTrigger() {
		return buffalo.getRawButton(B_LTRIGGER);
	}
	public boolean getBuffaloUpArrow() {
		return buffalo.getRawAxis(B_YAXIS) > .75;
	}
	public boolean getBuffaloDownArrow() {
		return buffalo.getRawAxis(B_YAXIS) < -.75;
	}
	public boolean getBuffaloRightArrow() {
		return buffalo.getRawAxis(B_XAXIS) > .75;
	}
	public boolean getBuffaloLeftArrow() {
		return buffalo.getRawAxis(B_XAXIS) < -.75;
	}
	public double getSaitekZ()
	{
		return joystickSmoother(applyDeadband(saitek.getRawAxis(2), oi_joystickDeadband));
	}
	public double getSaitekY()
	{
		return joystickSmoother(applyDeadband(saitek.getRawAxis(1), oi_joystickDeadband));
	}
	public double getSaitekX()
	{
		return joystickSmoother(applyDeadband(saitek.getRawAxis(0), oi_joystickDeadband));
	}
	public double getSaitekZRotate()
	{
		return joystickSmoother(applyDeadband(saitek.getRawAxis(3), oi_joystickDeadband));
	}
	/** *** COPIED FROM WPILIB: https://github.com/wpilibsuite/allwpilib/blob/master/wpilibj/src/main/java/edu/wpi/first/wpilibj/drive/RobotDriveBase.java ***
	 * Returns 0.0 if the given value is within the specified range around zero. The remaining range
	 * between the deadband and 1.0 is scaled from 0.0 to 1.0.
	 *
	 * @param value    value to clip
	 * @param deadband range around zero
	 */
	protected double applyDeadband(final double value, final double deadband) {
		if (Math.abs(value) > deadband) {
			if (value > 0.0) {
				return (value - deadband) / (1.0 - deadband);
			} else {
				return (value + deadband) / (1.0 - deadband);
			}
			} else {
				return 0.0;
			}
	}

	protected double joystickSmoother(final double value) { // Smooths out joystick values such that sensitivity is
															// lower towards the bottom end.
		return Math.abs(value)*value;
	}
}
