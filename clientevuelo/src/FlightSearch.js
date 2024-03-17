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
    <div className="container mx-auto p-10">
      <h2 className="text-2xl font-bold mb-4">Filtros:</h2>

      <div className="grid grid-cols-2 gap-4 mb-4">
        <div>
          <label className="block">Fecha de Inicio:</label>
          <input
            type="date"
            className="border rounded px-2 py-1 w-full"
            onChange={(e) => setStartDate(e.target.value)}
          />
        </div>

        <div>
          <label className="block">Fecha Fin:</label>
          <input
            type="date"
            className="border rounded px-2 py-1 w-full"
            onChange={(e) => setEndDate(e.target.value)}
          />
        </div>

        <div>
          <label className="block">Nombre de la aerolínea:</label>
          <input
            value={airLineName}
            onChange={(e) => setAirLineName(e.target.value)}
            className="border rounded px-2 py-1 w-full"
          />
        </div>

        <div>
          <label className="block">Nombre del destino:</label>
          <input
            value={destination}
            onChange={(e) => setDestination(e.target.value)}
            className="border rounded px-2 py-1 w-full"
          />
        </div>

        <div>
          <label className="block">Nombre del origen:</label>
          <input
            value={origin}
            onChange={(e) => setOrigin(e.target.value)}
            className="border rounded px-2 py-1 w-full"
          />
        </div>

        <div>
          <label className="block">Precio máximo:</label>
          <input
            onChange={(e) => setPrice(e.target.value)}
            className="border rounded px-2 py-1 w-full"
          />
        </div>

        <div>
          <label className="block">Precio mínimo:</label>
          <input className="border rounded px-2 py-1 w-full" />
        </div>
      </div>

      <div className="text-center">
        <button
          onClick={handleSearch}
          className="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600"
        >
          Buscar
        </button>
      </div>

      {loading && <p className="text-center">Cargando....</p>}

      <h2 className="text-2xl font-bold mt-4">Resultados de la búsqueda</h2>

      {flights.length > 0 ? (
        <ul>
          {flights.map((flight, index) => (
            <li key={index}>
              {Object.keys(flight).map((key) => (
                <div key={key}>
                  {`${key}: ${
                    typeof flight[key] === "object"
                      ? JSON.stringify(flight[key])
                      : flight[key]
                  }`}
                </div>
              ))}
            </li>
          ))}
        </ul>
      ) : (
        <p className="text-center">No se encontraron vuelos</p>
      )}
    </div>
  );
}

export default FlightSearch;
