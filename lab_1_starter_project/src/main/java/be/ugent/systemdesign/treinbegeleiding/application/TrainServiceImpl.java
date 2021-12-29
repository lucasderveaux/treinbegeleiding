package be.ugent.systemdesign.treinbegeleiding.application;

import be.ugent.systemdesign.treinbegeleiding.API.messaging.MessageOutputGateway;
import be.ugent.systemdesign.treinbegeleiding.application.command.*;
import be.ugent.systemdesign.treinbegeleiding.domain.Train;
import be.ugent.systemdesign.treinbegeleiding.domain.TrainRepository;
import be.ugent.systemdesign.treinbegeleiding.domain.TrainStatus;
import be.ugent.systemdesign.treinbegeleiding.domain.TrainStop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalTime;
import java.util.List;

@Transactional
@Service
public class TrainServiceImpl  implements TrainService {
    @Autowired
    TrainRepository trainRepo;

    @Autowired
    CommandDispatcher commandDispatcher;

    @Override
    public Response addTrain(Integer trainId) {
       try{
            Train t = new Train(trainId);
            trainRepo.saveTrain(t);

        }catch(RuntimeException e){
            return new Response(ResponseStatus.FAIL,
                    "Train couldn't be added");
        }
        return new Response(ResponseStatus.SUCCESS,"train is added with id:"+trainId);
    }

    @Override
    public Response signalSecurail(Integer trainId, Integer trainStopId) {
        try{
            Train t = trainRepo.findOne(trainId);
            TrainStop tst = trainRepo.findTrainStop(trainStopId);
            commandDispatcher.securailHelpCommand(new SecurailHelpCommand(tst.getStation(),trainStopId));
            t.signalSecurail(trainStopId);
            trainRepo.saveTrain(t);

        }catch(RuntimeException e){
            return new Response(ResponseStatus.FAIL,"couldn't signal securail on train: "+trainId);
        }
        return new Response(ResponseStatus.SUCCESS,"signal of securail on train: "+trainId+" succeeded");
    }

    @Override
    public Response signalTrainDefect(Integer trainId, Integer trainStopId) {
        try{
            Train t = trainRepo.findOne(trainId);
            commandDispatcher.noteTrainProblemCommand(new NoteTrainProblemCommand(trainId));
            t.signalTrainDefect(trainStopId);
            trainRepo.saveTrain(t);

        }catch(RuntimeException e){
            return new Response(ResponseStatus.FAIL,"couldn't signal traindefect: "+trainId);
        }
        return new Response(ResponseStatus.SUCCESS,"signal of traindefect: "+trainId+" succeeded");
    }

    @Override
    public Response signalTrackDefect(Integer trainId, Integer trainStopId) {
        try{
            Train t = trainRepo.findOne(trainId);
            TrainStop tst = trainRepo.findTrainStop(trainStopId);
            commandDispatcher.noteTrackProblemCommand(new NoteTrackProblemCommand(tst.getStation(),tst.getTrack()));
            t.signalTrackDefect(trainStopId);
            trainRepo.saveTrain(t);

        }catch(RuntimeException e){
            return new Response(ResponseStatus.FAIL,"couldn't signal trackdefect: "+trainId);
        }
        return new Response(ResponseStatus.SUCCESS,"signal of trackdefect: "+trainId+" succeeded");
    }

    @Override
    public Response cancelTrain(Integer trainId,Integer trainStopId) {
        try{
            Train t = trainRepo.findOne(trainId);
            t.cancelTrain(trainStopId);
            trainRepo.saveTrain(t);
        }catch(RuntimeException e){
            return new Response(ResponseStatus.FAIL,"couldn't cancel train: "+trainId);
        }
        return new Response(ResponseStatus.SUCCESS,"cancellation of train: "+trainId+" succeeded");
    }

    @Override
    public Response trainIsReady(Integer trainId, Integer trainStop) {
        try{
            Train t = trainRepo.findOne(trainId);
            t.trainIsReady(trainStop);
            trainRepo.saveTrain(t);
        }catch (RuntimeException e){
            return new Response(ResponseStatus.FAIL,"couldn't signal train to be ready: "+trainId);
        }
        return new Response(ResponseStatus.SUCCESS,"train: "+trainId+" is ready");
    }

    @Override
    public TrainDeparturePermissionResponse trainCanLeave(Integer trainId, Integer trainStopId) {
        try{
            Train t = trainRepo.findOne(trainId);
            TrainStop tst = trainRepo.findTrainStop(trainStopId);
            tst.setActualDepartureTime(LocalTime.now());
            t.trainCanLeave(tst);
            trainRepo.saveTrainStop(tst);
            trainRepo.saveTrain(t);
        }catch(RuntimeException e){
            return new TrainDeparturePermissionResponse(ResponseStatus.FAIL,"train: "+trainId+" couldn't leave",trainId,trainStopId);
        }
        return new TrainDeparturePermissionResponse(ResponseStatus.SUCCESS,"train: "+trainId+" couldn leave",trainId,trainStopId);
    }

    @Override
    public Response addTrainStop(Integer trainStopId, Integer trainId, String _station, Integer _track, LocalTime _arrivalTime, LocalTime _departureTime) {
       try{
            Train t = trainRepo.findOne(trainId);
            TrainStop tst = new TrainStop(trainStopId,trainId,_station,_track,_arrivalTime,_departureTime);
            t.addTrainStop(tst);

            trainRepo.saveTrainStop(tst);
            trainRepo.saveTrain(t);
        }catch(RuntimeException e){
           return new Response(ResponseStatus.FAIL,"trainstop couldn't be added with id:"+trainStopId);
       }
       return new Response(ResponseStatus.SUCCESS,"trainstop added with id: "+trainStopId);
    }
}
