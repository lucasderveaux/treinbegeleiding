package be.ugent.systemdesign.treinbegeleiding.domain.seedwork;

import be.ugent.systemdesign.treinbegeleiding.domain.Train;
import be.ugent.systemdesign.treinbegeleiding.domain.TrainStatus;
import lombok.Getter;

import java.time.LocalDateTime;

public abstract class DomainEvent {
    @Getter
    private final LocalDateTime createdTime;

    public DomainEvent(){
        this.createdTime = LocalDateTime.now();

    }
}
