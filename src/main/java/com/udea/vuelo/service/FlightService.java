package com.udea.vuelo.service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.udea.vuelo.model.Flight;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class FlightService {
    /*lógica del negocio*/
    //método de la logica de busqueda de vuelos
    public List<List<Flight>> searchFlights(LocalDate startDate, LocalDate endDate, String airLine, String origin, String destination, int price) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("flights.json");
            if (inputStream != null) {
                Flight[] flights = objectMapper.readValue(inputStream, Flight[].class);
                return Arrays.asList(
                        Arrays.stream(flights)
                                .filter(flight -> isDateInRange(flight.getDepartureDate(), startDate, endDate))
                                .filter(flight -> isAtributeEquals(airLine, flight.getAirline()))
                                .filter(flight -> isAtributeEquals(origin, flight.getOrigin()))
                                .filter(flight -> isAtributeEquals(destination, flight.getDestination()))
                                .filter(flight -> isPriceInRange(price, flight.getPrice()))
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

    private boolean isAtributeEquals(String airLineName, String airLineNameToCheck) {
        if (Objects.equals(airLineName, "")) {
            return true;
        }
        return airLineName.equals(airLineNameToCheck);
    }

    private boolean isPriceInRange(int price, int priceToCheck) {
        if (price == -1) {
            return true;
        }

        return priceToCheck < price;
    }
}
