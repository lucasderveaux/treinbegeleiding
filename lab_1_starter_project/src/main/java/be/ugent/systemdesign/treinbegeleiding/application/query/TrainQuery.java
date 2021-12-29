package be.ugent.systemdesign.treinbegeleiding.application.query;

import java.util.List;

public interface TrainQuery {

   public TrainReadModel findOne(Integer _trainId);
   public List<TrainStopReadModel> findAllTrainStopsWithTrainId(Integer _trainId);
   public List<TrainReadModel> findAll();

/*
    gelinkt aan status

     */
}
