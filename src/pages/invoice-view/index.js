import { useNavigate } from "react-router";
import { Container } from "@mui/material";
import LogoImage from "../../assets/logo.png";
import { useEffect, useState } from "react";
import styles from "./index.module.css";
import { convertNumberToString } from "../invoice";

function createData(invoiceId, invoiceFrom, invoiceTo, issuedDate) {
  return { invoiceId, invoiceFrom, invoiceTo, issuedDate };
}

const fakeInvoiceViewData = createData(
  "1",
  "Newyork, USA",
  "Florida, 32405, USA",
  "20/07/2019"
);

const InvoiceView = () => {
  const [invoiceView, setInvoiceView] = useState({ ...fakeInvoiceViewData });
  const navigate = useNavigate();

  useEffect(() => {
    fetch("http://localhost:9999/report-list/mentorId")
      .then((resp) => resp.json())
      .then((data) => {
        setInvoiceView(data);
      })
      .catch((err) => {
        console.log(err);
        setInvoiceView({ ...fakeInvoiceViewData });
      });
  }, []);

  return (
    <div className={styles.layoutWrapper}>
      <div className={styles.breadcumBarWrapper}>
        <div className={styles.bcLeft}>
          <div>
            <span className={styles.bcHome} onClick={() => navigate("/")}>
              Home
            </span>
            <span
              className={styles.bcPersonProfile}
              onClick={() => navigate("/invoice")}
            >
              Invoices
            </span>
            <span className={styles.bcPersonProfile}>Invoice View</span>
          </div>
          <h2>Invoice View</h2>
        </div>
      </div>
      <div className={styles.containerWrapper}>
        <Container
          maxWidth="md"
          sx={{
            padding: "30px 0 0",
          }}
        >
          <div className={styles.invoiceContent}>
            <div className={styles.invoiceItem}>
              <div className="row">
                <div className="col-md-6">
                  <div className={styles.invoiceLogo}>
                    <img src={LogoImage} alt="logo" />
                  </div>
                </div>
                <div className="col-md-6">
                  <p className={styles.invoiceDetails}>
                    <strong className={styles.customText}>Order:</strong> #
                    {convertNumberToString(invoiceView.invoiceId, 5)} <br />
                    <strong className={styles.customText}>Issued:</strong>{" "}
                    {invoiceView.issuedDate}
                  </p>
                </div>
              </div>
            </div>
            <div className={styles.invoiceItem}>
              <div className="row">
                <div className="col-md-6">
                  <div className={styles.invoiceInfo}>
                    <strong className={styles.customText}>Invoice From</strong>
                    <p className={styles.invoiceAddress}>
                      {invoiceView.invoiceFrom} <br />
                    </p>
                  </div>
                </div>
                <div className="col-md-6">
                  <div className={styles.invoiceDetails}>
                    <strong className={styles.customText}>Invoice To</strong>
                    <p className={styles.invoiceAddress}>
                      {invoiceView.invoiceTo} <br />
                    </p>
                  </div>
                </div>
              </div>
            </div>
            <div className={styles.invoiceItem}>
              <div className="row">
                <div className="col-md-12">
                  <div className={styles.invoiceInfo}>
                    <strong className={styles.customText}>
                      Payment Method
                    </strong>
                    <p className="invoice-details invoice-details-two">
                      Debit Card <br />
                      XXXXXXXXXXXX-2541 <br />
                      HDFC Bank
                      <br />
                    </p>
                  </div>
                </div>
              </div>
            </div>
            <div className={styles.invoiceItem}>
              <div className="row">
                <div className="col-md-12">
                  <div className="table-responsive">
                    <table className="invoice-table table table-bordered">
                      <thead>
                        <tr>
                          <th>Description</th>
                          <th className="text-center">Quantity</th>
                          <th className="text-center">VAT</th>
                          <th className="text-right">Total</th>
                        </tr>
                      </thead>
                      <tbody>
                        <tr>
                          <td>General Consultation</td>
                          <td className="text-center">1</td>
                          <td className="text-center">$0</td>
                          <td className="text-right">$100</td>
                        </tr>
                        <tr>
                          <td>Video Call Booking</td>
                          <td className="text-center">1</td>
                          <td className="text-center">$0</td>
                          <td className="text-right">$250</td>
                        </tr>
                      </tbody>
                    </table>
                  </div>
                </div>
                <div
                  className="col-md-6 col-xl-4 ml-auto"
                  style={{ marginLeft: "auto" }}
                >
                  <div className="table-responsive">
                    <table className="invoice-table-two table">
                      <tbody>
                        <tr>
                          <th>Subtotal:</th>
                          <td>
                            <span>$350</span>
                          </td>
                        </tr>
                        <tr>
                          <th>Discount:</th>
                          <td>
                            <span>-10%</span>
                          </td>
                        </tr>
                        <tr>
                          <th>Total Amount:</th>
                          <td>
                            <span>$315</span>
                          </td>
                        </tr>
                      </tbody>
                    </table>
                  </div>
                </div>
              </div>
            </div>
            <div class="other-info">
              <h4>Other information</h4>
              <p class="text-muted mb-0">
                Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus
                sed dictum ligula, cursus blandit risus. Maecenas eget metus non
                tellus dignissim aliquam ut a ex. Maecenas sed vehicula dui, ac
                suscipit lacus. Sed finibus leo vitae lorem interdum, eu
                scelerisque tellus fermentum. Curabitur sit amet lacinia lorem.
                Nullam finibus pellentesque libero.
              </p>
            </div>
          </div>
        </Container>
      </div>
    </div>
  );
};

export default InvoiceView;
