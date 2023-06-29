import Box from "@mui/material/Box";
import ClickAwayListener from "@mui/base/ClickAwayListener";
import React from "react";
import KeyboardArrowDownIcon from "@mui/icons-material/KeyboardArrowDown";
import styles from "./index.module.css";

const CustomInputFilter = ({ filterTitle, isDropDown, content }) => {
  const [open, setOpen] = React.useState(false);

  const handleClick = () => {
    setOpen((prev) => !prev);
  };

  const handleClickAway = () => {
    setOpen(false);
  };

  const dropDownStyles = {
    position: "absolute",
    top: 56,
    left: 0,
    zIndex: 1,
    paddingTop: "8px",
    paddingRight: "12px",
    paddingLeft: "12px",
    paddingBottom: "16px",
    bgcolor: "#fff",
    width: "240px",
    maxHeight: "320px",
    overflow: "hidden auto",
    boxShadow:
      "rgb(255, 255, 255) 0px 0px 0px 0px, rgba(0, 0, 0, 0.05) 0px 0px 0px 1px, rgba(0, 0, 0, 0.1) 0px 10px 15px -3px, rgba(0, 0, 0, 0.1) 0px 4px 6px -4px",
  };

  return (
    <ClickAwayListener
      mouseEvent="onMouseDown"
      touchEvent="onTouchStart"
      onClickAway={handleClickAway}
    >
      <Box sx={{ position: "relative", width: "fit-content" }}>
        <button className={styles.titleWrapper} onClick={handleClick}>
          <span>{filterTitle}</span>
          <KeyboardArrowDownIcon />
        </button>
        {open ? (
          isDropDown ? (
            <Box sx={dropDownStyles}>{content}</Box>
          ) : (
            { content }
          )
        ) : null}
      </Box>
    </ClickAwayListener>
  );
};

export default CustomInputFilter;
