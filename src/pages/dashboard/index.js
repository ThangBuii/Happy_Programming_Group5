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
import { useState, useEffect, useContext } from "react";
import { Link, useLocation } from "react-router-dom";
import { EyeFill } from "react-bootstrap-icons";
import AvatarDefault from "../../assets/avatar-thinking-3-svgrepo-com.svg";
import { linkObjList } from "../../component/sidebar";
import styles from "./index.module.css";
import { request } from "../../axios_helper";
import { ApplicationContext } from "../../routes/AppRoutes";

const Dashboard = () => {
  const { user } = useContext(ApplicationContext);
  const role = user.role; // authen => context => role
  const location = useLocation();
  const [dashboards, setDashboard] = useState([]);

  const fetchData = () => {
    if (role === -1) return;
    const url = role === 1 ? "/api/mentor/dashboard" : "/api/mentee/dashboard";
    request("GET", url)
      .then((response) => {
        setDashboard(response.data);
      })
      .catch((error) => {
        console.error(error);
      });
  };
  useEffect(() => {
    fetchData();
  }, [role]);

  const handleClickUpdate = (id, type) => {
    console.log(id, type);

    if (type === 0) {
      request("DELETE", `/api/mentee/booking/${id}`)
        .then((response) => {
          if(response.status === 200) {
            fetchData();
          }
        })
        .catch((error) => {
          console.log(error);
        });
    } else {
      const data = {
        status: type
      }
      request("PUT", `/api/mentor/booking/${id}`,data)
        .then((response) => {
          if(response.status === 200) {
            fetchData();
          }
        })
        .catch((error) => {
          console.log(error);
        });
    }
  };

  return (
    <MainLayout
      pageTitle="Dashboard"
      layoutContent={
        <>
          <div className={styles.dbWrapper}>

            {role === 1 ? (
              <h2>Mentee Lists</h2>
            ) : (
              <h2>Mentor Lists</h2>
            )}

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
                            src={item.avatar ? `data:image/jpeg;base64, ${item.avatar}` : AvatarDefault}
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
                          {item.status === 0 && role === 2 && (
                            <>
                              <Button
                                variant="contained"
                                color="warning"
                                onClick={() => handleClickUpdate(item.bookingID, 0)}
                              >
                                Delete
                              </Button>
                            </>
                          )}

                          {item.status === 0 && role === 1 && (
                            <>
                              <Button
                                variant="contained"
                                color="primary"
                                onClick={() => handleClickUpdate(item.bookingID, 1)}
                              >
                                Accept
                              </Button>
                              <Button
                                variant="contained"
                                color="warning"
                                onClick={() => handleClickUpdate(item.bookingID, 2)}
                              >
                                Reject
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
