package be.ugent.systemdesign.treinbegeleiding.infrastructure;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import be.ugent.systemdesign.treinbegeleiding.domain.Train;
import be.ugent.systemdesign.treinbegeleiding.domain.TrainStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Repository;

import be.ugent.systemdesign.treinbegeleiding.domain.TrainRepository;
import be.ugent.systemdesign.treinbegeleiding.domain.TrainStop;



@Repository
public class TrainRepositoryImpl implements TrainRepository {

    @Autowired
    TrainDataModelRepository trainDMRepo;

    @Autowired
    TrainStopDataModelRepository trainStopDMRepo;

    @Autowired
    ApplicationEventPublisher eventPublisher;

    @Override
    public Train findOne(Integer id) {
        TrainDataModel TrainDTM =  trainDMRepo.findById(id).orElseThrow(TrainNotFoundException::new);
        return mapToTrain(TrainDTM);
    }

    @Override
    public void saveTrain(Train _train) {
        TrainDataModel dataModel = mapToTrainModel(_train);
        trainDMRepo.save(dataModel);
        if(_train.getDomainEventList().size()!=0) {
            _train.getDomainEventList().forEach(domainEvent -> {
                eventPublisher.publishEvent(domainEvent);
            });
            _train.clearList();
        }
    }

    @Override
    public void saveTrainStop(TrainStop _trainstop){
        TrainStopDataModel tsdtm = mapToTrainStopModel(_trainstop);
        trainStopDMRepo.save(tsdtm);
    }

    @Override
    public TrainStop findTrainStop(Integer id) {
        TrainStopDataModel dataModel = trainStopDMRepo.findById(id).orElseThrow(TrainStopNotFoundException::new);
        return mapToTrainStop(dataModel);
    }

    @Override
    public List<TrainStop> findAllTrainStopsWithTrainId(Integer id) {
        TrainDataModel t =  trainDMRepo.findById(id).orElseThrow(TrainNotFoundException::new);
        return trainStopDMRepo.findAllByTrain(t).stream().map(tst->mapToTrainStop(tst)).collect(Collectors.toList());
    }

    @Override
    public List<Train> findByStatus(TrainStatus status) {
        return trainDMRepo.findAllByStatus(status.toString()).stream().map(tdm -> mapToTrain(tdm)).collect(Collectors.toList());
    }

    private TrainDataModel findTrainDataModel(Integer id){
        return trainDMRepo.findById(id).orElseThrow(TrainNotFoundException::new);
    }

    private TrainDataModel mapToTrainModel(Train train) {
        if(train.getTrainStops() != null){
            return new TrainDataModel(train.getTrainId(), train.getStatus(), train.getReady(),train.getTrainStops().stream().map(elt -> mapToTrainStopModel(elt)).collect(Collectors.toList()));
        }
        return new TrainDataModel(train.getTrainId(), train.getStatus(),train.getReady());
    }

    private TrainStopDataModel mapToTrainStopModel(TrainStop trainStop) {
        return new TrainStopDataModel(trainStop.getTrainStopId(), findTrainDataModel(trainStop.getTrainId()), trainStop.getStation(),trainStop.getTrack(), trainStop.getArrivalTime(), trainStop.getDepartureTime(),trainStop.getActualDepartureTime());
    }

    private Train mapToTrain(TrainDataModel _p) {
        Train p = Train.builder()
                .trainId(_p.getTrainId())
                .trainStops(_p.getTrainStops().stream().map(elt -> mapToTrainStop(elt)).collect(Collectors.toList()))
                .status(TrainStatus.valueOf(_p.getStatus()))
                .build();

        return p;
    }

    private TrainStop mapToTrainStop(TrainStopDataModel _p) {
        TrainStop p = TrainStop.builder()
                .trainStopId(_p.getTrainStopId())
                .track(_p.getTrack())
                .trainId(_p.getTrain().getTrainId())
                .arrivalTime((_p.getArrivalTime()))
                .departureTime(_p.getDepartureTime())
                .station(_p.getStation())
                .actualDepartureTime(_p.getActualDepartureTime())
                .build();

        return p;
    }


}
