import { CircularProgress } from "@mui/material";
import MainAdminLayout from "../../../component/admin/main-layout";
import { DataGrid } from "@mui/x-data-grid";
import { useEffect, useState } from "react";
// import { useNavigate } from "react-router";
import styles from "./index.module.css";
import { request } from '../../../axios_helper'
// Có một bảng ở dưới gồm có: Amount, Date, Receipt ID

const breadcrumbArr = [
  { to: "/admin/dashboard", represent: "Dashboard" },
  { to: "/admin/revenue", represent: "Revenue" },
];

const ManageRevenue = () => {
  // const navigate = useNavigate();
  const [isLoading, seIsLoading] = useState(true);
  const [revenueRow, setRevenueRow] = useState([]);

  useEffect(() => {
    request("GET", "/api/admin/revenue")
      .then((response) => {
        const rowsWithIds = response.data.map((row) => ({
          id: row.revenue_id,
          ...row,
        }));
        setRevenueRow(rowsWithIds);
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
        const value = Number(params.value);
        return isNaN(value) ? "" : `$${value.toFixed(2)}`;
      },
    },
    {
      field: "created_Date",
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
      field: "receipt_id",
      headerName: "Receipt Id",
      type: "string",
      flex: 0.3,
      align: "left",
      headerAlign: "left",
      renderHeader: (params) => (
        <strong style={{ fontSize: "16px" }}>{"Invoice No"}</strong>
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
