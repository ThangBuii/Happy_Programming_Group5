import { LinearProgress } from "@mui/material";
import styles from "./index.module.css";

const Statistic = ({ icon, quantity, about, percent, color }) => {
  return (
    <div className={styles.layoutWrapper}>
      <div className={styles.headWrapper}>
        <span className={styles.iconWrapper} style={{ borderColor: color }}>
          {icon}
        </span>
        <h3>{quantity}</h3>
      </div>
      <div className={styles.bottomWrapper}>
        <h6>{about}</h6>
        <LinearProgress
          variant="determinate"
          value={percent}
          sx={{
            height: "6px",
            borderRadius: "4px",
            backgroundColor: "#e9ecef",
            "& .MuiLinearProgress-bar": {
              backgroundColor: color || "#1976d2",
            },
          }}
        />
      </div>
    </div>
  );
};

export default Statistic;
