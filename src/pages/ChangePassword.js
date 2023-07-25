import React, { useState } from "react";
import { request } from "../axios_helper";
import { Snackbar, Slide, Alert } from "@mui/material";
import "../styles/SignIn.css";
import { setDataToLocal } from "../axios_helper";
import { Link } from "react-router-dom";

const ChangePassword = ({ userName }) => {
  const [oldPassword, setOldPassword] = useState("");
  const [newPassword, setNewPassword] = useState("");
  const [rePass, setRepass] = useState("");
  const [isValid, setIsValid] = useState(true);
  const [errorMessage, setErrorMessage] = useState("");
  const [snackbarOpen, setSnackbarOpen] = useState(false);




  const handleChangePassword = () => {

    const payload = {


      old_password: oldPassword,
      new_password: newPassword,
      repass: rePass,

    };

    request("POST", "/api/password", payload)
      .then((response) => {
        // Handle the successful password change here
        setSnackbarOpen(true);
        setErrorMessage("Password changed successfully.");

        const user = {
          isAuthenticated: true,
          token: response.data.account.token,
          role: response.data.account.role,
        };
        setDataToLocal("token", user.token);
        setDataToLocal("user", user);

        // Redirect to sign-in page
        setTimeout(() => {
          window.location.href = "/dashboard"; // Perform a full page reload to ensure the new token and user data is updated
        }, 2000); // Wait for 2 seconds before redirecting
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

    <div className="login-bg">
      <div className="login-container">
        <div className="login-content-row">
          <div className="col-12 text-login">Change Password</div>
          <div className="col-12 form-group login-input">
            <label>Old Password:</label>
            <input
              type="password"
              value={oldPassword}
              onChange={(e) => setOldPassword(e.target.value)}
              className="form-control"
              placeholder="Enter your old password"
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

          <div className="col-12 form-group login-input">
            <label>Repass:</label>
            <input
              type="password"
              value={rePass}
              onChange={(e) => setRepass(e.target.value)}
              className="form-control"
              placeholder="Enter your Repass"
            />
          </div>

          <div>
            <Link to={"/dashboard"}>   <button className="btn-login" onClick={handleChangePassword}>
            Change Password
            </button>
            </Link>
          </div>
        </div>
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
