package be.ugent.systemdesign.treinbegeleiding.infrastructure;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalTime;


@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name="TRAIN_STOP")
public class TrainStopDataModel {

    @Id
    @Getter
    private Integer trainStopId;
    private String station;
    private Integer track;
    private LocalTime arrivalTime;
    private LocalTime departureTime;
    private LocalTime actualDepartureTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trainId")
    private TrainDataModel train;

    public TrainStopDataModel(Integer trainStopId, TrainDataModel train, String station, Integer track, LocalTime arrivalTime, LocalTime departureTime,LocalTime actualDepartureTime) {
        this.trainStopId = trainStopId;
        this.train = train;
        this.station = station;
        this.track = track;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.actualDepartureTime=actualDepartureTime;
    }

}
