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
            @RequestParam(required = false, name = "startDate") String startDate,
            @RequestParam(required = false, name = "endDate") String endDate,
            @RequestParam(required = false, name = "airLineName") String airLineName,
            @RequestParam(required = false, name = "origin") String origin,
            @RequestParam(required = false, name = "destination") String destination,
            @RequestParam(required = false, name = "price") int price) {
        LocalDate parsedStartDate = LocalDate.parse(startDate);
        LocalDate parsedEndDate = LocalDate.parse(endDate);
        return flightService.searchFlights(parsedStartDate, parsedEndDate, airLineName, origin, destination, price);
    }
}