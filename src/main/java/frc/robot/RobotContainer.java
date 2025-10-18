// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.Arm0;
import frc.robot.commands.Arm90;
import frc.robot.commands.DriveForward;
import frc.robot.commands.DriveTime;
import frc.robot.commands.StopAtWall;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.DistanceSubsystem;
import frc.robot.subsystems.XRPDrivetrain;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a`
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final XRPDrivetrain m_xrpDrivetrain = new XRPDrivetrain();
  private final ArmSubsystem armSubsystem = new ArmSubsystem();
  private final DistanceSubsystem distanceSubsystem = new DistanceSubsystem();

  private final CommandXboxController controller = new CommandXboxController(0);

  private final DriveForward driveForward = new DriveForward(m_xrpDrivetrain);

  private final DriveTime driveTime = new DriveTime(m_xrpDrivetrain, 0.5, 2);

  private final Arm0 arm0 = new Arm0(armSubsystem);
  private final Arm90 arm90 = new Arm90(armSubsystem);
  private final StopAtWall stopAtWall= new StopAtWall(m_xrpDrivetrain, distanceSubsystem);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link edu.wpi.first.wpilibj.GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    m_xrpDrivetrain.setDefaultCommand(Commands.run(() ->m_xrpDrivetrain.arcadeDrive(controller.getLeftY(), controller.getRightX()), m_xrpDrivetrain));
    controller.a().whileTrue(driveForward);
    controller.b().onTrue(driveTime);
    controller.leftBumper().onTrue(arm0);
    controller.leftBumper().onTrue(arm90);
    controller.rightTrigger().onTrue(stopAtWall);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * 
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return new InstantCommand();
  }
}
