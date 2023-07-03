import { Button, CircularProgress } from "@mui/material";
import MainAdminLayout from "../../../component/admin/main-layout";
import { DataGrid } from "@mui/x-data-grid";
import AvatarDefault from "../../../assets/avatar-thinking-3-svgrepo-com.svg";
import { useEffect, useState } from "react";
// import { useNavigate } from "react-router";
import styles from "./index.module.css";

// Mentor Name, Skill Name, Duration,Session Name,Status, Action: View

const breadcrumbArr = [
  { to: "/admin/dashboard", represent: "Dashboard" },
  { to: "/admin/sessions", represent: "Sessions" },
];

const fakeRowSessionData = [
  {
    id: "user1",
    name: "Le Minh Quan",
    imageUrl:
      "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTu5iuH9GH49VUAv0qvlrKiFRnsgEC6maRA9g&usqp=CAU",
    skillList: ["Javascript", "CSS"],
    duration: 6.5,
    sessionName: "Toi la ai",
    status: 0,
  },
  {
    id: "user2",
    name: "Hasagiiiii",
    imageUrl:
      "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTu5iuH9GH49VUAv0qvlrKiFRnsgEC6maRA9g&usqp=CAU",
    skillList: ["C#", "C", "C++", "Python", "Java", "React"],
    duration: 8,
    sessionName: "Bi kip 10 diem dai hoc",
    status: 1,
  },
  {
    id: "user3",
    name: "Pzzanggg",
    imageUrl:
      "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTu5iuH9GH49VUAv0qvlrKiFRnsgEC6maRA9g&usqp=CAU",
    skillList: ["Chem gio"],
    duration: 2.6,
    sessionName: "It vua moi nghe",
    status: 2,
  },
];

const ManageSession = () => {
  // const navigate = useNavigate();
  const [isLoading, seIsLoading] = useState(true);
  const [sessionRow, setSessionRow] = useState([]);

  useEffect(() => {
    fetch("http://localhost:9999/all-mentor")
      .then((resp) => resp.json())
      .then((data) => {
        setSessionRow(data);
      })
      .catch((err) => {
        console.log(err);
        setSessionRow([...fakeRowSessionData]);
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
      flex: 0.2,
      align: "left",
      headerAlign: "left",
      renderHeader: (params) => (
        <strong style={{ fontSize: "16px" }}>{"Mentor Name"}</strong>
      ),
      renderCell: ({ row }) => {
        return (
          <div className={styles.mentorInfoWrapper}>
            <img src={row.imageUrl || AvatarDefault} alt="avatar" />
            <div className={styles.infoLeft}>
              <h4>{row.name}</h4>
            </div>
          </div>
        );
      },
    },
    {
      field: "skillList",
      headerName: "Skill List",
      type: "string",
      flex: 0.2,
      minWidth: 270,
      align: "left",
      headerAlign: "left",
      renderCell: ({ value }) => {
        return (
          <div className={styles.skillList}>
            {value.map((skill, index) => {
              if (index <= 2)
                return (
                  <div key={index} className={styles.skillItem}>
                    {skill}
                  </div>
                );
              else return null;
            })}
            {value.length > 3 && <span>+ {value.length - 3} more</span>}
          </div>
        );
      },
      renderHeader: (params) => (
        <strong style={{ fontSize: "16px" }}>{"Skill List"}</strong>
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
        return `${params.value} h`;
      },
    },
    {
      field: "sessionName",
      headerName: "Session Name",
      type: "number",
      flex: 0.25,
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
      field: "id",
      headerName: "Actions",
      type: "string",
      flex: 0.15,
      align: "center",
      headerAlign: "center",
      renderCell: ({ value, row }) => {
        return (
          <div className={styles.actionWrapper}>
            <Button
              variant="contained"
              color="primary"
              // onClick={() => navigate(`/profile/${row.id}`)}
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
    />
  );
};

export default ManageSession;
