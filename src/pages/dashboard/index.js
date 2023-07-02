import {
  TableContainer,
  TableRow,
  TableCell,
  TableHead,
  Table,
  TableBody,
} from "@mui/material";
import MainLayout from "../../component/main-layout";
import { useState, useEffect } from "react";
import { Link, useLocation } from "react-router-dom";
import { EyeFill } from "react-bootstrap-icons";
import AvatarDefault from "../../assets/avatar-thinking-3-svgrepo-com.svg";
import { linkObjList } from "../../component/sidebar";
import styles from "./index.module.css";

function createData(
  accountId,
  username,
  imageUrl,
  email,
  created_Date,
  status
) {
  return { accountId, username, imageUrl, email, created_Date, status };
}

const fakeData = [
  createData(
    "mentor1",
    "nguyen trong tai",
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTu5iuH9GH49VUAv0qvlrKiFRnsgEC6maRA9g&usqp=CAU",
    "email@gmail.com",
    "08, August, 2023",
    0
  ),
  createData(
    "mentor2",
    "nguyen trong tai",
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTu5iuH9GH49VUAv0qvlrKiFRnsgEC6maRA9g&usqp=CAU",
    "email@gmail.com",
    "08, August, 2023",
    1
  ),
  createData(
    "mentor3",
    "nguyen trong tai",
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTu5iuH9GH49VUAv0qvlrKiFRnsgEC6maRA9g&usqp=CAU",
    "email@gmail.com",
    "08, August, 2023",
    2
  ),
  createData(
    "mentor4",
    "nguyen trong tai",
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTu5iuH9GH49VUAv0qvlrKiFRnsgEC6maRA9g&usqp=CAU",
    "email@gmail.com",
    "08, August, 2023",
    2
  ),
  createData(
    "mentor5",
    "nguyen trong tai",
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTu5iuH9GH49VUAv0qvlrKiFRnsgEC6maRA9g&usqp=CAU",
    "email@gmail.com",
    "08, August, 2023",
    1
  ),
  createData(
    "mentor6",
    "nguyen trong tai",
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTu5iuH9GH49VUAv0qvlrKiFRnsgEC6maRA9g&usqp=CAU",
    "email@gmail.com",
    "08, August, 2023",
    0
  ),
  createData(
    "mentor7",
    "nguyen trong tai",
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTu5iuH9GH49VUAv0qvlrKiFRnsgEC6maRA9g&usqp=CAU",
    "email@gmail.com",
    "08, August, 2023",
    1
  ),
  createData(
    "mentor8",
    "nguyen trong tai",
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTu5iuH9GH49VUAv0qvlrKiFRnsgEC6maRA9g&usqp=CAU",
    "email@gmail.com",
    "08, August, 2023",
    2
  ),
  createData(
    "mentor9",
    "nguyen trong tai",
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTu5iuH9GH49VUAv0qvlrKiFRnsgEC6maRA9g&usqp=CAU",
    "email@gmail.com",
    "08, August, 2023",
    2
  ),
  createData(
    "mentor10",
    "nguyen trong tai",
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTu5iuH9GH49VUAv0qvlrKiFRnsgEC6maRA9g&usqp=CAU",
    "email@gmail.com",
    "08, August, 2023",
    2
  ),
];

const Dashboard = () => {
  const location = useLocation();
  const [bookings, setBookings] = useState([]);
  const postData = {
    id: 3,
  };

  useEffect(() => {
    fetch("http://localhost:8080/list-mentee", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(postData),
    })
      .then((res) => res.json())
      .then((result) => {
        setBookings(result);
      })
      .catch((err) => {
        setBookings([...fakeData]);
      });
  }, []);

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
                  {bookings.map((item) => (
                    <TableRow key={item.accountId} hover={true}>
                      <TableCell align="left">
                        <div className={styles.mentorInfoWrapper}>
                          <img
                            src={item.imageUrl || AvatarDefault}
                            alt="avatar"
                          />
                          <div className={styles.infoLeft}>
                            <h4>{item.username}</h4>
                            <p>{item.email}</p>
                          </div>
                        </div>
                      </TableCell>
                      <TableCell align="left">{item.created_Date}</TableCell>

                      <TableCell
                        align="center"
                        style={{
                          backgroundColor:
                            item.status === 0
                              ? "Pending"
                              : item.status === 1
                              ? "Accepted"
                              : item.status === 2
                              ? "Rejected"
                              : "inherit",
                        }}
                      >
                        {item.status === 0
                          ? "Pending"
                          : item.status === 1
                          ? "Accepted"
                          : item.status === 2
                          ? "Rejected"
                          : ""}
                      </TableCell>
                      <TableCell align="center">
                        <Link
                          className={styles.customAction}
                          to={`/bookings/${item.menteeId}`}
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

export default Dashboard;
