// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.RobotContainer;
import frc.robot.Constants.ClimbConstants;
import frc.robot.Constants.DriveConstants;
import frc.robot.subsystems.ClimbSubsystem;
import frc.robot.subsystems.DriveSubsystem;

import java.security.acl.Group;

import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/** An example command that uses an example subsystem. */
public class ClimbCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final ClimbSubsystem m_climbSubsystem;
  private int butnum;
 // private final RobotContainer m_operatorJoystick;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public ClimbCommand(ClimbSubsystem climbTrain, Joystick joystick) {
    m_climbSubsystem = climbTrain;
    butnum = joystick.getPort();
    addRequirements(m_climbSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if(butnum == 9)
      m_climbSubsystem.rotateClockwise();
    else if(butnum == 10)
      m_climbSubsystem.rotateCounterclockwise();
    else if(butnum == 8)
      m_climbSubsystem.liftExtend();
     else if(butnum == 7)
      m_climbSubsystem.liftRetract();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(butnum == 9 || butnum == 10)
      m_climbSubsystem.rotateStop();
      else
      m_climbSubsystem.liftStop();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    
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
