// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.ArmReleaseCommand;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.LiftCommand;
import frc.robot.commands.ShiftGearCommand;
import frc.robot.subsystems.ClimbSubsystem;
import frc.robot.subsystems.DriveSubsystem;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DriveSubsystem m_drivetrain;

  public final ClimbSubsystem m_climbSubsystem;
 
  private final DriveCommand m_driveCommand; 
 
  private final ShiftGearCommand m_shiftGearCommand;

  public static Joystick m_driverJoystick;

  public static Joystick m_operatorJoystick;

  public final LiftCommand m_liftCommand;

  public static ArmReleaseCommand m_armReleaseCommand;

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings

    this.m_driverJoystick = new Joystick(0);
    this.m_operatorJoystick = new Joystick(1);
    this.m_drivetrain = new DriveSubsystem();
    this.m_climbSubsystem = new ClimbSubsystem();
    
    this.m_driveCommand = new DriveCommand(this.m_drivetrain, this.m_driverJoystick);

    this.m_shiftGearCommand = new ShiftGearCommand(this.m_drivetrain);

    this.m_liftCommand = new LiftCommand(this.m_climbSubsystem, this.m_operatorJoystick);

    this.m_armReleaseCommand = new ArmReleaseCommand(this.m_climbSubsystem);

    this.configureButtonBindings();
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
    JoystickButton liftRetractMotorButton = new JoystickButton(this.m_operatorJoystick, 7);
    liftRetractMotorButton.whileHeld(this.m_liftCommand);

    JoystickButton liftExtendMotorButton = new JoystickButton(this.m_operatorJoystick, 8);
    liftExtendMotorButton.whileHeld(this.m_liftCommand);

    JoystickButton armReleaseButton = new JoystickButton(this.m_operatorJoystick, 9);
    armReleaseButton.whenPressed(this.m_armReleaseCommand);

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
