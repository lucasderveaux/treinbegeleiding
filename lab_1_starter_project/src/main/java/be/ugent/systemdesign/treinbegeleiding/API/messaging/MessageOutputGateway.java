package be.ugent.systemdesign.treinbegeleiding.API.messaging;

import be.ugent.systemdesign.treinbegeleiding.application.command.CommandDispatcher;
import be.ugent.systemdesign.treinbegeleiding.application.command.NoteTrackProblemCommand;
import be.ugent.systemdesign.treinbegeleiding.application.command.NoteTrainProblemCommand;
import be.ugent.systemdesign.treinbegeleiding.application.command.SecurailHelpCommand;
import be.ugent.systemdesign.treinbegeleiding.application.event.EventDispatcher;
import be.ugent.systemdesign.treinbegeleiding.domain.TrainIsReadyEvent;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface MessageOutputGateway extends CommandDispatcher, EventDispatcher {
    @Gateway(requestChannel = Channels.SECURAIL_HELP_REQUEST)
    void securailHelpCommand(SecurailHelpCommand command);

    @Gateway(requestChannel = Channels.NOTE_TRAIN_DEFECT_REQUEST)
    void noteTrainProblemCommand(NoteTrainProblemCommand command);

    @Gateway(requestChannel = Channels.NOTE_TRACK_DEFECT_REQUEST)
    void noteTrackProblemCommand(NoteTrackProblemCommand command);

    @Gateway(requestChannel = Channels.TRAIN_IS_READY_EVENT)
    void publishTrainIsReadyEvent(TrainIsReadyEvent event);
}
