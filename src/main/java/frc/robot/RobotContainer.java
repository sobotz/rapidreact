// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.auto.*;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.AutoCommand;
import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DriveSubsystem m_drivetrain;

  private final DriveCommand m_driveCommand;

  // private final AutoCommand m_autocommand;
  private final PathB00 m_pathb00;
  private final PathB11 m_pathb11;

  SendableChooser<Command> m_chooser = new SendableChooser<>();

  public static Joystick m_driverJoystick;

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings

    this.m_driverJoystick = new Joystick(0);
    this.m_drivetrain = new DriveSubsystem();
    
    //this.m_autocommand = new AutoCommand(this.m_drivetrain);
    this.m_driveCommand = new DriveCommand(this.m_drivetrain, this.m_driverJoystick);
   
    this.m_pathb00 = new PathB00(this.m_drivetrain);
    this.m_pathb11 = new PathB11(this.m_drivetrain);

    configureButtonBindings();

    m_chooser.setDefaultOption("Path B00", m_pathb00); // https://docs.wpilib.org/en/stable/docs/software/dashboards/smartdashboard/choosing-an-autonomous-program-from-smartdashboard.html
    m_chooser.addOption("Path B11", m_pathb11);

    SmartDashboard.putData(m_chooser);
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {}

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getTeleopCommand() {
    // Use differential drive
    return this.m_driveCommand;
  }
  
  public Command getAutonomousCommand() {
    return m_chooser.getSelected();
  }
}
