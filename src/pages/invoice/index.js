import { useContext, useEffect, useState } from "react";
import MainLayout from "../../component/main-layout";
import {
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
} from "@mui/material";
import AvatarDefault from "../../assets/avatar-thinking-3-svgrepo-com.svg";
import { Link } from "react-router-dom";
import { EyeFill } from "react-bootstrap-icons";
import styles from "./index.module.css";
import {request} from '../../axios_helper'
import { ApplicationContext } from "../../routes/AppRoutes";
// Invoice No, Mentor, Amount, Paid on

export function convertNumberToString(number, numOfString) {
  let str = number.toString();

  while (str.length < numOfString) {
    str = "0" + str;
  }

  return str;
}

const Invoice = () => {
  const [invoiceList, setInvoiceList] = useState([]);
  const { user } = useContext(ApplicationContext);
  const role = user.role;

  useEffect(() => {
    if (role === -1) return;
    const url = role === 1 ? "/api/mentor/invoice" : "/api/mentee/invoice"
    request("GET", url)
      .then((response) => {
        setInvoiceList(response.data);
      })
      .catch((err) => {
        console.log(err);
        
      });
  }, []);

  return (
    <MainLayout
      pageTitle={"Invoices"}
      layoutContent={
        <div className={styles.layoutWrapper}>
          <TableContainer
            sx={{
              background: "#E7E7D7",

              border: "5px solid #B5C49C",
              borderRadius: "10px",
            }}
          >
            <Table sx={{ minWidth: 650 }} aria-label="simple table">
              <TableHead>
                <TableRow>
                  <TableCell
                    // sx={{ minWidth: 220 }}
                    className={styles.tableCellHead}
                    align="left"
                  >
                    INVOICE NO
                  </TableCell>
                  <TableCell
                    // sx={{ minWidth: 150 }}
                    className={styles.tableCellHead}
                    align="left"
                  >
                    MENTOR
                  </TableCell>
                  <TableCell
                    // sx={{ minWidth: 500 }}
                    className={styles.tableCellHead}
                    align="left"
                  >
                    AMOUNT
                  </TableCell>
                  <TableCell className={styles.tableCellHead} align="left">
                    PAID ON
                  </TableCell>
                  <TableCell className={styles.tableCellHead} align="center">
                    ACTION
                  </TableCell>
                </TableRow>
              </TableHead>
              <TableBody>
                {invoiceList.map((item) => (
                  <TableRow
                    key={item.receipt_id}
                    sx={{ "&:last-child td, &:last-child th": { border: 0 } }}
                    hover={true}
                  >
                    <TableCell align="left">
                      <Link
                        to={`/invoice/${item.receipt_id}`}
                        className={styles.customIdLink}
                      >{`#INV-${convertNumberToString(
                        item.receipt_id
                      )}`}</Link>
                    </TableCell>
                    <TableCell align="left">
                      <div className={styles.mentorInfoWrapper}>
                        <img
                          src={item.avatar ? `data:image/jpeg;base64, ${item.avatar}` : AvatarDefault}
                          alt="avatar"
                        />
                        <div className={styles.infoLeft}>
                          <h4>{item.username}</h4>
                          <p>{item.email}</p>
                        </div>
                      </div>
                    </TableCell>
                    <TableCell align="left">
                      <div
                        className={styles.reportDetail}
                      >{`$${item.amount}`}</div>
                    </TableCell>
                    <TableCell align="left">{item.created_Date}</TableCell>
                    <TableCell align="center">
                      <Link
                        className={styles.customAction}
                        to={`/invoice/${item.receipt_id}`}
                      >
                        <EyeFill />
                        <span>View</span>
                      </Link>
                    </TableCell>
                  </TableRow>
                ))}
              </TableBody>
            </Table>
          </TableContainer>
        </div>
      }
    />
  );
};

export default Invoice;
