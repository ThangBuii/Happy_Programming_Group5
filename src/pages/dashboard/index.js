import {
  TableContainer,
  TableRow,
  TableCell,
  TableHead,
  Table,
  TableBody,
} from "@mui/material";
import MainLayout from "../../component/main-layout/MainLayout";
import { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import { EyeFill } from "react-bootstrap-icons";
import AvatarDefault from "../../assets/avatar-thinking-3-svgrepo-com.svg";
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
  const [report, setReport] = useState([]);
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
        setReport(result);
      })
      .catch((err) => {
        setReport([...fakeData]);
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
                  {report.map((r) => (
                    <TableRow key={r.accountId} hover={true}>
                      <TableCell align="left">
                        <div className={styles.mentorInfoWrapper}>
                          <img src={r.imageUrl || AvatarDefault} alt="avatar" />
                          <div className={styles.infoLeft}>
                            <h4>{r.username}</h4>
                            <p>{r.email}</p>
                          </div>
                        </div>
                      </TableCell>
                      <TableCell align="left">{r.created_Date}</TableCell>

                      <TableCell
                        align="center"
                        style={{
                          backgroundColor:
                            r.status === 0
                              ? "Pending"
                              : r.status === 1
                              ? "Accepted"
                              : r.status === 2
                              ? "Rejected"
                              : "inherit",
                        }}
                      >
                        {r.status === 0
                          ? "Pending"
                          : r.status === 1
                          ? "Accepted"
                          : r.status === 2
                          ? "Rejected"
                          : ""}
                      </TableCell>
                      <TableCell align="center">
                        <Link
                          className={styles.customAction}
                          to={`/person/${r.menteeId}`}
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
