package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonFX;

public class intake {
    TalonFX feeder;
    
    public intake(){
        feeder = new TalonFX(20);
        
    }
}
