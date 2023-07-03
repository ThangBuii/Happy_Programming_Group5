import { useEffect, useState } from "react";
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

// Invoice No, Mentor, Amount, Paid on

function createData(invoiceId, mentorInfo, amount, paidOn) {
  return { invoiceId, mentorInfo, amount, paidOn };
}

const fakeInvoiceListData = [
  createData(
    1,
    {
      accountId: "user1",
      name: "Le Van Luyen",
      email: "luyen@xxvideo.com",
      imageUrl:
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTu5iuH9GH49VUAv0qvlrKiFRnsgEC6maRA9g&usqp=CAU",
    },
    200,
    "	14 Nov 2019"
  ),
  createData(
    11,
    {
      accountId: "user1",
      name: "Le Van Luyen",
      email: "luyen@xxvideo.com",
      imageUrl:
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTu5iuH9GH49VUAv0qvlrKiFRnsgEC6maRA9g&usqp=CAU",
    },
    300,
    "	14 Nov 2019"
  ),
  createData(
    11111,
    {
      accountId: "user1",
      name: "Le Van Luyen",
      email: "luyen@xxvideo.com",
      imageUrl:
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTu5iuH9GH49VUAv0qvlrKiFRnsgEC6maRA9g&usqp=CAU",
    },
    230,
    "	14 Nov 2019"
  ),
];

export function convertNumberToString(number, numOfString) {
  let str = number.toString();

  while (str.length < numOfString) {
    str = "0" + str;
  }

  return str;
}

const Invoice = () => {
  const [invoiceList, setInvoiceList] = useState([...fakeInvoiceListData]);

  useEffect(() => {
    fetch("http://localhost:9999/report-list/mentorId")
      .then((resp) => resp.json())
      .then((data) => {
        setInvoiceList(data);
      })
      .catch((err) => {
        console.log(err);
        setInvoiceList([...fakeInvoiceListData]);
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
                    key={item.invoiceId}
                    sx={{ "&:last-child td, &:last-child th": { border: 0 } }}
                    hover={true}
                  >
                    <TableCell align="left">
                      <Link
                        to={`/invoice/${item.invoiceId}`}
                        className={styles.customIdLink}
                      >{`#INV-${convertNumberToString(
                        item.invoiceId,
                        4
                      )}`}</Link>
                    </TableCell>
                    <TableCell align="left">
                      <div className={styles.mentorInfoWrapper}>
                        <img
                          src={item.mentorInfo.imageUrl || AvatarDefault}
                          alt="avatar"
                        />
                        <div className={styles.infoLeft}>
                          <h4>{item.mentorInfo.name}</h4>
                          <p>{item.mentorInfo.email}</p>
                        </div>
                      </div>
                    </TableCell>
                    <TableCell align="left">
                      <div
                        className={styles.reportDetail}
                      >{`$${item.amount}`}</div>
                    </TableCell>
                    <TableCell align="left">{item.paidOn}</TableCell>
                    <TableCell align="center">
                      <Link
                        className={styles.customAction}
                        to={`/invoice/${item.invoiceId}`}
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
