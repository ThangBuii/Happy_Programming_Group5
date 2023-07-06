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
import {request} from '../../axios_helper'

// Mentor Info, Created Date, Content, Rating




const Feedback = () => {
  const [reportList, setReportList] = useState([]);

  useEffect(() => {
    request("GET", "/api/mentee/feedbacks")
      .then((response) => {
        setReportList(response.data);
      })
      .catch((error) => {
        console.error(error);
      });
  }, []);

  const handleDelete = (id) => {
    if (window.confirm("Muon xoa-id: " + id + "?")) {
      request("DELETE",`/api/feedback/${id}`)
        .then(() => {
          alert("Delete success");
          window.location.reload();
        })
        .catch((err) => {
          console.log(err.message);
        });
    }
  };

  // useEffect(() => {
  //   fetch("http://localhost:9999/report-list/mentorId")
  //     .then((resp) => resp.json())
  //     .then((data) => {
  //       setReportList(data);
  //     })
  //     .catch((err) => {
  //       console.log(err);
  //       setReportList([...fakeFeedbackListData]);
  //     });
  // }, []);
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
                    // sx={{ minWidth: 500 }}
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
                    key={item.feedback_id}
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
                    <TableCell align="left">{item.created_date}</TableCell>
                    <TableCell align="left">
                      <div className={styles.reportDetail}>{item.content}</div>
                    </TableCell>
                    <TableCell align="center">
                      <Rating
                        defaultValue={item.rating}
                        readOnly
                        precision={0.5}
                      />
                    </TableCell>
                    <TableCell align="center">
                      <Button onClick={() => handleDelete(item.feedback_id)} 
                      variant="contained" color="warning">
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
