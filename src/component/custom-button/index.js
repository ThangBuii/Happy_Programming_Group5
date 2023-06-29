import React from "react";
import styles from "./index.module.css";

const CustomButton = ({ content, onClick }) => {
  return (
    <button className={styles.cutomBtn} onClick={onClick}>
      {content}
    </button>
  );
};

export default CustomButton;
