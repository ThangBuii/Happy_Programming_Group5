import { Button, CircularProgress, TextField } from "@mui/material";
import MainAdminLayout from "../../../component/admin/main-layout";
import { DataGrid } from "@mui/x-data-grid";
import AvatarDefault from "../../../assets/avatar-thinking-3-svgrepo-com.svg";
import { useEffect, useRef, useState } from "react";
// import { useNavigate } from "react-router";
import styles from "./index.module.css";
import { request } from '../../../axios_helper'
// Account Name, Title, Content, Create-date, Role(Mentee(1), Mentor(2)), Status(Reviewed(1), Pending(0)) Action: Update -> Với status đang Pending thì hiện nút update, Reviewed thì không

const breadcrumbArr = [
  { to: "/admin/dashboard", represent: "Dashboard" },
  { to: "/admin/report", represent: "Report" },
];



const ManageReport = () => {
  // const navigate = useNavigate();
  const [isLoading, seIsLoading] = useState(true);
  const [reportRow, setReportRow] = useState([]);
  const [isShowDialogOpen, setIsShowDialogOpen] = useState(false);
  const [chooseId, setChooseId] = useState("");
  const inputRef = useRef(null);
  const [listIdLoading, setListIdLoading] = useState([]);
  const [snackBarState, setSnackBarState] = useState({
    isSnackBarOpen: false,
    severity: "",
    content: "",
  });

  const handleClickUpdate = (id) => {
    setIsShowDialogOpen(true);
    setChooseId(id);
  };

  const handleCloseShowDialog = () => {
    setIsShowDialogOpen(false);
    setChooseId("");
  };

  const handleGetReport = (id) => {
    return reportRow.find((item) => item.id === id);
  };

  const handleConfirmUpdate = async (id) => {
    setListIdLoading((pre) => [...pre, id]);

    console.log("check data: >> ", inputRef.current.value, id);

    const requestBody = {
      answer: inputRef.current.value,
      report_id: id
    };
    // post ma gui data len voi inputRef.current.value ******
    request("PUT", "/api/admin/report",requestBody)
      .then((response) => {
        if (response.status === 200) {
          setReportRow((pre) =>
            pre.map((item) => {
              if (item.report_id === id) {
                return {
                  ...item,
                  status: 1,
                };
              }
              return item;
            })
          );
          handleCloseShowDialog(id);
        }
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

  useEffect(() => {
    request("GET", "/api/admin/reports")
      .then((response) => {
        const rowsWithIds = response.data.map((row) => ({
          id: row.report_id,
          ...row,
        }));
        setReportRow(rowsWithIds);
      })
      .catch((err) => {
        console.log(err);
      })
      .finally(() => {
        seIsLoading(false);
      });
  }, []);

  const columns = [
    {
      field: "name",
      headerName: "Mentor Name",
      type: "string",
      flex: 0.13,
      align: "left",
      headerAlign: "left",
      renderHeader: (params) => (
        <strong style={{ fontSize: "16px" }}>{"Mentor Name"}</strong>
      ),
      renderCell: ({ row }) => {
        return (
          <div className={styles.mentorInfoWrapper}>
            <img src={row.avatar || AvatarDefault} alt="avatar" />
            <div className={styles.infoLeft}>
              <h4>{row.username}</h4>
            </div>
          </div>
        );
      },
    },
    {
      field: "title",
      headerName: "Title",
      type: "string",
      flex: 0.15,
      align: "left",
      headerAlign: "left",
      renderHeader: (params) => (
        <strong style={{ fontSize: "16px" }}>{"Title"}</strong>
      ),
    },
    {
      field: "content",
      headerName: "Content",
      type: "string",
      flex: 0.4,
      align: "left",
      headerAlign: "left",
      renderHeader: (params) => (
        <strong style={{ fontSize: "16px" }}>{"Content"}</strong>
      ),
      renderCell: ({ value }) => (
        <div className={styles.customContent}>
          <span>{value}</span>
        </div>
      ),
    },
    {
      field: "role",
      headerName: "Role",
      type: "number",
      flex: 0.1,
      align: "center",
      headerAlign: "center",
      renderCell: ({ value }) => {
        return (
          <span
            className={
              value === 1
                ? styles.acceptStatus
                : value === 2
                ? styles.rejectStatus
                : ""
            }
          >
            {value === 1 ? "Mentor" : value === 2 ? "Mentee" : ""}
          </span>
        );
      },
      renderHeader: (params) => (
        <strong style={{ fontSize: "16px" }}>{"Role"}</strong>
      ),
    },

    {
      field: "status",
      headerName: "Status",
      type: "number",
      flex: 0.1,
      align: "center",
      headerAlign: "center",
      renderCell: ({ value }) => {
        return (
          <span
            className={
              value === 0
                ? styles.pendindStatus
                : value === 1
                ? styles.acceptStatus
                : ""
            }
          >
            {value === 0
              ? "Pending"
              : value === 1
              ? "Reviewed"
              : ""}
          </span>
        );
      },
      renderHeader: (params) => (
        <strong style={{ fontSize: "16px" }}>{"Status"}</strong>
      ),
    },
    {
      field: "actions",
      headerName: "Actions",
      type: "string",
      flex: 0.1,
      align: "center",
      headerAlign: "center",
      renderCell: ({ value, row }) => {
        return (
          <div className={styles.actionWrapper}>
            {row.status === 0 && (
              <Button
                variant="contained"
                color="success"
                onClick={() => handleClickUpdate(row.report_id)}
                disabled={listIdLoading.includes(row.report_id)}
              >
                Update
              </Button>
            )}
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
      title="List of Reports"
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
              style={{ height: 570, width: "100%" }}
            >
              <DataGrid
                sx={{
                  backgroundColor: "#fff",
                  padding: "24px",
                  "& .MuiDataGrid-columnHeaders": {
                    backgroundColor: "rgb(248, 249, 250)",
                  },
                  "& .MuiDataGrid-row, & .MuiDataGrid-cell": {
                    maxHeight: "80px !important",
                    minHeight: "80px !important",
                  },
                }}
                rows={reportRow}
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
      isShowDialogOpen={isShowDialogOpen}
      onCloseShowDialog={handleCloseShowDialog}
      showDialogTitle={
        <div className={styles.titleWrapper}>
          <h4>Update Report</h4>
        </div>
      }
      showDialogContent={
        handleGetReport(chooseId) && (
          <div className={styles.contentWrapper}>
            <div className={styles.topCtWrarpper}>
              <h4>
                Title <span>{handleGetReport(chooseId).createdDate}</span>
              </h4>
              <div className={styles.reportDetail}>
                {handleGetReport(chooseId).title}
              </div>
              <h4>Content</h4>
              <div className={styles.reportDetail}>
                {handleGetReport(chooseId).content}
              </div>
              <h4>Answer</h4>
              <div className={styles.reportAnswer}>
                <TextField
                  multiline
                  minRows={3}
                  maxRows={5}
                  fullWidth
                  inputRef={inputRef}
                />
              </div>
            </div>
            <div className={styles.botCtWrarpper}>
              <Button
                variant="contained"
                color="primary"
                onClick={() => handleConfirmUpdate(chooseId)}
                disabled={listIdLoading.includes(chooseId)}
              >
                Update
              </Button>
              <Button
                variant="contained"
                color="secondary"
                onClick={handleCloseShowDialog}
              >
                Close
              </Button>
            </div>
          </div>
        )
      }
      isSnackBarOpen={snackBarState.isSnackBarOpen}
      onCloseSnackBar={onCloseSnackBar}
      severity={snackBarState.severity}
      severityContent={snackBarState.content}
    />
  );
};

export default ManageReport;
