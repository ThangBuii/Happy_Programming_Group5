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
  duration: "30",
  price: 200,
  description: "Nice session to become billion",
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
              <h4>Session Duration</h4>
              <div className={styles.sessionDetail}>{sessions.duration}</div>
              <h4>Session Price</h4>
              <div className={styles.sessionDetail}>{sessions.price}</div>
              <h4>Session Description</h4>
              <div className={styles.sessionDetail}>{sessions.description}</div>
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
