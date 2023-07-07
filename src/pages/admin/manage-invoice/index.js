import { CircularProgress } from "@mui/material";
import MainAdminLayout from "../../../component/admin/main-layout";
import { DataGrid } from "@mui/x-data-grid";
import { useEffect, useState } from "react";
// import { useNavigate } from "react-router";
import styles from "./index.module.css";
import { Link, useLocation } from "react-router-dom";

// Booking ID, Created Date, Payment Method

const breadcrumbArr = [
  { to: "/admin/dashboard", represent: "Dashboard" },
  { to: "/admin/invoice", represent: "Invoice" },
];

const fakeRowInvoiceData = [
  {
    id: "Booking1",
    paymentMethod: "ATM",
    createdDate: "October 13, 2014",
    bookingId: "Booking1",
  },
  {
    id: "Booking2",
    paymentMethod: "ATM",
    createdDate: "October 14, 2014",
    bookingId: "Booking2",
  },
  {
    id: "Booking3",
    paymentMethod: "ATM",
    createdDate: "October 12, 2014",
    bookingId: "Booking3",
  },
];

const ManageInvoice = () => {
  // const navigate = useNavigate();
  const location = useLocation();
  const [isLoading, seIsLoading] = useState(true);
  const [invoiceRow, setInvoiceRow] = useState([]);

  useEffect(() => {
    fetch("http://localhost:9999/all-mentor")
      .then((resp) => resp.json())
      .then((data) => {
        setInvoiceRow(data);
      })
      .catch((err) => {
        console.log(err);
        setInvoiceRow([...fakeRowInvoiceData]);
      })
      .finally(() => {
        seIsLoading(false);
      });
  }, []);

  const columns = [
    {
      field: "bookingId",
      headerName: "Booking Id",
      type: "string",
      flex: 0.3,
      align: "left",
      headerAlign: "left",
      renderHeader: (params) => (
        <strong style={{ fontSize: "16px" }}>{"Booking Id"}</strong>
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
      field: "createdDate",
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
      field: "paymentMethod",
      headerName: "Payment Method",
      type: "string",
      flex: 0.3,
      align: "left",
      headerAlign: "left",
      renderHeader: (params) => (
        <strong style={{ fontSize: "16px" }}>{"Payment Method"}</strong>
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
