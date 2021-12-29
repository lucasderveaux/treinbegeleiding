package be.ugent.systemdesign.treinbegeleiding.application.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TrainDeparturePermission {
    private Integer trainStopId;
    private Integer trainId;
}
