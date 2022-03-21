// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;


import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.LauncherConstants;
import frc.robot.subsystems.ColorSensorSubsystem;
import frc.robot.subsystems.LauncherSubsystem;
import frc.robot.subsystems.SensorSubsystem;
import frc.robot.subsystems.SerializerSubsystem;

public class ActivateLauncherCommand extends CommandBase {
  private SerializerSubsystem serializer;
  private LauncherSubsystem launcher;
  private ColorSensorSubsystem colorSensor;
  private boolean shootInTarget;
  private Timer timer;
  private SensorSubsystem sensors;


  private int targetVelocity;

  /**
   * Creates a new LaunchAllCommand.
   */
  public ActivateLauncherCommand(SerializerSubsystem serializer1, LauncherSubsystem launcher1, SensorSubsystem sensors) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.serializer = serializer1;
    this.launcher = launcher1;
    this.targetVelocity = LauncherConstants.TEAM_VELOCITY;
    //sensors = new SensorSubsystem();
    colorSensor = new ColorSensorSubsystem(sensors);
    shootInTarget = colorSensor.shootCorrectly();

    addRequirements(serializer, launcher);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    this.launcher.startLauncher(targetVelocity);
    /**if(shootInTarget){
      /*targetVelocity*
    }
    else{
      this.launcher.startLauncher(1);
    }*/
    
   /*if(shootInTarget){
      this.launcher.startLauncher(2);/*targetVelocity*
    }
    else{
      this.launcher.startLauncher(1);
    }*/
    serializer.getCommandMode();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //this.targetVelocity = (colorSensor.allyBall()) ? (LauncherConstants.TEAM_VELOCITY) : LauncherConstants.ENEMY_VELOCITY;
    if (launcher.getVelocity() > targetVelocity - 200 && launcher.getVelocity() < targetVelocity + 200) {
      this.serializer.runBelt();
    } else {
      this.serializer.stopBelt();
    }
    /*
    if(shootInTarget){
      this.launcher.startLauncher(2);/*targetVelocity
    }
    else{
      this.launcher.startLauncher(.5);
    }
    launcher.stopLauncher();
    timer.delay(2);
    serializer.runBelt();
    */
  }
  
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    this.launcher.stopLauncher();
    this.serializer.stopBelt();
    serializer.getSerializerMode();
  }

}

