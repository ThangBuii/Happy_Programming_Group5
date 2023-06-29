

import Sidebar from "../sidebar/SideNavBar";
import styles from "./index.module.css";

const MainLayout = ({ pageTitle, layoutContent }) => {
  return (
    <div className={styles.layoutWrapper}>

      <h2 className={styles.pageTitle}>{pageTitle}</h2>
      <div className={styles.layoutContent}>
        <Sidebar/>
        {layoutContent}
      </div>
     
    </div>
  );
};

export default MainLayout;
