// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;

import frc.robot.auto.*;

import frc.robot.commands.DriveCommand;
import frc.robot.commands.LaunchSerializerCommand;
import frc.robot.commands.ReverseSerializerCommand;
import frc.robot.commands.DeployIntakeCommand;
import frc.robot.commands.ShiftGearCommand;
import frc.robot.subsystems.ColorSensorSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.LauncherSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.SerializerSubsystem;



import frc.robot.commands.ActivateLauncherCommand;
import frc.robot.commands.ColorSensorCommand;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;

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
  private ColorSensorSubsystem m_colorSensor;
  
  private final DriveCommand m_driveCommand;
  private final ShiftGearCommand m_shiftGearCommand;

  public static DeployIntakeCommand deployIntakeCommand;
  private final LaunchSerializerCommand m_launchSerializer;
  private final ReverseSerializerCommand reverseSerializerCommand;
  private final ActivateLauncherCommand launchCommand;
  private ColorSensorCommand colorSensorCommand;


  private final PathBR01 m_pathbr01;
  private final PathBR02 m_pathbr02;
  private final PathBR11 m_pathbr11;
  private final PathBR41 m_pathbr41;
  private final PathBR61 m_pathbr61;

  SendableChooser<Command> m_chooser = new SendableChooser<>();


  public static Joystick m_driverJoystick;
  public Joystick m_operatorJoystick;


  /** The container for the robot
   * ++. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings


    this.m_driverJoystick = new Joystick(0);
    m_operatorJoystick = new Joystick(1);
    
    this.m_drivetrain = new DriveSubsystem();
    this.m_intake = new IntakeSubsystem();
    this.m_serializer = new SerializerSubsystem();
    this.m_launcher = new LauncherSubsystem();
    this.m_colorSensor = new ColorSensorSubsystem();
    
    //this.m_autocommand = new AutoCommand(this.m_drivetrain);
    this.m_driveCommand = new DriveCommand(this.m_drivetrain, this.m_driverJoystick);

    this.m_shiftGearCommand = new ShiftGearCommand(this.m_drivetrain);
   
    this.m_pathbr01 = new PathBR01(this.m_drivetrain);
    this.m_pathbr02 = new PathBR02(this.m_drivetrain,this.m_intake, this.m_launcher, this.m_serializer);
    this.m_pathbr11 = new PathBR11(this.m_drivetrain,this.m_intake, this.m_launcher, this.m_serializer);
    this.m_pathbr41 = new PathBR41(this.m_drivetrain,this.m_intake, this.m_launcher, this.m_serializer);
    this.m_pathbr61 = new PathBR61(this.m_drivetrain,this.m_intake, this.m_launcher, this.m_serializer);

    m_chooser.setDefaultOption("Path BR01", m_pathbr01); // https://docs.wpilib.org/en/stable/docs/software/dashboards/smartdashboard/choosing-an-autonomous-program-from-smartdashboard.html
    m_chooser.addOption("Path BR02", m_pathbr02);
    m_chooser.addOption("Path BR11", m_pathbr11);
    m_chooser.addOption("Path BR41", m_pathbr41);
    m_chooser.addOption("Path BR61", m_pathbr61);

    SmartDashboard.putData("Auto Mode:" , m_chooser);

    deployIntakeCommand = new DeployIntakeCommand(this.m_intake, this.m_serializer);  
    m_launchSerializer = new LaunchSerializerCommand(this.m_serializer);
    reverseSerializerCommand = new ReverseSerializerCommand(this.m_intake, this.m_serializer);
    launchCommand = new ActivateLauncherCommand(this.m_serializer, this.m_launcher);
    colorSensorCommand = new ColorSensorCommand();
 
   

    
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
    JoystickButton DeployIntakeButton = new JoystickButton(m_operatorJoystick, 4);
    JoystickButton serializerButton = new JoystickButton(this.m_operatorJoystick, 3);
    JoystickButton reverseSerializerButton = new JoystickButton(m_operatorJoystick,2);
    JoystickButton launchButton = new JoystickButton(m_operatorJoystick,6);
    JoystickButton gearShiftButton = new JoystickButton(this.m_driverJoystick, 1);
    JoystickButton switchTeamColor = new JoystickButton(m_operatorJoystick, 1);

    gearShiftButton.whenPressed(this.m_shiftGearCommand);
    DeployIntakeButton.whenHeld(deployIntakeCommand);

    
    serializerButton.whenHeld(this.m_launchSerializer);
    reverseSerializerButton.whenHeld(reverseSerializerCommand);
    launchButton.whenHeld(launchCommand);=
    switchTeamColor.whenPressed(colorSensorCommand);
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

  public Command getAutonomousCommand() {
    return m_chooser.getSelected();
  }
}
