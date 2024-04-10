package co.edu.uptc.project11.services;

import co.edu.uptc.SimpleUptcList.services.SimpleUptcList;
import co.edu.uptc.project11.models.Place;
import co.edu.uptc.project11.utils.DataListsUtils;

public class PlaceService {

    public PlaceService(){
        Place p1 = new Place("C124", "Salón C124", "Edificio Central");
        Place p2 = new Place("R203", "Salón R203", "Edificio Rafeal Azula");
        Place p3 = new Place("A103", "Salón A103", "Edificio Aulas");
        Place p4 = new Place("D102", "Salón D102", "Edificio Central");
        Place p5 = new Place("Coliseo", "Coliseo Deportivo", "Coliseo Central");
        Place p6 = new Place("LN 203", "Laboratorio LN203", "Edificio Laboratorios Nuevos");
        Place p7 = new Place("C303", "Salón C303", "Edificio Central");
        Place p8 = new Place("AD1", "Auditorio 1", "Edificio Administrativo");
        Place p9 = new Place("RA203", "Salón RA204", "Edificio RA");
        Place p10 = new Place("Segundo Piso", "cafeteria", "Restaurante");

        addPlace(getPlaces(), p1);
        addPlace(getPlaces(), p2);
        addPlace(getPlaces(), p3);
        addPlace(getPlaces(), p4);
        addPlace(getPlaces(), p5);
        addPlace(getPlaces(), p6);
        addPlace(getPlaces(), p7);
        addPlace(getPlaces(), p8);
        addPlace(getPlaces(), p9);
        addPlace(getPlaces(), p10);
    }

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
