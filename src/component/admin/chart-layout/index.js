import styles from "./index.module.css";

const ChartLayout = ({ title, content }) => {
  return (
    <div className={styles.layoutWrapper}>
      <div className={styles.headWrapper}>
        <h4>{title}</h4>
      </div>
      <div className={styles.bottomWrapper}>{content}</div>
    </div>
  );
};

export default ChartLayout;
