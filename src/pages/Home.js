
import { Alert, Slide, Snackbar } from "@mui/material";
import Hero from "../component/Hero";
import MainHeader from "../component/MainHeader";
import { useEffect, useState } from "react";
import { useLocation  } from "react-router-dom";
const Home = () => {
  const [errorMessage, setErrorMessage] = useState("");
  const [snackbarOpen, setSnackbarOpen] = useState(false);
  const location = useLocation();
  
  useEffect(() => {
    // Check if there's an error message in the location state
    const errorMessageFromState = location.state?.errorMessage || null;

    if (errorMessageFromState) {
      // If there's an error message, show the Snackbar
      setSnackbarOpen(true);
      setErrorMessage(errorMessageFromState);

      // Clear the error message in the location state after showing it
      const updatedState = { ...location.state, errorMessage: "" };
      window.history.replaceState(updatedState, "");
    }
  }, []);

  // Function to handle Snackbar close
  const handleSnackbarClose = () => {
    setSnackbarOpen(false);
  };

  return (
    <>
      <MainHeader />
      <Hero />
      <div>
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
    </>
  );
};
export default Home;
