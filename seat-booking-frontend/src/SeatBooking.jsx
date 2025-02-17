import { useEffect, useState } from "react";
import axios from "axios";

const API_BASE_URL = "http://localhost:8080/api/seats";

function SeatBooking() {
  const [seats, setSeats] = useState([]);

  useEffect(() => {
    fetchSeats();
  }, []);

  const fetchSeats = async () => {
    try {
      const response = await axios.get(API_BASE_URL);
      setSeats(response.data);
    } catch (error) {
      console.error("Error fetching seats:", error);
    }
  };

  const bookSeat = async (seatNumber) => {
    try {
      await axios.post(`${API_BASE_URL}/book/${seatNumber}`);
      fetchSeats(); // Refresh seats after booking
    } catch (error) {
      alert(error.response.data || "Booking failed!");
    }
  };

  const cancelBooking = async (seatNumber) => {
    try {
      await axios.post(`${API_BASE_URL}/cancel/${seatNumber}`);
      fetchSeats(); // Refresh seats after canceling
    } catch (error) {
      alert(error.response.data || "Cancellation failed!");
    }
  };

  return (
    <div className="container">
      <h1>Seat Booking System</h1>
      <div className="seat-grid">
        {seats.map((seat) => (
          <div key={seat.id} className={`seat ${seat.booked ? "booked" : ""}`}>
            <span>{seat.seatNumber}</span>
            {seat.booked ? (
              <button
                className="cancel-btn"
                onClick={() => cancelBooking(seat.seatNumber)}
              >
                Cancel
              </button>
            ) : (
              <button
                className="book-btn"
                onClick={() => bookSeat(seat.seatNumber)}
              >
                Book
              </button>
            )}
          </div>
        ))}
      </div>
    </div>
  );
}

export default SeatBooking;
