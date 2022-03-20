// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.VisionSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class AquireTargetCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  private final VisionSubsystem m_vision;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public AquireTargetCommand(VisionSubsystem vision) {
    m_vision = vision;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_vision);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_vision.correctX();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_vision.correctX();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_vision.stopMotor();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
