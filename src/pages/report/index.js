import { useEffect, useState } from "react";
import MainLayout from "../../component/main-layout";
import {
  Button,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
} from "@mui/material";
import { Link, useNavigate } from "react-router-dom";
import { EyeFill, Plus } from "react-bootstrap-icons";
import styles from "./index.module.css";

// Title, Content, Created Date, Status

function createData(reportId, title, content, createDate, status) {
  return { reportId, title, content, createDate, status };
}

const fakeReportListData = [
  createData(
    "report1",
    "Chat luong gio hoc te",
    "Nhóm học tập rất cần thiết trong dạy học theo định hướng phát triển năng lực người học. Khi học theo nhóm các em được chia sẻ ý kiến cho nhau, được hỗ trợ giúp đỡ nhau để cùng tiến bộ nhằm phát triển năng lực và phẩm chất, hoàn thiện bản thân trong quá trình học tập.",
    "08, August, 2023",
    0
  ),
  createData(
    "report2",
    "Chat luong gio hoc te",
    "Nhóm học tập rất cần thiết trong dạy học theo định hướng phát triển năng lực người học. Khi học theo nhóm các em được chia sẻ ý kiến cho nhau, được hỗ trợ giúp đỡ nhau để cùng tiến bộ nhằm phát triển năng lực và phẩm chất, hoàn thiện bản thân trong quá trình học tập.",
    "08, August, 2023",
    1
  ),
  createData(
    "report3",
    "Chat luong gio hoc te",
    "Nhóm học tập rất cần thiết trong dạy học theo định hướng phát triển năng lực người học. Khi học theo nhóm các em được chia sẻ ý kiến cho nhau, được hỗ trợ giúp đỡ nhau để cùng tiến bộ nhằm phát triển năng lực và phẩm chất, hoàn thiện bản thân trong quá trình học tập.",
    "08, August, 2023",
    2
  ),
];

const Report = () => {
  const [reportList, setReportList] = useState([...fakeReportListData]);
  const navigate = useNavigate();

  useEffect(() => {
    fetch("http://localhost:9999/Booking")
      .then((resp) => resp.json())
      .then((data) => {
        setReportList(data);
      })
      .catch((err) => {
        console.log(err);
        setReportList([...fakeReportListData]);
      });
  }, []);

  return (
    <MainLayout
      pageTitle={"List Of Report"}
      layoutContent={
        <div className={styles.layoutWrapper}>
          <div className={styles.actionAdd}>
            <Button
              variant="contained"
              startIcon={<Plus />}
              onClick={() => navigate("/report/add")}
            >
              Add Report
            </Button>
          </div>
          <TableContainer
            sx={{
              background: "#E7E7D7",

              border: "5px solid #B5C49C",
              borderRadius: "10px",
            }}
          >
            <Table sx={{ minWidth: 650 }} aria-label="simple table">
              <TableHead>
                <TableRow>
                  <TableCell
                    sx={{ minWidth: 100 }}
                    className={styles.tableCellHead}
                    align="left"
                  >
                    TITLE
                  </TableCell>
                  <TableCell className={styles.tableCellHead} align="left">
                    CONTENT
                  </TableCell>
                  <TableCell
                    sx={{ minWidth: 140 }}
                    className={styles.tableCellHead}
                    align="left"
                  >
                    CREATE DATE
                  </TableCell>
                  <TableCell className={styles.tableCellHead} align="center">
                    STATUS
                  </TableCell>
                  <TableCell className={styles.tableCellHead} align="center">
                    Action
                  </TableCell>
                </TableRow>
              </TableHead>
              <TableBody>
                {reportList.map((item) => (
                  <TableRow
                    key={item.reportId}
                    sx={{ "&:last-child td, &:last-child th": { border: 0 } }}
                    hover={true}
                  >
                    <TableCell align="left">
                      <div className={styles.reportDetail}>{item.title}</div>
                    </TableCell>
                    <TableCell align="left">
                      <div className={styles.reportDetail}>{item.content}</div>
                    </TableCell>
                    <TableCell align="left">{item.createDate}</TableCell>
                    <TableCell
                      align="center"
                      sx={{
                        verticalAlign: "middle",
                      }}
                    >
                      <span
                        className={
                          item.status === 0
                            ? styles.pendindStatus
                            : item.status === 1
                            ? styles.acceptStatus
                            : item.status === 2
                            ? styles.rejectStatus
                            : ""
                        }
                      >
                        {item.status === 0
                          ? "Pending"
                          : item.status === 1
                          ? "Accepted"
                          : item.status === 2
                          ? "Rejected"
                          : ""}
                      </span>
                    </TableCell>
                    <TableCell align="center">
                      <div className={styles.actionWrapper}>
                        <Link
                          className={styles.customAction}
                          to={`/report/${item.reportId}`}
                        >
                          <EyeFill />
                          <span>View</span>
                        </Link>
                      </div>
                    </TableCell>
                  </TableRow>
                ))}
              </TableBody>
            </Table>
          </TableContainer>
        </div>
      }
    />
  );
};

export default Report;
