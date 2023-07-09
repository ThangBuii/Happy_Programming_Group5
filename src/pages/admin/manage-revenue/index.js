import { CircularProgress } from "@mui/material";
import MainAdminLayout from "../../../component/admin/main-layout";
import { DataGrid } from "@mui/x-data-grid";
import { useEffect, useState } from "react";
// import { useNavigate } from "react-router";
import Statistic from "../../../component/admin/statistic";
import FolderOutlinedIcon from "@mui/icons-material/FolderOutlined";
import styles from "./index.module.css";
import { request } from "../../../axios_helper";
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
  const [totalRevenue, setTotalRevenue] = useState();

  // useEffect(() => {
  //   request("GET", "/api/admin/revenue")
  //     .then((response) => {
  //       const rowsWithIds = response.data.map((row) => ({
  //         id: row.revenue_id,
  //         ...row,
  //       }));
  //       setRevenueRow(rowsWithIds);
  //     })
  //     .catch((err) => {
  //       console.log(err);
  //     })
  //     .finally(() => {
  //       seIsLoading(false);
  //     });
  // }, []);

  useEffect(() => {
    seIsLoading(true);
    Promise.all([
      fetch("http://localhost:9999/revenue-row"),
      fetch("http://localhost:9999/total-revenue"),
    ])
      .then((responses) => {
        return Promise.all(responses.map((response) => response.json()));
      })
      .then(([data1, data2]) => {
        setRevenueRow(data1);
        setTotalRevenue(data2);
      })
      .catch((err) => {
        console.log(err);
        setRevenueRow([...fakeRowRevenueData]);
        setTotalRevenue(50);
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
              <div className="row">
                <div className="col-sm-6 col-12 mx-auto">
                  <Statistic
                    icon={<FolderOutlinedIcon sx={{ color: "#ffbc34" }} />}
                    about={"Revenue"}
                    percent={totalRevenue}
                    quantity={"$62523"}
                    color={"#ffbc34"}
                  />
                </div>
              </div>
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
