package frc.robot.subsystems;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.PWMSparkMax;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;

import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorMatch;

public class ControlPanel {

  private final I2C.Port i2cPort = I2C.Port.kOnboard;

  private final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);

  private final ColorMatch m_colorMatcher = new ColorMatch();

  private PWMSparkMax m_rotationControlMotor;
  private static final int m_rotationControlMotorID = 4;

  private final Color BlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
  private final Color GreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
  private final Color RedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
  private final Color YellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);

  public void PositionControlINIT() {
    m_colorMatcher.addColorMatch(BlueTarget);
    m_colorMatcher.addColorMatch(GreenTarget);
    m_colorMatcher.addColorMatch(RedTarget);
    m_colorMatcher.addColorMatch(YellowTarget);    
  }

  public void PositionControlMAIN() {

    Color detectedColor = m_colorSensor.getColor();

    String robotColor;
    String fieldColor;
    ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);

    if (match.color == BlueTarget) {
      robotColor = "Blue";
      fieldColor = "Red";
    } else if (match.color == RedTarget) {
      robotColor = "Red";
      fieldColor = "Blue";
    } else if (match.color == GreenTarget) {
      robotColor = "Green";
      fieldColor = "Yellow";
    } else if (match.color == YellowTarget) {
      robotColor = "Yellow";
      fieldColor= "Green";
    } else {
      robotColor = "Unknown";
      fieldColor = "Unknown";
    }

    SmartDashboard.putNumber("Red", detectedColor.red);
    SmartDashboard.putNumber("Green", detectedColor.green);
    SmartDashboard.putNumber("Blue", detectedColor.blue);
    SmartDashboard.putNumber("Confidence", match.confidence);
    SmartDashboard.putString("Robot Sensor", robotColor);
    SmartDashboard.putString("Field Sensor", fieldColor);
  }

  public void RotationControlINIT() {
    m_rotationControlMotor = new PWMSparkMax(m_rotationControlMotorID);
    m_colorMatcher.addColorMatch(BlueTarget);
    m_colorMatcher.addColorMatch(GreenTarget);
    m_colorMatcher.addColorMatch(RedTarget);
    m_colorMatcher.addColorMatch(YellowTarget);   
  }
  
  public void RotationControlMAIN(boolean getBoxA) {  //  Pass oi.getBoxA to this
    Color detectedColor = m_colorSensor.getColor();
    Color detectedStartColor = m_colorSensor.getColor();
    ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedStartColor);

    int i = 0;
    m_rotationControlMotor.set(.3);
    while (i < 7) {
      detectedColor = m_colorSensor.getColor();
      match = m_colorMatcher.matchClosestColor(detectedColor);
      if (match.color == detectedStartColor) i++; else if (getBoxA) break;
    }
    m_rotationControlMotor.set(0);
  }
}