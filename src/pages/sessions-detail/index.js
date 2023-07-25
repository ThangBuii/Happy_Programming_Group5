import { CircularProgress, Container } from "@mui/material";
import { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router";
import { request } from "../../axios_helper";
import { useParams } from "react-router-dom";
import AvatarDefault from "../../assets/avatar-thinking-3-svgrepo-com.svg";
import styles from "./index.module.css";


const SessionsDetail = () => {
  const location = useLocation();
  const prevPath = location.state?.prevPath || null;
  const navigate = useNavigate();
  const { sessions_id } = useParams();
  const [sessions, setSessions] = useState({});
  const [isLoading, seIsLoading] = useState(true);

  useEffect(() => {
    seIsLoading(true);
    request("GET", `/api/public/sessions/${sessions_id}`)
      .then((response) => {
        setSessions(response.data);
      })
      .catch((error) => {
        console.error(error);
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
                  src={sessions.avatar ? `data:image/jpeg;base64, ${sessions.avatar}` : AvatarDefault}
                  alt="avatar"
                />
                <div className={styles.infoLeft}>
                  <h4>{sessions.mentor_name}</h4>
                </div>
              </div>
            </div>
            <div className={styles.contentWrapper}>
              <h4>Session Name</h4>
              <div className={styles.sessionDetail}>{sessions.session_name}</div>
              <h4>Session Duration</h4>
              <div className={styles.sessionDetail}>{sessions.duration}</div>
              <h4>Session Price</h4>
              <div className={styles.sessionDetail}>{sessions.price}</div>
              <h4>Session Description</h4>
              <div className={styles.sessionDetail}>{sessions.description}</div>
              <h4>Session Skills</h4>
              <div className={styles.skillList}>
                
                  <div className={styles.skillItem}>
                    {sessions.skill_name}
                  </div>
                
              </div>
            </div>
          </Container>
        )}
      </div>
    </div>
  );
};

export default SessionsDetail;
