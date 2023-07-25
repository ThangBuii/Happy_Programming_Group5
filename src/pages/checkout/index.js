import { useLocation, useNavigate, useParams } from "react-router";
import {
  Alert,
  Button,
  CircularProgress,
  Container,
  Slide,
  Snackbar,
  TextField,
} from "@mui/material";
import { Home, NavigateNext, Send } from "@mui/icons-material";
import { Link } from "react-router-dom";
import { useContext, useEffect, useState } from "react";
import styles from "./index.module.css";
import { request } from "../../axios_helper";
import AvatarDefault from "../../assets/avatar-thinking-3-svgrepo-com.svg";
import { ApplicationContext } from "../../routes/AppRoutes";

const Checkout = () => {
  const [sessions, setSessions] = useState([]);
  const [sessionDetail, setSessionDetail] = useState();
  const [dayChoosed, setDayChoosed] = useState();
  const [isLoading, seIsLoading] = useState(true);
  const [snackbarOpen, setSnackbarOpen] = useState(false);
  const [chosenTime, setChosenTime] = useState(); //
  const navigate = useNavigate();
  const params = useParams();
  const location = useLocation();
  const { listPrevPath } = location.state;
  const { user } = useContext(ApplicationContext);
  const role = user.role;
  const [errorMessage, setErrorMessage] = useState("");
  const [selectedOption, setSelectedOption] = useState("");
  const [successPayment, setSuccessPayment] = useState(false);
  console.log(params, location);

  useEffect(() => {
    Promise.all([
      request("GET", `/api/public/times/${params.sessionId}`),
      request("GET", `/api/public/sessions/${params.sessionId}`)
    ])
      .then(([data1, data2]) => {
        console.log(data1, data2);
        setSessions([...data1.data]);
        // Handle the data from the second request (data2) as needed
        setSessionDetail(data2.data);
      })
      .catch((err) => {
        console.log(err);
      })
      .finally(() => {
        seIsLoading(false);
      });
  }, []);
  console.log("session >>>>", sessions)

  const getSpots = (sessionsTmp, dayChoosedTmp) => {
    const selectedSession = sessionsTmp.find(
      (item) => item.schedule_date === dayChoosedTmp
    );

    if (!selectedSession || !selectedSession.times || selectedSession.times.length === 0) {
      return [];
    }

    return selectedSession.times;
  };

  return (
    <div className={styles.layoutWrapper}>
      {isLoading ? (
        <div className={styles.customLoading}>
          <CircularProgress />
        </div>
      ) : (
        <Container maxWidth="lg">
          <div className={styles.breadcumWrapper}>
            <div onClick={() => navigate("/")} className={styles.home}>
              <Home />
            </div>
            {listPrevPath &&
              listPrevPath.length > 0 &&
              listPrevPath.map((item) => {
                if (!item.to) return null;
                return (
                  <div key={item.to}>
                    <NavigateNext />
                    <Link to={item.to}>{item.represent}</Link>
                  </div>
                );
              })}
            <NavigateNext />
            <a>Book</a>
          </div>

          <div className={styles.contentWrapper}>
            <div className="row">
              <div className="col-12 col-lg-6">
                <div className={styles.timeListWrapper}>
                  {sessions.map((item) => {
                    const date = new Date(item.schedule_date);
                    const formattedDate = date.toLocaleDateString("en-GB", {
                      day: "2-digit",
                      month: "2-digit",
                      year: "numeric",
                    });

                    return (
                      <div
                        key={item.schedule_date}
                        className={`${styles.timeItemWrapper} ${item.schedule_date === dayChoosed ? styles.timeItemActive : ""
                          }`}
                        onClick={() => setDayChoosed(item.schedule_date)}
                      >
                        <span>{formattedDate}</span>
                        <span>{item.times.length} spots</span>
                      </div>
                    );
                  })}
                </div>
                <div className={styles.spotListWrapper}>
                  {getSpots(sessions, dayChoosed) && getSpots(sessions, dayChoosed).length > 0 ? (
                    getSpots(sessions, dayChoosed).map((item, index) => (
                      <div
                        key={index}
                        className={`${styles.timeItemWrapper} ${item.time_id === chosenTime ? styles.timeItemActive : ""
                          }`}
                        onClick={() => setChosenTime(item.time_id)}
                      >
                        {item.start_time}
                      </div>
                    ))
                  ) : (
                    <div className={styles.notFound}>
                      Don't have any slot
                    </div>
                  )}
                </div>
              </div>

              <div className={`col-12 col-lg-6 ${styles.customLeft}`}>
                <h2 className={styles.rightTitle}>Your Session</h2>
                <div className={styles.checkoutWrapper}>
                  <div className={styles.topWrapper}>
                    <div className={styles.imageWrapper}>
                      <img src={sessionDetail.avatar
                        ? `data:image/jpeg;base64, ${sessionDetail.avatar}`
                        : AvatarDefault} alt="avatar" />
                    </div>
                    <div className={styles.sessionName}>
                      <span>{sessionDetail.session_name}</span>
                      <span>Carried out by {sessionDetail.mentor_name}</span>
                    </div>
                  </div>

                  <div className={styles.checkoutDes}>
                    <div className={styles.checkoutItem}>
                      <span>Price per Session</span>
                      <span>${sessionDetail.price}</span>
                    </div>
                    <div className={styles.checkoutItem}>
                      <span>Duration</span>
                      <span>{sessionDetail.duration} minutes</span>
                    </div>
                    <div className={styles.checkoutMain}>
                      <span>Total</span>
                      <span>${sessionDetail.price}</span>
                    </div>
                  </div>
                  <div className={styles.paymentWidget}>
                    <h4 className="card-title">Payment Method</h4>
                    <div className={styles.radioWrapper}>
                      <label className={styles.paymentRatio}>
                        <input
                          type="radio"
                          name="radio"
                          className={styles.customRadio}
                          value="Credit Card"
                          checked={selectedOption === "Credit Card"}
                          onChange={(e) => setSelectedOption(e.target.value)}
                        />
                        <span className="checkmark"></span>Credit card
                      </label>
                      <label className={styles.paymentRatio}>
                        <input
                          type="radio"
                          name="radio"
                          className={styles.customRadio}
                          value="Paypal"
                          checked={selectedOption === "Paypal"}
                          onChange={(e) => setSelectedOption(e.target.value)}
                        />
                        <span className="checkmark"></span>Paypal
                      </label>
                    </div>
                  </div>
                  <div className={styles.actionWrapper}>
                    <Button
                      variant="contained"
                      endIcon={<Send />}
                      fullWidth
                      onClick={() => {
                        const data1 = {
                          time_id: chosenTime,
                          payment_method: selectedOption,
                        }
                      
                        if (role === -1) {
                          setSnackbarOpen(true);
                          setErrorMessage("Please login in order to book this session!")
                          setSuccessPayment(false);
                          return
                        } else if (role === 0 || role === 1) {
                          setSnackbarOpen(true);
                          setErrorMessage("Please login as mentee in order to book this session!")
                          setSuccessPayment(false);
                          return
                        }else if(!chosenTime){
                          setSnackbarOpen(true);
                          setErrorMessage("Please choose your date")
                          setSuccessPayment(false);
                          return;
                        }else if(!selectedOption){
                          setSnackbarOpen(true);
                          setErrorMessage("Please choose your payment method")
                          setSuccessPayment(false);
                          return;
                        }
                        request("POST", "/api/mentee/booking", data1)
                          .then((response) => {
                            // Handle the response from the API request

                            // Set any necessary state or perform additional actions
                            setSnackbarOpen(true);
                            setSuccessPayment(true);
                            setErrorMessage("Payment Success!");
                          })
                          .catch((error) => {
                            // Handle errors from the API request
                            console.error("Error:", error);
                          })      
                      }}
                    >
                      Confirm and Pay
                    </Button>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </Container>
      )
      }
      <Snackbar
        anchorOrigin={{ vertical: "top", horizontal: "right" }}
        open={snackbarOpen}
        autoHideDuration={2000}
        onClose={() => {
          setSnackbarOpen(false);
          if(successPayment){
              navigate("/");
          }else{
            if (role === -1 || role === 0 || role === 1) {
              navigate("/login");
            }
          }
        }}
        style={{ marginTop: "40px" }}
        TransitionComponent={({ children }) => (
          <Slide direction="left" in={snackbarOpen}>
            {children}
          </Slide>
        )}
      >
        <Alert severity={successPayment? "success" : "error"} variant="filled" sx={{ width: "100%" }}>
          {errorMessage}
        </Alert>
      </Snackbar>
    </div >
  );
};

export default Checkout;
