package be.ugent.systemdesign.treinbegeleiding.API.messaging;

import be.ugent.systemdesign.treinbegeleiding.application.command.CommandHandler;
import be.ugent.systemdesign.treinbegeleiding.application.command.TrainDeparturePermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
public class MessageInputGateway {

    @Autowired
    CommandHandler commandHandler;

    @StreamListener(Channels.TRAIN_DEPARTURE_PERMISSION)
    public void receiveTrainDeparturePemission(TrainDeparturePermission permission){
        commandHandler.trainCanLeave(permission);
    }
}
