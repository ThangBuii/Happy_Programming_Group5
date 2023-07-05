import NavigateNextIcon from "@mui/icons-material/NavigateNext";
import HomeIcon from "@mui/icons-material/Home";
import { Link, useLocation, useNavigate, useParams } from "react-router-dom";
import CircularProgress from "@mui/material/CircularProgress";
import BookmarkIcon from "@mui/icons-material/Bookmark";
import BookmarkBorderIcon from "@mui/icons-material/BookmarkBorder";
import { ReactComponent as AddressIcon } from "../../assets/address.svg";
import { ReactComponent as RatingIcon } from "../../assets/rating.svg";
import { useEffect, useState } from "react";
import {
  Button,
  FormControlLabel,
  Radio,
  RadioGroup,
  Rating,
} from "@mui/material";
import styles from "./index.module.css";
import { request } from '../../axios_helper'


const MentorProfile = () => {
  const location = useLocation();
  const { prevPath } = location.state;
  const navigate = useNavigate();
  const params = useParams();
  const [radioOption, setRadioOption] = useState("30-79");
  const [isLoading, seIsLoading] = useState(true);
  const [mentorInfo, setMentorInfo] = useState({});
  const [ratingList, setRatingList] = useState([]);

  const handleRadioChange = (event) => {
    setRadioOption(event.target.value);
  };

  useEffect(() => {
    request("GET", `/api/profile/${params.id}`)
      .then((response) => {
        setMentorInfo(response.data);
      })
      .catch((error) => {
        console.error(error);
      })
      .finally(() => {
        seIsLoading(false);
      })
      
  }, []);

  // useEffect(() => {
  //   const fetchData = async () => {
  //     try {
  //       seIsLoading(true);
  //       const [profileResponse, ratingListResponse] = await Promise.all([
  //         request("GET", `/api/profile/${params.id}`),
  //         request("GET", `/api/feedbacks/${params.id}`),
  //       ]);

  //       const profileData = await profileResponse.json();
  //       const ratingListData = await ratingListResponse.json();

  //       if (profileData.success) {
  //         setMentorInfo(profileData.data); // Assuming `profileData.data` contains the desired mentor information
  //       } else {
  //         console.log("Error: Unable to fetch mentor profile");
  //       }

  //       if (Array.isArray(ratingListData)) {
  //         setRatingList([...ratingListData]);
  //       } else {
  //         console.log("Error: Invalid rating list data");
  //       }
  //     } catch (err) {
  //       console.log(err);
  //     } finally {
  //       seIsLoading(false);
  //     }
  //   };

  //   fetchData();
  // }, [params.id]);

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
                <NavigateNextIcon />
                <Link to={prevPath.to}>{prevPath.represent}</Link>
                <NavigateNextIcon />
                <Link>{mentorInfo.username}</Link>
              </div>

              <div className={styles.topBtWrapper}>
                <div className={styles.avatarImgWrapper}>
                  <img src={mentorInfo.avatar} alt="avatar" />
                </div>
                <div className={styles.isBookMark}>
                  {mentorInfo.isBookMark ? (
                    <span>
                      UnSave <BookmarkIcon />
                    </span>
                  ) : (
                    <span>
                      Save <BookmarkBorderIcon />
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
                  {mentorInfo.short_description}
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
                      value={radioOption}
                      onChange={handleRadioChange}
                    >
                      <FormControlLabel
                        className={styles.radioItem}
                        value="30-79"
                        control={<Radio />}
                        label={
                          <div className={styles.labelWrapper}>
                            <h4>Resume Feedback</h4>
                            <span>30 minutes, $79 per session</span>
                          </div>
                        }
                      />
                      <FormControlLabel
                        className={styles.radioItem}
                        value="45-99"
                        control={<Radio />}
                        label={
                          <div className={styles.labelWrapper}>
                            <h4>Work Review</h4>
                            <span>45 minutes, $99 per session</span>
                          </div>
                        }
                      />
                      <FormControlLabel
                        className={styles.radioItem}
                        value="60-129"
                        control={<Radio />}
                        label={
                          <div className={styles.labelWrapper}>
                            <h4>Interview Preparation</h4>
                            <span>60 minutes, $129 per session</span>
                          </div>
                        }
                      />
                      <FormControlLabel
                        className={styles.radioItem}
                        value="60-169"
                        control={<Radio />}
                        label={
                          <div className={styles.labelWrapper}>
                            <h4>Expert Consultation</h4>
                            <span>60 minutes, $169 per session</span>
                          </div>
                        }
                      />
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
                {/* {ratingList.map((item) => (
                  <div key={item.accountId} className={styles.ratingItem}>
                    <div className={styles.infoAccount}>
                      <img src={item.avatar} alt="avatar" />
                      <div className={styles.accountDetail}>
                        <div className={styles.accountDetailTop}>
                          <h3>{item.username}</h3>
                          <span>&nbsp;on {item.created_date}</span>
                        </div>
                        <Rating
                          defaultValue={item.rating}
                          readOnly
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
                ))} */}
              </div>
              <hr />
              <div id="skill" className={styles.mainSkillList}>
                <h3>Skills</h3>
                <div className={styles.skillList}>
                          {mentorInfo.skills.map((skill) => (
                            <div key={skill.skill_id} className={styles.skillItem}>
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
