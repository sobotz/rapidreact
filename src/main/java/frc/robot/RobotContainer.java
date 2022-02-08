// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.ClimbCommand;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.ShiftGearCommand;
import frc.robot.subsystems.ClimbSubsystem;
import frc.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

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
 
  private final ShiftGearCommand m_shiftGearCommand;

  public static Joystick m_driverJoystick;

  public static Joystick m_operatorJoystick;

  public static ClimbCommand m_climbCommand;

  public static ClimbSubsystem m_climbSubsystem;

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings

    this.m_driverJoystick = new Joystick(0);
    this.m_operatorJoystick = new Joystick(1);
    this.m_drivetrain = new DriveSubsystem();
    
    this.m_driveCommand = new DriveCommand(this.m_drivetrain, this.m_driverJoystick);

    this.m_shiftGearCommand = new ShiftGearCommand(this.m_drivetrain);

    this.configureButtonBindings();

    this.m_climbCommand = new ClimbCommand(this.m_climbSubsystem, this.m_operatorJoystick);
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    JoystickButton gearShiftButton = new JoystickButton(this.m_driverJoystick, 1);
    gearShiftButton.whenPressed(this.m_shiftGearCommand);

    // random button
    JoystickButton rotateMotorButton = new JoystickButton(this.m_operatorJoystick, 9);
    rotateMotorButton.whenPressed(this.m_climbCommand);

    JoystickButton liftMotorButton = new JoystickButton(this.m_operatorJoystick, 8);
    liftMotorButton.whenPressed(this.m_climbCommand);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getTeleopCommand() {
    // Use differential drive
    return this.m_driveCommand;
  }
}
