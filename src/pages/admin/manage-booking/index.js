import { Button, CircularProgress } from "@mui/material";
import MainAdminLayout from "../../../component/admin/main-layout";
import { DataGrid } from "@mui/x-data-grid";
import AvatarDefault from "../../../assets/avatar-thinking-3-svgrepo-com.svg";
import { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router";
import styles from "./index.module.css";
import { request } from '../../../axios_helper'
// Mentee Name,Mentor Name,SessionID, Booking Time(Hiển thị tương tự trong sample), Status(Pending, Accepted, Rejected), Amount, Action: View -> Ấn vào hiện Booking Details

const breadcrumbArr = [
  { to: "/admin/dashboard", represent: "Dashboard" },
  { to: "/admin/bookings", represent: "Booking" },
];



const ManageBooking = () => {
  const navigate = useNavigate();
  const location = useLocation();
  const [isLoading, seIsLoading] = useState(true);
  const [bookingRow, setBookingRow] = useState([]);
  // const [chooseId, setChooseId] = useState("");

  useEffect(() => {
    request("GET", "/api/admin/bookings")
      .then((response) => {
        const rowsWithIds = response.data.map((row) => ({
          id: row.bookingID,
          ...row,
        }));
        setBookingRow(rowsWithIds);
      })
      .catch((err) => {
        console.log(err);
      })
      .finally(() => {
        seIsLoading(false);
      });
  }, []);

  const handleClickView = (id) => {
    navigate(`/bookings/${id}`, {
      state: {
        prevPath: {
          to: location.pathname,
          represent: "Manage Bookings",
        },
      },
    });
  };

  const columns = [
    {
      field: "mentor",
      headerName: "Mentor Name",
      type: "string",
      flex: 0.2,
      align: "left",
      headerAlign: "left",
      renderHeader: (params) => (
        <strong style={{ fontSize: "16px" }}>{"Mentor Name"}</strong>
      ),
      valueGetter: ({ row }) => {
        return row.mentorUsername;
      },
      renderCell: ({ row }) => {
        return (
          <div className={styles.mentorInfoWrapper}>
            <img src={row.avatarMentor || AvatarDefault} alt="avatar" />
            <div className={styles.infoLeft}>
              <h4>{row.mentorUsername}</h4>
            </div>
          </div>
        );
      },
    },
    {
      field: "mentee",
      headerName: "Mentee Name",
      type: "string",
      flex: 0.2,
      align: "left",
      headerAlign: "left",
      renderHeader: (params) => (
        <strong style={{ fontSize: "16px" }}>{"Mentee Name"}</strong>
      ),
      valueGetter: ({ row }) => {
        return row.menteeUsername;
      },
      renderCell: ({ row }) => {
        return (
          <div className={styles.mentorInfoWrapper}>
            <img src={row.avatarMentor || AvatarDefault} alt="avatar" />
            <div className={styles.infoLeft}>
              <h4>{row.menteeUsername}</h4>
            </div>
          </div>
        );
      },
    },
    {
      field: "session_id",
      headerName: "Session Id",
      type: "string",
      flex: 0.1,
      align: "left",
      headerAlign: "left",
      renderHeader: (params) => (
        <strong style={{ fontSize: "16px" }}>{"Session Id"}</strong>
      ),
    },
    {
      field: "created_date",
      headerName: "Created Date",
      type: "string",
      flex: 0.2,
      align: "left",
      headerAlign: "left",
      renderHeader: (params) => (
        <strong style={{ fontSize: "16px" }}>{"Created Date"}</strong>
      ),
      valueGetter: ({ row }) => {
        return row.created_date;
      },
      renderCell: ({ row }) => {
        return (
          <div className={styles.dateWrapper}>
            {row.created_date}
          </div>
        );
      },
    },
    {
      field: "status",
      headerName: "Status",
      type: "number",
      flex: 0.1,
      align: "center",
      headerAlign: "center",
      renderHeader: (params) => (
        <strong style={{ fontSize: "16px" }}>{"Status"}</strong>
      ),
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
            <Button
              variant="contained"
              color="primary"
              onClick={() => handleClickView(row.id)}
            >
              View
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
      title="List of Bookings"
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
                rows={bookingRow}
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
    />
  );
};

export default ManageBooking;
