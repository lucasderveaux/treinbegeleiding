package be.ugent.systemdesign.treinbegeleiding.application;

import be.ugent.systemdesign.treinbegeleiding.application.command.TrainDeparturePermissionResponse;
import be.ugent.systemdesign.treinbegeleiding.domain.TrainStatus;
import be.ugent.systemdesign.treinbegeleiding.domain.TrainStop;

import java.time.LocalTime;
import java.util.List;

public interface TrainService {
    Response addTrain(Integer trainId);
    Response signalSecurail(Integer trainId, Integer trainStopId);
    Response signalTrainDefect(Integer trainId, Integer trainStopId);
    Response signalTrackDefect(Integer trainId,Integer trainStopId);
    Response cancelTrain(Integer trainId,Integer trainStop);
    Response trainIsReady(Integer trainId, Integer trainStop);
    TrainDeparturePermissionResponse trainCanLeave(Integer trainId, Integer trainStopId);

    //voorlopig doe ik het één per één toevoegen cuz I'm not sure
    Response addTrainStop(Integer trainStopId, Integer trainId, String _station, Integer _track, LocalTime _arrivalTime, LocalTime _departureTime);

    //change Trainstops????
    //deleteTrain
    //DeleteTrainStops?
}
