import React, { useState } from "react";
import { request } from "../axios_helper";
import { Snackbar, Slide, Alert } from "@mui/material";
import "../styles/SignIn.css";
const ChangePassword = ({ userName }) => {
  const [currentPassword, setCurrentPassword] = useState("");
  const [newPassword, setNewPassword] = useState("");
  const [isValid, setIsValid] = useState(true);
  const [errorMessage, setErrorMessage] = useState("");
  const [snackbarOpen, setSnackbarOpen] = useState(false);

  const handleChangePassword = () => {
    const payload = {
      account: {
        email: userName,
        currentPassword: currentPassword,
        newPassword: newPassword,
      },
    };

    request("POST", "/api/changePassword", payload)
      .then(() => {
        // Handle the successful password change here
        setSnackbarOpen(true);
        setErrorMessage("Password changed successfully.");
        // You can add any additional logic or redirection after the password change
      })
      .catch((error) => {
        // Handle the error here (e.g., show an error message)
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
    <div>
      {/* Your change password form and UI elements */}
      <div className="col-12 form-group login-input">
        <label>Current Password:</label>
        <input
          type="password"
          value={currentPassword}
          onChange={(e) => setCurrentPassword(e.target.value)}
          className="form-control"
          placeholder="Enter your current password"
        />
      </div>

      <div className="col-12 form-group login-input">
        <label>New Password:</label>
        <input
          type="password"
          value={newPassword}
          onChange={(e) => setNewPassword(e.target.value)}
          className="form-control"
          placeholder="Enter your new password"
        />
      </div>

      <div>
        <button className="btn-login" onClick={handleChangePassword}>
          Change Password
        </button>
      </div>

      <Snackbar
        anchorOrigin={{ vertical: "top", horizontal: "right" }}
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
        <Alert severity={isValid ? "success" : "error"} variant="filled" sx={{ width: "100%" }}>
          {errorMessage}
        </Alert>
      </Snackbar>
    </div>
  );
};

export default ChangePassword;
