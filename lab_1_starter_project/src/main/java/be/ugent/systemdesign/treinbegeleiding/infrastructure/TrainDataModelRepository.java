package be.ugent.systemdesign.treinbegeleiding.infrastructure;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainDataModelRepository extends JpaRepository<TrainDataModel, Integer> {
    List<TrainDataModel> findAllByStatus(String _status);
    /*
    TrainDataModel findOne(Integer _trainId);
    */
}