import { useLocation, useNavigate, useParams } from "react-router";
import { Button, CircularProgress, Container, TextField } from "@mui/material";
import { Home, NavigateNext, Send } from "@mui/icons-material";
import { Link } from "react-router-dom";
import { useEffect, useState } from "react";
import styles from "./index.module.css";

const fakeSessionsDetail = {
  sessionId: "session1",
  sessionName: "Lam ngheo khong kho",
  mentor: {
    id: "user1",
    name: "A Minh Quan",
    imageUrl:
      "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTu5iuH9GH49VUAv0qvlrKiFRnsgEC6maRA9g&usqp=CAU",
  },
  price: 79,
  duration: 30,
  timeList: [
    {
      day: "Mon",
      spots: [
        { spotFrom: 8.3, spotTo: 11.0 },
        { spotFrom: 12.3, spotTo: 13.0 },
      ],
    },
    {
      day: "Tue",
      spots: [
        { spotFrom: 9.0, spotTo: 10.0 },
        { spotFrom: 14.0, spotTo: 16.0 },
      ],
    },
    {
      day: "Wed",
      spots: [
        { spotFrom: 11.0, spotTo: 12.0 },
        { spotFrom: 13.0, spotTo: 14.0 },
        { spotFrom: 15.0, spotTo: 16.0 },
      ],
    },
    {
      day: "Thu",
      spots: [
        { spotFrom: 10.0, spotTo: 11.0 },
        { spotFrom: 12.0, spotTo: 13.0 },
        { spotFrom: 14.0, spotTo: 15.0 },
        { spotFrom: 16.0, spotTo: 17.0 },
      ],
    },
    {
      day: "Fri",
      spots: [{ spotFrom: 9.0, spotTo: 12.0 }],
    },
    {
      day: "Sat",
      spots: [
        { spotFrom: 9.0, spotTo: 10.0 },
        { spotFrom: 11.0, spotTo: 12.0 },
        { spotFrom: 13.0, spotTo: 14.0 },
        { spotFrom: 15.0, spotTo: 16.0 },
      ],
    },
    {
      day: "Sun",
      spots: [],
    },
  ],
};
const Checkout = () => {
  const [sessions, setSessions] = useState({});
  const [dayChoosed, setDayChoosed] = useState("Mon");
  const [isLoading, seIsLoading] = useState(true);
  const navigate = useNavigate();
  const params = useParams();
  const location = useLocation();
  const { listPrevPath } = location.state;

  console.log(params, location);

  useEffect(() => {
    fetch(`http://localhost:9999/sessions/${params.id}/${params.sessionId}`) // lay theo sessionId thoi nhi???
      .then((resp) => resp.json())
      .then((data) => {
        setSessions({ ...data });
      })
      .catch((err) => {
        console.log(err);
        setSessions({ ...fakeSessionsDetail });
      })
      .finally(() => {
        seIsLoading(false);
      });
  }, []);

  const getSpots = (sessionsTmp, dayChoosedTmp) => {
    if (!sessionsTmp.timeList || sessionsTmp.timeList.length === 0) return [];
    const result = sessionsTmp.timeList.find(
      (item) => item.day === dayChoosedTmp
    ).spots;
    return result || [];
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
                  {sessions.timeList.map((item) => (
                    <div
                      key={item.day}
                      className={`${styles.timeItemWrapper} ${
                        item.day === dayChoosed ? styles.timeItemActive : ""
                      }`}
                      onClick={() => setDayChoosed(item.day)}
                    >
                      <span>{item.day}</span>
                      <span>{item.spots.length} spots</span>
                    </div>
                  ))}
                </div>
                <div className={styles.spotListWrapper}>
                  {getSpots(sessions, dayChoosed) &&
                  getSpots(sessions, dayChoosed).length > 0 ? (
                    getSpots(sessions, dayChoosed).map((item, index) => (
                      <div key={index} className={styles.spotItem}>
                        {item.spotFrom.toFixed(2)} - {item.spotTo.toFixed(2)}
                      </div>
                    ))
                  ) : (
                    <div className={styles.notFound}>
                      Don't have any slot on this day!
                    </div>
                  )}
                </div>
              </div>

              <div className={`col-12 col-lg-6 ${styles.customLeft}`}>
                <h2 className={styles.rightTitle}>Your Session</h2>
                <div className={styles.checkoutWrapper}>
                  <div className={styles.topWrapper}>
                    <div className={styles.imageWrapper}>
                      <img src={sessions.mentor.imageUrl} alt="avatar" />
                    </div>
                    <div className={styles.sessionName}>
                      <span>{sessions.sessionName}</span>
                      <span>Carried out by {sessions.mentor.name}</span>
                    </div>
                  </div>

                  <div className={styles.checkoutDes}>
                    <div className={styles.checkoutItem}>
                      <span>Price per Session</span>
                      <span>${sessions.price}</span>
                    </div>
                    <div className={styles.checkoutItem}>
                      <span>Duration</span>
                      <span>{sessions.duration} minutes</span>
                    </div>
                    <div className={styles.checkoutItem}>
                      <span>Discount Code</span>
                      <TextField
                        sx={{
                          "& input": {
                            padding: "8px 12px",
                            fontSize: "14px",
                          },
                          "& input:focus": {
                            width: "auto",
                            margin: "0",
                            borderBottom: "0px solid",
                          },
                        }}
                        variant="outlined"
                        placeholder="Discount Code"
                      />
                    </div>
                    <div className={styles.checkoutMain}>
                      <span>Total</span>
                      <span>${sessions.price}</span>
                    </div>
                  </div>
                  <div className={styles.actionWrapper}>
                    <Button variant="contained" endIcon={<Send />} fullWidth>
                      Send
                    </Button>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </Container>
      )}
    </div>
  );
};

export default Checkout;
