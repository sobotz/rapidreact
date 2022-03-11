// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;


import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.LauncherConstants;
import frc.robot.subsystems.ColorSensorSubsystem;
import frc.robot.subsystems.LauncherSubsystem;
import frc.robot.subsystems.SerializerSubsystem;

public class ActivateLauncherCommand extends CommandBase {
  private SerializerSubsystem serializer;
  private LauncherSubsystem launcher;
  private ColorSensorSubsystem colorSensor;

  private int nFramesRun;

  /**
   * Creates a new LaunchAllCommand.
   */
  public ActivateLauncherCommand(SerializerSubsystem serializer1, LauncherSubsystem launcher1) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.serializer = serializer1;
    this.launcher = launcher1;

    addRequirements(serializer, launcher);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    this.nFramesRun = 0;
    this.launcher.startLauncher();
    this.serializer.acceptingBalls = false;

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //launcher.get_velocity maybe

    if(colorSensor.getShootOne()){
      if (launcher.getVelocity() > LauncherConstants.TEAM_VELOCITY) {
        /**this.launcher.startRollers();
        this.serializer.runBelt();*/
        if(serializer.checkBallLeft()){
          colorSensor.removeFirstBall();
          SmartDashboard.putBoolean("Shoot", true);
        }
      }
    }
    if(!colorSensor.getShootOne()){
      if(launcher.getVelocity()> LauncherConstants.ENEMY_VELOCITY){
        /**launcher.startRollers();
        this.serializer.runBelt();*/
        if(serializer.checkBallLeft()){
          colorSensor.removeFirstBall();
          SmartDashboard.putBoolean("Shoot", false);
        }
      }

    }

    this.nFramesRun++;
 
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    this.launcher.stopRollers();
    this.launcher.stopLauncher();
    this.serializer.stopBelt();
    this.serializer.acceptingBalls = true;
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
     return this.nFramesRun > 500;
  }

}

