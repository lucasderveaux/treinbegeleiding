package be.ugent.systemdesign.treinbegeleiding.domain;

import java.util.ArrayList;
import java.util.List;

import be.ugent.systemdesign.treinbegeleiding.domain.seedwork.AggregateRoot;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Train extends AggregateRoot {
	
	private Integer trainId;
	private TrainStatus status;
	private Boolean ready;

	private List<TrainStop> trainStops;

	public Train(Integer _trainid){
		trainId = _trainid;
		this.trainStops = new ArrayList<TrainStop>();
		this.ready = false;
		status = TrainStatus.ON_TIME;
	}

	public void addTrainStop(TrainStop trainStop){
		if( trainStops!=null) {
			this.trainStops.add(trainStop);
		}else{
			this.trainStops=new ArrayList<TrainStop>();
			this.trainStops.add(trainStop);
		}
	}

	private void delayTrain(){
		changeStatus(TrainStatus.DELAYED);
	}

	public void signalSecurail(Integer trainStopId){
		delayTrain();
	}

	public void signalTrainDefect(Integer trainStopId){
		delayTrain();
	}

	public void signalTrackDefect(Integer trainStopId){
		delayTrain();
	}

	public void cancelTrain(Integer trainStopId){
		changeStatus(TrainStatus.CANCELLED);
	}

	public void trainIsReady(Integer trainStopId){
		this.ready = true;
		addEventToList(new TrainIsReadyEvent(trainId,trainStopId,status.name()));
	}

	private void changeStatus(TrainStatus status){
		this.status = status;
	}

    public void trainCanLeave(TrainStop tst) {
		this.ready=false;
		for(TrainStop trainStop:trainStops){
			if(trainStop.getTrainId()==tst.getTrainId()){
				trainStops.remove(trainStop);
				trainStops.add(tst);
			}
		}
    }
}
