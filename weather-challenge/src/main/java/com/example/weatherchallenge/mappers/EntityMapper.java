package com.example.weatherchallenge.mappers;

import com.example.weatherchallenge.integration.model.dto.Weather.WeatherChannelDto;
import com.example.weatherchallenge.integration.model.dto.Weather.WeatherServiceResponseDto;
import com.example.weatherchallenge.model.Board;
import com.example.weatherchallenge.model.Location;
import com.example.weatherchallenge.model.LocationWeatherStatus;
import com.example.weatherchallenge.model.dto.BoardDto;
import com.example.weatherchallenge.model.dto.LocationWeatherDto;
import com.example.weatherchallenge.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class EntityMapper {
    @Autowired
    private DateUtils dateUtils;

    public List<BoardDto> boardListToBoardDtoList(List<Board> list) {
        List<BoardDto> dtoList = new ArrayList<>();
        for (Iterator<Board> i = list.iterator(); i.hasNext(); ) {
            Board item = i.next();
            dtoList.add(boardToDto(item));
        }

        return dtoList;
    }

    public BoardDto boardToDto(Board board) {
        ArrayList<String> stringLocations = locationListToArrayList(board.getLocations());

        return new BoardDto(board.getName(), board.getUsername(), stringLocations);
    }

    private ArrayList<String> locationListToArrayList(List<Location> locations) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (Iterator<Location> i = locations.iterator(); i.hasNext(); ) {
            Location item = i.next();
            arrayList.add(item.getName());
        }

        return arrayList;
    }

    public LocationWeatherDto mapToLocationWeatherDto (WeatherServiceResponseDto responseDto, Location location) {
        WeatherChannelDto channelDto = responseDto.channel[0];
        return new LocationWeatherDto(location.getName(), channelDto.title, channelDto.item.condition.date, channelDto.item.condition.temp, channelDto.item.condition.text);
    }

    public LocationWeatherDto mapToLocationWeatherDto (LocationWeatherStatus locationWeatherStatus, Location location) {
        return new LocationWeatherDto(location.getName(), locationWeatherStatus.getTitle(), dateUtils.toStringDate(locationWeatherStatus.getWeatherStatusDate()), locationWeatherStatus.getTemperature(), locationWeatherStatus.getCurrentWeather());
    }

    public ArrayList<LocationWeatherDto> mapToLocationWeatherDtoList(WeatherServiceResponseDto responseDto, Location[] boardLocations) {
        ArrayList<LocationWeatherDto> locationWeatherDtoList = new ArrayList<>();

        int i = 0;
        for (WeatherChannelDto channel : responseDto.channel) {
            String locationName = boardLocations[i].getName();
            locationWeatherDtoList.add(new LocationWeatherDto(locationName, channel.title, channel.item.condition.date, channel.item.condition.temp, channel.item.condition.text));
            i++;
        }

        return locationWeatherDtoList;
    }

    public String mapLocationsToString(List<Location> locations) {
        String locationsString = "";
        if (locations.size() > 0) {
            for (Location location : locations) {
                locationsString += "\"" + location.getName() + "\",";
            }
            locationsString = locationsString.substring(0, locationsString.length() - 1);
        }

        return locationsString;
    }
}
