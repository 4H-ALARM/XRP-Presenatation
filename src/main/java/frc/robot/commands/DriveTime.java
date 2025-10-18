package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.XRPDrivetrain;
import edu.wpi.first.wpilibj.Timer;

public class DriveTime extends Command {
    private final XRPDrivetrain drive;
    private final double speed;
    private final double duration;
    private final Timer timer = new Timer();

    public DriveTime(XRPDrivetrain subsystem, double speed, double duration) {
        this.drive = subsystem;
        this.speed = speed;
        this.duration = duration;
        addRequirements(drive);
    }

    @Override
    public void initialize() {
        timer.reset();
        timer.start();
    }

    @Override
    public void execute() {
        drive.arcadeDrive(speed, 0);
    }

    @Override
    public void end(boolean interrupted) {
        drive.arcadeDrive(0, 0);
        timer.stop();
    }

    @Override
    public boolean isFinished() {
        return timer.hasElapsed(duration);
    }
}