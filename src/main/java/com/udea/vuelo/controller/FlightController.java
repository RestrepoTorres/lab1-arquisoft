package com.udea.vuelo.controller;

import java.time.LocalDate;
import java.util.List;

import com.udea.vuelo.model.Flight;
import com.udea.vuelo.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/flights")  //ruta base de la api
public class FlightController {
    @Autowired //inyección de dependecias del servicio (lógica)
    private FlightService flightService;

    @GetMapping("/search")
    public List<List<Flight>> searchFlights(
            @RequestParam(name = "startDate") String startDate,
            @RequestParam(name = "endDate") String endDate) {
        LocalDate parsedStartDate = LocalDate.parse(startDate);
        LocalDate parsedEndDate = LocalDate.parse(endDate);
        return flightService.searchFlights(parsedStartDate, parsedEndDate);
    }
    @GetMapping("/searchbyairline")
    public List<List<Flight>> searchFlightsByAirLine(
            @RequestParam(name = "airLineName") String airLineName) {
        return flightService.searchFlightsByAirLine(airLineName);
    }
    @GetMapping("/searchbyorigin")
    public List<List<Flight>> searchFlightsByOrigin(
            @RequestParam(name = "airLineName") String airLineName) {
        return flightService.searchFlightsByAirLine(airLineName);
    }

    @GetMapping("/searchbydestination")
    public List<List<Flight>> searchFlightsByDestination(
            @RequestParam(name = "airLineName") String airLineName) {
        return flightService.searchFlightsByAirLine(airLineName);
    }

    @GetMapping("/searchbyprice")
    public List<List<Flight>> searchFlightsByPrice(
            @RequestParam(name = "airLineName") String airLineName) {
        return flightService.searchFlightsByAirLine(airLineName);
    }

}
