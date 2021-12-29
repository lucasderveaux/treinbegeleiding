package be.ugent.systemdesign.treinbegeleiding.API.rest;


import be.ugent.systemdesign.treinbegeleiding.application.query.TrainQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path="trains")
@CrossOrigin(origins="*")
public class TrainRestController {
    @Autowired
    TrainQuery trainQuery;

    @GetMapping
    public List<TrainViewModel> getAll(){
        return trainQuery.findAll().stream().map(tst -> new TrainViewModel(tst)).collect(Collectors.toList());
    }


    @GetMapping("/train/{id}")
    public TrainViewModel findOne(@PathVariable("id") String _trainId){
        TrainViewModel t = new TrainViewModel(trainQuery.findOne(Integer.parseInt(_trainId)));
        return t;
    }
}
