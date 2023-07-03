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
import MentorProfile from "../pages/mentor-profile";
import BookingsDetail from "../pages/bookings-detail";
import Report from "../pages/report";
import ReportAdd from "../pages/report-add";
import ReportDetail from "../pages/report-detail";
import Feedback from "../pages/feedback";
import InvoiceView from "../pages/invoice-view";
import Invoice from "../pages/invoice";
import Profile from "../pages/profile";
import EditProfile from "../pages/profile-edit";

import Navbar from "../component/admin/navbar/Navbar";
import React, { useState } from "react";

export const ApplicationContext = React.createContext([]);

const AppRoutes = () => {
  const [user, setUser] = useState([]);

  const makeSignIn = (user) => {
    setUser(user);
  };
  const makeSignOut = (user) => {
    setUser([]);
  };
  return (
    <ApplicationContext.Provider
      value={{ user, setUser, makeSignIn, makeSignOut }}
    >
      <Router>
        <Header />
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/findmentor" element={<FindMentor />} />
          {/* <Route path="/person/:id" element={<PersonProfile />} /> */}
          <Route path="/mentor/:id" element={<MentorProfile />} />

          <Route path="history" element={<History />} />

          <Route path="/login" element={<SigIn />} />
          <Route path="/resgiter" element={<SigUp />} />

          <Route path="/bookings/:id" element={<BookingsDetail />} />
          <Route path="/bookings" element={<Booking />} />
          <Route path="/dashboard" element={<Dashboard />} />

          <Route path="/favorite-mentor" element={<FavouriteMentor />} />

          <Route path="/feedback" element={<Feedback />} />

          <Route path="/invoice/:id" element={<InvoiceView />} />
          <Route path="/invoice" element={<Invoice />} />

          <Route path="/report/add" element={<ReportAdd />} />
          <Route path="/report/:id" element={<ReportDetail />} />
          <Route path="/report" element={<Report />} />

          <Route path="/profile/edit" element={<EditProfile />} />
          <Route path="/profile" element={<Profile />} />

          {/*Call Dashboard Admin */}
          <Route path="/admin" element={<Navbar />} />
        </Routes>

        <Footer />
      </Router>
    </ApplicationContext.Provider>
  );
};

export default AppRoutes;
