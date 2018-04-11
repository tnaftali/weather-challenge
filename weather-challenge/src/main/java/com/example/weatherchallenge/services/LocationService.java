package com.example.weatherchallenge.services;

import com.example.weatherchallenge.factories.LocationFactory;
import com.example.weatherchallenge.helpers.LocationHelper;
import com.example.weatherchallenge.iservices.ILocationService;
import com.example.weatherchallenge.mappers.EntityMapper;
import com.example.weatherchallenge.model.Board;
import com.example.weatherchallenge.model.Location;
import com.example.weatherchallenge.model.User;
import com.example.weatherchallenge.model.dto.LocationCriteriaDto;
import com.example.weatherchallenge.model.dto.LocationDto;
import com.example.weatherchallenge.repositories.BoardRepository;
import com.example.weatherchallenge.repositories.LocationRepository;
import com.example.weatherchallenge.searchers.BoardSearcher;
import com.example.weatherchallenge.searchers.UserSearcher;
import com.example.weatherchallenge.validators.LocationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationService implements ILocationService {
    @Autowired
    LocationValidator locationValidator;
    @Autowired
    LocationRepository locationRepository;
    @Autowired
    UserSearcher userSearcher;
    @Autowired
    BoardSearcher boardSearcher;
    @Autowired
    BoardRepository boardRepository;
    @Autowired
    LocationFactory locationFactory;
    @Autowired
    EntityMapper entityMapper;
    @Autowired
    LocationHelper locationHelper;

    public LocationDto addLocationToBoard(LocationCriteriaDto locationCriteriaDto) {
        User user = userSearcher.findById(locationCriteriaDto.username);

        locationValidator.validate(locationCriteriaDto.location, locationCriteriaDto.boardName, user);

        Board board = boardSearcher.findByUserAndName(user, locationCriteriaDto.boardName);
        Location location = locationHelper.getOrCreate(locationCriteriaDto.location);
        board.addLocation(location);
        locationRepository.save(location);
        boardRepository.save(board);

        return entityMapper.locationToDto(location);
    }

    public void deleteLocationFromBoard(LocationCriteriaDto locationCriteriaDto) {
        locationValidator.validateLocationExistenceInRepository(locationCriteriaDto.location);

        User user = userSearcher.findById(locationCriteriaDto.username);
        Board board = boardSearcher.findByUserAndName(user, locationCriteriaDto.boardName);
        board.removeLocation(locationCriteriaDto.location);

        boardRepository.save(board);
    }
}
