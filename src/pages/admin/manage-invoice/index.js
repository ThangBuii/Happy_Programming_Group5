import {Button, CircularProgress } from "@mui/material";
import MainAdminLayout from "../../../component/admin/main-layout";
import { DataGrid } from "@mui/x-data-grid";
import { useEffect, useState } from "react";
// import { useNavigate } from "react-router";
import styles from "./index.module.css";
import { Link, useLocation } from "react-router-dom";
import { useNavigate } from "react-router";
import { request } from '../../../axios_helper'
// Booking ID, Created Date, Payment Method

const breadcrumbArr = [
  { to: "/admin/dashboard", represent: "Dashboard" },
  { to: "/admin/invoice", represent: "Invoice" },
];

const ManageInvoice = () => {
  // const navigate = useNavigate();
  const location = useLocation();
  const [isLoading, seIsLoading] = useState(true);
  const [invoiceRow, setInvoiceRow] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    request("GET", "/api/admin/invoice")
      .then((response) => {
        const rowsWithIds = response.data.map((row) => ({
          id: row.receipt_id,
          ...row,
        }));
        setInvoiceRow(rowsWithIds);
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
      field: "booking_id",
      headerName: "Booking No",
      type: "string",
      flex: 0.3,
      align: "left",
      headerAlign: "left",
      renderHeader: (params) => (
        <strong style={{ fontSize: "16px" }}>{"Booking No"}</strong>
      ),
      renderCell: ({ value }) => {
        return (
          <Link
            to={`/bookings/${value}`}
            state={{
              prevPath: {
                to: location.pathname,
                represent: "Manage Invoice",
              },
            }}
          >
            {value}
          </Link>
        );
      },
    },

    {
      field: "created_Date",
      headerName: "Created Date",
      type: "date",
      flex: 0.4,
      align: "left",
      headerAlign: "left",
      renderHeader: (params) => (
        <strong style={{ fontSize: "16px" }}>{"Created Date"}</strong>
      ),
      valueGetter: ({ value }) => {
        return new Date(value);
      },
    },
    {
      field: "payment_method",
      headerName: "Payment Method",
      type: "string",
      flex: 0.3,
      align: "left",
      headerAlign: "left",
      renderHeader: (params) => (
        <strong style={{ fontSize: "16px" }}>{"Payment Method"}</strong>
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
                navigate(`/invoice/${row.receipt_id}`, {
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
          </div>
        );
      },
    }
  ];

  return (
    <MainAdminLayout
      title="List of Invoice"
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
                rows={invoiceRow}
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

export default ManageInvoice;
