// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.IntakeSubsystem;


public class DeployIntakeCommand extends InstantCommand {
  private final IntakeSubsystem m_intake;
  /** Creates a new DeployIntakeCommand. */
  
    public DeployIntakeCommand(IntakeSubsystem subsystem) {
      m_intake = subsystem;
      
      // Use addRequirements() here to declare subsystem dependencies.
      addRequirements(m_intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_intake.toggleIntake();
  }

  public void end() {
    m_intake.toggleIntake();
  }

  // Called every time the scheduler runs while the command is scheduled.
  

  
  // Called once the command ends or is interrupted.
 
  

  // Returns true when the command should end.

  
  
}
