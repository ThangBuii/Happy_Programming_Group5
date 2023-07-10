import { Link, useLocation } from "react-router-dom";
import { useContext, useEffect, useState } from "react";
import MainLayout from "../../component/main-layout";
import {
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
} from "@mui/material";
import AvatarDefault from "../../assets/avatar-thinking-3-svgrepo-com.svg";
import { EyeFill } from "react-bootstrap-icons";
import { linkObjList } from "../../component/sidebar";
import styles from "./index.module.css";
import { request } from "../../axios_helper";
import { ApplicationContext } from "../../routes/AppRoutes";

const Booking = () => {
  const { user } = useContext(ApplicationContext);
  const role = user.role;
  const location = useLocation();
  console.log(location.pathname, linkObjList);
  const [bookings, setBookings] = useState([]);
  //call API
  useEffect(() => {
    if (role === -1) return;
    const url = role === 1 ? "/api/mentor/bookings" : "/api/mentee/bookings"
   request("GET",url)
      
      .then((response) => {
        setBookings(response.data);
      })
      .catch((err) => {
        console.log(err);
    
      });
  }, []);

  return (
    <MainLayout
      pageTitle="List Of Booking"
      layoutContent={
        <>
          <div className={styles.dbWrapper}>
            <h2>Booking Summary</h2>
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
                      SCHEDULED DATE
                    </TableCell>
                    <TableCell className={styles.tableCellHead} align="center">
                      SCHEDULED TIMINGS
                    </TableCell>
                    <TableCell className={styles.tableCellHead} align="center">
                      Action
                    </TableCell>
                  </TableRow>
                </TableHead>
                <TableBody>
                  {bookings.map((item) => (
                    <TableRow
                      key={item.bookingID}
                      sx={{ "&:last-child td, &:last-child th": { border: 0 } }}
                      hover={true}
                    >
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
                      <TableCell align="left">{item.scheduleDate}</TableCell>
                      <TableCell align="center">{item.scheduleTime}</TableCell>
                      <TableCell align="center">
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

export default Booking;
