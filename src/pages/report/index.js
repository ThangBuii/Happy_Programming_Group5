import { useContext, useEffect, useState } from "react";
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
import {request} from '../../axios_helper'

// Title, Content, Created Date, Status


const Report = () => {
  const [reportList, setReportList] = useState([]);
  const navigate = useNavigate(); 

  useEffect(() => {
    request("GET", "/api/men/reports")
      .then((response) => {
        setReportList(response.data);
      })
      .catch((error) => {
        console.error(error);
      });
  }, []);

  return (
    <MainLayout
      pageTitle={"List Of Report"}
      titleControl={
        <div className={styles.actionAdd}>
          <Button
            variant="contained"
            startIcon={<Plus />}
            onClick={() => navigate("/report/add")}
          >
            Add Report
          </Button>
        </div>
      }
      layoutContent={
        <div className={styles.layoutWrapper}>
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
                    key={item.report_id}
                    sx={{ "&:last-child td, &:last-child th": { border: 0 } }}
                    hover={true}
                  >
                    <TableCell align="left">
                      <div className={styles.reportDetail}>{item.title}</div>
                    </TableCell>
                    <TableCell align="left">
                      <div className={styles.reportDetail}>{item.content}</div>
                    </TableCell>
                    <TableCell align="left">{item.created_date}</TableCell>
                    <TableCell align="center">
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
                          to={`/report/${item.report_id}`}
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
