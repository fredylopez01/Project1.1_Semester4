package co.edu.uptc.project11.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uptc.SimpleUptcList.services.SimpleUptcList;
import co.edu.uptc.project11.dtos.PlaceDto;
import co.edu.uptc.project11.exceptions.ProjectException;
import co.edu.uptc.project11.models.Place;
import co.edu.uptc.project11.services.PlaceService;

@RestController
@RequestMapping("/places")
public class PlaceController{
    PlaceService placeService = new PlaceService();

    @GetMapping("/list")
    public ResponseEntity<Object> getPlaces(){
        try {
            SimpleUptcList<PlaceDto> subjectsDto = PlaceDto.fromPlaces(placeService.getPlaces());
            return ResponseEntity.ok().body(subjectsDto);
        } catch (ProjectException e) {
            return ResponseEntity.status(e.getMessageException().getHttpStatus()).body(e.getMessageException());
        }
    }

    @PostMapping("/addPlace")
    public ResponseEntity<Object> addPlace(@RequestBody PlaceDto placeDto){
        try{
            PlaceDto.validatePlace(placeDto);
            SimpleUptcList<Place> places = placeService.getPlaces();
            placeService.addPlace(places, PlaceDto.fromPlaceDto(placeDto));
            return ResponseEntity.ok().body(placeDto);
        } catch (ProjectException e) {
            return ResponseEntity.status(e.getMessageException().getHttpStatus()).body(e.getMessageException());
        }
    }

    @DeleteMapping("/deletePlace/{identification}")
    public ResponseEntity<Object> deleteSubject(@PathVariable String identification){
        try{
            SimpleUptcList<Place> places = placeService.getPlaces();
            Place deletedPlace= placeService.deletePlace(places, identification);
            return ResponseEntity.ok().body(PlaceDto.fromPlace(deletedPlace));
        } catch (ProjectException e) {
            return ResponseEntity.status(e.getMessageException().getHttpStatus()).body(e.getMessageException());
        }
    }

    @PutMapping("/setPlace/{identification}")
    public ResponseEntity<Object> setPlace(@RequestBody PlaceDto placeDto, @PathVariable String identification){
        try{
            PlaceDto.validatePlace(placeDto);
            SimpleUptcList<Place> places = placeService.getPlaces();
            Place place = placeService.setPlace(places, identification, PlaceDto.fromPlaceDto(placeDto));
            return ResponseEntity.ok().body(PlaceDto.fromPlace(place));
        } catch (ProjectException e) {
            return ResponseEntity.status(e.getMessageException().getHttpStatus()).body(e.getMessageException());
        }
    }
    
}