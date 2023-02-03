// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  
    public final class DriveConstants {

        public static final int RIGHT_FRONT_TALON = 4;
        public static final int LEFT_FRONT_TALON = 2;
        public static final int RIGHT_BACK_TALON = 3;
        public static final int LEFT_BACK_TALON = 1;
    
        public static final double ACCELERATION_CONSTANT = 3;
        public static final double MAX_ROTATION_SPEED = .7;
        public static final double MIN_SPEED = .02;
        public static final double JOY_DEADBAND = .04;
        public static final int GEAR_SHIFT_DEPLOY = 1;

        public static final int GEAR_SHIFT_RETRACT = 0;

        
    }


    public static final class IntakeConstants{
        public static final int INTAKE_MOTOR = 10;
        public static final double MAXIMUM_INTAKE_SPEED = 0.8;
        public static final int INTAKE_SOLENOID_DEPLOY = 2;
        public static final int INTAKE_SOLENOID_RETRACT = 3;
    }


        // Sets the sensitivity of the joystick, higher constant = more gradual
        // acceleration


		

        // Sets the sensitivity of the joystick, higher constant = more gradual acceleration

        // TO REVERT TO LINEAR ACCELERATION SET TO 1
    // public static final double ACCELERATION_CONSTANT = 2;
    public static final class SerializerConstants{
        public static final int SERIALIZER_SENSOR_1 = 0;
        public static final int SERIALIZER_SENSOR_2 = 1;
        public static final int SERIALIZER_SENSOR_3 = 2;
        public static final int SERIALIZER_MOTOR = 8;
        // public static final int FEED_MOTOR = 10;
	    public static final double SERIALIZER_SPEED = 0.3;
    }
    public static final class LauncherConstants {
		public static final int LAUNCHER_MOTOR_1 = 5;
        // ADD PORT WHEN MOTOR IS WIRED
        // Change to 7 when on eletra bot
		public static final int LAUNCHER_MOTOR_2 = 6;
        public static final int ROLLER_MOTOR = 10;
        public static final int TEAM_VELOCITY = -22750;
        public static final int ENEMY_VELOCITY = 10000;
        
    }
    
    public final class ColorSensorConstants{
        public static final double COLOR_THRESHOLD = .35;
    }

    public final class SensorsConstants{
        public static final int SENSORS_THRESHOLD = 1;
    }

    // Configuration Constants
    public static final class AutoConstants{
        /**
         * Which PID slot to pull gains from. Starting 2018, you can choose from
         * 0,1,2 or 3. Only the first two (0,1) are visible in web-based
         * configuration.
         */
        public static final int kSlotIdx = 0;

        /**
         * Talon FX supports multiple (cascaded) PID loops. For
         * now we just want the primary one.
         */
        public static final int kPIDLoopIdx = 0;

        /**
         * set to zero to skip waiting for confirmation, set to nonzero to wait and
         * report to DS if action fails.
         */
        public static final int kTimeoutMs = 30;

        public static final double kP = 0.005; //0.01
        public static final double kI = 0;
        public static final double kD = 0; //100
        public static final double kF = 0.034875/10;

        public static final double autoDrive = 20771.75; // 8050;
    }

    public static final class VisionConstants{
        public static final int ACTUATION_MOTOR = 7;
        public static final int MAX_ROTATION_VALUE = 36000;// at 90 degrees, unit is 150,000; at 
        public static final double MAX_SPEED = .6;
        public static final double LOGISTIC_GROWTH_RATE = .45;
        public static final double MIN_ADJUST_SPEED = .2;
        public static final double DEADBAND_RANGE = 0.05;
        public static final double LIMELIGHT_TO_HUB_HEIGHT = 65.5; //inches
        public static final double LIMEELIGHT_ANGLE = 36;
        public static final double MIN_DISTANCE = 43; //inches
        public static final double MAX_DISTANCE = 79; //inches
        public static final double ALIGNMENT_RANGE = 3;
        public static final double kP = 4;
        public static final double LIMELIGHT_HALF_X_FOV = 29.8;
        public static final double kD = 0;
        public static final double kI = 0;
        public static final double LIMELIGHT_ANGLE = 36;

    }
    public static final class ClimbConstants
    {
        public static final int LIFT_MOTOR = 9;

        public static final double LIFT_SPEED = 0.4;
        public static final int ARM_IN = 4;
        public static final int ARM_OUT = 5;
    }
}



