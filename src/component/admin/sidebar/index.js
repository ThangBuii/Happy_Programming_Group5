import { NavLink } from "react-router-dom";
import HomeOutlinedIcon from "@mui/icons-material/HomeOutlined";
import styles from "./index.module.css";

export const linkAdminList = [
  {
    to: "/admin/dashboard",
    represent: "Dashboard",
  },
  {
    to: "/admin/bookings",
    represent: "Bookings",
  },
  {
    to: "/admin/mentor",
    represent: "Mentor",
  },
  {
    to: "/admin/mentee",
    represent: "Mentee",
  },
  {
    to: "/admin/sessions",
    represent: "Sessions",
  },
  {
    to: "/admin/feedback",
    represent: "Feedback",
  },
  {
    to: "/admin/skill",
    represent: "Skill",
  },
  {
    to: "/admin/report",
    represent: "Report",
  },
  {
    to: "/admin/revenue",
    represent: "Revenue",
  },
  {
    to: "/admin/invoice",
    represent: "Invoice",
  },
];

const AdminSidebar = () => {
  // const location = useLocation();

  const navLinkClass = ({ isActive }) => {
    return isActive ? styles.activeLink : styles.normalLink;
  };

  return (
    <div className={styles.sidebarWrapper}>
      <li className={styles.menuTitle}>
        <span>
          <HomeOutlinedIcon /> Main
        </span>
      </li>
      {linkAdminList.map((linkItem) => (
        // <div
        //   key={linkItem.to}
        //   className={
        //     location.pathname === linkItem.to
        //       ? `${styles.linkWrapper} ${styles.linkWrapperActive}`
        //       : styles.linkWrapper
        //   }
        // >
        <NavLink key={linkItem.to} to={linkItem.to} className={navLinkClass}>
          {linkItem.represent}
        </NavLink>
        // </div>
      ))}
    </div>
  );
};

export default AdminSidebar;
