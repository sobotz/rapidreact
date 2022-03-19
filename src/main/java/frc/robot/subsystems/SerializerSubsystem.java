package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.SerializerConstants;
import frc.robot.commands.ReverseSerializerCommand;


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
  //private ReverseSerializerCommand reverseSerializer;
  // Initializes variables that wiil be used in the program
  public boolean runSerializer;
  //public boolean lastSerializerVal = false; // previous launcher sensor value
  public boolean interrupted;
  public boolean lastIntakeVal = false;


  public boolean trippedLauncherSensor;

  public SerializerSubsystem(SensorSubsystem sensors, IntakeSubsystem intake) {

    
    // instantiates sensor values with respect to the contants method

    this.serializerMotor = new WPI_TalonSRX(SerializerConstants.SERIALIZER_MOTOR);
    serializerMotor.configFactoryDefault();
    this.sensors = sensors;
    runSerializer = false;
    m_intake = intake;
    interrupted = false;

    //this.reverseSerializer = reverseSerializer1;

    trippedLauncherSensor = false;

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
    /*if (reverseSerializer.isFinished()){
      if (sensors.getIntakeVal()|| (!sensors.getIntakeVal() && lastIntakeVal )){
        //Statments for one ball going in when no balls in serializer
        if (  (sensors.getIntakeVal())  && !sensors.getSerializerVal() && !sensors.getLauncherVal()){
          lastIntakeVal = false;
        }
        else if (  (!sensors.getIntakeVal() && lastIntakeVal)  && !sensors.getSerializerVal()){
          lastIntakeVal = false;
        }
        if (  (!sensors.getIntakeVal() && lastIntakeVal)  && sensors.getSerializerVal() && !sensors.getLauncherVal()){
          
          lastIntakeVal = false;
        }
        //
  
  
        //Statments for 1 ball going in when 1 ball is in serializer currently resting at serializer sensor
        if (  (sensors.getIntakeVal())  && sensors.getSerializerVal() && !sensors.getLauncherVal()){
          
          lastIntakeVal = false;
        }
        else if (  (sensors.getIntakeVal())  && sensors.getLauncherVal()){
          lastIntakeVal = false;
        }
        else if (  (!sensors.getIntakeVal() && lastIntakeVal) && !sensors.getLauncherVal() ){
          lastIntakeVal = false;
        }
        if (  (!sensors.getIntakeVal() && lastIntakeVal)  && sensors.getSerializerVal()){
          lastIntakeVal = false;
          
        }
        if (  (sensors.getIntakeVal())  && sensors.getSerializerVal() && sensors.getLauncherVal()){
          lastIntakeVal = false;
        }
      }
    }*/
    if (sensors.getIntakeVal()|| (!sensors.getIntakeVal() && lastIntakeVal )){
      //Statments for one ball going in when no balls in serializer
      if (  (sensors.getIntakeVal())  && !sensors.getSerializerVal() && !sensors.getLauncherVal()){
        runBelt();
        lastIntakeVal = true;
      }
      else if (  (!sensors.getIntakeVal() && lastIntakeVal)  && !sensors.getSerializerVal()){
        runBelt();
      }
      if (  (!sensors.getIntakeVal() && lastIntakeVal)  && sensors.getSerializerVal() && !sensors.getLauncherVal()){
        stopBelt();
        lastIntakeVal = false;
      }
      //

      /*if(sensors.getLauncherVal()){
          trippedLauncherSensor = true;
      }
      if(!sensors.getLauncherVal()){
        trippedLauncherSensor = false;
      }*/

      //Statments for 1 ball going in when 1 ball is in serializer currently resting at serializer sensor
      if ((sensors.getIntakeVal())  && sensors.getSerializerVal() && !sensors.getLauncherVal()){
        runBelt();
        lastIntakeVal = true;
      }
      else if ((sensors.getIntakeVal())  && sensors.getLauncherVal()){
        stopBelt();
        //change
        lastIntakeVal = false;
      }
      else if ((!sensors.getIntakeVal() && lastIntakeVal) && !sensors.getLauncherVal() ){
        runBelt();
      }

      if ((!sensors.getIntakeVal() && lastIntakeVal)  && sensors.getLauncherVal()){
        stopBelt();
        lastIntakeVal = false;
        m_intake.retractIntake();
      }
      
      if ((sensors.getIntakeVal())  && sensors.getSerializerVal() && sensors.getLauncherVal()){
        stopBelt();
        lastIntakeVal = false;
        m_intake.retractIntake();
      }
      //


    }

    if(sensors.getLauncherVal()){
      trippedLauncherSensor = true;
    }
    if(!sensors.getLauncherVal()){
      trippedLauncherSensor = false;
    }



      //statment run first ball all the way to launcher
    /*if (sensors.getIntakeVal()|| (!sensors.getIntakeVal() && lastIntakeVal )){
    //Statments for one ball going in when no balls in serializer
      if (  (sensors.getIntakeVal())  && !sensors.getSerializerVal()  && !sensors.getLauncherVal()){
        runBelt();
        lastIntakeVal = true;
      }
      else if (  (!sensors.getIntakeVal() && lastIntakeVal)  && !sensors.getSerializerVal() && !sensors.getLauncherVal()){
        runBelt();
      }
      else if (  (sensors.getIntakeVal() && lastIntakeVal)  && sensors.getSerializerVal() && !sensors.getLauncherVal()){
        runBelt();
      }
      if (  (!sensors.getIntakeVal() && lastIntakeVal)  && !sensors.getSerializerVal() && sensors.getLauncherVal()){
        stopBelt();
        lastIntakeVal = false;
      }
        //

        //Statments for 1 ball going in when 1 ball is in serializer currently resting at Launcher sensor 
      if (  (sensors.getIntakeVal())  && !sensors.getSerializerVal() && sensors.getLauncherVal()){
        runBelt();
        lastIntakeVal = true;
      }
      else if (  (!sensors.getIntakeVal() && lastIntakeVal)  && !sensors.getSerializerVal() && sensors.getLauncherVal() ){
        runBelt();
      }
      if (  (!sensors.getIntakeVal() && lastIntakeVal)  && sensors.getSerializerVal()){
        stopBelt();
        lastIntakeVal = false;        }
      }*/
      // I was maybe a little bored
      
    
  }
  
  public boolean ToggleInterrupt(){
    interrupted = !interrupted;
    return interrupted;
  }

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

  public boolean getLauncherSensorVal(){
    return trippedLauncherSensor;
  }


}