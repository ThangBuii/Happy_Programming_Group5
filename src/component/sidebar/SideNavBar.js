import { Link, useLocation } from "react-router-dom";
import styles from "./index.module.css";

const linkObjList = [
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
    to: "/messages",
    represent: "Messages",
  },
  {
    to: "/receipt",
    represent: "Receipt",
  },
  {
    to: "/feedback",
    represent: "Feedback",
  },
  {
    to: "/report",
    represent: "Report",
  },
];

const Sidebar = () => {
  const location = useLocation();
  console.log(location);

  return (
    <div className={styles.sidebarWrapper}>
      <img
        src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT-Q_ZZIlFsebqqPjyA-zpNe_6a0mLgs2IlLQ&usqp=CAU"
        alt="avatar"
      />
      
      {linkObjList.map((linkItem) => (
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
      ))}
    </div>
  );
};

export default Sidebar;
