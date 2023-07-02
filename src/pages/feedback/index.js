import { useEffect, useState } from "react";
import MainLayout from "../../component/main-layout";
import {
  Button,
  Rating,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
} from "@mui/material";
import AvatarDefault from "../../assets/avatar-thinking-3-svgrepo-com.svg";
import styles from "./index.module.css";

// Mentor Info, Created Date, Content, Rating

function createData(feedbackId, mentorInfo, createDate, content, rating) {
  return { feedbackId, mentorInfo, createDate, content, rating };
}

const fakeFeedbackListData = [
  createData(
    "feedback1",
    {
      accountId: "user1",
      name: "Le Van Luyen",
      email: "luyen@xxvideo.com",
      imageUrl:
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTu5iuH9GH49VUAv0qvlrKiFRnsgEC6maRA9g&usqp=CAU",
    },
    "08, August, 2023",
    "Khoa hoc rat hay va thu vi",
    4
  ),
  createData(
    "feedback2",
    {
      accountId: "user2",
      name: "Hasagiii",
      email: "luyen@xxvideo.com",
      imageUrl:
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTu5iuH9GH49VUAv0qvlrKiFRnsgEC6maRA9g&usqp=CAU",
    },
    "08, August, 2023",
    "Khoa hoc rat hay va thu vi",
    4.5
  ),
  createData(
    "feedback1",
    {
      accountId: "user3",
      name: "Le Van Luyen2",
      email: "luyen@xxvideo.com",
      imageUrl:
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTu5iuH9GH49VUAv0qvlrKiFRnsgEC6maRA9g&usqp=CAU",
    },
    "08, August, 2023",
    "Khoa hoc rat hay va thu vi",
    5
  ),
];

const Feedback = () => {
  const [reportList, setReportList] = useState([...fakeFeedbackListData]);

  useEffect(() => {
    fetch("http://localhost:9999/report-list/mentorId")
      .then((resp) => resp.json())
      .then((data) => {
        setReportList(data);
      })
      .catch((err) => {
        console.log(err);
        setReportList([...fakeFeedbackListData]);
      });
  }, []);
  return (
    <MainLayout
      pageTitle={"List Of Report"}
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
                    sx={{ minWidth: 220 }}
                    className={styles.tableCellHead}
                    align="left"
                  >
                    MENTOR INFO
                  </TableCell>
                  <TableCell
                    sx={{ minWidth: 150 }}
                    className={styles.tableCellHead}
                    align="left"
                  >
                    CREATED DATE
                  </TableCell>
                  <TableCell
                    sx={{ minWidth: 500 }}
                    className={styles.tableCellHead}
                    align="left"
                  >
                    CONTENT
                  </TableCell>
                  <TableCell className={styles.tableCellHead} align="center">
                    RATING
                  </TableCell>
                  <TableCell className={styles.tableCellHead} align="center">
                    Action
                  </TableCell>
                </TableRow>
              </TableHead>
              <TableBody>
                {reportList.map((item) => (
                  <TableRow
                    key={item.feedbackId}
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
                    <TableCell align="left">{item.createDate}</TableCell>
                    <TableCell align="left">
                      <div className={styles.reportDetail}>{item.content}</div>
                    </TableCell>
                    <TableCell align="center">
                      <Rating defaultValue={item.rating} readOnly />
                    </TableCell>
                    <TableCell align="center">
                      <Button variant="contained" color="warning">
                        Delete
                      </Button>
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

export default Feedback;
