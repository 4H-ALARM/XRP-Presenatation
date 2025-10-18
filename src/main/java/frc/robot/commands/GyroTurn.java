package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.XRPDrivetrain;
import edu.wpi.first.wpilibj.xrp.XRPGyro;


public class GyroTurn extends Command {
    private final XRPDrivetrain drive;
    private final XRPGyro gyro = new XRPGyro();
    private final PIDController controller;
    private final double targetAngle;

    public GyroTurn(XRPDrivetrain sDrivetrain, double angle) {
        this.drive = sDrivetrain;
        this.targetAngle = angle;
        controller = new PIDController(0.03, 0.0, 0.002);
        addRequirements(drive);
    }

    @Override
    public void initialize() {
        gyro.reset();
        controller.setSetpoint(targetAngle);
    }

    @Override
    public void execute() {
        double output = controller.calculate(gyro.getAngle());
        drive.arcadeDrive(0, output); // Rotate in place
    }

    @Override
    public void end(boolean interrupted) {
        drive.arcadeDrive(0, 0);
    }

    @Override
    public boolean isFinished() {
        return Math.abs(targetAngle - gyro.getAngle()) < 2.0; // within 2 degrees
    }
}