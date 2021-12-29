package be.ugent.systemdesign.treinbegeleiding.application.command;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import be.ugent.systemdesign.treinbegeleiding.application.Response;
import be.ugent.systemdesign.treinbegeleiding.application.ResponseStatus;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class NoteTrainProblemResponse extends Response {

    private String trainId;

    public NoteTrainProblemResponse(String message, ResponseStatus status, String trainId) {
        super(status, message);
        this.trainId = trainId;
    }
}