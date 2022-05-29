package frc.robot;

import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonPipelineResult;

public class vision {
    PhotonCamera camera;
    PhotonPipelineResult result;
    double cameraHeight;
    double cameraPitch;
    double targetHeight;

    public vision(){
        camera = new PhotonCamera("LifeCam");
        result = camera.getLatestResult();
    }

    public boolean hasTargets(){
        return result.hasTargets();
    }
    public double getPitch(){
        return result.getBestTarget().getPitch();
    }

    public double getYaw(){
        return result.getBestTarget().getYaw();
    }

}
