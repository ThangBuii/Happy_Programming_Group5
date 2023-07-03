import {
  Breadcrumbs,
  Button,
  Dialog,
  DialogActions,
  DialogContent,
  DialogContentText,
  DialogTitle,
} from "@mui/material";
import AdminSidebar from "../sidebar";
import { Link } from "react-router-dom";
import styles from "./index.module.css";

const MainAdminLayout = ({
  title,
  breadCum,
  content,
  isDialogOpen = false,
  onClose,
  dialogTitle = "",
  dialogContent = "",
  onDisAgreeDialog = onClose,
  onAgreeDialog = onClose,
}) => {
  return (
    <div className={styles.layoutWrapper}>
      <AdminSidebar />
      <div className={styles.contentWrapper}>
        <h3>{title}</h3>
        <Breadcrumbs sx={{ textDecoration: "none" }}>
          {breadCum.length > 0 &&
            breadCum.map((item, index) => (
              <Link
                key={item.to}
                className={
                  index !== breadCum.length - 1
                    ? styles.notLastBC
                    : styles.lastBC
                }
                to={item.to}
              >
                {item.represent}
              </Link>
            ))}
        </Breadcrumbs>
        <div className={styles.contentDetailWrapper}>{content}</div>
      </div>

      <Dialog open={isDialogOpen} onClose={onClose}>
        <DialogTitle>{dialogTitle}</DialogTitle>
        <DialogContent>
          <DialogContentText>{dialogContent}</DialogContentText>
        </DialogContent>
        <DialogActions>
          <Button onClick={onDisAgreeDialog}>Disagree</Button>
          <Button onClick={onAgreeDialog} autoFocus>
            Agree
          </Button>
        </DialogActions>
      </Dialog>
    </div>
  );
};

export default MainAdminLayout;
