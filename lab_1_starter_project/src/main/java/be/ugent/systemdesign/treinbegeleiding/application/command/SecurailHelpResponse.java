package be.ugent.systemdesign.treinbegeleiding.application.command;

import be.ugent.systemdesign.treinbegeleiding.application.Response;
import be.ugent.systemdesign.treinbegeleiding.application.ResponseStatus;
import be.ugent.systemdesign.treinbegeleiding.domain.TrainStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class SecurailHelpResponse extends Response {
    private String station;
    private Integer trainId;

    public SecurailHelpResponse(String message, ResponseStatus status, Integer trainId, String station){
        super(status,message);
        this.station=station;
        this.trainId=trainId;
    }
}
