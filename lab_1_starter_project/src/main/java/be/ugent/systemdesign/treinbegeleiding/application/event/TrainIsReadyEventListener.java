package be.ugent.systemdesign.treinbegeleiding.application.event;

import be.ugent.systemdesign.treinbegeleiding.domain.TrainIsReadyEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
public class TrainIsReadyEventListener {
    private static final Logger log = LoggerFactory.getLogger(TrainIsReadyEventListener.class);

    @Autowired
    EventDispatcher eventDispatcher;

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleTranChangeAsync(TrainIsReadyEvent event){
        log.info("handle event async"+event.getTrainId()+event.getTrainStopId()+event.getStatus());
        eventDispatcher.publishTrainIsReadyEvent(event);
    }
}
