package be.ugent.systemdesign.treinbegeleiding.infrastructure;

import be.ugent.systemdesign.treinbegeleiding.application.query.TrainQuery;
import be.ugent.systemdesign.treinbegeleiding.application.query.TrainReadModel;
import be.ugent.systemdesign.treinbegeleiding.application.query.TrainStopReadModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainQueryImpl implements TrainQuery {
    @Autowired
    TrainDataModelRepository trainDMRepo;

    @Autowired
    TrainStopDataModelRepository traindStopDMRepo;


    @Override
    public TrainReadModel findOne(Integer _trainId) {
        TrainDataModel t = trainDMRepo.findById(_trainId).orElseThrow(TrainNotFoundException::new);
        return new TrainReadModel(_trainId,t.getStatus(),t.getTrainStops().stream().map(tst -> mapToTrainStopReadModel(tst)).collect(Collectors.toList()));
    }

    @Override
    public List<TrainStopReadModel> findAllTrainStopsWithTrainId(Integer _trainId) {
        TrainDataModel t = trainDMRepo.findById(_trainId).orElseThrow(TrainNotFoundException::new);
        return traindStopDMRepo.findAllByTrain(t).stream()
                .map(tst -> mapToTrainStopReadModel(tst))
                .collect(Collectors.toList());
    }

    @Override
    public List<TrainReadModel> findAll() {
        return trainDMRepo.findAll().stream().map(tdm->mapToTrainReadModel(tdm)).collect(Collectors.toList());
    }

    private TrainStopReadModel mapToTrainStopReadModel(TrainStopDataModel tst){
        return new TrainStopReadModel(tst.getTrainStopId(),tst.getStation(),tst.getArrivalTime(),tst.getDepartureTime(),tst.getActualDepartureTime());
    }

    private TrainReadModel mapToTrainReadModel(TrainDataModel tdm){
        return new TrainReadModel(tdm.getTrainId(),tdm.getStatus(),findAllTrainStopsWithTrainId(tdm.getTrainId()));
    }



}
