package frc.robot.outputs.drive;

import frc.robot.models.enums.RestMode;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.SpeedController;

public class Talon implements IMotor {
    private WPI_TalonSRX _talon;

    /**
     * Wrap the instantation of TalonSRX with it's CAN Id. At present we are
     * resetting it by default. If you don't want to reset it, use Talon(int
     * motorId, boolean doReset) instead where doReset = false
     */
    public Talon(int canId) {
        this(canId, true);
    }

    /**
     * Wrap the instantation of TalonSRX with it's CAN Id. If you don't want to
     * reset it to default configs, set doReset = false
     */
    public Talon(int motorId, boolean doReset) {
        _talon = new WPI_TalonSRX(motorId);
        if (!doReset)
            _talon.configFactoryDefault();
    }

    public void SetIdleMode(RestMode restMode) {
        if (restMode == RestMode.Coast)
            _talon.setNeutralMode(NeutralMode.Coast);
        if (restMode == RestMode.Brake)
            _talon.setNeutralMode(NeutralMode.Brake);
    }

    public void IsInverted(boolean isInverted) {
        _talon.setInverted(isInverted);
    }

    public void Reset() {
        _talon.configFactoryDefault();
    }

    /** Sets power in percen mode valid values between -1.0 and 1.0 */
    public void SetPower(double power) {
        _talon.set(ControlMode.PercentOutput, power);
    }

    public  SpeedController GetSpeedController() {
        return _talon;
    }
}