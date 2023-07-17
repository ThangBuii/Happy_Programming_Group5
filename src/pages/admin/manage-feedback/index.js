import { Button, CircularProgress, Rating, Skeleton } from "@mui/material";
import MainAdminLayout from "../../../component/admin/main-layout";
import { DataGrid } from "@mui/x-data-grid";
import AvatarDefault from "../../../assets/avatar-thinking-3-svgrepo-com.svg";
import { useEffect, useState } from "react";
import NavigateNextIcon from "@mui/icons-material/NavigateNext";
// import { useNavigate } from "react-router";
import styles from "./index.module.css";
import { request } from '../../../axios_helper'
// Mentor Name, Mentee Name, Created Date, Action: View xem thong tin chi tiet feedback, Action: delete có pop up ban có chắc ko

const breadcrumbArr = [
  { to: "/admin/dashboard", represent: "Dashboard" },
  { to: "/admin/feedback", represent: "Feedback" },
];


const ManageFeedback = () => {
  // const navigate = useNavigate();
  const [isLoading, seIsLoading] = useState(true);
  const [isLoadingDetail, setIsLoadingDetail] = useState(false);
  const [listIdLoading, setListIdLoading] = useState([]);
  const [snackBarState, setSnackBarState] = useState({
    isSnackBarOpen: false,
    severity: "",
    content: "",
  });
  const [feedbackRow, setFeedbackRow] = useState([]);
  const [feedbackDetail, setFeedbackDetail] = useState(null);
  const [isConfirmDialogOpen, setIsConfirmDialogOpen] = useState(false);
  const [isShowDialogOpen, setIsShowDialogOpen] = useState(false);
  const [chooseId, setChooseId] = useState("");

  useEffect(() => {
    request("GET", "/api/admin/feedbacks")
      .then((response) => {
        const rowsWithIds = response.data.map((row) => ({
          id: row.feedback_id,
          ...row,
        }));
        setFeedbackRow(rowsWithIds);
      })
      .catch((err) => {
        console.log(err);
      })
      .finally(() => {
        seIsLoading(false);
      });
  }, []);

  useEffect(() => {
    if (chooseId !== "" && isLoadingDetail)
      request("GET", `/api/admin/feedback/${chooseId}`)
        .then((response) => {
          setFeedbackDetail(response.data);
        })
        .catch((err) => {
          console.log(err);
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

  const handleAgreeDelete = async (id) => {
    handleCloseConfirmDialog(id);
    setListIdLoading((pre) => [...pre, id]);

    request("DELETE", `/api/feedback/${id}`)
      .then((response) => {
        if (response.status === 200)
          setFeedbackRow((pre) => pre.filter((item) => item.id !== id));
      })
      .catch((err) => {
        console.log(err);
        setSnackBarState((pre) => ({
          isSnackBarOpen: true,
          severity: "error",
          content: "Something went wrong!",
        }));
      })
      .finally(() => {
        setListIdLoading((pre) => pre.filter((item) => item !== id));
      });
  };

  const onCloseSnackBar = (event, reason) => {
    if (reason === "clickaway") {
      return;
    }

    setSnackBarState((pre) => ({
      isSnackBarOpen: false,
      severity: "",
      content: "",
    }));
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
      valueGetter: ({ row }) => {
        return row.mentor_username;
      },
      renderCell: ({ row }) => {
        return (
          <div className={styles.mentorInfoWrapper}>
            <img src={row.mentor_avatar ? `data:image/jpeg;base64, ${row.mentor_avatar}` : AvatarDefault} alt="avatar" />
            <div className={styles.infoLeft}>
              <h4>{row.mentor_username}</h4>
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
      valueGetter: ({ row }) => {
        return row.mentee_username;
      },
      renderCell: ({ row }) => {
        return (
          <div className={styles.mentorInfoWrapper}>
            <img src={row.mentee_avatar ? `data:image/jpeg;base64, ${row.mentee_avatar}` : AvatarDefault} alt="avatar" />
            <div className={styles.infoLeft}>
              <h4>{row.mentee_username}</h4>
            </div>
          </div>
        );
      },
    },
    {
      field: "created_date",
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
              onClick={() => handleClickView(row.feedback_id)}
            >
              View
            </Button>
            <Button
              variant="contained"
              color="warning"
              onClick={() => handleClickDelete(row.feedback_id)}
              disabled={listIdLoading.includes(row.feedback_id)}
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
      onAgreeConfirmDialog={() => handleAgreeDelete(chooseId)}
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
                  src={feedbackDetail.mentee_avatar ? `data:image/jpeg;base64, ${feedbackDetail.mentee_avatar}` : AvatarDefault}
                  alt="avatar"
                />
                <div className={styles.infoLeft}>
                  <h4>{feedbackDetail.mentee_username}</h4>
                  <div className={styles.ratingWrapper}>
                    <Rating
                      defaultValue={feedbackDetail.rating}
                      precision={0.5}
                      readOnly
                    />
                    <div className={styles.createdDate}>
                      <b>{feedbackDetail.created_date}</b>
                    </div>
                  </div>
                </div>
              </div>
              <NavigateNextIcon />
              <div className={styles.mentorInfoWrapper}>
                <img
                  src={feedbackDetail.mentor_avatar ? `data:image/jpeg;base64, ${feedbackDetail.mentor_avatar}` : AvatarDefault}
                  alt="avatar"
                />
                <div className={styles.infoLeft}>
                  <h4>{feedbackDetail.mentor_username}</h4>
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
      isSnackBarOpen={snackBarState.isSnackBarOpen}
      onCloseSnackBar={onCloseSnackBar}
      severity={snackBarState.severity}
      severityContent={snackBarState.content}
    />
  );
};

export default ManageFeedback;
