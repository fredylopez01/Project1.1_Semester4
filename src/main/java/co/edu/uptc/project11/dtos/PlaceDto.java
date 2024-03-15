package co.edu.uptc.project11.dtos;

import co.edu.uptc.SimpleUptcList.services.SimpleUptcList;
import co.edu.uptc.project11.models.Place;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlaceDto {
    private String identification;
    private String name;
    private String physicalLocation;

    public static PlaceDto fromPlace(Place place){
        PlaceDto placeDto = new PlaceDto();
        placeDto.setIdentification(place.getIdentification());
        placeDto.setName(place.getName());
        placeDto.setPhysicalLocation(place.getPhysicalLocation());
        return placeDto;
    }

    public static Place fromPlaceDto(PlaceDto placeDto){
        Place place = new Place();
        place.setIdentification(placeDto.getIdentification());
        place.setName(placeDto.getName());
        place.setPhysicalLocation(placeDto.getPhysicalLocation());
        return place;
    }

    public static SimpleUptcList<PlaceDto> fromPlaces(SimpleUptcList<Place> places){
        SimpleUptcList<PlaceDto> placesDto = new SimpleUptcList<>();
        for (Place place : places) {
            placesDto.add(fromPlace(place));
        }
        return placesDto;
    }

    public static SimpleUptcList<Place> fromPlacesDto(SimpleUptcList<PlaceDto> placesDto){
        SimpleUptcList<Place> places = new SimpleUptcList<>();
        for (PlaceDto placeDto : placesDto) {
            places.add(fromPlaceDto(placeDto));
        }
        return places;
    }
}
