package com.example.weatherchallenge.integration;

import com.example.weatherchallenge.integration.model.dto.Weather.WeatherServiceResponseDto;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Service;
import org.yql4j.*;
import org.yql4j.types.QueryResultType;
import java.io.IOException;

@Service
public class WeatherIntegrationService {
    private static String query = "select title, item.condition from weather.forecast where woeid in (select woeid from geo.places(1) where text in (%s)) and u=\"C\"";
    private static String existsQuery = "select title, item.condition from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"%s\") and u=\"C\"";

    public WeatherServiceResponseDto getLocationsCurrentWeather(String location) {
        String formattedQuery = String.format(this.query, location);
        YqlResult queryResult = doServiceRequest(formattedQuery);

        return mapQueryResult(queryResult);
    }

    public boolean existsLocationCurrentWeather(String location) {
        String formattedQuery = String.format(this.existsQuery, location);
        YqlResult queryResult = doServiceRequest(formattedQuery);

        try {
            mapQueryResult(queryResult);
        } catch (NullPointerException e) {
            return false;
        }

        return true;
    }

    private WeatherServiceResponseDto mapQueryResult(YqlResult queryResult) {
        QueryResultType<WeatherServiceResponseDto> mappedResult = null;
        try {
            mappedResult = queryResult.getContentAsMappedObject(new TypeReference<QueryResultType<WeatherServiceResponseDto>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return mappedResult.getResults();
    }

    private YqlResult doServiceRequest(String query) {
        YqlClient client = YqlClients.createDefault();
        YqlQuery resultQuery = YqlQueryBuilder.fromQueryString(query).withFormat(ResultFormat.JSON).build();
        YqlResult result = null;
        try {
            result = client.query(resultQuery);
        } catch (YqlException e) {
            e.printStackTrace();
        }

        return result;
    }
}