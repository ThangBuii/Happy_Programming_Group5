import { Link, useLocation } from "react-router-dom";
import styles from "./index.module.css";
import { useContext } from "react";
import { ApplicationContext } from "../../routes/AppRoutes";

export const linkObjList = [
  {
    to: "/dashboard",
    represent: "Dashboard",
  },
  {
    to: "/bookings",
    represent: "Bookings",
  },
  {
    to: "/favorite-mentor",
    represent: "Favorite Mentor",
  },
  {
    to: "/feedback",
    represent: "Feedback",
  },
  {
    to: "/schedule-timings",
    represent: "Schedule Timings",
  },
  {
    to: "/report",
    represent: "Report",
  },
  {
    to: "/invoice",
    represent: "Invoice",
  },
  {
    to: "/sessions",
    represent: "Sessions",
  },
  {
    to: "/profile",
    represent: "Profile",
  },
  {
    to: "/changepassword",
    represent: "Password",
  }
];

const Sidebar = () => {
  const location = useLocation();
  const { user } = useContext(ApplicationContext);
  const role = user.role;

  const showFavoriteMentorLink = role === 2;
  const showScheduleTimingsLink = role === 1;
  const showSessionLink = role === 1;

  return (
    <div className={styles.sidebarWrapper}>
      <img
        src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT-Q_ZZIlFsebqqPjyA-zpNe_6a0mLgs2IlLQ&usqp=CAU"
        alt="avatar"
      />

      {linkObjList.map((linkItem) =>
        // Only render the Favorite Mentor link if showFavoriteMentorLink is true
        (linkItem.to === "/favorite-mentor" && !showFavoriteMentorLink) ||
        (linkItem.to === "/sessions" && !showSessionLink) ||
        // Only render the Schedule Timings link if showScheduleTimingsLink is true
        (linkItem.to === "/schedule-timings" &&
          !showScheduleTimingsLink) ? null : (
          <div
            key={linkItem.to}
            className={
              location.pathname === linkItem.to
                ? `${styles.linkWrapper} ${styles.linkWrapperActive}`
                : styles.linkWrapper
            }
          >
            <Link to={linkItem.to}>{linkItem.represent}</Link>
          </div>
        )
      )}
    </div>
  );
};

export default Sidebar;
