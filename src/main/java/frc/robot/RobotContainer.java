// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.


package frc.robot;


//Controllers
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;

import frc.robot.commands.AquireTargetCommand;

import frc.robot.subsystems.VisionSubsystem;

//


//Subsystem
import frc.robot.subsystems.DriveSubsystem;

import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.SerializerSubsystem;
import frc.robot.subsystems.LauncherSubsystem;
import frc.robot.subsystems.SensorSubsystem;
// import frc.robot.subsystems.ColorSensorSubsystem;
import frc.robot.subsystems.ClimbSubsystem;
//





//Commands

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import frc.robot.commands.DriveCommand;
import frc.robot.commands.ShiftGearCommand;

import frc.robot.commands.DeployIntakeCommand;
import frc.robot.commands.LaunchSerializerCommand;
import frc.robot.commands.ReverseSerializerCommand;
import frc.robot.commands.RunAllCommand;
import frc.robot.commands.ActivateLauncherCommand;
import frc.robot.commands.AquireTargetCommand;

import frc.robot.commands.LiftCommand;
import frc.robot.commands.LiftRetractCommand;
import frc.robot.commands.LowLaunchCommand;
import frc.robot.commands.ArmReleaseCommand;
import frc.robot.commands.CoastCommand;

//


//SmartDashBoard
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
//


//Lonely
import frc.robot.auto.*;


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...


  //Subsystem
  private final DriveSubsystem m_drivetrain;

  private final VisionSubsystem m_vision;

  public final ClimbSubsystem m_climbSubsystem;

  private final IntakeSubsystem m_intake;
  private SerializerSubsystem m_serializer;
  private LauncherSubsystem m_launcher;

  private ColorSensorSubsystem m_colorSensor;
  private SensorSubsystem m_sensor;


  //

  //Commands
  private final DriveCommand m_driveCommand;
  private final ShiftGearCommand m_shiftGearCommand;


  private final AquireTargetCommand m_visionCommand;

  public static DeployIntakeCommand deployIntakeCommand;
  private final LaunchSerializerCommand m_launchSerializer;
  private final ReverseSerializerCommand reverseSerializerCommand;
  private final ActivateLauncherCommand launchCommand;
  private final LowLaunchCommand lowLaunchCommand;

  public final LiftCommand m_liftCommand;
  public final ArmReleaseCommand m_armReleaseCommand;
  public final LiftRetractCommand m_liftRetractCommand;

  public final CoastCommand m_coastCommand;
  
  private final RunAllCommand runAllCommand;  
  //



  //Auto
  private final PathBR01 m_pathbr01;
  private final PathBR02 m_pathbr02;
  private final PathBR03 m_pathbr03;
  private final PathBR11 m_pathbr11;
  private final PathBR12 m_pathbr12;
  private final PathBR13 m_pathbr13;
  private final PathBR21 m_pathbr21;
  private final PathBR22 m_pathbr22; 
  SendableChooser<Command> m_chooser = new SendableChooser<>();
  


  public static Joystick m_driverJoystick;

  public Joystick m_operatorJoystick;



  


  /** The container for the robot
   * ++. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings


    this.m_driverJoystick = new Joystick(0);

    this.m_operatorJoystick = new Joystick(1);
    
    this.m_vision = new VisionSubsystem();


    



    //Commands
    //this.m_autocommand = new AutoCommand(this.m_drivetrain);
    
    //Subsystems


 

    this.m_drivetrain = new DriveSubsystem();
    m_sensor = new SensorSubsystem();
    this.m_intake = new IntakeSubsystem(m_sensor);
    this.m_serializer = new SerializerSubsystem(m_sensor, m_intake);

    

    
    this.m_launcher = new LauncherSubsystem();

    this.m_colorSensor = new ColorSensorSubsystem(m_sensor);
    //

    this.m_driveCommand = new DriveCommand(this.m_drivetrain, this.m_driverJoystick);
    this.m_shiftGearCommand = new ShiftGearCommand(this.m_drivetrain);

    this.m_visionCommand = new AquireTargetCommand(this.m_vision);
    deployIntakeCommand = new DeployIntakeCommand(this.m_intake, this.m_serializer);  
    m_launchSerializer = new LaunchSerializerCommand(this.m_serializer, m_sensor);
    reverseSerializerCommand = new ReverseSerializerCommand(m_intake,m_serializer,m_sensor);
    launchCommand = new ActivateLauncherCommand(this.m_serializer, this.m_launcher, this.m_vision);
    lowLaunchCommand = new LowLaunchCommand(this.m_serializer, this.m_launcher, this.m_vision);
    runAllCommand = new RunAllCommand(m_colorSensor,m_launcher,m_serializer);

    this.m_climbSubsystem = new ClimbSubsystem();
	  this.m_liftCommand = new LiftCommand(this.m_climbSubsystem, this.m_operatorJoystick);
    this.m_liftRetractCommand = new LiftRetractCommand(this.m_climbSubsystem, this.m_operatorJoystick);
    this.m_armReleaseCommand = new ArmReleaseCommand(this.m_climbSubsystem);

    this.m_coastCommand = new CoastCommand(this.m_drivetrain);

    this.configureButtonBindings();

    
    //
    


    //Auto
    this.m_pathbr01 = new PathBR01(this.m_drivetrain);
    this.m_pathbr02 = new PathBR02(this.m_drivetrain,this.m_intake, this.m_launcher, this.m_serializer);
    this.m_pathbr03 = new PathBR03(this.m_drivetrain,this.m_intake, this.m_launcher, this.m_serializer);
    this.m_pathbr11 = new PathBR11(this.m_drivetrain,this.m_intake, this.m_launcher, this.m_serializer);
    this.m_pathbr12 = new PathBR12(this.m_drivetrain,this.m_intake, this.m_launcher, this.m_serializer);
    this.m_pathbr13 = new PathBR13(this.m_drivetrain,this.m_intake, this.m_launcher, this.m_serializer);
    this.m_pathbr21 = new PathBR21(this.m_drivetrain,this.m_intake, this.m_launcher, this.m_serializer);
    this.m_pathbr22 = new PathBR22(this.m_drivetrain,this.m_intake, this.m_launcher, this.m_serializer);

   //m_chooser.setDefaultOption("Path BR01", m_pathbr01); // https://docs.wpilib.org/en/stable/docs/software/dashboards/smartdashboard/choosing-an-autonomous-program-from-smartdashboard.html
    m_chooser.setDefaultOption("Path BR01", m_pathbr01);
    m_chooser.addOption("Path BR02", m_pathbr02);
    m_chooser.addOption("Path BR03", m_pathbr03);
    m_chooser.addOption("Path BR11", m_pathbr11);
    m_chooser.addOption("Path BR12", m_pathbr12);
    m_chooser.addOption("Path BR13", m_pathbr13);
    m_chooser.addOption("Path BR21", m_pathbr21);
    m_chooser.addOption("Path BR22", m_pathbr22); 

    SmartDashboard.putData("Auto Mode:" , m_chooser);
    //

    
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
    JoystickButton reverseSerializerButton = new JoystickButton(this.m_operatorJoystick,2);
    JoystickButton launchButton = new JoystickButton(m_operatorJoystick,6);
    JoystickButton lowLaunchButton = new JoystickButton(m_operatorJoystick, 5);
    JoystickButton gearShiftButton = new JoystickButton(this.m_driverJoystick, 6);
    // JoystickButton switchTeamColor = new JoystickButton(m_operatorJoystick, 1);
    // JoystickButton runAllCommandButton = new JoystickButton(this.m_operatorJoystick, 5);

    gearShiftButton.whenPressed(this.m_shiftGearCommand);


    JoystickButton visionButton = new JoystickButton(this.m_operatorJoystick, 1);
    visionButton.whenHeld(this.m_visionCommand);

    DeployIntakeButton.whenHeld(deployIntakeCommand);

    
    serializerButton.whenHeld(this.m_launchSerializer);
    reverseSerializerButton.whenHeld(reverseSerializerCommand);
    launchButton.whenHeld(launchCommand);
    lowLaunchButton.whenHeld(lowLaunchCommand);
    // runAllCommandButton.whenHeld(runAllCommand);

    JoystickButton liftRetractMotorButton = new JoystickButton(this.m_operatorJoystick, 8);
    liftRetractMotorButton.whileHeld(this.m_liftRetractCommand);

    JoystickButton liftExtendMotorButton = new JoystickButton(this.m_operatorJoystick, 7);
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
  public Command getTeleopInitCommand() {
    // Use differential drive
    return this.m_driveCommand;
  }

  public Command getAutonomousCommand() {
    return m_chooser.getSelected();
  }
  public Command getAutonomousEndCommand() {
    return this.m_coastCommand;
  }
}
