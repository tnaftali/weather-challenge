package com.example.weatherchallenge.mappers;

import com.example.weatherchallenge.integration.model.dto.Weather.WeatherChannelDto;
import com.example.weatherchallenge.integration.model.dto.Weather.WeatherServiceResponseDto;
import com.example.weatherchallenge.model.Board;
import com.example.weatherchallenge.model.Location;
import com.example.weatherchallenge.model.User;
import com.example.weatherchallenge.model.dto.BoardDto;
import com.example.weatherchallenge.model.dto.LocationDto;
import com.example.weatherchallenge.model.dto.LocationWeatherDto;
import com.example.weatherchallenge.model.dto.UserDto;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class EntityMapper {

    public List<BoardDto> boardListToBoardDtoList(List<Board> list) {
        List<BoardDto> dtoList = new ArrayList<>();
        for (Iterator<Board> i = list.iterator(); i.hasNext(); ) {
            Board item = i.next();
            dtoList.add(boardToDto(item));
        }

        return dtoList;
    }

    public List<UserDto> userListToUserDtoList(List<User> list) {
        List<UserDto> dtoList = new ArrayList<>();
        for (Iterator<User> i = list.iterator(); i.hasNext(); ) {
            User item = i.next();
            dtoList.add(userToDto(item));
        }

        return dtoList;
    }

    public LocationDto locationToDto(Location location) {
        return new LocationDto(location.getName());
    }

    public BoardDto boardToDto(Board board) {
        ArrayList<String> stringLocations = locationListToArrayList(board.getLocations());

        return new BoardDto(board.getName(), board.getUsername(), stringLocations);
    }

    public UserDto userToDto(User user) {
        return new UserDto(user.getUsername(), user.getCreatedAt());
    }

    private ArrayList<String> locationListToArrayList(List<Location> locations) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (Iterator<Location> i = locations.iterator(); i.hasNext(); ) {
            Location item = i.next();
            arrayList.add(item.getName());
        }

        return arrayList;
    }

    public ArrayList<LocationWeatherDto> mapToLocationWeatherDtoList(WeatherServiceResponseDto responseDto, Location[] boardLocations) {
        ArrayList<LocationWeatherDto> locationWeatherDtoList = new ArrayList<>();

        int i = 0;
        for (WeatherChannelDto channel: responseDto.channel) {
            String locationName = boardLocations[i].getName();
            locationWeatherDtoList.add(new LocationWeatherDto(locationName, channel.title, channel.item.condition.date, channel.item.condition.temp, channel.units.temperature, channel.item.condition.text ));
            i++;
        }

        return locationWeatherDtoList;
    }
}
