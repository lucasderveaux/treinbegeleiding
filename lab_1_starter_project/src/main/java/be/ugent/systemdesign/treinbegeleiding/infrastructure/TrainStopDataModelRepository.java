package be.ugent.systemdesign.treinbegeleiding.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrainStopDataModelRepository extends JpaRepository<TrainStopDataModel, Integer> {
   List<TrainStopDataModel> findAllByTrain(TrainDataModel t);
}