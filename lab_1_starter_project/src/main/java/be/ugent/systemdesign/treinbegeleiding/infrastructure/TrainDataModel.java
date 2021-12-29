package be.ugent.systemdesign.treinbegeleiding.infrastructure;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import be.ugent.systemdesign.treinbegeleiding.domain.TrainStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name="TRAIN")
public class TrainDataModel {

    @Id
    @Getter
    private Integer trainId;
    private String status;
    private Boolean ready;

    @Setter
    @OneToMany(mappedBy="train", fetch = FetchType.EAGER)
    private List<TrainStopDataModel> trainStops;

    public TrainDataModel(Integer trainId, TrainStatus status,Boolean ready, List<TrainStopDataModel> trainStops) {
        this.trainId = trainId;
        this.status = status.name();
        this.ready = ready;
        this.trainStops = trainStops;
    }

    public TrainDataModel(Integer trainId, TrainStatus status, Boolean ready) {
        this.trainId = trainId;
        this.status = status.name();
        this.ready=ready;
        this.trainStops = new ArrayList<TrainStopDataModel>();
    }
}
