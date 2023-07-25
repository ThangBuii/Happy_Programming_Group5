import { Alert, Button, Container, Slide, Snackbar, TextField } from "@mui/material";
import styles from "./index.module.css";
import { useNavigate } from "react-router";
import { useState } from "react";
import { request } from "../../axios_helper";

const ReportAdd = () => {
  const navigate = useNavigate();
  const [inputValue, setInputValue] = useState({ title: "", content: "" });
  const [errorMessage, setErrorMessage] = useState("");
  const [snackbarOpen, setSnackbarOpen] = useState(false);
  const handleSubmit = (e) => {
    e.preventDefault();
    

    request("POST", "/api/men/report", inputValue)
      .then((response) => {
        navigate("/report")
        
      })
      .catch((error) => {
        setSnackbarOpen(true);
        if (
          error.response &&
          error.response.data &&
          error.response.data.errors
        ) {
          setErrorMessage(error.response.data.errors.message);
        } else {
          setErrorMessage("An error occurred. Please try again.");
        }
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
      <Snackbar
      anchorOrigin={{ vertical: 'top', horizontal: 'right' }}
        open={snackbarOpen}
        autoHideDuration={2000}
        onClose={() => setSnackbarOpen(false)}
        style={{ marginTop: "40px" }} 
        TransitionComponent={({ children }) => (
          <Slide direction="left" in={snackbarOpen}>
            {children}
          </Slide>
        )}
      >
        <Alert severity="error" variant="filled" sx={{ width: "100%" }}>
          {errorMessage}
        </Alert>
      </Snackbar>
    </div>
  );
};

export default ReportAdd;
