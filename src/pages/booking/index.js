import { Link } from "react-router-dom";
import { useEffect, useState } from "react";
import MainLayout from "../../component/main-layout/MainLayout";
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
import styles from "./index.module.css";

function createData(accountId, name, imageUrl, email, date) {
  return { accountId, name, imageUrl, email, date, timings: date };
}

const fakeData = [
  createData(
    "mentor1",
    "nguyen trong tai",
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTu5iuH9GH49VUAv0qvlrKiFRnsgEC6maRA9g&usqp=CAU",
    "email@gmail.com",
    "08, August, 2023"
  ),
  createData(
    "mentor2",
    "nguyen trong tai",
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTu5iuH9GH49VUAv0qvlrKiFRnsgEC6maRA9g&usqp=CAU",
    "email@gmail.com",
    "08, August, 2023"
  ),
  createData(
    "mentor3",
    "nguyen trong tai",
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTu5iuH9GH49VUAv0qvlrKiFRnsgEC6maRA9g&usqp=CAU",
    "email@gmail.com",
    "08, August, 2023"
  ),
  createData(
    "mentor4",
    "nguyen trong tai",
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTu5iuH9GH49VUAv0qvlrKiFRnsgEC6maRA9g&usqp=CAU",
    "email@gmail.com",
    "08, August, 2023"
  ),
  createData(
    "mentor5",
    "nguyen trong tai",
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTu5iuH9GH49VUAv0qvlrKiFRnsgEC6maRA9g&usqp=CAU",
    "email@gmail.com",
    "08, August, 2023"
  ),
  createData(
    "mentor6",
    "nguyen trong tai",
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTu5iuH9GH49VUAv0qvlrKiFRnsgEC6maRA9g&usqp=CAU",
    "email@gmail.com",
    "08, August, 2023"
  ),
  createData(
    "mentor7",
    "nguyen trong tai",
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTu5iuH9GH49VUAv0qvlrKiFRnsgEC6maRA9g&usqp=CAU",
    "email@gmail.com",
    "08, August, 2023"
  ),
  createData(
    "mentor8",
    "nguyen trong tai",
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTu5iuH9GH49VUAv0qvlrKiFRnsgEC6maRA9g&usqp=CAU",
    "email@gmail.com",
    "08, August, 2023"
  ),
  createData(
    "mentor9",
    "nguyen trong tai",
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTu5iuH9GH49VUAv0qvlrKiFRnsgEC6maRA9g&usqp=CAU",
    "email@gmail.com",
    "08, August, 2023"
  ),
  createData(
    "mentor10",
    "nguyen trong tai",
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTu5iuH9GH49VUAv0qvlrKiFRnsgEC6maRA9g&usqp=CAU",
    "email@gmail.com",
    "08, August, 2023"
  ),
];

const Booking = () => {
  const [bookings, setBookings] = useState([]);
  //call API
  useEffect(() => {
    fetch("http://localhost:9999/Booking")
      .then((resp) => resp.json())
      .then((data) => {
        setBookings(data);
      })
      .catch((err) => {
        console.log(err);
        setBookings(fakeData);
      });
  }, []);

  // delete
  // const handleDelete = (name) => {
  //   if (window.confirm("Muon xoa-name: " + name + "?")) {
  //     fetch("http://localhost:9999/Booking/" + name, {
  //       method: "DELETE",
  //     })
  //       .then(() => {
  //         alert("Delete success");
  //         window.location.reload();
  //       })
  //       .catch((err) => {
  //         console.log(err.message);
  //       });
  //   }
  // };

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
                      key={item.accountId}
                      sx={{ "&:last-child td, &:last-child th": { border: 0 } }}
                      hover={true}
                    >
                      <TableCell align="left">
                        <div className={styles.mentorInfoWrapper}>
                          <img
                            src={item.imageUrl || AvatarDefault}
                            alt="avatar"
                          />
                          <div className={styles.infoLeft}>
                            <h4>{item.name}</h4>
                            <p>{item.email}</p>
                          </div>
                        </div>
                      </TableCell>
                      <TableCell align="left">{item.date}</TableCell>
                      <TableCell align="center">{item.timings}</TableCell>
                      <TableCell align="center">
                        <Link
                          className={styles.customAction}
                          to={`/person/${item.menteeId}`}
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
