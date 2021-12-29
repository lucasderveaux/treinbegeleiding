package be.ugent.systemdesign.treinbegeleiding.application.query;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class TrainReadModel {
    private Integer trainId;
    private String status;
    private List<TrainStopReadModel> trainStops;
}
