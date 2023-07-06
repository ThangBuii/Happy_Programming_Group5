import { CircularProgress, Container } from "@mui/material";
import { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router";
import { request } from "../../axios_helper";
import { useParams } from "react-router-dom";
import AvatarDefault from "../../assets/avatar-thinking-3-svgrepo-com.svg";
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
  created_date: "October 13, 2014",
  timeList: [
    {
      day: "Mon",
      spots: [
        { slotFrom: "8:30", slotTo: "11:00" },
        { slotFrom: "12:30", slotTo: "13:00" },
      ],
    },
    {
      day: "Tue",
      spots: [
        { slotFrom: "9:00", slotTo: "10:00" },
        { slotFrom: "14:00", slotTo: "16:00" },
      ],
    },
    {
      day: "Wed",
      spots: [
        { slotFrom: "11:00", slotTo: "12:00" },
        { slotFrom: "13:00", slotTo: "14:00" },
        { slotFrom: "15:00", slotTo: "16:00" },
      ],
    },
    {
      day: "Thu",
      spots: [
        { slotFrom: "10:00", slotTo: "11:00" },
        { slotFrom: "12:00", slotTo: "13:00" },
        { slotFrom: "14:00", slotTo: "15:00" },
        { slotFrom: "16:00", slotTo: "17:00" },
      ],
    },
    {
      day: "Fri",
      spots: [{ slotFrom: "9:00", slotTo: "12:00" }],
    },
    {
      day: "Sat",
      spots: [
        { slotFrom: "9:00", slotTo: "10:00" },
        { slotFrom: "11:00", slotTo: "12:00" },
        { slotFrom: "13:00", slotTo: "14:00" },
        { slotFrom: "15:00", slotTo: "16:00" },
      ],
    },
    {
      day: "Sun",
      spots: [],
    },
  ],
  skillList: [
    "NestJS",
    "React",
    "Typescript",
    "Ionic",
    "Framework",
    "NodeJS",
    "UX",
    "Design",
    "Node",
    "Angular",
    "C#",
    "AWS",
    "JavaScript",
    "Coding",
  ],
};

const SessionsDetail = () => {
  const location = useLocation();
  const prevPath = location.state?.prevPath || null;
  const navigate = useNavigate();
  const { sessions_id } = useParams();
  const [sessions, setSessions] = useState({});
  const [isLoading, seIsLoading] = useState(true);
  const [dayChoosed, setDayChoosed] = useState("Mon");

  useEffect(() => {
    seIsLoading(true);
    request("GET", `/api/sessions/${sessions_id}`)
      .then((response) => {
        setSessions(response.data);
      })
      .catch((error) => {
        console.error(error);
        setSessions({ ...fakeSessionsDetail });
      })
      .finally(() => {
        seIsLoading(false);
      });
  }, [sessions_id]);

  const getSpots = (sessionsTmp, dayChoosedTmp) => {
    if (!sessionsTmp.timeList || sessionsTmp.timeList.length === 0) return [];
    const result = sessionsTmp.timeList.find(
      (item) => item.day === dayChoosedTmp
    ).spots;
    return result || [];
  };

  return (
    <div className={styles.layoutWrapper}>
      <div className={styles.breadcumBarWrapper}>
        <div className={styles.bcLeft}>
          <div>
            <span className={styles.bcHome} onClick={() => navigate("/")}>
              Home
            </span>
            {prevPath && (
              <span
                className={styles.bcPersonProfile}
                onClick={() => navigate(prevPath.to)}
              >
                {prevPath.represent}
              </span>
            )}
            <span className={styles.bcPersonProfile}>Session Detail</span>
          </div>
          <h2>Session Detail</h2>
        </div>
      </div>
      <div className={styles.containerWrapper}>
        {isLoading ? (
          <div className={styles.loadingWrapper}>
            <CircularProgress />
          </div>
        ) : (
          <Container
            maxWidth="xl"
            sx={{
              padding: "30px 0 0",
            }}
          >
            <div className={styles.topWrapper}>
              <div className={styles.mentorInfoWrapper}>
                <img
                  src={sessions.mentor.imageUrl || AvatarDefault}
                  alt="avatar"
                />
                <div className={styles.infoLeft}>
                  <h4>{sessions.mentor.name}</h4>
                </div>
              </div>
              <span>{sessions.created_date}</span>
            </div>
            <div className={styles.contentWrapper}>
              <h4>Session Name</h4>
              <div className={styles.sessionDetail}>{sessions.sessionName}</div>
              <h4>Session Spots</h4>
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
                      {item.slotFrom} - {item.slotTo}
                    </div>
                  ))
                ) : (
                  <div className={styles.notFound}>
                    Don't have any slot on this day!
                  </div>
                )}
              </div>
              <h4>Session Skills</h4>
              <div className={styles.skillList}>
                {sessions.skillList.map((skill, index) => (
                  <div key={index} className={styles.skillItem}>
                    {skill}
                  </div>
                ))}
              </div>
            </div>
          </Container>
        )}
      </div>
    </div>
  );
};

export default SessionsDetail;
