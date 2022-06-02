package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatorCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class shooter {
    Conversion conversion = new Conversion();
    public enum ShooterState{
        REV,
        OFF
    }

    ShooterState shooterstate = ShooterState.OFF;

    // you should generally change all variables that you dont want to access outside of the class into private
    private TalonFX topFW;
    private TalonFX botFW;
    private TalonFXConfiguration topConfig;
    private TalonFXConfiguration botConfig;

    private double topSpeed;
    private double botSpeed;

    double increment = .05;

    

    public shooter(){

        topFW = new TalonFX(1);
        botFW = new TalonFX(2);
        
        topConfig = new TalonFXConfiguration();
        botConfig = new TalonFXConfiguration();

        //you really only need kP and kF for flywheel
        topConfig.slot0.kP = 0;
        topConfig.slot0.kF = .01;

        botConfig.slot0.kP = 0;
        botConfig.slot0.kF = .01;
        
        topFW.configAllSettings(topConfig);
        botFW.configAllSettings(botConfig);

        topFW.setNeutralMode(NeutralMode.Coast);
        topFW.setNeutralMode(NeutralMode.Coast);

        //compensate to less than 12V on the comp robot its 10V
        topFW.configVoltageCompSaturation(12);
        botFW.configVoltageCompSaturation(12);
    
        topFW.enableVoltageCompensation(true);
        botFW.enableVoltageCompensation(true);

        //topFW.configStatorCurrentLimit(new StatorCurrentLimitConfiguration(true, 60, 70, 1));
        //botFW.configStatorCurrentLimit(new StatorCurrentLimitConfiguration(true, 60, 70, 1));

        topFW.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
        botFW.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);

        topFW.setInverted(true);
        botFW.setInverted(true);

        topFW.configVelocityMeasurementWindow(1, 1);
        botFW.configVelocityMeasurementWindow(1, 1);

    }

    public void update(){
        ShooterState snapShooterState; 
        snapShooterState = shooterstate;
        switch(snapShooterState){
            case REV:
                setShooter();
                break;
            case OFF:
                setShooterOFF();
                break;
        }
    }

    public void adjustSpeed(double topAdjust, double botAdjust){
        topSpeed += topAdjust;
        botSpeed += botAdjust;
    }

    public void setShooter(){
        topFW.set(ControlMode.PercentOutput, topSpeed);
        botFW.set(ControlMode.PercentOutput, botSpeed);
    }

    public void setShooterOFF(){
        topFW.set(ControlMode.PercentOutput, 0);
        botFW.set(ControlMode.PercentOutput, 0);
    }

    private double getTopAmp(){
        return topFW.getStatorCurrent();
    }

    private double getBotAmp(){
        return botFW.getStatorCurrent();
    }

    private double getTopRPM(){
        return (topFW.getSelectedSensorVelocity() * 600) / 2048;
    }

    private double getBotRPM(){
        return (botFW.getSelectedSensorVelocity() * 600) / 2048;
    }

    public void configFeedbackVals(double kP, double kF){
        topFW.config_kP(0, kP);
        botFW.config_kP(0, kP);

        topFW.config_kF(0, kF);
        botFW.config_kF(0, kF);
    }

    //make a method to log data instead of dumping it all in robot and then put it in a periodic fn
    public void logData(){
        SmartDashboard.putNumber("topFW speed", topSpeed);
        SmartDashboard.putNumber("botFW speed", botSpeed);
        SmartDashboard.putNumber("topFW RPM", getTopRPM());
        SmartDashboard.putNumber("botFW RPM", getBotRPM());
        SmartDashboard.putNumber("topFW Amps", getTopAmp());
        SmartDashboard.putNumber("botFW Amps", getBotAmp());
        SmartDashboard.putNumber("top velocity", topFW.getSelectedSensorVelocity());
        SmartDashboard.putNumber("bot velocity", botFW.getSelectedSensorVelocity());
        SmartDashboard.putNumber("topFW temp C", topFW.getTemperature());
        SmartDashboard.putNumber("botFW temp C", botFW.getTemperature());
    }
    public double getTop_kF(){
        return SmartDashboard.getNumber("t0p kF", .01);
    }

    public double getBot_kF(){
        return SmartDashboard.getNumber("b0t kF", .01);
    }
}
