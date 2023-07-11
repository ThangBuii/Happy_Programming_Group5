import { Button, CircularProgress } from "@mui/material";
import MainAdminLayout from "../../../component/admin/main-layout";
import { DataGrid } from "@mui/x-data-grid";
import AvatarDefault from "../../../assets/avatar-thinking-3-svgrepo-com.svg";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router";
import styles from "./index.module.css";
import { request } from '../../../axios_helper'

// Mentee Name,Member Since,Numbers of Booking, Action : delete(pop up co muon delete ko), View ấn vào để xem thông tin chi tiết mentor

const breadcrumbArr = [
  { to: "/admin/dashboard", represent: "Dashboard" },
  { to: "/admin/mentee", represent: "Mentee" },
];

const ManageMentee = () => {
  const navigate = useNavigate();
  const [isLoading, seIsLoading] = useState(true);
  const [menteeRow, setMenteeRow] = useState([]);
  const [isConfirmDialogOpen, setIsConfirmDialogOpen] = useState(false);
  const [chooseId, setChooseId] = useState("");
  const [listIdLoading, setListIdLoading] = useState([]);
  const [snackBarState, setSnackBarState] = useState({
    isSnackBarOpen: false,
    severity: "",
    content: "",
  });

  useEffect(() => {
    request("GET", "/api/admin/mentee-list")
      .then((response) => {
        const rowsWithIds = response.data.map((row) => ({
          id: row.account_id,
          ...row,
        }));
        setMenteeRow(rowsWithIds);
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
          setMenteeRow((pre) => pre.filter((item) => item.id !== id));
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
      headerName: "Mentee Name",
      type: "string",
      flex: 0.2,
      align: "left",
      headerAlign: "left",
      renderHeader: (params) => (
        <strong style={{ fontSize: "16px" }}>{"Mentee Name"}</strong>
      ),
      renderCell: ({ row }) => {
        return (
          <div className={styles.mentorInfoWrapper}>
            <img src={row.avatar ? `data:image/jpeg;base64, ${row.avatar}` : AvatarDefault} alt="avatar" />
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
      title="List of Mentee"
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
                rows={menteeRow}
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
      confirmDialogTitle="Confirm delete mentee?"
      confirmDialogContent={`Are you sure you want to delete user ${getChooseName(
        menteeRow,
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

export default ManageMentee;
