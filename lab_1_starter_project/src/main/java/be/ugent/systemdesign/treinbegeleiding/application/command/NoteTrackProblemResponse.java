package be.ugent.systemdesign.treinbegeleiding.application.command;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import be.ugent.systemdesign.treinbegeleiding.application.Response;
import be.ugent.systemdesign.treinbegeleiding.application.ResponseStatus;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class NoteTrackProblemResponse extends Response {

    private String station;
    private Integer track;

    public NoteTrackProblemResponse(ResponseStatus status,String message, String station, Integer track) {
        super(status, message);
        this.station = station;
        this.track = track;
    }

}