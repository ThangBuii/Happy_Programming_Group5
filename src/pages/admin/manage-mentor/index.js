import { Button, CircularProgress } from "@mui/material";
import MainAdminLayout from "../../../component/admin/main-layout";
import { DataGrid } from "@mui/x-data-grid";
import AvatarDefault from "../../../assets/avatar-thinking-3-svgrepo-com.svg";
import { useEffect, useState } from "react";
import styles from "./index.module.css";
import { useNavigate } from "react-router";
import { request } from '../../../axios_helper'

// Mentor Name,Member Since,Numbers of Booking,Earned, Action : delete(pop up co muon delete ko),View ấn vào để xem thông tin chi tiết mentee

const breadcrumbArr = [
  { to: "/admin/dashboard", represent: "Dashboard" },
  { to: "/admin/mentor", represent: "Mentor" },
];


const ManageMentor = () => {
  const navigate = useNavigate();
  const [isLoading, seIsLoading] = useState(true);
  const [mentorRow, setMentorRow] = useState([]);
  const [isConfirmDialogOpen, setIsConfirmDialogOpen] = useState(false);
  const [chooseId, setChooseId] = useState("");
  const [listIdLoading, setListIdLoading] = useState([]);
  const [snackBarState, setSnackBarState] = useState({
    isSnackBarOpen: false,
    severity: "",
    content: "",
  });

  useEffect(() => {
    request("GET", "/api/admin/mentor-list")
      .then((response) => {
        const rowsWithIds = response.data.map((row) => ({
          id: row.account_id,
          ...row,
        }));
        setMentorRow(rowsWithIds);
      })
      .catch((error) => {
        console.error(error);
      })
      .finally(() => {
        seIsLoading(false);
      });
  }, []);

  const handleCloseConfirmDialog = () => {
    setIsConfirmDialogOpen(false);
    setChooseId("");
  };

  const handleClickDelete = (id) => {
    setIsConfirmDialogOpen(true);
    setChooseId(id);
  };

  const getChooseName = (rowTmp, id) => {
    if (id === "" || rowTmp.lenght === 0) return "";
    const item = rowTmp.find((item) => item.id === id);
    if (!item) return "";
    return item.name;
  };

  const handleAgreeDelete = async (id) => {
    handleCloseConfirmDialog(id);
    setListIdLoading((pre) => [...pre, id]);

    await fetch(`http://localhost:9999/mentee/delete/${id}`)
      .then((resp) => resp.json())
      .then((data) => {
        if (data.isSuccess === 0)
          setMentorRow((pre) => pre.filter((item) => item.id !== id));
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
      field: "name",
      headerName: "Mentor Name",
      type: "string",
      flex: 0.2,
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
      field: "created_date",
      headerName: "Member Since",
      type: "date",
      flex: 0.2,
      align: "left",
      headerAlign: "left",
      valueGetter: ({ value }) => {
        return new Date(value);
      },
      renderHeader: (params) => (
        <strong style={{ fontSize: "16px" }}>{"Member Since"}</strong>
      ),
    },
    {
      field: "numOfBookings",
      headerName: "Numbers of Booking",
      type: "number",
      flex: 0.2,
      align: "center",
      headerAlign: "center",
      renderCell: ({ row }) => {
        return (
          <div className={styles.infoLeft}>
            <h4>{row.numberOfBooking}</h4>
          </div>
        );
      },
      renderHeader: (params) => (
        <strong style={{ fontSize: "16px" }}>{"Numbers of Booking"}</strong>
      ),
    },
    {
      field: "earned",
      headerName: "Earned",
      type: "number",
      flex: 0.2,
      align: "center",
      headerAlign: "center",
      renderCell: ({ row }) => {
        return (
          <div className={styles.infoLeft}>
            {row.earned === "0" ? (
              <h4>None</h4>
            ) : (
              <h4>${row.earned}</h4>
            )}
          </div>
        );
      },
      renderHeader: (params) => (
        <strong style={{ fontSize: "16px" }}>{"Earned"}</strong>
      ),
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
              onClick={() => navigate(`/profile/${row.account_id}`)}
            >
              View
            </Button>
            <Button
              variant="contained"
              color="warning"
              onClick={() => handleClickDelete(row.account_id)}
              disabled={listIdLoading.includes(row.account_id)}
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
      title="List of Mentor"
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
                rows={mentorRow}
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
      confirmDialogTitle="Confirm delete mentor?"
      confirmDialogContent={`Are you sure you want to delete user ${getChooseName(
        mentorRow,
        chooseId
      )}`}
      onAgreeConfirmDialog={() => handleAgreeDelete(chooseId)}
      onDisAgreeConfirmDialog={handleCloseConfirmDialog}
      isSnackBarOpen={snackBarState.isSnackBarOpen}
      onCloseSnackBar={onCloseSnackBar}
      severity={snackBarState.severity}
      severityContent={snackBarState.content}
    />
  );
};

export default ManageMentor;
