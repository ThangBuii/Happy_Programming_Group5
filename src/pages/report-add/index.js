import { Button, Container, TextField } from "@mui/material";
import styles from "./index.module.css";
import { useNavigate } from "react-router";
import { useState } from "react";
import { request } from "../../axios_helper";

const ReportAdd = () => {
  const navigate = useNavigate();
  const [inputValue, setInputValue] = useState({ title: "", content: "" });

  const handleSubmit = (e) => {
    e.preventDefault();
    

    request("POST", "/api/men/report", inputValue)
      .then((response) => {
        navigate("/report")
        
      })
      .catch((error) => {
        // Handle the error here (e.g., show an error message)
       
      
        console.error(error);
      });

  };
  return (
    <div className={styles.layoutWrapper}>
      <div className={styles.breadcumBarWrapper}>
        <div className={styles.bcLeft}>
          <div>
            <span className={styles.bcHome} onClick={() => navigate("/")}>
              Home
            </span>
            <span
              className={styles.bcPersonProfile}
              onClick={() => navigate("/report")}
            >
              Report
            </span>
            <span className={styles.bcPersonProfile}>Add Report</span>
          </div>
          <h2>Add Report</h2>
        </div>
      </div>
      <div className={styles.containerWrapper}>
        <Container
          maxWidth="xl"
          sx={{
            padding: "30px 0 0",
          }}
        >
          <h4>Title</h4>
          <TextField
            sx={{ mb: "20px" }}
            fullWidth
            hiddenLabel
            value={inputValue.title}
            onChange={(event) => {
              setInputValue((pre) => ({ ...pre, title: event.target.value }));
            }}
          />
          <h4>Content</h4>
          <TextField
            sx={{ mb: "20px" }}
            fullWidth
            hiddenLabel
            value={inputValue.content}
            onChange={(event) => {
              setInputValue((pre) => ({ ...pre, content: event.target.value }));
            }}
            multiline
            rows={3}
          />
          <div className={styles.reportAction}>
            <Button onClick={handleSubmit} variant="contained" sx={{ width: "200px" }}>
              Add
            </Button>
          </div>
        </Container>
      </div>
    </div>
  );
};

export default ReportAdd;
