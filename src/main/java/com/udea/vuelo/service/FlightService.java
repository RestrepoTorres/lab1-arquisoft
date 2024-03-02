package com.udea.vuelo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.udea.vuelo.model.Flight;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

@Service

public class FlightService {

    /*lógica del negocio*/

    //Ruta del archivo


    //método de la logica de busqueda de vuelos
    public List<List<Flight>> searchFlights(LocalDate startDate, LocalDate endDate) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("flights.json");
            if (inputStream != null) {
                Flight[] flights = objectMapper.readValue(inputStream, Flight[].class);
                return Arrays.asList(

                        Arrays.stream(flights)

                                .filter(flight -> isDateInRange(flight.getDepartureDate(), startDate, endDate))

                                .collect(Collectors.toList()));
            } else {
                return null;
            }
        } catch (IOException e) {
            throw new RuntimeException("Error leyendo el json" + e);
        }
    }


    public List<List<Flight>> searchFlightsByAirLine(String airLineName) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("flights.json");
            if (inputStream != null) {
                Flight[] flights = objectMapper.readValue(inputStream, Flight[].class);
                return Arrays.asList(Arrays.stream(flights).filter(flight -> isAirLineEquals(airLineName, flight.getAirline()))

                        .collect(Collectors.toList()));
            } else {
                return null;
            }
        } catch (IOException e) {
            throw new RuntimeException("Error leyendo el json" + e);
        }
    }

    private boolean isDateInRange(LocalDate dateToCheck, LocalDate startDate, LocalDate endDate) {
        //verifica si la fecha esta en el rango correcto
        return !dateToCheck.isBefore(startDate) && !dateToCheck.isAfter(endDate);
    }

    private boolean isAirLineEquals(String airLineName, String airLineNameToCheck) {
        return airLineName.equals(airLineNameToCheck);
    }
}
