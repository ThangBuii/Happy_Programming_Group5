import { Button, CircularProgress } from "@mui/material";
import MainAdminLayout from "../../../component/admin/main-layout";
import { DataGrid } from "@mui/x-data-grid";
import AvatarDefault from "../../../assets/avatar-thinking-3-svgrepo-com.svg";
import { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router";
import styles from "./index.module.css";
import { request } from '../../../axios_helper'
// Mentor Name, Skill Name, Duration,Session Name,Status, Action: View

const breadcrumbArr = [
  { to: "/admin/dashboard", represent: "Dashboard" },
  { to: "/admin/sessions", represent: "Sessions" },
];


const ManageSession = () => {
  const location = useLocation();
  const navigate = useNavigate();
  const [isLoading, seIsLoading] = useState(true);
  const [listIdLoading, setListIdLoading] = useState([]);
  const [snackBarState, setSnackBarState] = useState({
    isSnackBarOpen: false,
    severity: "",
    content: "",
  });
  const [sessionRow, setSessionRow] = useState([]);

  useEffect(() => {
    request("GET", "/api/admin/session")
      .then((response) => {
        const rowsWithIds = response.data.map((row) => ({
          id: row.session_id,
          ...row,
        }));
        setSessionRow(rowsWithIds);
      })
      .catch((err) => {
        console.log(err);
      })
      .finally(() => {
        seIsLoading(false);
      });
  }, []);

  const handleClickAccept = async (id) => {
    await processStatus(id, 1);
  };
  const handleClickReject = async (id) => {
    await processStatus(id, 2);
  };

  const processStatus = async (id, action) => {
    setListIdLoading((pre) => [...pre, id]);

    const requestBody = {
      status: action
    };
    // may cai nay nen dung post, sau co the sua lai nha
    request("PUT", `/api/admin/session/${id}`,requestBody)
      .then((response) => {
        if (response.status === 200)
          window.location.reload()
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
      field: "username",
      headerName: "Mentor Name",
      type: "string",
      flex: 0.15,
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
      field: "skill_name",
      headerName: "Skill List",
      type: "string",
      flex: 0.1,
      align: "left",
      headerAlign: "left",
      renderCell: ({ row }) => {
        return (
          <div className={styles.skillList}>
            <div key={row.session_id} className={styles.skillItem}>
              {row.skill_name}
            </div>
          </div>
        );
      },
      renderHeader: (params) => (
        <strong style={{ fontSize: "16px" }}>{"Skill"}</strong>
      ),
    },
    {
      field: "duration",
      headerName: "Duration",
      type: "number",
      flex: 0.1,
      align: "center",
      headerAlign: "center",
      renderHeader: (params) => (
        <strong style={{ fontSize: "16px" }}>{"Duration"}</strong>
      ),
      valueFormatter: (params) => {
        return `${params.value} min`;
      },
    },
    {
      field: "session_Name",
      headerName: "Session Name",
      type: "number",
      flex: 0.15,
      align: "left",
      headerAlign: "left",
      renderHeader: (params) => (
        <strong style={{ fontSize: "16px" }}>{"Session Name"}</strong>
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
                  : value === 2
                    ? styles.rejectStatus
                    : ""
            }
          >
            {value === 0
              ? "Pending"
              : value === 1
                ? "Accepted"
                : value === 2
                  ? "Rejected"
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
      flex: 0.25,
      align: "center",
      headerAlign: "center",
      renderCell: ({ value, row }) => {
        return (
          <div className={styles.actionWrapper}>
            <Button
              variant="contained"
              color="primary"
              onClick={() =>
                navigate(`/sessions/${row.id}`, {
                  state: {
                    prevPath: {
                      to: location.pathname,
                      represent: "Manage Sessions",
                    },
                  },
                })
              }
            >
              View
            </Button>
            {row.status === 0 && (
              <>
                <Button
                  variant="contained"
                  color="success"
                  onClick={() => handleClickAccept(row.session_id)}
                  disabled={listIdLoading.includes(row.session_id)}
                >
                  Accept
                </Button>
                <Button
                  variant="contained"
                  color="warning"
                  onClick={() => handleClickReject(row.session_id)}
                  disabled={listIdLoading.includes(row.session_id)}
                >
                  Reject
                </Button>
              </>
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
      title="List of Sessions"
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
                rows={sessionRow}
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
      isSnackBarOpen={snackBarState.isSnackBarOpen}
      onCloseSnackBar={onCloseSnackBar}
      severity={snackBarState.severity}
      severityContent={snackBarState.content}
    />
  );
};

export default ManageSession;
