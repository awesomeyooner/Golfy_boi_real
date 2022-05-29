// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  shooter Shooter = new shooter();
  //driveTrain drivetrain = new driveTrain();
  //vision Vision = new vision();
  //gyro Gyro = new gyro();
  Joystick controller = new Joystick(0);
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
    //SmartDashboard.putNumber("topSpeed", Shooter.topSpeed);
    //SmartDashboard.putNumber("botSpeed", Shooter.botSpeed);
    Shooter.increment = .05;
    SmartDashboard.putNumber("t0p kF", Shooter.topConfig.slot0.kF);
    SmartDashboard.putNumber("b0t kF", Shooter.botConfig.slot0.kF);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    SmartDashboard.putNumber("topFW speed", Shooter.topSpeed);
    SmartDashboard.putNumber("botFW speed", Shooter.botSpeed);
    SmartDashboard.putNumber("topFW RPM", Shooter.getTopRPM());
    SmartDashboard.putNumber("botFW RPM", Shooter.getBotRPM());
    SmartDashboard.putNumber("topFW Amps", Shooter.getTopAmp());
    SmartDashboard.putNumber("botFW Amps", Shooter.getBotAmp());

    Shooter.topConfig.slot0.kF = Shooter.getTop_kF();

    //SmartDashboard.putNumber("top Error", Shooter.topSpeed - Shooter.topFW.getSelectedSensorVelocity());
    //SmartDashboard.putNumber("bot Error", Shooter.botSpeed - Shooter.botFW.getSelectedSensorVelocity());

    if(controller.getRawButtonPressed(1))
      Shooter.adjustSpeed(0, Shooter.increment);
    if(controller.getRawButtonPressed(2))
      Shooter.adjustSpeed(0, -Shooter.increment);
    if(controller.getRawButtonPressed(3))
      Shooter.adjustSpeed(Shooter.increment, 0);
    if(controller.getRawButtonPressed(4))
      Shooter.adjustSpeed(-Shooter.increment, 0);
    
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select between different
   * autonomous modes using the dashboard. The sendable chooser code works with the Java
   * SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the chooser code and
   * uncomment the getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to the switch structure
   * below with additional strings. If using the SendableChooser make sure to add them to the
   * chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }
  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {

    //drivetrain.setMotor(controller.getRawAxis(1), controller.getRawAxis(4));
    
    if(controller.getRawButton(5))
    Shooter.setShooter();
    else if(controller.getRawButton(5) == false)
    Shooter.setShooterOFF();
  }


  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {}

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {}

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
}