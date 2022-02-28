// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean
 * constants. This class should not be used for any other purpose. All constants
 * should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public final class DriveConstants {
        public static final int RIGHT_FRONT_TALON = 4;
        public static final int LEFT_FRONT_TALON = 2;
        public static final int RIGHT_BACK_TALON = 3;
        public static final int LEFT_BACK_TALON = 1;

        public static final int GEAR_SHIFT_DEPLOY = 1;
        public static final int GEAR_SHIFT_RETRACT = 0;

        // Sets the sensitivity of the joystick, higher constant = more gradual
        // acceleration
        // TO REVERT TO LINEAR ACCELERATION SET TO 1
        public static final double ACCELERATION_CONSTANT = 2;
    }

		public static final int GEAR_SHIFT_DEPLOY = 1;
		public static final int GEAR_SHIFT_RETRACT = 0;

        // Sets the sensitivity of the joystick, higher constant = more gradual acceleration
        // TO REVERT TO LINEAR ACCELERATION SET TO 1
        public static final double ACCELERATION_CONSTANT = 2;

        // Configuration Constants
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

        public static final double kP = 0.5;
        public static final double kI = 0;
        public static final double kD = 0;
        public static final double kF = 0.034875;
}

