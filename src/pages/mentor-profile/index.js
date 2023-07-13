import NavigateNextIcon from "@mui/icons-material/NavigateNext";
import HomeIcon from "@mui/icons-material/Home";
import { Link, useLocation, useNavigate, useParams } from "react-router-dom";
import CircularProgress from "@mui/material/CircularProgress";
import BookmarkIcon from "@mui/icons-material/Bookmark";
import BookmarkBorderIcon from "@mui/icons-material/BookmarkBorder";
import { ReactComponent as AddressIcon } from "../../assets/address.svg";
import { ReactComponent as RatingIcon } from "../../assets/rating.svg";
import { useEffect, useState } from "react";
import AvatarDefault from "../../assets/avatar-thinking-3-svgrepo-com.svg";
import {
  Button,
  FormControlLabel,
  Radio,
  RadioGroup,
  Rating,
} from "@mui/material";
import styles from "./index.module.css";
import { request } from "../../axios_helper";

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

  const handleRadioChange = (event) => {
    setSessionIdChoosed(event.target.value);
  };

  const handleClickBook = () => {
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
  }, [params.id]);
  console.log(mentorInfo)

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
                    <BookmarkIcon/>
                    </span>
                  ) : (
                    <span>
                    <BookmarkBorderIcon/>
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
                                <h4>{item.session_name}</h4>
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
    </div>
  );
};

export default MentorProfile;
