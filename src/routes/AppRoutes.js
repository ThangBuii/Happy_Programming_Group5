import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import History from "../pages/History";
import SigIn from "../pages/SignIn";
import SigUp from "../pages/SignUp";
import Home from "../pages/Home";

import Booking from "../pages/booking";
import Dashboard from "../pages/dashboard";
import FavouriteMentor from "../pages/favorite";
import Header from "../component/Header";
import Footer from "../component/Footer/Footer";
import FindMentor from "../pages/find-mentor";
import PersonProfile from "../pages/person-profile";
import MentorProfile from "../pages/mentor-profile";

const AppRoutes = () => {
  return (
    <Router>
      <Header />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/findmentor" element={<FindMentor />} />
        <Route path="/person/:id" element={<PersonProfile />} />
        <Route path="/mentor/:id" element={<MentorProfile />} />
        <Route path="history" element={<History />} />
        <Route path="/login" element={<SigIn />} />
        <Route path="/resgiter" element={<SigUp />} />
        <Route path="/bookings" element={<Booking />} />
        <Route path="/dashboard" element={<Dashboard />} />
        <Route path="favorite-mentor" element={<FavouriteMentor />} />
      </Routes>
      {/*Call Dashboard Admin */}

      <Footer />
    </Router>
  );
};

export default AppRoutes;
