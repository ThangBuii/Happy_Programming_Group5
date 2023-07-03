import { Button, CircularProgress, Rating, Skeleton } from "@mui/material";
import MainAdminLayout from "../../../component/admin/main-layout";
import { DataGrid } from "@mui/x-data-grid";
import AvatarDefault from "../../../assets/avatar-thinking-3-svgrepo-com.svg";
import { useEffect, useState } from "react";
import NavigateNextIcon from "@mui/icons-material/NavigateNext";
// import { useNavigate } from "react-router";
import styles from "./index.module.css";

// Mentor Name, Mentee Name, Created Date, Action: View xem thong tin chi tiet feedback, Action: delete có pop up ban có chắc ko

const breadcrumbArr = [
  { to: "/admin/dashboard", represent: "Dashboard" },
  { to: "/admin/feedback", represent: "Feedback" },
];

const fakeRowFeedbackData = [
  {
    id: "feedback1",
    mentor: {
      id: "user1",
      name: "A Minh Quan",
      imageUrl:
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTu5iuH9GH49VUAv0qvlrKiFRnsgEC6maRA9g&usqp=CAU",
    },
    mentee: {
      id: "user2",
      name: "B Minh Quan",
      imageUrl:
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTu5iuH9GH49VUAv0qvlrKiFRnsgEC6maRA9g&usqp=CAU",
    },
    createdDate: "October 13, 2014",
  },
  {
    id: "feedback2",
    mentor: {
      id: "user3",
      name: "D Minh Quan",
      imageUrl:
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTu5iuH9GH49VUAv0qvlrKiFRnsgEC6maRA9g&usqp=CAU",
    },
    mentee: {
      id: "user4",
      name: "G Minh Quan",
      imageUrl:
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTu5iuH9GH49VUAv0qvlrKiFRnsgEC6maRA9g&usqp=CAU",
    },
    createdDate: "October 13, 2015",
  },
  {
    id: "feedback3",
    mentor: {
      id: "user5",
      name: "B Minh Quan",
      imageUrl:
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTu5iuH9GH49VUAv0qvlrKiFRnsgEC6maRA9g&usqp=CAU",
    },
    mentee: {
      id: "user6",
      name: "Q Minh Quan",
      imageUrl:
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTu5iuH9GH49VUAv0qvlrKiFRnsgEC6maRA9g&usqp=CAU",
    },
    createdDate: "October 13, 2012",
  },
];

const fakeFeedbackDetail = {
  feebackId: "feedback1",
  mentor: {
    id: "user1",
    name: "A Minh Quan",
    imageUrl:
      "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTu5iuH9GH49VUAv0qvlrKiFRnsgEC6maRA9g&usqp=CAU",
  },
  mentee: {
    id: "user2",
    name: "B Minh Quan",
    imageUrl:
      "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTu5iuH9GH49VUAv0qvlrKiFRnsgEC6maRA9g&usqp=CAU",
  },
  createdDate: "October 13, 2012",
  rating: 4.5,
  content:
    "Nhóm học tập rất cần thiết trong dạy học theo định hướng phát triển năng lực người học. Khi học theo nhóm các em được chia sẻ ý kiến cho nhau, được hỗ trợ giúp đỡ nhau để cùng tiến bộ nhằm phát triển năng lực và phẩm chất, hoàn thiện bản thân trong quá trình học tập.",
};

const ManageFeedback = () => {
  // const navigate = useNavigate();
  const [isLoading, seIsLoading] = useState(true);
  const [isLoadingDetail, setIsLoadingDetail] = useState(false);
  const [feedbackRow, setFeedbackRow] = useState([]);
  const [feedbackDetail, setFeedbackDetail] = useState(null);
  const [isConfirmDialogOpen, setIsConfirmDialogOpen] = useState(false);
  const [isShowDialogOpen, setIsShowDialogOpen] = useState(false);
  const [chooseId, setChooseId] = useState("");

  useEffect(() => {
    fetch("http://localhost:9999/all-mentor")
      .then((resp) => resp.json())
      .then((data) => {
        setFeedbackRow(data);
      })
      .catch((err) => {
        console.log(err);
        setFeedbackRow([...fakeRowFeedbackData]);
      })
      .finally(() => {
        seIsLoading(false);
      });
  }, []);

  useEffect(() => {
    if (chooseId !== "" && isLoadingDetail)
      fetch("http://localhost:9999/feedback/id")
        .then((resp) => resp.json())
        .then((data) => {
          setFeedbackDetail(data);
        })
        .catch((err) => {
          console.log(err);
          setFeedbackDetail({ ...fakeFeedbackDetail });
        })
        .finally(() => {
          setIsLoadingDetail(false);
        });
  }, [chooseId, isLoadingDetail]);

  const handleCloseConfirmDialog = () => {
    setIsConfirmDialogOpen(false);
    setChooseId("");
  };

  const handleCloseShowDialog = () => {
    setIsShowDialogOpen(false);
    setChooseId("");
  };

  const handleClickView = (id) => {
    setIsShowDialogOpen(true);
    setIsLoadingDetail(true);
    setChooseId(id);
  };

  const handleClickDelete = (id) => {
    setIsConfirmDialogOpen(true);
    setChooseId(id);
  };

  const columns = [
    {
      field: "mentor",
      headerName: "Mentor Name",
      type: "string",
      flex: 0.25,
      align: "left",
      headerAlign: "left",
      renderHeader: (params) => (
        <strong style={{ fontSize: "16px" }}>{"Mentor Name"}</strong>
      ),
      valueGetter: ({ value }) => {
        return value.name;
      },
      renderCell: ({ row }) => {
        return (
          <div className={styles.mentorInfoWrapper}>
            <img src={row.mentor.imageUrl || AvatarDefault} alt="avatar" />
            <div className={styles.infoLeft}>
              <h4>{row.mentor.name}</h4>
            </div>
          </div>
        );
      },
    },
    {
      field: "mentee",
      headerName: "Mentee Name",
      type: "string",
      flex: 0.25,
      align: "left",
      headerAlign: "left",
      renderHeader: (params) => (
        <strong style={{ fontSize: "16px" }}>{"Mentee Name"}</strong>
      ),
      valueGetter: ({ value }) => {
        return value.name;
      },
      renderCell: ({ row }) => {
        return (
          <div className={styles.mentorInfoWrapper}>
            <img src={row.mentee.imageUrl || AvatarDefault} alt="avatar" />
            <div className={styles.infoLeft}>
              <h4>{row.mentee.name}</h4>
            </div>
          </div>
        );
      },
    },
    {
      field: "createdDate",
      headerName: "Created Date",
      type: "date",
      flex: 0.25,
      align: "left",
      headerAlign: "left",
      renderHeader: (params) => (
        <strong style={{ fontSize: "16px" }}>{"Created Date"}</strong>
      ),
      valueGetter: ({ value }) => {
        return new Date(value);
      },
    },
    {
      field: "actions",
      headerName: "Actions",
      type: "string",
      flex: 0.2,
      align: "center",
      headerAlign: "center",
      renderCell: ({ value, row }) => {
        return (
          <div className={styles.actionWrapper}>
            <Button
              variant="contained"
              color="primary"
              onClick={() => handleClickView(row.id)}
            >
              View
            </Button>
            <Button
              variant="contained"
              color="warning"
              onClick={() => handleClickDelete(row.id)}
            >
              Delete
            </Button>
          </div>
        );
      },
      renderHeader: (params) => (
        <strong style={{ fontSize: "16px" }}>{"Actions"}</strong>
      ),
    },
  ];

  return (
    <MainAdminLayout
      title="List of Feedback"
      breadCum={[...breadcrumbArr]}
      content={
        <>
          {isLoading ? (
            <div className={styles.customLoading}>
              <CircularProgress />
            </div>
          ) : (
            <div
              className={styles.layoutWrapper}
              style={{ height: 400, width: "100%" }}
            >
              <DataGrid
                sx={{
                  backgroundColor: "#fff",
                  padding: "24px",
                  "& .MuiDataGrid-columnHeaders": {
                    backgroundColor: "rgb(248, 249, 250)",
                  },
                }}
                rows={feedbackRow}
                columns={columns}
                initialState={{
                  pagination: {
                    paginationModel: { page: 0, pageSize: 5 },
                  },
                }}
                pageSizeOptions={[5, 10]}
              />
            </div>
          )}
        </>
      }
      isConfirmDialogOpen={isConfirmDialogOpen}
      onCloseConfirmDialog={handleCloseConfirmDialog}
      confirmDialogTitle="Confirm delete feedback?"
      confirmDialogContent={`Are you sure you want to delete this feedback?`}
      onAgreeConfirmDialog={() => handleClickDelete(chooseId)}
      onDisAgreeConfirmDialog={handleCloseConfirmDialog}
      isShowDialogOpen={isShowDialogOpen}
      onCloseShowDialog={handleCloseShowDialog}
      showDialogTitle={
        <>
          {isLoadingDetail || feedbackDetail === null ? (
            <Skeleton variant="circular" width={40} height={40} />
          ) : (
            <div className={styles.titleWrapper}>
              <div className={styles.mentorInfoWrapper}>
                <img
                  src={feedbackDetail.mentee.imageUrl || AvatarDefault}
                  alt="avatar"
                />
                <div className={styles.infoLeft}>
                  <h4>{feedbackDetail.mentee.name}</h4>
                  <div className={styles.ratingWrapper}>
                    <Rating
                      defaultValue={feedbackDetail.rating}
                      precision={0.5}
                      readOnly
                    />
                    <div className={styles.createdDate}>
                      <b>{feedbackDetail.createdDate}</b>
                    </div>
                  </div>
                </div>
              </div>
              <NavigateNextIcon />
              <div className={styles.mentorInfoWrapper}>
                <img
                  src={feedbackDetail.mentor.imageUrl || AvatarDefault}
                  alt="avatar"
                />
                <div className={styles.infoLeft}>
                  <h4>{feedbackDetail.mentor.name}</h4>
                </div>
              </div>
            </div>
          )}
        </>
      }
      showDialogContent={
        <>
          {isLoadingDetail || feedbackDetail === null ? (
            <Skeleton
              variant="rectangular"
              sx={{ maxWidth: "100%" }}
              width={600}
              height={200}
            />
          ) : (
            <>{feedbackDetail.content}</>
          )}
        </>
      }
    />
  );
};

export default ManageFeedback;
