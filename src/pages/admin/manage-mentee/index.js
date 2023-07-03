import MainAdminLayout from "../../../component/admin/main-layout";
import styles from "./index.module.css";

const breadcrumbArr = [
  { to: "/admin/dashboard", represent: "Dashboard" },
  { to: "/admin/mentee", represent: "Mentee" },
];

const ManageMentee = () => {
  return (
    <MainAdminLayout
      title="List of Mentee"
      breadCum={[...breadcrumbArr]}
      content={<div className={styles.layoutWrapper}></div>}
    />
  );
};

export default ManageMentee;
