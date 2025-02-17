import { Route, Routes } from "react-router-dom";
import SeatBooking from "./SeatBooking";

function App() {
  return (
    <Routes>
      <Route path="/" element={<SeatBooking />} />
    </Routes>
  );
}

export default App;
