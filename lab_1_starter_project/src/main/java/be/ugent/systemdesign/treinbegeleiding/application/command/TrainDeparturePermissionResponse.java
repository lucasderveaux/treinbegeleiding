package be.ugent.systemdesign.treinbegeleiding.application.command;

import be.ugent.systemdesign.treinbegeleiding.application.Response;
import be.ugent.systemdesign.treinbegeleiding.application.ResponseStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrainDeparturePermissionResponse extends Response  {
    private Integer trainStopId;
    private Integer trainId;

    public TrainDeparturePermissionResponse(ResponseStatus status,String message,  Integer trainId,Integer trainStopId){
        super(status,message);
        this.trainId=trainId;
        this.trainStopId = trainStopId;
    }
}
