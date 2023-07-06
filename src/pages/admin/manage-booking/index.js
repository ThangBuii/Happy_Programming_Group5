import { Button, CircularProgress } from "@mui/material";
import MainAdminLayout from "../../../component/admin/main-layout";
import { DataGrid } from "@mui/x-data-grid";
import AvatarDefault from "../../../assets/avatar-thinking-3-svgrepo-com.svg";
import { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router";
import styles from "./index.module.css";

// Mentee Name,Mentor Name,SessionID, Booking Time(Hiển thị tương tự trong sample), Status(Pending, Accepted, Rejected), Amount, Action: View -> Ấn vào hiện Booking Details

const breadcrumbArr = [
  { to: "/admin/dashboard", represent: "Dashboard" },
  { to: "/admin/bookings", represent: "Booking" },
];

const fakeRowBookingData = [
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
    sessionId: "session1",
    bookingDate: "October 13, 2014",
    bookFrom: "20:00",
    bookTo: "21:15",
    status: 0,
    amount: 1000.2,
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
    sessionId: "session3",
    bookingDate: "October 13, 2015",
    bookFrom: "11:00",
    bookTo: "12:15",
    status: 2,
    amount: 2000.2,
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
    sessionId: "session2",
    bookingDate: "October 13, 2012",
    bookFrom: "18:00",
    bookTo: "20:15",
    status: 1,
    amount: 1050.2,
  },
];

const ManageBooking = () => {
  const navigate = useNavigate();
  const location = useLocation();
  const [isLoading, seIsLoading] = useState(true);
  const [bookingRow, setBookingRow] = useState([]);
  // const [chooseId, setChooseId] = useState("");

  useEffect(() => {
    fetch("http://localhost:9999/all-bookings")
      .then((resp) => resp.json())
      .then((data) => {
        setBookingRow([...data]);
      })
      .catch((err) => {
        console.log(err);
        setBookingRow([...fakeRowBookingData]);
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
      flex: 0.2,
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
      field: "sessionId",
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
      field: "bookingDate",
      headerName: "Created Date",
      type: "date",
      flex: 0.2,
      align: "left",
      headerAlign: "left",
      renderHeader: (params) => (
        <strong style={{ fontSize: "16px" }}>{"Created Date"}</strong>
      ),
      valueGetter: ({ value }) => {
        return new Date(value);
      },
      renderCell: ({ row }) => {
        return (
          <div className={styles.dateWrapper}>
            {row.bookingDate}
            <span style={{ display: "block", color: "#1e88e5" }}>
              {row.bookFrom} - {row.bookTo}
            </span>
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
      field: "amount",
      headerName: "Amount",
      type: "string",
      flex: 0.1,
      align: "left",
      headerAlign: "left",
      renderHeader: (params) => (
        <strong style={{ fontSize: "16px" }}>{"Amount"}</strong>
      ),
      valueFormatter: (params) => {
        return `$${params.value.toFixed(2)}`;
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
