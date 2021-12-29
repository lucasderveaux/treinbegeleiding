package be.ugent.systemdesign.treinbegeleiding.application.query;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalTime;

@Getter
@AllArgsConstructor
public class TrainStopReadModel {
    private Integer trainStopId;
    private String station;
    private LocalTime ArrivalTime;
    private LocalTime DepartureTime;
    private LocalTime actualDepartureTime;

    public String toString() {
        StringBuilder b = new StringBuilder();
        b.append(trainStopId);
        b.append(" - ");
        b.append(station);
        b.append(" - ");
        b.append(ArrivalTime);
        b.append(" - ");
        b.append(DepartureTime);
        return b.toString();
    }
}
