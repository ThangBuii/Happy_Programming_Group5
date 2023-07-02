import MainLayout from "../../component/main-layout";
import styles from "./index.module.css";

const InvoiceView = () => {
  return (
    <MainLayout
      pageTitle={"Invoice View"}
      layoutContent={<div className={styles.layoutWrapper}></div>}
    />
  );
};

export default InvoiceView;
