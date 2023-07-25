import { useNavigate } from "react-router-dom";
import "../styles/SignIn.css";
import {} from "react-bootstrap";
import { useContext, useState } from "react";
import { request, setDataToLocal } from "../axios_helper";
import { ApplicationContext } from "../routes/AppRoutes";
import { Alert, Slide, Snackbar } from "@mui/material";

const SignUp = () => {
  const navigate = useNavigate();

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [username, setUsername] = useState("");
  const [role, setRole] = useState("");
  const [isValid, setIsValid] = useState(true);
  const [errorMessage, setErrorMessage] = useState("");
  const [snackbarOpen, setSnackbarOpen] = useState(false);

  const { makeSignIn } = useContext(ApplicationContext);

  const handleSubmit = (e) => {
    e.preventDefault();
    const payload = {
      account: {
        username: username,
        email: email,
        password: password,
        role: role === "" ? "" : role === "mentor" ? "1" : "2",
      },
    };

    request("POST", "/api/public/register", payload)
      .then((response) => {
        const user = {isAuthenticated: true, token: response.data.account.token, role: response.data.account.role}
        setDataToLocal("token", user.token);
        setDataToLocal("user", user);
        makeSignIn({...user});
        // Handle the response here (e.g., navigate to a new page)
        navigate("/");
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
    <div></div>
      <section class="background-radial-gradient overflow-hidden">
        <div class="container px-4 py-5 px-md-5 text-center text-lg-start my-5">
          <div class="row gx-lg-5 align-items-center mb-5">
            <div class="col-lg-6 mb-5 mb-lg-0">
              <h1 class="my-5 display-5 fw-bold ls-tight">
                The best offer <br />
                <span>for your business</span>
              </h1>
              <p class="mb-4 opacity-70">
                Lorem ipsum dolor, sit amet consectetur adipisicing elit.
                Temporibus, expedita iusto veniam atque, magni tempora mollitia
                dolorum consequatur nulla, neque debitis eos reprehenderit quasi
                ab ipsum nisi dolorem modi. Quos?
              </p>
            </div>

            <div class="col-lg-6 mb-5 mb-lg-0 position-relative">
              <div
                id="radius-shape-1"
                class="position-absolute rounded-circle shadow-5-strong"
              ></div>
              <div
                id="radius-shape-2"
                class="position-absolute shadow-5-strong"
              ></div>

              <div class="">
                <div class="card-body px-4 py-5 px-md-5">
                  <form>
                    <div class="form-outline mb-4">
                      <label class="form-label" for="form3Example3">
                        Username
                      </label>
                      <input
                        type="text"
                        value={username}
                        onChange={(e) => {
                          setUsername(e.target.value);
                        }}
                        class="form-control"
                      />
                    </div>

                    <div class="form-outline mb-4">
                      <label class="form-label" for="form3Example3">
                        Email address
                      </label>
                      <input
                        type="email"
                        value={email}
                        onChange={(e) => {
                          setEmail(e.target.value);
                        }}
                        class="form-control"
                      />
                    </div>
                    <div class="form-outline mb-4">
                      <label class="form-label" for="form3Example4">
                        Password
                      </label>
                      <input
                        type="password"
                        value={password}
                        onChange={(e) => {
                          setPassword(e.target.value);
                        }}
                        class="form-control"
                      />
                    </div>

                    <div class="form-check sign-up-input">
                      <input
                        type="radio"
                        className="form-check-input"
                        id="menteeRadio"
                        name="role"
                        value="mentee"
                        checked={role === "mentee"}
                        onChange={(e) => {
                          setRole(e.target.value);
                        }}
                      />
                      <label class="form-check-label" for="menteeRadio">
                        Mentee
                      </label>
                    </div>

                    <div class="form-check sign-up-input">
                      <input
                        type="radio"
                        className="form-check-input"
                        id="mentorRadio"
                        name="role"
                        value="mentor"
                        checked={role === "mentor"}
                        onChange={(e) => {
                          setRole(e.target.value);
                        }}
                      />
                      <label class="form-check-label" for="mentorRadio">
                        Mentor
                      </label>
                    </div>
                    <button
                      onClick={handleSubmit}
                      type="submit"
                      class="btn btn-primary btn-block mb-4"
                    >
                      Sign up
                    </button>
              

                    
                  </form>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>
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

export default SignUp;
