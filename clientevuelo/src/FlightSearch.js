import React, { useState } from "react";
import axios from "axios";

function FlightSearch() {
  const [startDate, setStartDate] = useState("");
  const [endDate, setEndDate] = useState("");
  const [flights, setFlights] = useState([]);
  const [loading, setLoading] = useState(false);
  const [airLineName, setAirLineName] = useState("");
  const [destination, setDestination] = useState("");

  const handleSearch = async () => {
    setLoading(true);
    try {
      const response = await axios.get(
        `http://localhost:8080/flights/search?startDate=${startDate}&endDate=${endDate}`
      );
      setFlights(response.data);
      console.log(response.data);
    } catch (error) {
      console.log("error en la carga de datos de  vuelo");
    }
    setLoading(false);
  };

  const handleSearchByAirLine = async () => {
    setLoading(true);
    try {
      const response = await axios.get(
        `http://localhost:8080/flights/searchbyairline?airLineName=${airLineName}`
      );
      setFlights(response.data);
      console.log(response.data);
    } catch (error) {
      console.log("error en la carga de datos de  vuelo");
    }
    setLoading(false);
  };

  const handleSearchByDestination = async () => {
    setLoading(true);
    try {
      const response = await axios.get(
        `http://localhost:8080/flights/searchbydestination?destination=${destination}`
      );
      setFlights(response.data);
      console.log(response.data);
    } catch (error) {
      console.log("error en la carga de datos de  vuelo");
    }
    setLoading(false);
  };
  return (
    <div>
      <h2>Buscar Vuelos</h2>

      <div>
        <label>Fecha de Inicio: </label>

        <input
          type="date"
          value={startDate}
          onChange={(e) => setStartDate(e.target.value)}
        />

        <label>Fecha Fin: </label>

        <input
          type="date"
          value={endDate}
          onChange={(e) => setEndDate(e.target.value)}
        />

        <button onClick={handleSearch}>Buscar por fechas </button>
      </div>
      <div>
        <label>nombre de la aerolinea: </label>
        <input
          value={airLineName}
          onChange={(e) => setAirLineName(e.target.value)}
        />
        <button onClick={handleSearchByAirLine}>Buscar por aerolinea </button>
      </div>

      <div>
        <label>nombre del destino : </label>
        <input
          value={destination}
          onChange={(e) => setDestination(e.target.value)}
        />
        <button onClick={handleSearchByDestination}>Buscar por aerolinea </button>
      </div>

      {loading && <p>Cargando....</p>}

      <h2>Resultados de la busqueda..</h2>

      {flights.length > 0 ? (
        <ul>
          {flights.map((flight, index) => (
            <li key={index}>
              {Object.keys(flight).map((key) => (
                <div key={key}>
                  {`${key}: ${
                    typeof flight[key] === "object"
                      ? JSON.stringify(flight[key])
                      : flights[key]
                  }`}
                </div>
              ))}
            </li>
          ))}
        </ul>
      ) : (
        <p>No se encontraron vuelos</p>
      )}
    </div>
  );
}

export default FlightSearch;
