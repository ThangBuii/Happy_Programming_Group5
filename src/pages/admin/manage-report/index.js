import { Button, CircularProgress } from "@mui/material";
import MainAdminLayout from "../../../component/admin/main-layout";
import { DataGrid } from "@mui/x-data-grid";
import AvatarDefault from "../../../assets/avatar-thinking-3-svgrepo-com.svg";
import { useEffect, useState } from "react";
// import { useNavigate } from "react-router";
import styles from "./index.module.css";

// Account Name, Title, Content, Create-date, Role(Mentee(1), Mentor(2)), Status(Reviewed(1), Pending(0)) Action: Update -> Với status đang Pending thì hiện nút update, Reviewed thì không

const breadcrumbArr = [
  { to: "/admin/dashboard", represent: "Dashboard" },
  { to: "/admin/report", represent: "Report" },
];

const fakeRowReportData = [
  {
    id: "user1",
    name: "Le Minh Quan",
    imageUrl:
      "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTu5iuH9GH49VUAv0qvlrKiFRnsgEC6maRA9g&usqp=CAU",
    title: "chat luong bai viet kem",
    content:
      "Có một điều mà tôi nghĩ những người thường xuyên theo dõi và đọc các bài viết trên Spiderum đều biết. Đó là các bài viết kém chất lượng xuất hiện ngày càng nhiều hơn. Thậm chí còn có những bài tôi không cho đó là bài viết. Những bài chỉ có 1 dòng, những bài chỉ có 5 cái gạch đầu dòng với mỗi dòng một từ, những bài đếm đi đếm lại chưa đến được 20 từ... Chuyện gì đã xảy ra vậy? Tôi sẽ không chỉ cụ thể bài viết nào ra đây, nhưng các bạn có thể dễ dàng tìm thấy chúng thôi.",
    createdDate: "October 13, 2014",
    role: 1,
    status: 0,
  },
  {
    id: "user2",
    name: "Pzzang",
    imageUrl:
      "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTu5iuH9GH49VUAv0qvlrKiFRnsgEC6maRA9g&usqp=CAU",
    title: "chat luong bai viet kem",
    content: "Có một điều mà tôi nghĩ những người thường xuyên theo dõi.",
    createdDate: "October 13, 2014",
    role: 2,
    status: 1,
  },
  {
    id: "user3",
    name: "Zed99",
    imageUrl:
      "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTu5iuH9GH49VUAv0qvlrKiFRnsgEC6maRA9g&usqp=CAU",
    title: "chat luong bai viet kem",
    content:
      "Có một điều mà tôi nghĩ những người thường xuyên theo dõi và đọc các bài viết trên Spiderum đều biết.",
    createdDate: "October 13, 2014",
    role: 1,
    status: 0,
  },
  {
    id: "user4",
    name: "Zed99",
    imageUrl:
      "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTu5iuH9GH49VUAv0qvlrKiFRnsgEC6maRA9g&usqp=CAU",
    title: "chat luong bai viet kem",
    content:
      "Có một điều mà tôi nghĩ những người thường xuyên theo dõi và đọc các bài viết trên Spiderum đều biết.",
    createdDate: "October 13, 2014",
    role: 1,
    status: 0,
  },
];

const ManageReport = () => {
  // const navigate = useNavigate();
  const [isLoading, seIsLoading] = useState(true);
  const [reportRow, setReportRow] = useState([]);

  useEffect(() => {
    fetch("http://localhost:9999/all-mentor")
      .then((resp) => resp.json())
      .then((data) => {
        setReportRow(data);
      })
      .catch((err) => {
        console.log(err);
        setReportRow([...fakeRowReportData]);
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
            <img src={row.imageUrl || AvatarDefault} alt="avatar" />
            <div className={styles.infoLeft}>
              <h4>{row.name}</h4>
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
            {value === 1 ? "Mentee" : value === 2 ? "Mentor" : ""}
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
      flex: 0.1,
      align: "center",
      headerAlign: "center",
      renderCell: ({ value, row }) => {
        return (
          <div className={styles.actionWrapper}>
            {row.status === 0 && (
              <Button variant="contained" color="success">
                Accept
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
    />
  );
};

export default ManageReport;
