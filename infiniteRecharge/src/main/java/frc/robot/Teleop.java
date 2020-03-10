package frc.robot;

import com.revrobotics.CANEncoder;

import frc.robot.models.DriveAdjust;
import frc.robot.outputs.motion.OI;
import frc.robot.outputs.motion.PID;
import frc.robot.outputs.vision.Limelight;
import frc.robot.systems.drivetrain.ArcadeDrive;
import frc.robot.systems.subsystems.*;

public class Teleop
{
    private Shooter _Shooter;
    private Feeder _Feeder;
    private Collector _Collector;
    private Hopper _Hopper;
    private ArcadeDrive _Drivetrain;
    private CANEncoder _ShootingEncoder;
    private OI _DriveController;
    private OI _OperatorController;
    private Limelight _Limelight;

    public Teleop(Shooter shooter, Feeder feeder, Collector collector, Hopper hopper, ArcadeDrive drivetrain, CANEncoder shootingencoder, OI drivecontroller, OI operatorcontroller)
    {
        _Shooter = shooter;
        _Feeder = feeder;
        _Collector = collector;
        _Hopper = hopper;
        _Drivetrain = drivetrain;
        _ShootingEncoder = shootingencoder;
        _DriveController = drivecontroller;
        _OperatorController = operatorcontroller;
        _DriveController = new OI(0);
        _OperatorController = new OI(1);
        _Shooter.ResetShooter();
        _Feeder.Feed(0.0);
    }

    public void RunTeleop()
    {
        //driver control
        _Drivetrain.Drive(_DriveController);

        //hopper control
        if (_OperatorController.dpad() == 180.0)
        {
        _Hopper.ReverseHopper();
        }
        else if (_OperatorController.dpad() == 90.0)
        {
        _Hopper.Hop();
        }
        else
        {
        _Hopper.StopMotors();
        }

        //collector control
        if (_DriveController.RButton())
        {      
        _Collector.Collect();    
        }
        else if (_DriveController.LButton())
        {
        _Collector.ReverseCollector();
        }
        else 
        {
        _Collector.StopMotor();
        }

        //feeder control
        if (_OperatorController.getY())
        {
        _Feeder.Feed(-0.4);
        }
        else if (_OperatorController.getA())
        {
        _Feeder.Feed(0.4);
        }
        else if (_OperatorController.getB())
        {
        _Feeder.Feed(-0.2);
        }
        else
        {
        _Feeder.StopMotor();
        }

        if (_OperatorController.getDriveRightTrigger() > 0)
        {
            if (!_OperatorController.RButton()) _Shooter.Shoot(.28); else if (_OperatorController.RButton()) _Shooter.Shoot(.32);
                //init line shot (10 foot)
                if (_ShootingEncoder.getVelocity() > 1250 && _ShootingEncoder.getVelocity() < 1500 && !_OperatorController.RButton())
                {
                    _Shooter.StopMotors();
                    _Feeder.Feed(.4);
                }
                //trench shot (18 foot)
                else if (_ShootingEncoder.getVelocity() > 1645 && _OperatorController.RButton())
                {
                    _Shooter.StopMotors();
                    _Feeder.Feed(.4);
                }
                else if (!_OperatorController.getA() || !_OperatorController.getY())
                {
                    _Feeder.Feed(0.0);
                }
        }
        else
        {
            _Shooter.Shoot(.2);
        }

        if (_DriveController.getDriveLeftTrigger() == 1 && (Math.abs(_DriveController.getDriveLeftY()) == 0) && (Math.abs(_DriveController.getDriveRightX()) == 0))
        {
            _Shooter.Shoot(.31);
            DriveAdjust driveAdjust = PID.CalculateDrive(-_Limelight.GetTx(), _Limelight.GetTy());
            _Drivetrain.Drive(driveAdjust);
        }
    }
}