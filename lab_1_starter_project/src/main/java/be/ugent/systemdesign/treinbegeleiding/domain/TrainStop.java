package be.ugent.systemdesign.treinbegeleiding.domain;

import be.ugent.systemdesign.treinbegeleiding.domain.seedwork.DomainEvent;
import lombok.*;

import java.time.LocalTime;


@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TrainStop extends DomainEvent {

	private Integer trainStopId;
	private Integer trainId;
	private String station;
	private Integer track;
	private LocalTime arrivalTime;
	private LocalTime departureTime;
	private LocalTime actualDepartureTime;
	
	public TrainStop(Integer trainStopId, Integer trainId, String _station, int _track, LocalTime _arrivalTime, LocalTime _departureTime) {
		super();
		this.trainStopId =trainStopId;
		this.station = _station;
		this.track = _track;
		this.arrivalTime = _arrivalTime;
		this.departureTime = _departureTime;
		this.trainId = trainId;
		this.actualDepartureTime=null;
	}

	@Override
	public String toString(){
		return "TrainStopid: "+trainStopId+"\tstation: "+station+"\tarrivalTime: "+arrivalTime+"\tDepartureTime: "+departureTime+"\n";
	}

}
