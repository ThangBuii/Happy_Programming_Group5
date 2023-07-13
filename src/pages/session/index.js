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
  console.log(location.pathname, linkObjList);
  const [session, setSession] = useState([]);
  const [isOpenDialog, setOpendDialog] = useState(false);
  const [originFilterListSkill, setOriginFilterListSkill] = useState([]);
  const [filterListSkill, setFilterListSkill] = useState([]);
  const [filterSearch, setFilterSearch] = useState("");
  const [addSession, setAddSession] = useState({
    skills: [],
    session_Name: "",
    price: 0,
    description: "",
    duration: 0,
  });
  const [isLoading, seIsLoading] = useState(true);
  const navigate = useNavigate();

  useEffect(() => {
    if (role !== -1 && role !== 1) navigate("/");
  }, []);

  useEffect(() => {
    seIsLoading(true);
    if (role === -1) return;
    const url = role === 1 ? "/api/mentor/session" : "/api/mentee/session";
    Promise.all([request("GET", url), request("GET", "/api/men/skills")])
      .then((responses) => {
        return Promise.all(responses.map((response) => response.data));
      })
      .then(([data1, data2]) => {
        setSession(data1.data);
        setOriginFilterListSkill(handleBuildFilterSkills(data2, data1.skills));
      })
      .catch((err) => {
        console.log(err);
        setOriginFilterListSkill(handleBuildFilterSkills(fakeListSkills));
      })
      .finally(() => {
        seIsLoading(false);
      });
  }, []);

  useEffect(() => {
    if (originFilterListSkill.length > 0) {
      const newFilterListSkill = originFilterListSkill.filter((skill) =>
        skill.skill_name.toLowerCase().includes(filterSearch.toLowerCase())
      );
      setFilterListSkill(newFilterListSkill);
    }
  }, [originFilterListSkill, filterSearch]);

  const handleClickFilterItem = (filterName) => {
    let nameState = false;
    const newFilterSkill = originFilterListSkill.map((item) => {
      if (item.skill_name !== filterName) return item;
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
        skills: [...pre.skills, filterName],
      }));
    else
      setAddSession((pre) => ({
        ...pre,
        skills: [...pre.skills].filter((item) => item !== filterName),
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
              Add Report
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
                          Mentor Name
                        </TableCell>
                        <TableCell
                          className={styles.tableCellHead}
                          align="left"
                        >
                          Skill List
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
                          Session Name
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
                            <div className={styles.mentorInfoWrapper}>
                              <img
                                src={
                                  item.avatar
                                    ? `data:image/jpeg;base64, ${item.avatar}`
                                    : AvatarDefault
                                }
                                alt="avatar"
                              />
                              <div className={styles.infoLeft}>
                                <h4>{item.username}</h4>
                              </div>
                            </div>
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
                          <TableCell align="center">{item.duration}</TableCell>
                          <TableCell align="left">
                            {item.session_Name}
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
        <DialogTitle>Subscribe</DialogTitle>
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
              setAddSession((pre) => ({ ...pre, session_Name: e.target.value }))
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
                      onClick={() => handleClickFilterItem(item.skill_name)}
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
            {addSession.skills &&
              addSession.skills.map((skill, index) => (
                <div key={index} className={styles.skillItem}>
                  {skill}
                  <IconButton
                    color="secondary"
                    className={styles.customDeleteSkill}
                    onClick={() => handleClickFilterItem(skill)}
                  >
                    <CloseIcon />
                  </IconButton>
                </div>
              ))}
          </div>
        </DialogContent>
        <DialogActions>
          <Button onClick={handleCloseDialog}>Cancel</Button>
          <Button onClick={handleSubmitAddSessions}>Subscribe</Button>
        </DialogActions>
      </Dialog>
    </>
  );
};

export default Session;
