package be.ugent.systemdesign.treinbegeleiding.API.rest;

import be.ugent.systemdesign.treinbegeleiding.application.query.TrainReadModel;
import lombok.Getter;

@Getter
public class TrainViewModel {
    private String trainId;
    private String trainStops;
    private String status;

    public TrainViewModel(TrainReadModel t){
        this.trainId = t.getTrainId().toString();
        trainStops = createTrainStops(t);
        this.status = t.getStatus();
    }

    private String createTrainStops(TrainReadModel t){
        StringBuilder b = new StringBuilder();
        t.getTrainStops().stream().forEach(tst->{
            b.append("[");
            b.append(tst.toString());
            b.append("]");
        });

        return b.toString();
    }
}
