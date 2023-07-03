import { Container } from "@mui/material";
import { useEffect, useState } from "react";
import { useNavigate  } from "react-router";
import styles from "./index.module.css";
import {request} from '../../axios_helper'
import { useParams } from "react-router-dom";


const ReportDetail = () => {
  const navigate = useNavigate();
  const {report_id} = useParams();
  const [report, setReport] = useState({});

  useEffect(() => {
    request("GET", `/api/report/${report_id}`)
      .then((response) => {
        setReport(response.data);
      })
      .catch((error) => {
        console.error(error);
      });
  }, []);

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
            <span className={styles.bcPersonProfile}>Report Detail</span>
          </div>
          <h2>Report Detail</h2>
        </div>
      </div>
      <div className={styles.containerWrapper}>
        <Container
          maxWidth="xl"
          sx={{
            padding: "30px 0 0",
          }}
        >
          <h4>
            Title <span>{report.created_date}</span>
          </h4>
          <div className={styles.reportDetail}>{report.title}</div>
          <h4>Content</h4>
          <div className={styles.reportDetail}>{report.content}</div>
          <h4>
            Answer
          </h4>
          <div className={styles.reportAnswer}>
            {report.answer || "Please wait for answering!"}
          </div>
        </Container>
      </div>
    </div>
  );
};

export default ReportDetail;
