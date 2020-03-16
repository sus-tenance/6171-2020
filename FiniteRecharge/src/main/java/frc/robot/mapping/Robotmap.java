package frc.robot.mapping;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Port;

/**
 * @author Jacob Lewis
 * @author Josh ppshootaman
 */
public class Robotmap {

    // DRIVETRAIN
    // SPARK MAX
    public static final int _frontLeftMotorID = 7;
    public static final int _rearLeftMotorID = 8;
    public static final int _frontRightMotorID = 1;
    public static final int _rearRightMotorID = 4;

    // INTAKE
    // TALON
    public static final int _intakeMotorID = 16;

    // HOPPER
    // TALONS
    public static final int _hopperLeftMotorID = 4;
    public static final int _hopperRightMotorID = 19;

    // FEEDER
    // TALON
    public static final int _feederMotorID = 13;

    // SHOOTER
    // SPARK MAX
    public static final int _shooterRightMotorID = 6;
    public static final int _shooterLeftMotorID = 2;

    // WINCH / CLIMB
    // SPARK MAX
    public static final int _winchLeftMotor = 3;
    public static final int _winchRightMotor = 5;
    // TALON
    public static final int _slideMotor = 12;

    // AUTOINDEXER
    // DIO PORTS
    public static final int[][] sonars = new int[][] { { 0, 1 }, { 2, 3 } };

    // BLINKIN LED Driver for the Neopixel
    public static final int blinkinNeopixel = 1;

    public static final Port navxPort = SerialPort.Port.kMXP;
}