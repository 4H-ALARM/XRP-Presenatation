package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.xrp.XRPServo;

public class ArmSubsystem extends SubsystemBase {
    private final XRPServo servo = new XRPServo(2); // Channel 2

    public void moveArm(double angle) {
      servo.setAngle(angle);
    }

    public double getArm() {
      return servo.getAngle();
    }

}