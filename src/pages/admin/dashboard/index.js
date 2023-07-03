import MainAdminLayout from "../../../component/admin/main-layout";
import styles from "./index.module.css";

const AdminDashboard = () => {
  return (
    <MainAdminLayout
      title="Welcome Admin"
      breadCum={[{ to: "/admin/dashboard", represent: "Dashboard" }]}
      content={<div className={styles.layoutWrapper}></div>}
    />
  );
};

export default AdminDashboard;
