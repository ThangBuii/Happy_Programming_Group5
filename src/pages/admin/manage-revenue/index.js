import { CircularProgress } from "@mui/material";
import MainAdminLayout from "../../../component/admin/main-layout";
import { DataGrid } from "@mui/x-data-grid";
import { useEffect, useState } from "react";
// import { useNavigate } from "react-router";
import styles from "./index.module.css";

// Có một bảng ở dưới gồm có: Amount, Date, Receipt ID

const breadcrumbArr = [
  { to: "/admin/dashboard", represent: "Dashboard" },
  { to: "/admin/revenue", represent: "Revenue" },
];

const fakeRowRevenueData = [
  {
    id: "Receipt1",
    amount: 2000.3,
    date: "October 13, 2014",
    receiptId: "Receipt1",
  },
  {
    id: "Receipt2",
    amount: 1500,
    date: "October 14, 2014",
    receiptId: "Receipt2",
  },
  {
    id: "Receipt3",
    amount: 1800.0,
    date: "October 12, 2014",
    receiptId: "Receipt3",
  },
];

const ManageRevenue = () => {
  // const navigate = useNavigate();
  const [isLoading, seIsLoading] = useState(true);
  const [revenueRow, setRevenueRow] = useState([]);

  useEffect(() => {
    fetch("http://localhost:9999/all-mentor")
      .then((resp) => resp.json())
      .then((data) => {
        setRevenueRow(data);
      })
      .catch((err) => {
        console.log(err);
        setRevenueRow([...fakeRowRevenueData]);
      })
      .finally(() => {
        seIsLoading(false);
      });
  }, []);

  const columns = [
    {
      field: "amount",
      headerName: "Amount",
      type: "string",
      flex: 0.3,
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
      field: "date",
      headerName: "Date",
      type: "date",
      flex: 0.4,
      align: "left",
      headerAlign: "left",
      renderHeader: (params) => (
        <strong style={{ fontSize: "16px" }}>{"Date"}</strong>
      ),
      valueGetter: ({ value }) => {
        return new Date(value);
      },
    },
    {
      field: "receiptId",
      headerName: "Receipt Id",
      type: "string",
      flex: 0.3,
      align: "left",
      headerAlign: "left",
      renderHeader: (params) => (
        <strong style={{ fontSize: "16px" }}>{"Receipt Id"}</strong>
      ),
    },
  ];

  return (
    <MainAdminLayout
      title="List of Revenue"
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
                rows={revenueRow}
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

export default ManageRevenue;
