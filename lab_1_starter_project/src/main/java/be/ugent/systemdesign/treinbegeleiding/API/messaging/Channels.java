package be.ugent.systemdesign.treinbegeleiding.API.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface Channels {
    static final String NOTE_TRACK_DEFECT_REQUEST="note_track_defect_request";
    static final String NOTE_TRAIN_DEFECT_REQUEST="note_train_defect_request";
    static final String SECURAIL_HELP_REQUEST="securail_help_request";

    static final String NOTE_TRACK_DEFECT_RESPONSE="note_track_defect_response";
    static final String NOTE_TRAIN_DEFECT_RESPONSE="note_train_defect_response";
    static final String SECURAIL_HELP_RESPONSE="securail_help_response";

    static final String TRAIN_IS_READY_EVENT="train_is_ready_event";

    static final String TRAIN_DEPARTURE_PERMISSION = "train_departure_permission";

    @Output(NOTE_TRACK_DEFECT_REQUEST)
    MessageChannel noteTrackDefectRequest();

    @Output(NOTE_TRAIN_DEFECT_REQUEST)
    MessageChannel noteTrainDefectRequest();

    @Output(SECURAIL_HELP_REQUEST)
    MessageChannel securailHelpRequest();

    @Input(NOTE_TRACK_DEFECT_RESPONSE)
    SubscribableChannel noteTrackDefectResponse();

    @Input(NOTE_TRAIN_DEFECT_RESPONSE)
    SubscribableChannel noteTrainDefectResponse();

    @Input(SECURAIL_HELP_RESPONSE)
    SubscribableChannel securailHelpResponse();

    @Output(TRAIN_IS_READY_EVENT)
    MessageChannel trainIsReadyEvent();

    @Input(TRAIN_DEPARTURE_PERMISSION)
    MessageChannel trainDeparturePermission();

}
