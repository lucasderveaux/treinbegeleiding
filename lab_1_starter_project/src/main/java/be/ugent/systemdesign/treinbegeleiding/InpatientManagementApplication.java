package be.ugent.systemdesign.treinbegeleiding;

import be.ugent.systemdesign.treinbegeleiding.API.messaging.Channels;
import be.ugent.systemdesign.treinbegeleiding.application.Response;
import be.ugent.systemdesign.treinbegeleiding.application.TrainService;
import be.ugent.systemdesign.treinbegeleiding.domain.Train;
import be.ugent.systemdesign.treinbegeleiding.domain.TrainRepository;
import be.ugent.systemdesign.treinbegeleiding.domain.TrainStop;
import be.ugent.systemdesign.treinbegeleiding.infrastructure.TrainDataModelRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@EnableAsync
@EnableBinding(Channels.class)
@SpringBootApplication
public class InpatientManagementApplication {
	
	private static final Logger log = LoggerFactory.getLogger(InpatientManagementApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(InpatientManagementApplication.class, args);
	}

	@Bean
	CommandLineRunner testInpatientDataModelJPARepository(TrainRepository repo) {
		return (args) -> {

			  log.info("$Testing TrainDataModelJPARepository.");

			  log.info(">Find all trains from database.");
			  List<TrainStop> trainStops = repo.findAllTrainStopsWithTrainId(1);
			  logTrainStop(trainStops);
			  //logTrain(train);


			 /*
			  log.info(">Find all inpatients by status {} from database.",
			  InpatientStatus.REGISTERED); List<InpatientDataModel> inpatientsByStatus =
			  repo.findByStatus(InpatientStatus.REGISTERED.toString());
			  logInpatientDataModels(inpatientsByStatus);

			  InpatientDataModel firstInpatient = inpatientsAll.get(0);
			  log.info(">Find inpatients by last name {} and status {} from database.",
			  firstInpatient.getLastName(), firstInpatient.getStatus());
			  List<InpatientDataModel> inpatientsByLastNameAndStatus =
			  repo.findByLastNameAndStatus(firstInpatient.getLastName(),
			  firstInpatient.getStatus());
			  logInpatientDataModels(inpatientsByLastNameAndStatus);

			  Integer newPatientId = 5;
			  log.info(">Save new inpatient with id {} to database.", newPatientId);
			  InpatientDataModel newInpatient = new
			  InpatientDataModel(newPatientId,"X Æ A-Xii","Boucher",new Treatment(
			  "333","789"),"627",LocalDate.of(2020,5,4),InpatientStatus.INWARD,true);
			  repo.saveAndFlush(newInpatient);

			  log.info(">Find inpatient by id {} from database.", newPatientId);
			  Optional<InpatientDataModel> inpatientById= repo.findById(newPatientId);
			  inpatientById.ifPresentOrElse( (value) -> {
			  logInpatientDataModels(Collections.unmodifiableList(Arrays.asList(value)));
			  }, () -> { logInpatientDataModels(Collections.emptyList()); } );

			  log.info(">Delete inpatient by id {} from database.", newPatientId);
			  repo.deleteById(newPatientId);
			 */
		};
	}

	@Bean
	CommandLineRunner testTrainDataModelRepository(TrainDataModelRepository repo) {
		return (args) -> {
      /*
        log.info("$Testing TrainDataModelRepository.");
        log.info(">Find all trains from database."); List<TrainDataModel>
        allTrains = repo.findAll(); logTrainModelList(allTrains);
        log.info(">Find all inpatients by status {} from database.",
        TrainStatus.ON_TIME); List<TrainDataModel> trainsByStatus =
        repo.findByStatus(TrainStatus.ON_TIME.toString());
        logTrainModelList(trainsByStatus);
        Integer newTrainId = 5;
        log.info(">Save new train with id {} to database.", newTrainId);
        TrainDataModel newTrain = new TrainDataModel(newTrainId, TrainStatus.ON_TIME);
        repo.saveAndFlush(newTrain);
        log.info(">Find train by id {} from database.", newTrainId);
        Optional<TrainDataModel> trainById = repo.findById(newTrainId);
        trainById.ifPresentOrElse( (value) -> {
        logTrainModelList(Collections.unmodifiableList(Arrays.asList(value)));
        }, () -> { logTrainModelList(Collections.emptyList()); } );
        log.info(">Delete inpatient by id {} from database.", newTrainId);
        repo.deleteById(newTrainId);
      */
		};
	}

	@Bean
	CommandLineRunner testService(TrainService service) {
		return (args) -> {
			log.info("$Testing TrainService."); Response response;
			response = service.addTrain(8);logResponse(response);
			response = service.addTrainStop(4,8,"Schilde",3, LocalTime.now(),LocalTime.now());logResponse(response);
			response = service.addTrainStop(5,8,"Brasschaat",3, LocalTime.now(),LocalTime.now());logResponse(response);
			response = service.addTrainStop(6,8,"Schoten",3, LocalTime.now(),LocalTime.now());logResponse(response);

			response = service.addTrain(6);logResponse(response);
			response = service.addTrainStop(7,6,"Gent",4, LocalTime.now(),LocalTime.now());logResponse(response);
			response = service.addTrainStop(8,6,"Antwerpen",4, LocalTime.now(),LocalTime.now());logResponse(response);
			response = service.addTrainStop(9,6,"Aalst",4, LocalTime.now(),LocalTime.now());logResponse(response);
		};
	}

	@Bean
	CommandLineRunner testTrainChangeEvent(TrainService service){
		return(args)->{
			log.info("$testing trainService with cancellation and delay");
			Response  r = service.signalSecurail(8,5);
			logResponse(r);
			r = service.cancelTrain(6,9);
			logResponse(r);
		};
	}

	private void logTrainStop(List<TrainStop> stp) {
		for(int i = 0; i< stp.size();i++){
			log.info("-response id[{}] time[{}] LETSGOOO!!!", stp.get(i).getTrainStopId(), stp.get(i).getArrivalTime());
		}

	}
	private static void logResponse(Response response) {
		log.info("-response status[{}] message[{}]", response.status, response.message);
	}

	private static void logTrain(Train train) {
		log.info("-response id[{}] LETSGOOO!!!", train.getTrainId());
	}
}
