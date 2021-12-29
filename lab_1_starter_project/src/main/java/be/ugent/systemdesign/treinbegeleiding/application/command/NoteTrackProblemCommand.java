package be.ugent.systemdesign.treinbegeleiding.application.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NoteTrackProblemCommand {
    private String station;
    private Integer track;
}