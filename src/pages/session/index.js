import { Link, useLocation, useNavigate } from "react-router-dom";
import { useContext, useEffect, useState } from "react";
import MainLayout from "../../component/main-layout";
import {
  Button,
  Checkbox,
  CircularProgress,
  Dialog,
  DialogActions,
  DialogContent,
  DialogContentText,
  DialogTitle,
  IconButton,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  TextField,
} from "@mui/material";
import CloseIcon from "@mui/icons-material/Close";
import AvatarDefault from "../../assets/avatar-thinking-3-svgrepo-com.svg";
import { EyeFill, Plus } from "react-bootstrap-icons";
import { linkObjList } from "../../component/sidebar";
import styles from "./index.module.css";
import { request } from "../../axios_helper";
import { ApplicationContext } from "../../routes/AppRoutes";
import CustomInputFilter from "../../component/custom-input-filter";
import { handleBuildFilterSkills } from "../profile-edit";

const fakeListSkills = [
  { skill_id: "1", skill_name: "Python" },
  { skill_id: "2", skill_name: "Java" },
  { skill_id: "3", skill_name: "Csla" },
];

const Session = () => {
  const { user } = useContext(ApplicationContext);
  const role = user.role;
  const location = useLocation();
  const [session, setSession] = useState([]);
  const [isOpenDialog, setOpendDialog] = useState(false);
  const [originFilterListSkill, setOriginFilterListSkill] = useState([]);
  const [filterListSkill, setFilterListSkill] = useState([]);
  const [filterSearch, setFilterSearch] = useState("");
  const [addSession, setAddSession] = useState({
    skills: {},
    session_name: "",
    price: 0,
    description: "",
    duration: 0,
  });
  const [isLoading, setIsLoading] = useState(true);
  const navigate = useNavigate();

  useEffect(() => {
    if (role !== -1 && role !== 1) navigate("/");
  }, []);
  useEffect(() => {
    fetchData(); // Call the fetchData function
  }, []);

  const fetchData = () => {
    setIsLoading(true);
    Promise.all([
      request("GET", "/api/mentor/session"),
      request("GET", "/api/mentor/skill"),
    ])
      .then((responses) => {
        return Promise.all(responses.map((response) => response.data));
      })
      .then(([data1, data2]) => {
        setSession(data1);
        console.log(data1);
        setOriginFilterListSkill(handleBuildFilterSkills(data2));
      })
      .catch((err) => {
        console.log(err);
        setOriginFilterListSkill(handleBuildFilterSkills(fakeListSkills));
      })
      .finally(() => {
        setIsLoading(false);
      });
  };

  useEffect(() => {
    if (originFilterListSkill.length > 0) {
      const newFilterListSkill = originFilterListSkill.filter((skill) =>
        skill.skill_name.toLowerCase().includes(filterSearch.toLowerCase())
      );
      setFilterListSkill(newFilterListSkill);
    }
  }, [originFilterListSkill, filterSearch]);

  const handleClickFilterItem = (skill) => {
    let nameState = false;
    const newFilterSkill = originFilterListSkill.map((item) => {
      if (item.skill_name !== skill.skill_name)
        return {
          skill_id: item.skill_id,
          skill_name: item.skill_name,
          isChoosed: false,
        };
      else {
        nameState = !item.isChoosed;

        return {
          skill_id: item.skill_id,
          skill_name: item.skill_name,
          isChoosed: nameState,
        };
      }
    });

    if (nameState)
      setAddSession((pre) => ({
        ...pre,
        skills: { skill_id: skill.skill_id, skill_name: skill.skill_name },
      }));
    else
      setAddSession((pre) => ({
        ...pre,
        skills: {},
      }));
    setOriginFilterListSkill(newFilterSkill);
  };

  const handleCloseDialog = () => {
    setOpendDialog(false);
  };

  const handleOpenDialog = () => {
    setOpendDialog(true);
  };

  const handleSubmitAddSessions = () => {
    console.log("Submit add sessions >>: ", addSession);
    const data ={
      skill_id : addSession.skills.skill_id,
      session_name: addSession.session_name,
      duration: addSession.duration,
      price: addSession.price,
      description: addSession.description
    }
    request("POST", "/api/mentor/session", data)
    .then((response) => {
      if (response.status === 200) {
        fetchData(); // Call the fetchData function
        handleCloseDialog();
      }
    })
    .catch((err) => {
      console.log(err);
    });

  };

  return (
    <>
      <MainLayout
        pageTitle="List Of Sessions"
        titleControl={
          <div className={styles.actionAdd}>
            <Button
              variant="contained"
              startIcon={<Plus />}
              onClick={handleOpenDialog}
            >
              Add Session
            </Button>
          </div>
        }
        layoutContent={
          <>
            {isLoading ? (
              <div className={styles.loadingWrapper}>
                <CircularProgress />
              </div>
            ) : (
              <div className={styles.dbWrapper}>
                <h2>List Session</h2>
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
                          className={styles.tableCellHead}
                          align="left"
                        >
                          Session Name
                        </TableCell>
                        <TableCell
                          className={styles.tableCellHead}
                          align="left"
                        >
                          Session Skill
                        </TableCell>
                        <TableCell
                          className={styles.tableCellHead}
                          align="center"
                        >
                          Duration
                        </TableCell>

                        <TableCell
                          className={styles.tableCellHead}
                          align="left"
                        >
                          Status
                        </TableCell>
                        <TableCell
                          className={styles.tableCellHead}
                          align="center"
                        >
                          Action
                        </TableCell>
                      </TableRow>
                    </TableHead>
                    <TableBody>
                      {session.map((item) => (
                        <TableRow
                          key={item.bookingID}
                          sx={{
                            "&:last-child td, &:last-child th": { border: 0 },
                          }}
                          hover={true}
                        >
                          <TableCell align="left">
                            {item.session_name}
                          </TableCell>
                          <TableCell align="left">
                            <div className={styles.skillList}>
                              <div
                                key={item.session_id}
                                className={styles.skillItem}
                              >
                                {item.skill_name}
                              </div>
                            </div>
                          </TableCell>
                          <TableCell align="center">
                            {item.duration} minutes
                          </TableCell>

                          <TableCell align="center">
                            <span
                              className={
                                item.status === 0
                                  ? styles.pendindStatus
                                  : item.status === 1
                                  ? styles.acceptStatus
                                  : item.status === 2
                                  ? styles.rejectStatus
                                  : ""
                              }
                            >
                              {item.status === 0
                                ? "Pending"
                                : item.status === 1
                                ? "Accepted"
                                : item.status === 2
                                ? "Rejected"
                                : ""}
                            </span>
                          </TableCell>
                          <TableCell align="center">
                            <Link
                              className={styles.customAction}
                              to={`/sessions/${item.session_id}`}
                              state={{
                                prevPath: {
                                  to: location.pathname,
                                  represent: "Sessions",
                                },
                              }}
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
            )}
          </>
        }
      />
      <Dialog open={isOpenDialog} onClose={handleCloseDialog}>
        <DialogTitle>Submit</DialogTitle>
        <DialogContent>
          <DialogContentText>
            Add Infomation to submit Session
          </DialogContentText>
          <TextField
            label="Session Name"
            fullWidth
            variant="outlined"
            sx={{
              mt: "16px",
              mb: "20px",
              "& input:focus": {
                width: "100%",
                margin: "0",
                borderBottom: "0px solid",
              },
            }}
            value={addSession.session_Name}
            onChange={(e) =>
              setAddSession((pre) => ({ ...pre, session_name: e.target.value }))
            }
          />
          <TextField
            label="Duration"
            fullWidth
            type="number"
            variant="outlined"
            sx={{
              mb: "20px",
              "& input:focus": {
                width: "100%",
                margin: "0",
                borderBottom: "0px solid",
              },
            }}
            value={addSession.duration}
            onChange={(e) =>
              setAddSession((pre) => ({ ...pre, duration: e.target.value }))
            }
          />
          <TextField
            label="Price"
            fullWidth
            type="number"
            variant="outlined"
            sx={{
              mb: "20px",
              "& input:focus": {
                width: "100%",
                margin: "0",
                borderBottom: "0px solid",
              },
            }}
            value={addSession.price}
            onChange={(e) =>
              setAddSession((pre) => ({ ...pre, price: e.target.value }))
            }
          />
          <TextField
            label="Description"
            fullWidth
            variant="outlined"
            multiline
            minRows={3}
            sx={{
              mb: "20px",
              "& input:focus": {
                width: "100%",
                margin: "0",
                borderBottom: "0px solid",
              },
            }}
            value={addSession.description}
            onChange={(e) =>
              setAddSession((pre) => ({ ...pre, description: e.target.value }))
            }
          />
          <CustomInputFilter
            filterTitle={"Skills"}
            isDropDown={true}
            place="Top"
            content={
              <>
                <input
                  type="text"
                  placeholder="Search for skills"
                  className={styles.inputFilter}
                  value={filterSearch}
                  onChange={(e) => setFilterSearch(e.target.value)}
                />
                <div className={styles.searchList}>
                  {filterListSkill.map((item) => (
                    <div
                      key={item.skill_name}
                      className={styles.searchItem}
                      onClick={() => handleClickFilterItem(item)}
                    >
                      <Checkbox
                        sx={{
                          width: "14px",
                          height: "14px",
                          margin: "6px 0",
                        }}
                        checked={item.isChoosed}
                        readOnly
                      />
                      <span className={styles.filterName}>
                        {item.skill_name}
                      </span>
                      {/* <span className={styles.filterRemain}>441</span> */}
                    </div>
                  ))}
                </div>
              </>
            }
          />
          <div className={styles.skillList}>
            {addSession.skills.skill_name && (
              <div className={styles.skillItem}>
                {addSession.skills.skill_name}
                <IconButton
                  color="secondary"
                  className={styles.customDeleteSkill}
                  onClick={() => handleClickFilterItem(addSession.skills)}
                >
                  <CloseIcon />
                </IconButton>
              </div>
            )}
          </div>
        </DialogContent>
        <DialogActions>
          <Button onClick={handleCloseDialog}>Cancel</Button>
          <Button onClick={handleSubmitAddSessions}>Submit</Button>
        </DialogActions>
      </Dialog>
    </>
  );
};

export default Session;
