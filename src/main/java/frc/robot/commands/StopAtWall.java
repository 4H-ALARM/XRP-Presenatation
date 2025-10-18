package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.XRPDrivetrain;
import frc.robot.subsystems.DistanceSubsystem;

public class StopAtWall extends Command {
    private final XRPDrivetrain drive;
    private final DistanceSubsystem sensor;

    public StopAtWall(XRPDrivetrain drive, DistanceSubsystem sensor) {
        this.drive = drive;
        this.sensor = sensor;
        addRequirements(drive, sensor);
    }

    @Override
    public void execute() {
        drive.arcadeDrive(0.4, 0);
    }

    @Override
    public void end(boolean interrupted) {
        drive.arcadeDrive(0, 0);
    }

    @Override
    public boolean isFinished() {
        return sensor.getDistanceIn() < 5.0; // Stop within 10 in of obstacle
    }
}