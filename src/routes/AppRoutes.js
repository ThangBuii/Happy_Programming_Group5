import {
  BrowserRouter as Router,
  Routes,
  Route,
  Navigate,
} from "react-router-dom";
import SigIn from "../pages/SignIn";
import SigUp from "../pages/SignUp";
import Home from "../pages/Home";

import Booking from "../pages/booking";
import Dashboard from "../pages/dashboard";
import FavouriteMentor from "../pages/favorite";
import Header from "../component/Header";
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

import React, {useLayoutEffect, useState } from "react";
import AdminDashboard from "../pages/admin/dashboard";
import ManageMentee from "../pages/admin/manage-mentee";
import Footer from "../component/Footer";
import ManageMentor from "../pages/admin/manage-mentor";
import ManageSession from "../pages/admin/manage-session";
import ManageFeedback from "../pages/admin/manage-feedback";
import ManageRevenue from "../pages/admin/manage-revenue";
import ManageBooking from "../pages/admin/manage-booking";
import ManageInvoice from "../pages/admin/manage-invoice";
import ManageReport from "../pages/admin/manage-report";
import ManageSkill from "../pages/admin/manage-skill";
import Checkout from "../pages/checkout";
import ScheduleTimings from "../pages/schedule-timings";
import SessionsDetail from "../pages/sessions-detail";
import { getDataFromLocal } from "../axios_helper";
import PrivateRoute from "./PrivateRoute";
import PrivateAdminRoute from "./PrivateAdminRoute";
import PrivateMenteeRoute from "./PrivateMenteeRoute";
import PrivateMentorRoute from "./PrivateMentorRoute";
import Session from "../pages/session";
import ChangePassword from "../pages/ChangePassword";
import FeedbackAdd from "../pages/feedback-add";

export const ApplicationContext = React.createContext([]);

const AppRoutes = () => {
  const [user, setUser] = useState({
    isAuthenticated: false,
    token: "",
    role: -1,
  });

  const makeSignIn = (user) => {
    setUser(user);
  };
  const makeSignOut = (user) => {
    setUser({ isAuthenticated: false, token: "", role: -1 });
  };

  useLayoutEffect(() => {
    const tmpUser = getDataFromLocal("user")
      ? getDataFromLocal("user")
      : { isAuthenticated: false, token: "", role: -1 };
    setUser(tmpUser);
  }, []);

  return (
    <ApplicationContext.Provider
      value={{ user, setUser, makeSignIn, makeSignOut }}
    >
      <Router>
        <Header user={user} makeSignOut={makeSignOut} />
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/findmentor" element={<FindMentor />} />
          {/* <Route path="/person/:id" element={<PersonProfile />} /> */}

          <Route path="/mentor/:id" element={<MentorProfile />} />

          <Route path="/login" element={<SigIn />} />
          <Route path="/resgiter" element={<SigUp />} />
          <Route path="/changepassword" element={<ChangePassword/>}/>
          <Route
            path="/mentor/:id/checkout/:sessionId"
            element={<Checkout />}
          />
          <Route element={<PrivateRoute />}>
            <Route path="/feedback" element={<Feedback />} />

            <Route path="/invoice/:receipt_id" element={<InvoiceView />} />
            <Route path="/invoice" element={<Invoice />} />

            <Route path="/report/add" element={<ReportAdd />} />
            <Route path="/report/:report_id" element={<ReportDetail />} />
            <Route path="/report" element={<Report />} />
            <Route path="/profile/edit" element={<EditProfile />} />
            <Route path="/profile/:id" element={<Profile />} />
            <Route path="/profile" element={<Profile />} />
            <Route path="/bookings/:booking_id" element={<BookingsDetail />} />
            <Route path="/bookings" element={<Booking />} />
            <Route path="/dashboard" element={<Dashboard />} />
          </Route>




          <Route element={<PrivateMenteeRoute />}>
            <Route path="/favorite-mentor" element={<FavouriteMentor />} />
            <Route path="/feedback/add" element={<FeedbackAdd/>}/>
          </Route>

          <Route element={<PrivateMentorRoute />}>
            <Route path="/schedule-timings" element={<ScheduleTimings />} />
            <Route path="/sessions/:sessions_id" element={<SessionsDetail />} />
            <Route path="/sessions" element={<Session />} />
          </Route>






          {/* </Route> */}

          <Route element={<PrivateAdminRoute />}>
            {/*Call Dashboard Admin */}
            <Route path="/admin/dashboard" element={<AdminDashboard />} />
            <Route path="/admin/mentor" element={<ManageMentor />} />
            <Route path="/admin/mentee" element={<ManageMentee />} />
            <Route path="/admin/sessions" element={<ManageSession />} />
            <Route path="/admin/feedback" element={<ManageFeedback />} />
            <Route path="/admin/revenue" element={<ManageRevenue />} />
            <Route path="/admin/bookings" element={<ManageBooking />} />
            <Route path="/admin/invoice" element={<ManageInvoice />} />
            <Route path="/admin/report" element={<ManageReport />} />
            <Route path="/admin/skill" element={<ManageSkill />} />
            <Route path="/admin" element={<Navigate to={"dashboard"} />} />
          </Route>
        </Routes>

        <Footer />
      </Router>
    </ApplicationContext.Provider>
  );
};

export default AppRoutes;
