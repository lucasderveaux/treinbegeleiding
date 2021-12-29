package be.ugent.systemdesign.treinbegeleiding.domain;

import java.util.List;

public interface TrainRepository {

	public Train findOne(Integer id);
	public void saveTrain(Train _train);
	public void saveTrainStop(TrainStop _trainStop);
	public TrainStop findTrainStop(Integer id);
	public List<TrainStop> findAllTrainStopsWithTrainId(Integer id);
	public List<Train> findByStatus(TrainStatus status);
}
