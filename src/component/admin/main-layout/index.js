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
  isConfirmDialogOpen = false,
  onCloseConfirmDialog,
  confirmDialogTitle = "",
  confirmDialogContent = "",
  onDisAgreeConfirmDialog = onCloseConfirmDialog,
  onAgreeConfirmDialog = onCloseConfirmDialog,
  isShowDialogOpen = false,
  onCloseShowDialog,
  showDialogTitle = "",
  showDialogContent = "",
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

      {/* Confirm dialog */}
      <Dialog open={isConfirmDialogOpen} onClose={onCloseConfirmDialog}>
        <DialogTitle>{confirmDialogTitle}</DialogTitle>
        <DialogContent>
          <DialogContentText>{confirmDialogContent}</DialogContentText>
        </DialogContent>
        <DialogActions>
          <Button onClick={onDisAgreeConfirmDialog}>Disagree</Button>
          <Button onClick={onAgreeConfirmDialog} autoFocus>
            Agree
          </Button>
        </DialogActions>
      </Dialog>

      {/* Show Dialog */}
      <Dialog
        maxWidth="md"
        open={isShowDialogOpen}
        onClose={onCloseShowDialog}
        sx={{
          "& .MuiPaper-root": {
            padding: "10px",
          },
        }}
      >
        <DialogTitle>{showDialogTitle}</DialogTitle>
        <DialogContent>
          <DialogContentText>{showDialogContent}</DialogContentText>
        </DialogContent>
      </Dialog>
    </div>
  );
};

export default MainAdminLayout;
