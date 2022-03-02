// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.ActivateLauncherCommand;
import frc.robot.commands.DeployIntakeCommand;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.LaunchSerializerCommand;
import frc.robot.commands.PurgeLauncherCommand;
import frc.robot.commands.ReverseSerializerCommand;
import frc.robot.commands.ShiftGearCommand;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.LauncherSubsystem;
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
  private final IntakeSubsystem m_intake;
  private SerializerSubsystem m_serializer;
  private LauncherSubsystem m_launcher;



  private final DriveCommand m_driveCommand;
  private final ShiftGearCommand m_shiftGearCommand;



  public static DeployIntakeCommand DeployIntakeCommand;
  private final LaunchSerializerCommand m_launchSerializer;
  private final ReverseSerializerCommand reverseSerializerCommand;
  private final ActivateLauncherCommand launchCommand;
  private final PurgeLauncherCommand purgeLaunchCommand;

  


  public static Joystick m_driverJoystick;
  public Joystick m_operatorJoystick;

  



  /** The container for the robot
   * ++. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings

    m_driverJoystick = new Joystick(0);
    m_operatorJoystick = new Joystick(1);
    

    m_drivetrain = new DriveSubsystem();
    m_intake = new IntakeSubsystem();
    m_serializer = new SerializerSubsystem();
    m_launcher = new LauncherSubsystem();
    


    m_driveCommand = new DriveCommand(this.m_drivetrain, this.m_driverJoystick);
    m_shiftGearCommand = new ShiftGearCommand(this.m_drivetrain);

    
  
    DeployIntakeCommand = new DeployIntakeCommand(m_intake, m_serializer);  
    m_launchSerializer = new LaunchSerializerCommand(this.m_serializer);
    reverseSerializerCommand = new ReverseSerializerCommand(m_intake, m_serializer);
    launchCommand = new ActivateLauncherCommand(m_serializer, m_launcher);
    purgeLaunchCommand = new PurgeLauncherCommand(m_serializer,m_launcher);
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
    JoystickButton serializerButton = new JoystickButton(this.m_operatorJoystick, 2);
    JoystickButton reverseSerializerButton = new JoystickButton(m_operatorJoystick,3);
    JoystickButton launchButton = new JoystickButton(m_operatorJoystick,6);
    JoystickButton purgeLaunchButton = new JoystickButton(m_operatorJoystick,5);
    JoystickButton gearShiftButton = new JoystickButton(this.m_driverJoystick, 1);
    
    

    gearShiftButton.whenPressed(this.m_shiftGearCommand);

    DeployIntakeButton.whenHeld(DeployIntakeCommand);
    serializerButton.whenHeld(this.m_launchSerializer);
    reverseSerializerButton.whenHeld(reverseSerializerCommand);
    launchButton.whenHeld(launchCommand);
    purgeLaunchButton.whenHeld(purgeLaunchCommand);
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
