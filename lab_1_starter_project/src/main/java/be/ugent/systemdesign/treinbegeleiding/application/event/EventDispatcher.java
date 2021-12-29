package be.ugent.systemdesign.treinbegeleiding.application.event;

import be.ugent.systemdesign.treinbegeleiding.domain.TrainIsReadyEvent;
import org.springframework.stereotype.Service;

@Service
public interface EventDispatcher {
    void publishTrainIsReadyEvent(TrainIsReadyEvent event);
}
