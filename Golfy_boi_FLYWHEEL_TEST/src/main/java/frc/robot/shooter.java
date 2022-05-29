package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatorCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import edu.wpi.first.math.MathUtil;

public class shooter {

    // you should generally change all variables that you dont want to access outside of the class into private
    private TalonFX topFW;
    private TalonFX botFW;
    private TalonFXConfiguration topConfig;
    private TalonFXConfiguration botConfig;

    private double topSpeed;
    private double botSpeed;

    private double increment;

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

        topFW.configStatorCurrentLimit(new StatorCurrentLimitConfiguration(true, 60, 70, 1));
        botFW.configStatorCurrentLimit(new StatorCurrentLimitConfiguration(true, 60, 70, 1));

        topFW.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
        botFW.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);

        topFW.setInverted(true);
        botFW.setInverted(true);

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

    public double getTopAmp(){
        return topFW.getStatorCurrent();
    }

    public double getBotAmp(){
        return botFW.getStatorCurrent();
    }

    public double getTopRPM(){
        return (topFW.getSelectedSensorVelocity() * 600) / 2048;
    }

    public double getBotRPM(){
        return (botFW.getSelectedSensorVelocity() * 600) / 2048;
    }

    public void configFeedbackVals(double kP, double kF){
        topConfig.slot0.kP = kP;
        botConfig.slot0.kP = kP;

        topConfig.slot0.kF = kF;
        botConfig.slot0.kF = kF;
    }
}
