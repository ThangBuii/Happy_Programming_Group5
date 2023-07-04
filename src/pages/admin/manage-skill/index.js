import MainAdminLayout from "../../../component/admin/main-layout";
import styles from "./index.module.css";

const breadcrumbArr = [
  { to: "/admin/dashboard", represent: "Dashboard" },
  { to: "/admin/skill", represent: "Skill" },
];

const ManageSkill = () => {
  return (
    <MainAdminLayout
      title="List of Skills"
      breadCum={[...breadcrumbArr]}
      content={<div className={styles.layoutWrapper}></div>}
    />
  );
};

export default ManageSkill;
