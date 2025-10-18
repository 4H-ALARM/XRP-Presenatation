package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.XRPDrivetrain;

public class DriveForward extends Command {
    private final XRPDrivetrain drive;

    public DriveForward(XRPDrivetrain subsystem) {
        drive = subsystem;
        addRequirements(drive);
    }

    @Override
    public void execute() {
        drive.arcadeDrive(0.5, 0.0); // Forward at half speed
    }

    @Override
    public void end(boolean interrupted) {
        drive.arcadeDrive(0, 0);
    }

    @Override
    public boolean isFinished() {
        return false; // Runs until stopped
    }
}