import { useEffect, useRef, useState } from "react";
import {
  Alert,
  Button,
  CircularProgress,
  Dialog,
  DialogContent,
  IconButton,
  Skeleton,
  Slide,
  Snackbar,
} from "@mui/material";
import AddIcon from "@mui/icons-material/Add";
import MainLayout from "../../component/main-layout";
import CloseIcon from "@mui/icons-material/Close";
import styles from "./index.module.css";
import { DatePicker, LocalizationProvider } from "@mui/x-date-pickers";
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";
import { request } from "../../axios_helper";

const ScheduleTimings = () => {
  const [sessions, setSessions] = useState([]);
  const [slotList, setSlotList] = useState([]);
  const [sessionIdChoosed, setSessionIdChoosed] = useState("");
  const [dayChoosed, setDayChoosed] = useState("");
  const [isLoading, setIsLoading] = useState(true);
  const [isDetailLoading, seIsDetailLoading] = useState(false);
  const [isDialogOpen, setIsDialogOpen] = useState(false);
  const [isSaveChange, setIsSaveChange] = useState(false);
  const dialogRef = useRef(null);
  const [errorMessage, setErrorMessage] = useState("");
  const [snackbarOpen, setSnackbarOpen] = useState(false);

  //call API
  useEffect(() => {
    setIsLoading(true);
    request("GET", "/api/mentor/session")
      .then((response) => {
        setSessions(response.data);
      })
      .catch((err) => {
        console.log(err);
      })
      .finally(() => {
        setIsLoading(false);
      });
  }, []);

  const fetchData = (sessionIdChoosed, dayChoosed) => {
    seIsDetailLoading(true);
    const startDate = new Date(dayChoosed.$d);
    const year = startDate.getFullYear();
    const month = String(startDate.getMonth() + 1).padStart(2, '0');
    const day = String(startDate.getDate()).padStart(2, '0');
  
    const formattedDate = `${year}-${month}-${day}`;
    const sessionId = parseInt(sessionIdChoosed, 10);
    const data = {
      session_id: sessionId,
      start_date: formattedDate
    };
    request("POST", "/api/mentor/times", data)
      .then((data) => {
        setSlotList(data.data);
      })
      .catch((err) => {
        console.log(err);
      })
      .finally(() => {
        seIsDetailLoading(false);
      });
  };

  const handleDeleteSlot = async (sessionId, slotId) => {
    console.log(">>check send data", sessionId, slotId);

    request("DELETE", `/api/mentor/time/${slotId}`)
      .then((response) => {
        // success
        if (response.status === 200) {
          fetchData(sessionIdChoosed, dayChoosed);
          handleDialogClose();
        }
      })
      .catch((error) => {
        setSnackbarOpen(true);
      if (
        error.response &&
        error.response.data &&
        error.response.data.errors
      ) {
        setErrorMessage(error.response.data.errors.message);
      } else {
        setErrorMessage("An error occurred. Please try again.");
      }
        console.log(error);
      });
  };

  const handleDialogClose = () => {
    setIsDialogOpen(false);
  };

  const handleSaveChanges = async () => {
    // check xem da chon session chua
    if (!sessionIdChoosed) return;
    const startDate = new Date(dayChoosed.$d);
    const year = startDate.getFullYear();
    const month = String(startDate.getMonth() + 1).padStart(2, '0');
    const day = String(startDate.getDate()).padStart(2, '0');

    const formattedDate = `${year}-${month}-${day}`;
    const sessionId = parseInt(sessionIdChoosed, 10);
    const slotFrom =
      dialogRef.current.querySelector("input[name='start']")?.value || "";
    const slotTo =
      dialogRef.current.querySelector("input[name='end']")?.value || "";
    const data = {
      date: formattedDate,
      start_time: slotFrom,
      end_time: slotTo,
      session_id: sessionId,
    };


    // validate

    // call api  => add vao thi tra ra status + id, (id de ti con delete)
    request("POST", "/api/mentor/time", data)
    .then((response) => {
      if (response.status === 200) {
        fetchData(sessionIdChoosed, dayChoosed);
        handleDialogClose();
      }
    })
    .catch((error) => {
      setSnackbarOpen(true);
      if (
        error.response &&
        error.response.data &&
        error.response.data.errors
      ) {
        setErrorMessage(error.response.data.errors.message);
      } else {
        setErrorMessage("An error occurred. Please try again.");
      }
      console.log(error);
    })
    .finally(() => {
      setIsSaveChange(false);
    });
  };
  useEffect(() => {
    if (sessionIdChoosed === "" || dayChoosed === "") return;
    fetchData(sessionIdChoosed, dayChoosed);
  }, [dayChoosed, sessionIdChoosed]);

  return (
    <>
      <MainLayout
        pageTitle={"Schedule Timeings"}
        layoutContent={
          <div className={styles.layoutWrapper}>
            {isLoading ? (
              <div className={styles.loadingWrapper}>
                <CircularProgress />
              </div>
            ) : (
              <>
                <h4 className={styles.cardTitle}>Schedule Timings</h4>
                <div className="row">
                  <div className="col-lg-4">
                    <div className="form-group">
                      <label>Choose your session</label>
                      <select
                        className="select form-control"
                        value={sessionIdChoosed}
                        onChange={(e) => setSessionIdChoosed(e.target.value)}
                      >
                        <option value={""}>Select</option>
                        {sessions.map((item) => (
                          <option key={item.session_id} value={item.session_id}>
                            {item.session_name}
                          </option>
                        ))} 
                      </select>
                    </div>
                  </div>
                </div>
                <div className="row">
                  <div className="col-md-12">
                    <div className={styles.scheduleWrapper}>
                      <div className={styles.scheduleHead}>
                        {/* {dayList.map((item, index) => (
                          <div
                            key={index}
                            className={
                              item === dayChoosed
                                ? styles.dayActiveWrapper
                                : styles.dayWrapper
                            }
                            onClick={() => setDayChoosed(item)}
                          >
                            {item}
                          </div>
                        ))} */}
                        <LocalizationProvider dateAdapter={AdapterDayjs}>
                          <DatePicker
                            value={dayChoosed}
                            onChange={(newValue) => setDayChoosed(newValue)}
                            sx={{
                              "& input:focus": {
                                width: "auto",
                                margin: "0",
                                borderBottom: "0px solid",
                              },
                            }}
                          />
                        </LocalizationProvider>
                      </div>
                      <div className={styles.slotWrapper}>
                        <h4>
                          <span>Time Slots</span>
                          <Button
                            variant="text"
                            startIcon={<AddIcon />}
                            onClick={() => setIsDialogOpen(true)}
                          >
                            Add
                          </Button>
                        </h4>
                        <div className={styles.slotResult}>
                          {isDetailLoading ? (
                            <Skeleton animation="wave" width="50%" />
                          ) : slotList.length > 0 ? (
                            slotList.map((item) => (
                              <div
                                key={item.time_id}
                                className={styles.slotItem}
                              >
                                {item.start_time}{" "}
                                <IconButton
                                  color="secondary"
                                  className={styles.customDeleteSkill}
                                  onClick={() =>
                                    handleDeleteSlot(
                                      sessionIdChoosed,
                                      item.time_id
                                    )
                                  }
                                >
                                  <CloseIcon />
                                </IconButton>
                              </div>
                            ))
                          ) : (
                            <span>Not Available</span>
                          )}
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </>
            )}
            <Snackbar
              anchorOrigin={{ vertical: 'top', horizontal: 'right' }}
              open={snackbarOpen}
              autoHideDuration={2000}
              onClose={() => setSnackbarOpen(false)}
              style={{ marginTop: "40px" }}
              TransitionComponent={({ children }) => (
                <Slide direction="left" in={snackbarOpen}>
                  {children}
                </Slide>
              )}
            >
              <Alert severity="error" variant="filled" sx={{ width: "100%" }}>
                {errorMessage}
              </Alert>
            </Snackbar>
          </div>
        }
      />
      <Dialog
        open={isDialogOpen}
        onClose={handleDialogClose}
        maxWidth="md"
        ref={dialogRef}
      >
        <div className={styles.dialogHeadWrapper}>
          <span>Add Slot</span>
          <IconButton
            color="secondary"
            className={styles.customCloseDialog}
            onClick={handleDialogClose}
          >
            <CloseIcon />
          </IconButton>
        </div>
        <DialogContent sx={{ width: "470px", maxWidth: "100%" }}>
          <div className="row">
            <div className="col-12 col-md-6">
              <div className={styles.dialogTimeWrapper}>
                <label>Start Time</label>
                <input type="time" name="start" />
              </div>
            </div>
            <div className="col-12 col-md-6">
              <div className={styles.dialogTimeWrapper}>
                <label>End Time</label>
                <input type="time" name="end" />
              </div>
            </div>
          </div>
          <div className={styles.dialogActionWrapper}>
            <Button
              variant="contained"
              onClick={handleSaveChanges}
              disabled={isSaveChange}
            >
              Save Change
            </Button>
          </div>
        </DialogContent>
      </Dialog>
    </>
  );
};

export default ScheduleTimings;
