// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
// https://github.com/thordogzaan/Frc-2021-Falcon-500-Code/blob/main/Robot.java 
// Motion Magic: https://docs.ctre-phoenix.com/en/stable/ch16_ClosedLoop.html 

package frc.robot.auto;

import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.Constants;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

public class PathB11 extends CommandBase {
  private final DriveSubsystem m_drive;

  private boolean isFinished = false;

  private Timer timer;

  private WPI_TalonFX[] left = new WPI_TalonFX[2];
  private WPI_TalonFX[] right = new WPI_TalonFX[2];

   // Motor IDs (from Phoenix Tuner)
   private int LEFT1_ID = 2;
   private int LEFT2_ID = 3;
 
   private int RIGHT1_ID = 12;
   private int RIGHT2_ID = 13;

  public PathB11(DriveSubsystem drive) {
    this.m_drive = drive;
    this.timer = new Timer();
    // initialize launcher, serializer + intake variables when import

    addRequirements(this.m_drive);
  }

  public void straight(double distance, double speed)
  {
    speed = speed * -1;
    left[0].setSelectedSensorPosition(0);
    right[0].setSelectedSensorPosition(0);
    System.out.println("encoder val"+right[1].getSelectedSensorPosition());
    distance = distance *11900 + right[1].getSelectedSensorPosition();
    System.out.println("distance   "+distance);
    while(distance > right[1].getSelectedSensorPosition())
    {
      m_drive.drive(-0.5, 0);
      System.out.println("encoder val"+right[0].getSelectedSensorPosition());
    }
    m_drive.drive(0,0) ;
  }

  private WPI_TalonFX makeTalonFX(int id, boolean invert) { // Creates and configures a TalonFX
    WPI_TalonFX talon = new WPI_TalonFX(id);

    talon.configFactoryDefault();
    talon.setInverted(invert);
    talon.stopMotor();

    return talon;
  }
  
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    this.timer.start();
    left[0] = makeTalonFX(LEFT1_ID, false);
    left[1] = makeTalonFX(LEFT2_ID, false);

    right[0] = makeTalonFX(RIGHT1_ID, false);
    right[1] = makeTalonFX(RIGHT2_ID, false);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    /*
     * auto pseudo
     * move 40 3/8 in forward;
     * start intake;
     * turn turret; move back ____;
     * start launcher; start rollers;
     * run belts;
     * ( possibly collect another ball after shooting done?)
     */
    // motor turn & distance
    // use encoders
    // https://docs.wpilib.org/en/stable/docs/software/hardware-apis/sensors/encoders-software.html
    // Configures the encoder's distance-per-pulse
    // The robot moves forward 4 foot per encoder rotation
    // There are 256 pulses per encoder rotation ( i would replace with specifics of
    // our encoder which has 2048 counts per revolution = 512 ppr)
    // 6380 RPM
    // Another idea is instead of using encoders, call the drive method in drive
    // subsytem for a specific amount of time
    // Need to use FMS to choose multiple autonomous paths
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    this.m_drive.drive(0, 0);
    this.timer.stop();
    this.timer.reset();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return this.isFinished;
  }
}
