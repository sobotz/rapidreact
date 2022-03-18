package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.SerializerConstants;


public class SerializerSubsystem extends SubsystemBase {
  /**
   * Creates a new Serializer.
   */
  //private DigitalInput intakeSensor;
  //private DigitalInput launcherSensor;
  WPI_TalonSRX serializerMotor;

  // Initializes the sensors in the serializer and launcher
  private SensorSubsystem sensors;

  // Initializes variables that wiil be used in the program
  public boolean runSerializer;
  //public boolean lastSerializerVal = false; // previous launcher sensor value
  public boolean interrupted;

  public SerializerSubsystem(SensorSubsystem sensors) {
    
    // instantiates sensor values with respect to the contants method

    this.serializerMotor = new WPI_TalonSRX(SerializerConstants.SERIALIZER_MOTOR);
    serializerMotor.configFactoryDefault();
    this.sensors = sensors;
    runSerializer = false;

    interrupted = false;
    
  }
  
  
  // Called every time the Command Scheduler runs (every 20 miliseconds)
  public void periodic() {
    /*if(!interrupted){
      if (lastSerializerVal && sensors.getSerializerVal()) {
        runSerializer = true;
      } 
      if (sensors.getLauncherVal()) {
        runSerializer = false;
      }
      // ? basic if statement, Before ? is boolean condition, after ? before : run if true, after : run if false
      serializerMotor.set(ControlMode.PercentOutput, (runSerializer )? -SerializerConstants.SERIALIZER_SPEED : 0);
      lastSerializerVal = sensors.getIntakeVal();
    }*/
    //CHANGE
    if (sensors.getLauncherVal()){
      if (sensors.getIntakeVal() && !sensors.getSerializerVal() ){
        runBelt();
      }
    }
    if (!sensors.getLauncherVal()){
      if (sensors.getIntakeVal() && !sensors.getLauncherVal()){
        runBelt();
      }  
    }

  }
  
  public boolean ToggleInterrupt(){
    interrupted = !interrupted;
    return interrupted;
  }

  public void runBelt() {
    // turns serializer motor on
    serializerMotor.set(ControlMode.PercentOutput, -SerializerConstants.SERIALIZER_SPEED);
  }

  public void stopBelt(){
    serializerMotor.set(ControlMode.PercentOutput, 0);
  }

  public void reverseBelt() {
    serializerMotor.set(ControlMode.PercentOutput, SerializerConstants.SERIALIZER_SPEED);
  }
  
  public void runSerializer(double speed){
    serializerMotor.set(ControlMode.PercentOutput, -speed * SerializerConstants.SERIALIZER_SPEED);
  }

}