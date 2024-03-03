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

    public Flight[] getAllFlights() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("flights.json");
            if (inputStream != null) {
                return objectMapper.readValue(inputStream, Flight[].class);
            } else {
                return null;
            }
        } catch (IOException e) {
            throw new RuntimeException("Error leyendo el json" + e);
        }
    }

    public List<List<Flight>> searchFlightsByAirLine(String airLineName) {
        Flight[] flights = getAllFlights();
        return Arrays.asList(Arrays.stream(flights).filter(flight -> isAirLineEquals(airLineName, flight.getAirline()))
                .collect(Collectors.toList()));
    }

    public List<List<Flight>> searchFlightsByOrigin(String origin) {
        Flight[] flights = getAllFlights();
        return Arrays.asList(Arrays.stream(flights).filter(flight -> isOriginEquals(origin, flight.getOrigin()))
                .collect(Collectors.toList()));
    }


    public List<List<Flight>> searchFlightsByDestination(String destination) {
        Flight[] flights = getAllFlights();
        return Arrays.asList(Arrays.stream(flights).filter(flight -> isDestinationEquals(destination, flight.getDestination()))
                .collect(Collectors.toList()));
    }


    public List<List<Flight>> searchFlightsByPrice(int price) {
        Flight[] flights = getAllFlights();
        return Arrays.asList(Arrays.stream(flights).filter(flight -> isPriceInRange(price, flight.getPrice()))
                .collect(Collectors.toList()));
    }

    private boolean isDateInRange(LocalDate dateToCheck, LocalDate startDate, LocalDate endDate) {
        //verifica si la fecha esta en el rango correcto
        return !dateToCheck.isBefore(startDate) && !dateToCheck.isAfter(endDate);
    }

    private boolean isAirLineEquals(String airLineName, String airLineNameToCheck) {
        return airLineName.equals(airLineNameToCheck);
    }

    private boolean isOriginEquals(String origin, String originToCheck) {
        return origin.equals(originToCheck);
    }

    private boolean isDestinationEquals(String destination, String destinationToCheck) {
        return destination.equals(destinationToCheck);
    }

    private boolean isPriceInRange(int price, int priceToCheck) {
        return priceToCheck< price;
    }
}
