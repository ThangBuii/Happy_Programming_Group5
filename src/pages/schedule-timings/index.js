import { useEffect, useRef, useState } from "react";
import {
  Button,
  CircularProgress,
  Dialog,
  DialogContent,
  IconButton,
  Skeleton,
} from "@mui/material";
import AddIcon from "@mui/icons-material/Add";
import MainLayout from "../../component/main-layout";
import CloseIcon from "@mui/icons-material/Close";
import styles from "./index.module.css";

const fakeSessionList = [
  {
    sessionId: "session1",
    sessionName: "It vua moi nghe",
  },
  {
    sessionId: "session2",
    sessionName: "Bi kip 10 diem dai hoc",
  },
  {
    sessionId: "session3",
    sessionName: "Toi la ai",
  },
];

const fakeSlotList = [
  { slotId: "slot1", slotFrom: "8:30", slotTo: "11:00" },
  { slotId: "slot2", slotFrom: "12:30", slotTo: "13:00" },
];

const dayList = [
  "MONDAY",
  "TUESDAY",
  "WEDNESDAY",
  "THURSDAY",
  "FRIDAY",
  "SATURDAY",
  "SUNDAY",
];
const ScheduleTimings = () => {
  const [sessions, setSessions] = useState([]);
  const [slotList, setSlotList] = useState([]);
  const [sessionIdChoosed, setSessionIdChoosed] = useState("");
  const [dayChoosed, setDayChoosed] = useState("MONDAY");
  const [isLoading, setIsLoading] = useState(true);
  const [isDetailLoading, seIsDetailLoading] = useState(false);
  const [isDialogOpen, setIsDialogOpen] = useState(false);
  const [isSaveChange, setIsSaveChange] = useState(false);
  const dialogRef = useRef(null);

  //call API
  useEffect(() => {
    setIsLoading(true);
    fetch(`http://localhost:9999/my-session`)
      .then((resp) => resp.json())
      .then((data) => {
        setSessions([...data]);
      })
      .catch((err) => {
        console.log(err);
        setSessions([...fakeSessionList]);
      })
      .finally(() => {
        setIsLoading(false);
      });
  }, []);

  useEffect(() => {
    if (sessionIdChoosed === "" || dayChoosed === "") return;
    seIsDetailLoading(true);
    fetch(`http://localhost:9999/my-slot/${sessionIdChoosed}/${dayChoosed}`)
      .then((resp) => resp.json())
      .then((data) => {
        setSlotList([...data]);
      })
      .catch((err) => {
        console.log(err);
        setSlotList([...fakeSlotList]);
      })
      .finally(() => {
        seIsDetailLoading(false);
      });
  }, [dayChoosed, sessionIdChoosed]);

  const handleDeleteSlot = async (sessionId, slotId) => {
    console.log(">>check send data", sessionId, slotId);

    await fetch(`http://localhost:9999/...session/..daychoosed + deleteId`)
      .then((resp) => resp.json())
      .then((data) => {
        // success
        if (data.status === 0) {
          setSlotList((pre) =>
            [...pre].filter((item) => item.slotId !== slotId)
          );
        }
      })
      .catch((err) => {
        console.log(err);
        setSlotList((pre) => [...pre].filter((item) => item.slotId !== slotId));
      });
  };

  const handleDialogClose = () => {
    setIsDialogOpen(false);
  };

  const handleSaveChanges = async () => {
    // check xem da chon session chua
    if (!sessionIdChoosed) return;

    const slotFrom =
      dialogRef.current.querySelector("input[name='start']")?.value || "";
    const slotTo =
      dialogRef.current.querySelector("input[name='end']")?.value || "";

    // validate

    // call api  => add vao thi tra ra status + id, (id de ti con delete)
    setIsSaveChange(true);
    await fetch(`http://localhost:9999/...session/..daychoosed + data post`)
      .then((resp) => resp.json())
      .then((data) => {
        // success
        if (data.status === 0) {
          setSlotList((pre) => [...pre, { slotId: data.id, slotFrom, slotTo }]);
          handleDialogClose();
        }
      })
      .catch((err) => {
        console.log(err);
      })
      .finally(() => {
        setIsSaveChange(false);
      });
  };

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
                      <label>Timing Slot Duration</label>
                      <select
                        className="select form-control"
                        value={sessionIdChoosed}
                        onChange={(e) => setSessionIdChoosed(e.target.value)}
                      >
                        <option value={""}>Select</option>
                        {sessions.map((item) => (
                          <option key={item.sessionId} value={item.sessionId}>
                            {item.sessionName}
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
                        {dayList.map((item, index) => (
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
                        ))}
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
                                key={item.slotId}
                                className={styles.slotItem}
                              >
                                {item.slotFrom} - {item.slotTo}{" "}
                                <IconButton
                                  color="secondary"
                                  className={styles.customDeleteSkill}
                                  onClick={() =>
                                    handleDeleteSlot(
                                      sessionIdChoosed,
                                      item.slotId
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
