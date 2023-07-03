import { useNavigate } from "react-router";
import Sidebar from "../sidebar";
import styles from "./index.module.css";

const MainLayout = ({ pageTitle, layoutContent, titleControl }) => {
  const navigate = useNavigate();

  return (
    <div className={styles.layoutWrapper}>
      <div className={styles.breadcumBarWrapper}>
        <div className={styles.bcLeft}>
          <div>
            <span className={styles.bcHome} onClick={() => navigate("/")}>
              Home
            </span>
            <span className={styles.bcPersonProfile}>{pageTitle}</span>
          </div>
          <h2>{pageTitle}</h2>
        </div>
        <div className={styles.bcRight}>{titleControl}</div>
      </div>
      <div className={styles.layoutContent}>
        <Sidebar />
        <div className={styles.contentWrapper}>{layoutContent}</div>
      </div>
    </div>
  );
};

export default MainLayout;
