import React, { useState } from "react";
import axios from "axios";

function FlightSearch() {
  const [startDate, setStartDate] = useState("1700-02-20");
  const [endDate, setEndDate] = useState("2700-02-20");
  const [flights, setFlights] = useState([]);
  const [loading, setLoading] = useState(false);
  const [airLineName, setAirLineName] = useState("");
  const [origin, setOrigin] = useState("");
  const [destination, setDestination] = useState("");
  const [price, setPrice] = useState(-1);

  const handleSearch = async () => {
    setLoading(true);
    try {
      const response = await axios.get(
        `http://localhost:8080/flights/search?startDate=${startDate}&endDate=${endDate}&airLineName=${airLineName}&destination=${destination}&origin=${origin}&price=${price}`
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
        <br />
        <input type="date" onChange={(e) => setStartDate(e.target.value)} />
        <br />
        <label>Fecha Fin: </label>
        <br />
        <input type="date" onChange={(e) => setEndDate(e.target.value)} />
        <br />
        <label>nombre de la aerolinea: </label>
        <br />
        <input
          value={airLineName}
          onChange={(e) => setAirLineName(e.target.value)}
        />
        <br />
        <label>nombre del destino : </label>
        <br />
        <input
          value={destination}
          onChange={(e) => setDestination(e.target.value)}
        />
        <br />
        <label>nombre del origen : </label>
        <br />
        <input value={origin} onChange={(e) => setOrigin(e.target.value)} />
        <br />
        <label>Precio máximo: </label>
        <br />
        <input  onChange={(e) => setPrice(e.target.value)} />
        <br />
        <label>Precio minimo </label>
        <br />
        <input  />
        <br />
        <button onClick={handleSearch}>Buscar</button>
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