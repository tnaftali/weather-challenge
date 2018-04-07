package com.example.weatherchallenge.integration;

import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Service;
import org.yql4j.*;
import org.yql4j.types.QueryResultType;

import java.io.IOException;
import java.util.List;

@Service
public class WeatherIntegrationService {
    private static String cityQuery = "select title, item.condition, units from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"%s\") and u=\"C\"";
    private static String citiesquery = "select title, item.condition, units from weather.forecast where woeid in (select woeid from geo.places(1) where text in (%s)) and u=\"C\"";

    public void getCitiesCurrentWeather(List<String> cities) {
        String formattedCities = formatCities(cities);
        YqlClient client = YqlClients.createDefault();
        YqlQuery query = YqlQueryBuilder.fromQueryString(String.format(this.citiesquery, formattedCities)).withFormat(ResultFormat.JSON).build();
        YqlResult result = null;
        try {
            result = client.query(query);
        } catch (YqlException e) {
            e.printStackTrace();
        }

        String rawResult = result.getContentAsString();

        //        System.out.println(rawResult);

        // But if you are lazy, you may also get the content mapped as object graph
        // Please note though: You will have to provide your own mapping classes,
        // i.e. PlaceArrayType and PlaceType!
        QueryResultType<List<Object>> mappedResult =
                null;
        try {
            mappedResult = result.getContentAsMappedObject(new TypeReference<QueryResultType<List<Object>>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Object item : mappedResult.getResults()) {
            // Do something with the item
            System.out.println(item);
        }
    }

    public void getCityCurrentWeather(String city) {
        YqlClient client = YqlClients.createDefault();
        YqlQuery query = YqlQueryBuilder.fromQueryString(String.format(this.cityQuery, city)).withFormat(ResultFormat.JSON).build();
        YqlResult result = null;
        try {
            result = client.query(query);
        } catch (YqlException e) {
            e.printStackTrace();
        }

        String rawResult = result.getContentAsString();

        //        System.out.println(rawResult);

        // But if you are lazy, you may also get the content mapped as object graph
        // Please note though: You will have to provide your own mapping classes,
        // i.e. PlaceArrayType and PlaceType!
        QueryResultType<List<Object>> mappedResult =
                null;
        try {
            mappedResult = result.getContentAsMappedObject(new TypeReference<QueryResultType<List<Object>>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Object item : mappedResult.getResults()) {
            // Do something with the item
            System.out.println(item);
        }
    }

    private String formatCities(List<String> cities) {
        System.out.println(cities.toArray().toString());
        return cities.toArray().toString();
        //        String> dtoList = new ArrayList<>();
        //        for (Iterator<Board> i = list.iterator(); i.hasNext();) {
        //            Board item = i.next();
        //            dtoList.add(item.toDto());
        //        }
        //
        //        return dtoList;
    }
}