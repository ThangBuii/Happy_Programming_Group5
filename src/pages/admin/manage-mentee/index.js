import { Button, CircularProgress } from "@mui/material";
import MainAdminLayout from "../../../component/admin/main-layout";
import { DataGrid } from "@mui/x-data-grid";
import AvatarDefault from "../../../assets/avatar-thinking-3-svgrepo-com.svg";
import { useEffect, useState } from "react";
import styles from "./index.module.css";
import { useNavigate } from "react-router";

// Mentor Name,Member Since,Numbers of Booking, Action : delete(pop up co muon delete ko), View ấn vào để xem thông tin chi tiết mentor

const breadcrumbArr = [
  { to: "/admin/dashboard", represent: "Dashboard" },
  { to: "/admin/mentee", represent: "Mentee" },
];

const fakeRowMentorData = [
  {
    id: "user1",
    name: "Le Minh Quan",
    imageUrl:
      "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTu5iuH9GH49VUAv0qvlrKiFRnsgEC6maRA9g&usqp=CAU",
    numOfBookings: 6,
    memberSince: "08, August, 2023",
  },
  {
    id: "user2",
    name: "Hasagiiiii",
    imageUrl:
      "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTu5iuH9GH49VUAv0qvlrKiFRnsgEC6maRA9g&usqp=CAU",
    numOfBookings: 7,
    memberSince: "08, August, 2023",
  },
  {
    id: "user3",
    name: "Pzzanggg",
    imageUrl:
      "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTu5iuH9GH49VUAv0qvlrKiFRnsgEC6maRA9g&usqp=CAU",
    numOfBookings: 5,
    memberSince: "09, August, 2023",
  },
];

const ManageMentee = () => {
  const navigate = useNavigate();
  const [isLoading, seIsLoading] = useState(true);
  const [mentorRow, setMentorRow] = useState([]);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [chooseId, setChooseId] = useState("");

  useEffect(() => {
    fetch("http://localhost:9999/all-mentor")
      .then((resp) => resp.json())
      .then((data) => {
        setMentorRow(data);
      })
      .catch((err) => {
        console.log(err);
        setMentorRow([...fakeRowMentorData]);
      })
      .finally(() => {
        seIsLoading(false);
      });
  }, []);

  const handleCloseDialog = () => {
    setIsModalOpen(false);
    setChooseId("");
  };

  const handleClickDelete = (id) => {
    setIsModalOpen(true);
    setChooseId(id);
  };

  const getChooseName = (mentorRowTmp, id) => {
    if (id === "" || mentorRowTmp.lenght === 0) return "";
    const item = mentorRowTmp.find((item) => item.id === id);
    if (!item) return "";
    return item.name;
  };

  const columns = [
    {
      field: "name",
      headerName: "Mentor name",
      type: "string",
      flex: 0.3,
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
      field: "memberSince",
      headerName: "Member Since",
      type: "string",
      flex: 0.3,
      align: "left",
      headerAlign: "left",
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
      renderHeader: (params) => (
        <strong style={{ fontSize: "16px" }}>{"Numbers of Booking"}</strong>
      ),
    },
    {
      field: "id",
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
              onClick={() => navigate(`/profile/${row.id}`)}
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
      isDialogOpen={isModalOpen}
      onClose={handleCloseDialog}
      dialogTitle="Confirm delete mentor?"
      dialogContent={`Are you sure you want to delete user ${getChooseName(
        mentorRow,
        chooseId
      )}`}
      onAgreeDialog={() => handleClickDelete(chooseId)}
      onDisAgreeDialog={handleCloseDialog}
    />
  );
};

export default ManageMentee;
