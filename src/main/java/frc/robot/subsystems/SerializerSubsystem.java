package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.SerializerConstants;
import frc.robot.commands.ReverseSerializerCommand;
import edu.wpi.first.wpilibj.Timer;


public class SerializerSubsystem extends SubsystemBase {
  /**
   * Creates a new Serializer.
   */
  //private DigitalInput intakeSensor;
  //private DigitalInput launcherSensor;
  WPI_TalonSRX serializerMotor;

  // Initializes the sensors in the serializer and launcher
  private SensorSubsystem sensors;
  private IntakeSubsystem m_intake;

  private Timer timer;
  //private ReverseSerializerCommand reverseSerializer;
  // Initializes variables that wiil be used in the program
  public boolean runSerializer;
  //public boolean lastSerializerVal = false; // previous launcher sensor value
  public boolean interrupted;
  public boolean lastIntakeVal = false;
  public boolean lastSerializerVal = false;

  public boolean trippedLauncherSensor;
  public Boolean launchMode;
  public Boolean willRun;

  public int s0 = 0;
  public int s1 = 0;
  public int s2 = 0;

  public int serializerState = 0;


  public SerializerSubsystem(SensorSubsystem sensors, IntakeSubsystem intake) {

    
    // instantiates sensor values with respect to the contants method

    this.serializerMotor = new WPI_TalonSRX(SerializerConstants.SERIALIZER_MOTOR);
    serializerMotor.configFactoryDefault();
    this.sensors = sensors;
    runSerializer = false;
    m_intake = intake;
    interrupted = false;
    launchMode = false;
    willRun = false;

    trippedLauncherSensor = false;

  }
  
  
  // Called every time the Command Scheduler runs (every 20 miliseconds)
  public void periodic() {
    //STATE MACHINE CODE
    /*

    serializerState = s0 << 2 | s1 << 1 | s0;

    /*
    s0  s1  s2 |  index | Belts | Intake
    0   0   0  |  0     | 0     | X
    0   0   1  |  1     | 0     | X
    0   1   0  |  2     | 1     | X
    0   1   1  |  3     | 0     | 0
    1   0   0  |  4     | 0     | 1
    1   0   1  |  5     | 0     | 1
    1   1   0  |  6     | 1     | 1
    1   1   1  |  7     | 0     | -1

    Operator Interference needs to be checked

    s0 = intake sensor ( 0 is not triggered, 1 is triggered )
    s1 = serializer sensor ( 0 is not triggered, 1 is triggered )
    s2 = launcher sensor ( 0 is not triggered, 1 is triggered )
    */
   
    if (!launchMode) {
      if(!sensors.getLauncherVal()){
        if(!sensors.getIntakeVal() && lastIntakeVal){
          willRun = true;
        }
      } else {
        if (willRun){
          willRun = false;
        }
      }
    } else {
      willRun = false;
    }
    if (willRun) {
      runBelt();
    } else {
      stopBelt();
    }
    lastIntakeVal = sensors.getIntakeVal();
  }

    
     
      
  
  
  /*public boolean ToggleInterrupt(){
    interrupted = !interrupted;
    return interrupted;
  }*/

  public void runBelt() {
    // turns serializer motor on
    serializerMotor.set(ControlMode.PercentOutput, SerializerConstants.SERIALIZER_SPEED);
  }

  public void stopBelt(){
    serializerMotor.set(ControlMode.PercentOutput, 0);
  }

  public void reverseBelt() {
    serializerMotor.set(ControlMode.PercentOutput, -SerializerConstants.SERIALIZER_SPEED);
  }
  
  public void runSerializer(double speed){
    serializerMotor.set(ControlMode.PercentOutput, -speed * SerializerConstants.SERIALIZER_SPEED);
  }
  public void fastBelt(){
    serializerMotor.set(ControlMode.PercentOutput, 1);
  }

  public boolean getLauncherSensorVal(){
    return trippedLauncherSensor;
  }
  public boolean getCommandMode(){
    return launchMode = true;
  }
  public boolean getSerializerMode(){
    return launchMode = false;
  }

}