package be.ugent.systemdesign.treinbegeleiding.domain;

import be.ugent.systemdesign.treinbegeleiding.domain.seedwork.DomainEvent;
import lombok.Getter;

@Getter
public class TrainIsReadyEvent extends DomainEvent {

    private Integer trainId;
    private Integer trainStopId;
    private String status;

    public TrainIsReadyEvent(Integer trainId, Integer trainStopId, String status){
        super();
        this.trainId=trainId;
        this.trainStopId=trainStopId;
        this.status=status;
    }
}
