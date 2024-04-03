package co.edu.uptc.project11.services;

import co.edu.uptc.SimpleUptcList.services.SimpleUptcList;
import co.edu.uptc.project11.models.Place;
import co.edu.uptc.project11.utils.DataListsUtils;

public class PlaceService {

    public SimpleUptcList<Place> getPlaces() {
        SimpleUptcList<Place> places = loadDates();
        SimpleUptcList<Place> placesAux = new SimpleUptcList<>();
        for (Place place : places) {
            placesAux.add(place);
        }
        return placesAux;
    }
    
    public void addPlace(SimpleUptcList<Place> places, Place place) {
        boolean isAdd = isExistPlace(places, place.getIdentification());
        if(!isAdd){
            places.add(place);
            saveDates(places);
        }
    }

    public Place deletePlace(SimpleUptcList<Place> places, String identification) {
        Place place = new Place();
        for (int i = 0; i < places.size(); i++) {
            if(places.get(i).getIdentification().equalsIgnoreCase(identification)){
                place = places.remove(i);
                saveDates(places);
            }
        }
        return place;
    }

    public Place setPlace(SimpleUptcList<Place> places, String identification, Place newPlace) {
        Place place = new Place();
        for (int i = 0; i < places.size(); i++) {
            if(places.get(i).getIdentification().equalsIgnoreCase(identification)){
                place = places.set(i, newPlace);
                saveDates(places);
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
    
    public SimpleUptcList<Place> loadDates() {
        return DataListsUtils.places;
    }

    public void saveDates(SimpleUptcList<Place> places) {
        DataListsUtils.places = places;
    }

}
