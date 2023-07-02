import { Container } from "@mui/material";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router";
import styles from "./index.module.css";

function createData(reportId, title, content, createDate, answer) {
  return { reportId, title, content, createDate, answer };
}

const fakeReportData = createData(
  "report1",
  "Chat luong gio hoc te",
  "Nhóm học tập rất cần thiết trong dạy học theo định hướng phát triển năng lực người học. Khi học theo nhóm các em được chia sẻ ý kiến cho nhau, được hỗ trợ giúp đỡ nhau để cùng tiến bộ nhằm phát triển năng lực và phẩm chất, hoàn thiện bản thân trong quá trình học tập.",
  "08, August, 2023",
  {
    answerId: "answer1",
    answerText: "Cam on phan hoi cua ban ve mentor nay!",
    answerAt: "08, August, 2023",
  }
);

const ReportDetail = () => {
  const navigate = useNavigate();
  const [report, setReport] = useState({ ...fakeReportData });

  useEffect(() => {
    fetch("http://localhost:9999/Booking")
      .then((resp) => resp.json())
      .then((data) => {
        setReport(data);
      })
      .catch((err) => {
        console.log(err);
        setReport({ ...fakeReportData });
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
            Title <span>{report.createDate}</span>
          </h4>
          <div className={styles.reportDetail}>{report.title}</div>
          <h4>Content</h4>
          <div className={styles.reportDetail}>{report.content}</div>
          <h4>
            Answer <span>{report.answer.answerAt}</span>
          </h4>
          <div className={styles.reportAnswer}>
            {report.answer.answerText || "Please wait for answering!"}
          </div>
        </Container>
      </div>
    </div>
  );
};

export default ReportDetail;
