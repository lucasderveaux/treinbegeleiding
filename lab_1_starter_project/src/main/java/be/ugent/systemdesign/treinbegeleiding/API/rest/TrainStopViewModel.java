package be.ugent.systemdesign.treinbegeleiding.API.rest;

import be.ugent.systemdesign.treinbegeleiding.application.query.TrainStopReadModel;


public class TrainStopViewModel {
    private String trainStopId;
    private String Station;
    private String ArrivalTime;
    private String DepartureTime;
    private String actualDepartureTime;

    public TrainStopViewModel(TrainStopReadModel tsrm){
        this.trainStopId = tsrm.getTrainStopId().toString();
        this.Station = tsrm.getStation();
        this.ArrivalTime = tsrm.getArrivalTime().toString();
        this.DepartureTime = tsrm.getDepartureTime().toString();
        this.actualDepartureTime=tsrm.getActualDepartureTime().toString();
    }
}
