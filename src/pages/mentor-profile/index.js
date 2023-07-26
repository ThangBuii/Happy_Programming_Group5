import NavigateNextIcon from "@mui/icons-material/NavigateNext";
import HomeIcon from "@mui/icons-material/Home";
import { Link, useLocation, useNavigate, useParams } from "react-router-dom";
import CircularProgress from "@mui/material/CircularProgress";
import BookmarkIcon from "@mui/icons-material/Bookmark";
import BookmarkBorderIcon from "@mui/icons-material/BookmarkBorder";
import { ReactComponent as AddressIcon } from "../../assets/address.svg";
import { ReactComponent as RatingIcon } from "../../assets/rating.svg";
import { useContext, useEffect, useState } from "react";
import AvatarDefault from "../../assets/avatar-thinking-3-svgrepo-com.svg";
import {
  Alert,
  Button,
  FormControlLabel,
  Radio,
  RadioGroup,
  Rating,
  Slide,
  Snackbar,
} from "@mui/material";
import styles from "./index.module.css";
import { request } from "../../axios_helper";
import { ApplicationContext } from "../../routes/AppRoutes";

const MentorProfile = () => {
  const location = useLocation();
  const prevPath = location.state?.prevPath || null;
  const navigate = useNavigate();
  const params = useParams();
  const [isLoading, seIsLoading] = useState(true);
  const [mentorInfo, setMentorInfo] = useState({});
  const [ratingList, setRatingList] = useState([]);
  const [sessionList, setSessionList] = useState([]);
  const [sessionIdChoosed, setSessionIdChoosed] = useState("");
  const { user } = useContext(ApplicationContext);
  const role = user.role;
  const [errorMessage, setErrorMessage] = useState("");
  const [snackbarOpen, setSnackbarOpen] = useState(false);

  const handleRadioChange = (event) => {
    setSessionIdChoosed(event.target.value);
  };

  const handleClickBook = () => {
    if (!sessionIdChoosed) {
      setErrorMessage("You must select a session");
      setSnackbarOpen(true);
      return;
    }
    navigate(`/mentor/${mentorInfo.mentor_id}/checkout/${sessionIdChoosed}`, {
      state: {
        listPrevPath: [
          {
            to: prevPath?.to || null,
            represent: prevPath?.represent || null,
          },
          {
            to: location.pathname,
            represent: mentorInfo.name,
          },
        ],
      },
    });
  };

  useEffect(() => {
    fetch();
  }, [params.id]);
  
  const fetch = () => {
    seIsLoading(true);
    Promise.all([
      request("GET",`/api/public/profile/${params.id}`), // find mentor by mentorId
      request("GET",`/api/public/feedbacks/${params.id}`), // find all ratings list by mentorId
      request("GET",`/api/public/session/${params.id}`), // find all session list by mentorId
    ])
      .then(([data1, data2, data3]) => {
        setMentorInfo({ ...data1.data });
        setRatingList([...data2.data]);
        setSessionList([...data3.data]);

        if (data3.length > 0) setSessionIdChoosed(data3[0].session_id);
      })
      .catch((err) => {
        console.log(err);
      
      })
      .finally(() => {
        seIsLoading(false);
      });
  };

  const handleBookmarkClick = (id, type) => {
    // Send delete request to the backend

    if (role === -1 || role === 0 || role === 1) {
      setSnackbarOpen(true);
      setErrorMessage("Log in as a Mentee to add this Mentor to your favorite list");
    } else {
      if (type === 0) {
        request("DELETE", `/api/mentee/favorite/${id}`)
          .then(response => {
            // Handle successful deletion
            // Fetch the updated list of mentors
            if(response.status === 200){
              fetch();
            }
            return request("GET", "/api/mentee/favorite");
          })
          .catch(error => {
            // Handle error
            console.error('Error deleting bookmark:', error);
          });
      } else {
        request("POST", `/api/mentee/favorite/${id}`)
          .then(response => {
            // Handle successful deletion
            // Fetch the updated list of mentors
            if(response.status === 200){
              fetch();
            }
            return request("GET", "/api/mentee/favorite");
          })
          .catch(error => {
            // Handle error
            console.error('Error deleting bookmark:', error);
          });
      }
    }

  };

  return (
    <div className={styles.layoutWrapper}>
      {isLoading ? (
        <div className={styles.loadingWrapper}>
          <CircularProgress />
        </div>
      ) : (
        <>
          <div className={styles.topBgWrapper}>
            <div className={styles.topWrapper}>
              <div className={styles.breadcumWrapper}>
                <div onClick={() => navigate("/")} className={styles.home}>
                  <HomeIcon />
                </div>
                {prevPath && (
                  <>
                    <NavigateNextIcon />
                    <Link to={prevPath.to}>{prevPath.represent}</Link>
                  </>
                )}
                <NavigateNextIcon />
                <Link>{mentorInfo.username}</Link>
              </div>

              <div className={styles.topBtWrapper}>
                <div className={styles.avatarImgWrapper}>
                  <img src={mentorInfo.avatar ? `data:image/jpeg;base64, ${mentorInfo.avatar}` : AvatarDefault} alt="avatar" />
                </div>
                <div className={styles.isBookMark}>
                  {mentorInfo.favorite ? (
                    <span>
                    <BookmarkIcon onClick={() => handleBookmarkClick(mentorInfo.mentor_id, 0)}/>
                    </span>
                  ) : (
                    <span>
                    <BookmarkBorderIcon onClick={() => handleBookmarkClick(mentorInfo.mentor_id, 1)}/>
                    </span>
                  )}
                </div>
              </div>
            </div>
          </div>
          <div className={styles.detailSuperInfoWrapper}>
            <div className={styles.detailInfoWrapper}>
              <div className={styles.detailInfoLeft}>
                <h2 className={styles.mentorName}>{mentorInfo.username}</h2>
                <div className={styles.archieveList}>
                  
                    <div  className={styles.archieveItem}>
                      {mentorInfo.short_description}
                    </div>
                  
                </div>
              </div>
              <div className={styles.detailInfoRight}>
                <h3>Skills</h3>
                <div className={styles.skillList}>
                  {mentorInfo.skills.map((skill, index) => {
                    if (index <= 2)
                      return (
                        <div key={index} className={styles.skillItem}>
                          {skill.skill_name}
                        </div>
                      );
                    else return null;
                  })}
                  {mentorInfo.skills.length > 3 && (
                    <a href="#skill">
                      + {mentorInfo.skills.length - 3} more
                    </a>
                  )}
                </div>
              </div>
            </div>
          </div>
          <hr />

          <div className={styles.bottomOuterWith}>
            <div className={styles.bottomInnerWith}>
              <div className={styles.serviceWrapper}>
                <h2>Services</h2>
                <div className={styles.serviceDetailWrapper}>
                  <div className={styles.serviceHead}>Sessions</div>
                  <div className={styles.serviceContent}>
                    <RadioGroup
                      value={sessionIdChoosed}
                      onChange={handleRadioChange}
                    >
                      {sessionList.length > 0 &&
                        sessionList.map((item) => (
                          <FormControlLabel
                            className={styles.radioItem}
                            key={item.session_id}
                            value={item.session_id}
                            control={<Radio />}
                            label={
                              <div className={styles.labelWrapper}>
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
                              <h4>{item.session_name}</h4>
                            </Link>
                                
                                <span>
                                  {item.duration} minutes, $
                                  {item.price.toFixed(2)} per session
                                </span>
                              </div>
                            }
                          />
                        ))}
                    </RadioGroup>
                  </div>
                  <div className={styles.action}>
                    <Button
                      variant="contained"
                      sx={{
                        backgroundColor: "rgb(33, 163, 145)",
                        width: "100%",
                        "&:hover": {
                          backgroundColor: "rgb(17, 133, 119)",
                        },
                      }}
                      onClick={handleClickBook}
                    >
                      Book now
                    </Button>
                  </div>
                </div>
              </div>
              <div className={styles.aboutWrapper}>
                <h2>About</h2>
                <p>{mentorInfo.description}</p>
              </div>
              <hr />
              <div className={styles.ratingListWrapper}>
                <h2>What mentees say</h2>
                {ratingList.map((item) => (
                  <div className={styles.ratingItem}>
                    <div className={styles.infoAccount}>
                      <img src={item.avatar ? `data:image/jpeg;base64, ${item.avatar}` : AvatarDefault} alt="avatar" />
                      <div className={styles.accountDetail}>
                        <div className={styles.accountDetailTop}>
                          <h3>{item.username}</h3>
                          <span>&nbsp;on {item.created_date}</span>
                        </div>
                        <Rating
                          defaultValue={item.rating}
                          readOnly
                          precision={0.5}
                          sx={{
                            marginTop: "-6px",
                            marginLeft: "-6px",
                            "& .MuiRating-iconFilled": {
                              color: "rgb(33, 163, 145)", // Màu fill tùy chỉnh khi được chọn
                            },
                          }}
                        />
                      </div>
                    </div>
                    <p>{item.content}</p>
                  </div>
                ))}
              </div>
              <hr />
              <div id="skill" className={styles.mainSkillList}>
                <h3>Skills</h3>
                <div className={styles.skillList}>
                  {mentorInfo.skills.map((skill, index) => (
                    <div key={index} className={styles.skillItem}>
                      {skill.skill_name}
                    </div>
                  ))}
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
  );
};

export default MentorProfile;
