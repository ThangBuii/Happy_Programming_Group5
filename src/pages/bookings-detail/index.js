import { useLocation, useNavigate } from "react-router";
import {
  Container,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
} from "@mui/material";
import { useEffect, useState } from "react";
import AvatarDefault from "../../assets/avatar-thinking-3-svgrepo-com.svg";
import styles from "./index.module.css";

// Mentor Info(Ava,Usernam,Email), Mentee info(Ava,Username,Email), Scheduled date,Scheduled time, Status, Created Date

function createData(
  mentorInfo,
  menteeInfo,
  scheduledDate,
  scheduledTime,
  status,
  createdDate
) {
  return {
    mentorInfo,
    menteeInfo,
    scheduledDate,
    scheduledTime,
    status,
    createdDate,
  };
}

const fakeBookingDetailData = [
  createData(
    {
      accountId: "user1",
      name: "Le Van Luyen",
      email: "luyen@xxvideo.com",
      imageUrl:
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTu5iuH9GH49VUAv0qvlrKiFRnsgEC6maRA9g&usqp=CAU",
    },
    {
      accountId: "user2",
      name: "Ngo Ba Kha",
      email: "kha@gmail.com",
      imageUrl:
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTu5iuH9GH49VUAv0qvlrKiFRnsgEC6maRA9g&usqp=CAU",
    },
    "08, August, 2023",
    "08, August, 2023",
    0,
    "08, August, 2023"
  ),
  createData(
    {
      accountId: "user3",
      name: "Le Van Luyen1",
      email: "luyen@xxvideo.com",
      imageUrl:
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTu5iuH9GH49VUAv0qvlrKiFRnsgEC6maRA9g&usqp=CAU",
    },
    {
      accountId: "user4",
      name: "Ngo Ba Kha2",
      email: "kha@gmail.com",
      imageUrl:
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTu5iuH9GH49VUAv0qvlrKiFRnsgEC6maRA9g&usqp=CAU",
    },
    "08, August, 2023",
    "08, August, 2023",
    1,
    "08, August, 2023"
  ),
  createData(
    {
      accountId: "user5",
      name: "Le Van Luyen",
      email: "luyen@xxvideo.com",
      imageUrl:
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTu5iuH9GH49VUAv0qvlrKiFRnsgEC6maRA9g&usqp=CAU",
    },
    {
      accountId: "user6",
      name: "Ngo Ba Kha",
      email: "kha@gmail.com",
      imageUrl:
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTu5iuH9GH49VUAv0qvlrKiFRnsgEC6maRA9g&usqp=CAU",
    },
    "08, August, 2023",
    "08, August, 2023",
    2,
    "08, August, 2023"
  ),
  createData(
    {
      accountId: "user7",
      name: "Le Van Luyen",
      email: "luyen@xxvideo.com",
      imageUrl:
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTu5iuH9GH49VUAv0qvlrKiFRnsgEC6maRA9g&usqp=CAU",
    },
    {
      accountId: "user8",
      name: "Ngo Ba Kha",
      email: "kha@gmail.com",
      imageUrl:
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTu5iuH9GH49VUAv0qvlrKiFRnsgEC6maRA9g&usqp=CAU",
    },
    "08, August, 2023",
    "08, August, 2023",
    2,
    "08, August, 2023"
  ),
  createData(
    {
      accountId: "user9",
      name: "Le Van Luyen",
      email: "luyen@xxvideo.com",
      imageUrl:
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTu5iuH9GH49VUAv0qvlrKiFRnsgEC6maRA9g&usqp=CAU",
    },
    {
      accountId: "user10",
      name: "Ngo Ba Kha",
      email: "kha@gmail.com",
      imageUrl:
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTu5iuH9GH49VUAv0qvlrKiFRnsgEC6maRA9g&usqp=CAU",
    },
    "08, August, 2023",
    "08, August, 2023",
    0,
    "08, August, 2023"
  ),
  createData(
    {
      accountId: "user11",
      name: "Le Van Luyen",
      email: "luyen@xxvideo.com",
      imageUrl:
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTu5iuH9GH49VUAv0qvlrKiFRnsgEC6maRA9g&usqp=CAU",
    },
    {
      accountId: "user12",
      name: "Ngo Ba Kha",
      email: "kha@gmail.com",
      imageUrl:
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTu5iuH9GH49VUAv0qvlrKiFRnsgEC6maRA9g&usqp=CAU",
    },
    "08, August, 2023",
    "08, August, 2023",
    0,
    "08, August, 2023"
  ),
];

const BookingsDetail = () => {
  const location = useLocation();
  const navigate = useNavigate();
  const { prevPath } = location.state;
  const [bookings, setBookings] = useState([...fakeBookingDetailData]);

  //call API
  useEffect(() => {
    fetch("http://localhost:9999/booking-details")
      .then((resp) => resp.json())
      .then((data) => {
        setBookings(data);
      })
      .catch((err) => {
        console.log(err);
        setBookings([...fakeBookingDetailData]);
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
              onClick={() => navigate(prevPath.to)}
            >
              {prevPath.represent}
            </span>
            <span className={styles.bcPersonProfile}>Booking Detail</span>
          </div>
          <h2>Booking Detail</h2>
        </div>
      </div>
      <div className={styles.bookingWrapper}>
        <Container
          maxWidth="xl"
          sx={{
            padding: "30px 0 0",
          }}
        >
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
                    MENTOR INFO
                  </TableCell>
                  <TableCell className={styles.tableCellHead} align="left">
                    MENTEE INFO
                  </TableCell>
                  <TableCell className={styles.tableCellHead} align="left">
                    SCHEDULED DATE
                  </TableCell>
                  <TableCell className={styles.tableCellHead} align="left">
                    SCHEDULED TIMINGS
                  </TableCell>
                  <TableCell className={styles.tableCellHead} align="center">
                    STATUS
                  </TableCell>
                  <TableCell className={styles.tableCellHead} align="left">
                    CREATED DATE
                  </TableCell>
                </TableRow>
              </TableHead>
              <TableBody>
                {bookings.map((item) => (
                  <TableRow
                    key={`${item.mentorInfo.accountId}-${item.menteeInfo.accountId}`}
                    sx={{ "&:last-child td, &:last-child th": { border: 0 } }}
                    hover={true}
                  >
                    <TableCell align="left">
                      <div className={styles.mentorInfoWrapper}>
                        <img
                          src={item.mentorInfo.imageUrl || AvatarDefault}
                          alt="avatar"
                        />
                        <div className={styles.infoLeft}>
                          <h4>{item.mentorInfo.name}</h4>
                          <p>{item.mentorInfo.email}</p>
                        </div>
                      </div>
                    </TableCell>
                    <TableCell align="left">
                      <div className={styles.mentorInfoWrapper}>
                        <img
                          src={item.menteeInfo.imageUrl || AvatarDefault}
                          alt="avatar"
                        />
                        <div className={styles.infoLeft}>
                          <h4>{item.menteeInfo.name}</h4>
                          <p>{item.menteeInfo.email}</p>
                        </div>
                      </div>
                    </TableCell>
                    <TableCell align="left">{item.scheduledDate}</TableCell>
                    <TableCell align="left">{item.scheduledTime}</TableCell>
                    <TableCell
                      align="center"
                      sx={{
                        backgroundColor:
                          item.status === 0
                            ? "#FBA20A"
                            : item.status === 1
                            ? "#04AE1B"
                            : item.status === 2
                            ? "#FF0000"
                            : "#FF0000",
                        display: "block",
                        transform: "translateY(65%)",
                        padding: "8px",
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
                    <TableCell align="left">{item.createdDate}</TableCell>
                  </TableRow>
                ))}
              </TableBody>
            </Table>
          </TableContainer>
        </Container>
      </div>
    </div>
  );
};

export default BookingsDetail;
