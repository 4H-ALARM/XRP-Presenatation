package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.xrp.XRPRangefinder;

public class DistanceSubsystem extends SubsystemBase {
    private final XRPRangefinder distanceSensor = new XRPRangefinder();

    public double getDistanceIn() {
        return distanceSensor.getDistanceInches();
    }
}