import { useContext, useEffect, useState } from "react";
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
import { request } from "../../axios_helper";
import { ApplicationContext } from "../../routes/AppRoutes";

// Mentor Info, Created Date, Content, Rating

const Feedback = () => {
  const [reportList, setReportList] = useState([]);
  const { user } = useContext(ApplicationContext);
  const role = user.role;  // authen => context => role

  const fetchData = () => {
    if (role === -1) return;
    const url = role === 1 ? "/api/mentor/feedbacks" : "/api/mentee/feedbacks"
    request("GET", url)
      .then((response) => {
        setReportList(response.data);
      })
      .catch((error) => {
        console.error(error);
      });
  }

  useEffect(() => {
    fetchData();
  }, [role]);

  const handleDelete = (id) => {
    if (window.confirm("Muon xoa feedback: " + id + "?")) {
      request("DELETE", `/api/feedback/${id}`)
        .then(() => {
        alert("Delete success");
         fetchData();
        })
        .catch((err) => {
          console.log(err.message);
        });
    }
  };
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
                  {role === 2  && (
                        <TableCell className={styles.tableCellHead} align="center">
                        Action
                      </TableCell>
                      )}
                  
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
                        <img src={item.avatar ? `data:image/jpeg;base64, ${item.avatar}` : AvatarDefault} alt="avatar" />
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
                        sx={{
                          marginTop: "-6px",
                          marginLeft: "-6px",
                          "& .MuiRating-iconFilled": {
                            color: "rgb(33, 163, 145)", // Màu fill tùy chỉnh khi được chọn
                          },
                        }}
                      />
                    </TableCell>
                    <TableCell align="center">
                      {role === 2  && (
                        <Button
                          onClick={() => handleDelete(item.feedback_id)}
                          variant="contained"
                          color="warning"
                        >
                          Delete
                        </Button>
                      )}
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
