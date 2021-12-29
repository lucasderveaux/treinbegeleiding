package be.ugent.systemdesign.treinbegeleiding.application.command;

import org.springframework.stereotype.Service;

@Service
public interface CommandDispatcher {
    void noteTrackProblemCommand(NoteTrackProblemCommand command);
    void noteTrainProblemCommand(NoteTrainProblemCommand command);
    void securailHelpCommand(SecurailHelpCommand command);
}
