// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.subsystems.ClimbSubsystem;

/** An example command that uses an example subsystem. */
public class LiftCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final ClimbSubsystem m_climbSubsystem;

  private Joystick m_joystick;

  private Timer timer;

  private WPI_TalonFX liftMotor;

  private double exceeds = 10.0;    //Represents the voltage that it can't exceed. 
  private double count = 0;            //Represents the time that it has exceeded limit.
  private double limitCount = 5.0;      //Represents the amount of time count should be to stop retract
 // private final RobotContainer m_operatorJoystick;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public LiftCommand(ClimbSubsystem climbTrain, Joystick joystick) {
    m_climbSubsystem = climbTrain;
    m_joystick = joystick;
    this.liftMotor = new WPI_TalonFX(Constants.ClimbConstants.LIFT_MOTOR);
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
    if(m_joystick.getRawButton(8)){
      m_climbSubsystem.liftExtend();
    } if(m_joystick.getRawButton(7)){
      m_climbSubsystem.liftRetract();
      double current = this.liftMotor.getStatorCurrent();
      SmartDashboard.putString("Lift current:", String.valueOf(current));
      if(current >= exceeds){
        count = timer.get();
        if(count >= limitCount)
          m_climbSubsystem.liftStop();
      } else {
        timer.reset();
      }
    }
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
    if(i == 100)
      i++;
  }
  //oublic void dodo method written by the oz (very essential to code will break if delte (do not deltet))
}
