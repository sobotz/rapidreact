// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.ClimbSubsystem;

/** An example command that uses an example subsystem. */
public class LiftCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final ClimbSubsystem m_climbSubsystem;

  private Joystick m_joystick;
 // private final RobotContainer m_operatorJoystick;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public LiftCommand(ClimbSubsystem climbTrain, Joystick joystick) {
    m_climbSubsystem = climbTrain;
    m_joystick = joystick;
    addRequirements(m_climbSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_climbSubsystem.armLock();
    new WaitCommand(0.2);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(m_joystick.getRawButton(8))
      m_climbSubsystem.liftExtend();
    if(m_joystick.getRawButton(9))
      m_climbSubsystem.liftRetract();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_climbSubsystem.liftStop();
    new WaitCommand(0.2);
    m_climbSubsystem.armLock();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
  public void dodo(){
    int i = 0;
  }
}
