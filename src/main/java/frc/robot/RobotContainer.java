// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.DeployIntakeCommand;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.LaunchSerializerCommand;
import frc.robot.commands.ShiftGearCommand;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.SerializerSubsystem;
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


  private final IntakeSubsystem m_intake;


  private final ShiftGearCommand m_shiftGearCommand;

  private final LaunchSerializerCommand m_launchSerializer;

  public static Joystick m_driverJoystick;
  private Joystick m_operatorJoystick;
  
  public static DeployIntakeCommand DeployIntakeCommand;

  public Joystick m_operatorJoystick;


  private final SerializerSubsystem m_serializer;


  /** The container for the robot
   * ++. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings

    this.m_serializer = new SerializerSubsystem();
    this.m_driverJoystick = new Joystick(0);
    this.m_operatorJoystick = new Joystick(1);
    this.m_drivetrain = new DriveSubsystem();
    
    this.m_driveCommand = new DriveCommand(this.m_drivetrain, this.m_driverJoystick);
    this.m_launchSerializer = new LaunchSerializerCommand(this.m_serializer);
    this.m_shiftGearCommand = new ShiftGearCommand(this.m_drivetrain);
    m_intake = new IntakeSubsystem();

    DeployIntakeCommand = new DeployIntakeCommand(m_intake);  
    // m_driveCommand = new DriveCommand(m_drivetrain, m_driverJoystick.getRawAxis(0), m_driverJoystick.getRawAxis(1));
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    JoystickButton DeployIntakeButton = new JoystickButton(m_operatorJoystick, 1);
    
    DeployIntakeButton.whenHeld(DeployIntakeCommand);
    JoystickButton gearShiftButton = new JoystickButton(this.m_driverJoystick, 1);
    gearShiftButton.whenPressed(this.m_shiftGearCommand);
    JoystickButton serializerButton = new JoystickButton(this.m_operatorJoystick, 1);
    serializerButton.whenHeld(this.m_launchSerializer);

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
