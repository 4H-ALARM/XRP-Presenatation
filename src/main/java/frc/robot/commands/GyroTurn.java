package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.XRPDrivetrain;
import edu.wpi.first.wpilibj.xrp.XRPGyro;


public class GyroTurn extends Command {
  private final XRPDrivetrain drivetrain;
  private final XRPGyro gyro = new XRPGyro();
  private final double targetAngle;
  private final PIDController pid = new PIDController(0.02, 0.0, 0.001);

  // Feedforward constants
  private final double kS = 0.15; // static friction voltage
  private final double kV = 0.0;  // optional if you want velocity scaling

  public GyroTurn(XRPDrivetrain drivetrain, double angleDegrees) {
      this.drivetrain = drivetrain;
      this.targetAngle = angleDegrees;
      addRequirements(drivetrain);
      pid.setTolerance(2.0);
  }

  @Override
  public void execute() {
      double error = targetAngle - gyro.getAngle();
      double pidOutput = pid.calculate(gyro.getAngle(), targetAngle);

      // Add static friction feedforward
      double output = pidOutput;
      if (Math.abs(pidOutput) > 0.01) {
          output += Math.copySign(kS, pidOutput);
      }

      drivetrain.arcadeDrive(0, output);
  }

  @Override
  public boolean isFinished() {
      return pid.atSetpoint();
  }

  @Override
  public void end(boolean interrupted) {
      drivetrain.arcadeDrive(0, 0);
  }
}