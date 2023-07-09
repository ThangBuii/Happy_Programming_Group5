import {
  TableContainer,
  TableRow,
  TableCell,
  TableHead,
  Table,
  TableBody,
  Button,
} from "@mui/material";
import MainLayout from "../../component/main-layout";
import { useState, useEffect } from "react";
import { Link, useLocation } from "react-router-dom";
import { EyeFill } from "react-bootstrap-icons";
import AvatarDefault from "../../assets/avatar-thinking-3-svgrepo-com.svg";
import { linkObjList } from "../../component/sidebar";
import styles from "./index.module.css";
import { request } from "../../axios_helper";

const Dashboard = () => {
  const role = 1; // authen => context => role
  const location = useLocation();
  const [dashboards, setDashboard] = useState([]);
  const postData = {
    id: 3,
  };

  useEffect(() => {
    request("GET", "/api/mentee/dashboard")
      .then((response) => {
        setDashboard(response.data);
      })
      .catch((error) => {
        console.error(error);
      });
  }, []);

  const handleClickUpdate = (id, type) => {
    console.log(id, type);
  };

  return (
    <MainLayout
      pageTitle="Dashboard"
      layoutContent={
        <>
          <div className={styles.dbWrapper}>
            <div className={styles.dbHead}>
              <div className={styles.itemWrapper}></div>
              <div className={styles.itemWrapper}></div>
              <div className={styles.itemWrapper}></div>
            </div>
            <h2>Mentor Lists</h2>

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
                    <TableCell className={styles.tableCellHead} align="left">
                      BASIC INFO
                    </TableCell>
                    <TableCell className={styles.tableCellHead} align="left">
                      CREATED DATE
                    </TableCell>
                    <TableCell className={styles.tableCellHead} align="center">
                      Status
                    </TableCell>
                    <TableCell className={styles.tableCellHead} align="center">
                      Action
                    </TableCell>
                  </TableRow>
                </TableHead>

                <TableBody>
                  {dashboards.map((item) => (
                    <TableRow key={item.bookingID} hover={true}>
                      <TableCell align="left">
                        <div className={styles.mentorInfoWrapper}>
                          <img
                            src={item.avatar || AvatarDefault}
                            alt="avatar"
                          />
                          <div className={styles.infoLeft}>
                            <h4>{item.username}</h4>
                            <p>{item.email}</p>
                          </div>
                        </div>
                      </TableCell>
                      <TableCell align="left">{item.created_Date}</TableCell>

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
                        <div className={styles.centerWrapper}>
                          <Link
                            className={styles.customAction}
                            to={`/bookings/${item.bookingID}`}
                            state={{
                              prevPath: {
                                to: location.pathname,
                                represent: linkObjList.find(
                                  (cur) => cur.to === location.pathname
                                ).represent,
                              },
                            }}
                          >
                            <EyeFill />
                            <span>View</span>
                          </Link>
                          {role === 1 && (
                            <>
                              <Button
                                variant="contained"
                                color="primary"
                                onClick={() =>
                                  handleClickUpdate(item.bookingID, "UPDATE")
                                }
                              >
                                Accept
                              </Button>
                              <Button
                                variant="contained"
                                color="warning"
                                onClick={() =>
                                  handleClickUpdate(item.bookingID, "DELETE")
                                }
                              >
                                Delete
                              </Button>
                            </>
                          )}
                        </div>
                      </TableCell>
                    </TableRow>
                  ))}
                </TableBody>
              </Table>
            </TableContainer>
          </div>
        </>
      }
    />
  );
};

export default Dashboard;
