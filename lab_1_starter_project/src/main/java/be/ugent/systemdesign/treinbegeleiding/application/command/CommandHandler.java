package be.ugent.systemdesign.treinbegeleiding.application.command;

import be.ugent.systemdesign.treinbegeleiding.application.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandHandler {
    @Autowired
    TrainService trainService;

    public void trainCanLeave(TrainDeparturePermission permission){
        trainService.trainCanLeave(permission.getTrainId(),permission.getTrainStopId());
    }

}
