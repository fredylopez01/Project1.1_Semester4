package co.edu.uptc.project11.services;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.google.gson.reflect.TypeToken;

import co.edu.uptc.SimpleUptcList.services.SimpleUptcList;
import co.edu.uptc.project11.exceptions.ProjectException;
import co.edu.uptc.project11.exceptions.TypeMessageEnum;
import co.edu.uptc.project11.models.Place;
import co.edu.uptc.project11.persistence.PersistenceJSON;
import co.edu.uptc.project11.persistence.PersistenceProperties;

public class PlaceService {
	private PersistenceJSON<Place> persistenceJSON;

    public PlaceService(){
        String route = PersistenceProperties.read("routePlaces");
        persistenceJSON = new PersistenceJSON<>(route);
    }

    public SimpleUptcList<Place> getPlaces() throws ProjectException {
        try {
            SimpleUptcList<Place> places = loadDates();
            SimpleUptcList<Place> placesAux = new SimpleUptcList<>();
            for (Place place : places) {
                placesAux.add(place);
            }
            return placesAux;
        } catch (IOException e) {
            throw new ProjectException(TypeMessageEnum.FILE_NOT_FOUND);
        }
    }
    
    public void addPlace(SimpleUptcList<Place> places, Place place) throws ProjectException {
        try {
            boolean isAdd = isExistPlace(places, place.getIdentification());
            if(!isAdd){
                places.add(place);
                saveDates(places);
            }
        } catch (FileNotFoundException e) {
            throw new ProjectException(TypeMessageEnum.FILE_NOT_FOUND);
        }
    }

    public Place deletePlace(SimpleUptcList<Place> places, String identification) throws ProjectException {
        Place place = new Place();
        for (int i = 0; i < places.size(); i++) {
            if(places.get(i).getIdentification().equalsIgnoreCase(identification)){
                place = places.remove(i);
                try {
                    saveDates(places);
                } catch (FileNotFoundException e) {
                    throw new ProjectException(TypeMessageEnum.FILE_NOT_FOUND);
                }
            }
        }
        return place;
    }

    public Place setPlace(SimpleUptcList<Place> places, String identification, Place newPlace) throws ProjectException {
        Place place = new Place();
        for (int i = 0; i < places.size(); i++) {
            if(places.get(i).getIdentification().equalsIgnoreCase(identification)){
                place = places.set(i, newPlace);
                try {
                    saveDates(places);
                } catch (FileNotFoundException e) {
                    throw new ProjectException(TypeMessageEnum.FILE_NOT_FOUND);
                }
            }
        }
        return place;
    }

    public static boolean isExistPlace(SimpleUptcList<Place> places, String identification) {
        boolean isAdd = false;
        for (Place placei : places) {
            if(placei.getIdentification().equalsIgnoreCase(identification)){
                isAdd = true;
            }
        }
        return isAdd;
    }
    
    public SimpleUptcList<Place> loadDates() throws IOException{
        TypeToken<SimpleUptcList<Place>> listTypeToken = new TypeToken<SimpleUptcList<Place>>() {};
        return persistenceJSON.readDates(listTypeToken);
    }

    public void saveDates(SimpleUptcList<Place> subjects) throws FileNotFoundException{
        persistenceJSON.writeDates(subjects);
    }

}
