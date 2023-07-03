import { Breadcrumbs } from "@mui/material";
import AdminSidebar from "../sidebar";
import { Link } from "react-router-dom";
import styles from "./index.module.css";

const MainAdminLayout = ({ title, breadCum, content }) => {
  return (
    <div className={styles.layoutWrapper}>
      <AdminSidebar />
      <div className={styles.contentWrapper}>
        <h3>{title}</h3>
        <Breadcrumbs sx={{ textDecoration: "none" }}>
          {breadCum.length > 0 &&
            breadCum.map((item, index) => (
              <Link
                className={
                  index !== breadCum.length - 1
                    ? styles.notLastBC
                    : styles.lastBC
                }
                to={item.to}
              >
                {item.represent}
              </Link>
            ))}
        </Breadcrumbs>
        <div className={styles.contentDetailWrapper}>{content}</div>
      </div>
    </div>
  );
};

export default MainAdminLayout;
