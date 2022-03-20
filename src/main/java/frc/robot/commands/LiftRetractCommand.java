// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.ClimbSubsystem;
import frc.robot.subsystems.ClimbSubsystem.RetractStateEnum;

/** An example command that uses an example subsystem. */
public class LiftRetractCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final ClimbSubsystem m_climbSubsystem;

  private Joystick m_joystick;

  private Timer timer;

  private double exceeds = 80.0;    //Represents the current that it can't exceed. 
  private double count = 0;            //Represents the time that it has exceeded limit.
  private double limitCount = 0.5;      //Represents the amount of time count should be to stop retract
  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public LiftRetractCommand(ClimbSubsystem climbTrain, Joystick joystick) {
    m_climbSubsystem = climbTrain;
    m_joystick = joystick;
    timer = new Timer();
    addRequirements(m_climbSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_climbSubsystem.armLock();
    new WaitCommand(0.2);
    timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double current = m_climbSubsystem.getCurrent();
    SmartDashboard.putString("Lift current:", String.valueOf(current));
    if(m_climbSubsystem.RetractState != RetractStateEnum.ATLIMIT){
      m_climbSubsystem.liftRetract();
      System.out.println("AAAAAAAAAAAAAA");
     current = m_climbSubsystem.getCurrent();
      SmartDashboard.putString("Lift current:", String.valueOf(current));
      if(current >= exceeds){
        count = timer.get();
        if(count >= limitCount){
          m_climbSubsystem.RetractState = RetractStateEnum.ATLIMIT;
          m_climbSubsystem.liftStop();
        }
      } else {
          timer.reset();
      }
    }
    else
      m_climbSubsystem.liftStop();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_climbSubsystem.liftStop();
    new WaitCommand(0.2);
   m_climbSubsystem.armLock();
   m_climbSubsystem.RetractState = RetractStateEnum.IDLE;
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
  public void dodo(){
    int i = 0;
    if(i == 100)
      i++;
    if(i == 1)
      i--;
      m_joystick.getAxisCount();
  }
  //oublic void dodo method written by the oz (very essential to code will break if delte (do not deltet))
}
