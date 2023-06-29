import NavigateNextIcon from "@mui/icons-material/NavigateNext";
import HomeIcon from "@mui/icons-material/Home";
import { Link, useNavigate, useParams } from "react-router-dom";
import CircularProgress from "@mui/material/CircularProgress";
import StarBorderIcon from "@mui/icons-material/StarBorder";
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

function createMentorData(
  accountId,
  name,
  imageUrl,
  address,
  achievement,
  description,
  tag,
  averateRatings,
  numOfReivews,
  skillList
) {
  return {
    accountId,
    name,
    imageUrl,
    address,
    achievement,
    description,
    tag,
    averateRatings,
    numOfReivews,
    skillList,
  };
}

function createRatingListData(
  accountId,
  name,
  imageUrl,
  dateRating,
  rating,
  ratingDescription
) {
  return {
    accountId,
    name,
    imageUrl,
    dateRating,
    rating,
    ratingDescription,
  };
}

const fakeMentorData = createMentorData(
  "mentor1",
  "Kerry Ritter",
  "https://cdn.mentorcruise.com/cache/afb764f267a2b76282ae81f2540f41ff/5a98e7b63fa065c2/1425eaa03a80d2ec295a5271c89db9e5.jpg",
  "United States of America",
  [
    "Senior Software Engineer @ Microsoft",
    "Engineering leader who ships products. 12+ years of full-stack engineering. TypeScript, C#, NestJS, Remix, AWS",
  ],
  `I help engineers build and ship great products. Whether you're an entrepreneur trying to get your application to the finish line or you're an engineer looking to become irreplaceable at work, I can guide you to where you need to be.

  Hi, I'm Kerry. I have a passion for creating products and tools for solving real-world problems for individuals and businesses. I love helping other creators solve problems effectively and efficiently. I am a full-stack software engineer currently working at Microsoft leading front-end engineering for my team in the Intune organization. I am an early adopter of TypeScript, Ionic Framework, NestJS (I wrote a book on it https://ultimatecourses.com/ebooks/nestjs-restful-crud-api) and have been working heavily in Remix. I design and implement scalable architecture patterns in TypeScript (NestJS/NodeJS, Ionic, React/Angular) and C#, and I deploy to scalable, resilient infrastructure (serverless cloud technologies, event-driven architecture). I have led full-stack engineering teams for years and love helping others achieve new heights and reach their goal.
  
  As a mentor, I ideally work with people who are looking to build a product for themselves or as a start-up. Writing code is only a small part of the very difficult task of launching a real application; my expertise is helping engineers, both junior and experienced, go from “I have an idea” to a fully realized product. I can help you with development, architecture, DevOps, product design and UI design. If you're looking to get your product out of your head and into the cloud, let's chat.`,
  "Quick Responder",
  5.0,
  8,
  [
    "NestJS",
    "React",
    "Typescript",
    "Ionic",
    "Framework",
    "NodeJS",
    "UX",
    "Design",
    "Node",
    "Angular",
    "C#",
    "AWS",
    "JavaScript",
    "Coding",
  ]
);

const fakeRatingListData = [
  createRatingListData(
    "user1",
    "André",
    "https://cdn.mentorcruise.com/cache/7da199a7390973e3dbb2f27cb4b75cfe/a37286789f01baa0/880d77afb447464ca7292c359ba2b84d.jpg",
    "April 11, 2023",
    4.0,
    "If you're looking for someone with plenty of experience in full stack, responsive, kind, and to the point, look no further - Kerry's your guy 👍"
  ),
  createRatingListData(
    "user2",
    "André1",
    "https://cdn.mentorcruise.com/cache/7da199a7390973e3dbb2f27cb4b75cfe/a37286789f01baa0/880d77afb447464ca7292c359ba2b84d.jpg",
    "April 11, 2023",
    5.0,
    "If you're looking for someone with plenty of experience in full stack, responsive, kind, and to the point, look no further - Kerry's your guy 👍"
  ),
  createRatingListData(
    "user3",
    "André2",
    "https://cdn.mentorcruise.com/cache/7da199a7390973e3dbb2f27cb4b75cfe/a37286789f01baa0/880d77afb447464ca7292c359ba2b84d.jpg",
    "April 11, 2023",
    5.0,
    "If you're looking for someone with plenty of experience in full stack, responsive, kind, and to the point, look no further - Kerry's your guy 👍"
  ),
  createRatingListData(
    "user4",
    "André3",
    "https://cdn.mentorcruise.com/cache/7da199a7390973e3dbb2f27cb4b75cfe/a37286789f01baa0/880d77afb447464ca7292c359ba2b84d.jpg",
    "April 11, 2023",
    5.0,
    "If you're looking for someone with plenty of experience in full stack, responsive, kind, and to the point, look no further - Kerry's your guy 👍"
  ),
];

const MentorProfile = () => {
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
    seIsLoading(true);
    Promise.all([
      fetch(`http://localhost:9999/mentor/${params.id}`), // find mentor by mentorId
      fetch(`http://localhost:9999/rating-list/${params.id}`), // find all ratings list by mentorId
    ])
      .then((responses) => {
        return Promise.all(responses.map((response) => response.json()));
      })
      .then(([data1, data2]) => {
        setMentorInfo(...data1);
        setRatingList([...data2]);
      })
      .catch((err) => {
        console.log(err);
        setMentorInfo({ ...fakeMentorData });
        setRatingList([...fakeRatingListData]);
      })
      .finally(() => {
        seIsLoading(false);
      });
  }, [params.id]);

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
                <Link to="/findmentor">Find a Mentor</Link>
                <NavigateNextIcon />
                <Link>{mentorInfo.name}</Link>
              </div>

              <div className={styles.topBtWrapper}>
                <div className={styles.avatarImgWrapper}>
                  <img src={mentorInfo.imageUrl} alt="avatar" />
                </div>
                <div className={styles.tag}>
                  <StarBorderIcon />
                  <span>{mentorInfo.tag}</span>
                </div>
              </div>
            </div>
          </div>
          <div className={styles.detailSuperInfoWrapper}>
            <div className={styles.detailInfoWrapper}>
              <div className={styles.detailInfoLeft}>
                <h2 className={styles.mentorName}>{mentorInfo.name}</h2>
                <div className={styles.archieveList}>
                  {mentorInfo.achievement.map((item, index) => (
                    <div key={index} className={styles.archieveItem}>
                      {item}
                    </div>
                  ))}
                  <div className={styles.otherInfo}>
                    <div className={styles.otherItem}>
                      <AddressIcon />
                      <span>{mentorInfo.address}</span>
                    </div>
                    <div className={styles.otherItem}>
                      <RatingIcon />
                      <span>{mentorInfo.averateRatings.toFixed(1)}</span>
                      <span>&nbsp;({mentorInfo.numOfReivews} reviews)</span>
                    </div>
                  </div>
                </div>
              </div>
              <div className={styles.detailInfoRight}>
                <h3>Skills</h3>
                <div className={styles.skillList}>
                  {mentorInfo.skillList.map((skill, index) => {
                    if (index <= 2)
                      return (
                        <div key={index} className={styles.skillItem}>
                          {skill}
                        </div>
                      );
                    else return null;
                  })}
                  {mentorInfo.skillList.length > 3 && (
                    <a href="#skill">
                      + {mentorInfo.skillList.length - 3} more
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
                {ratingList.map((item) => (
                  <div key={item.accountId} className={styles.ratingItem}>
                    <div className={styles.infoAccount}>
                      <img src={item.imageUrl} alt="avatar" />
                      <div className={styles.accountDetail}>
                        <div className={styles.accountDetailTop}>
                          <h3>{item.name}</h3>
                          <span>&nbsp;on {item.dateRating}</span>
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
                    <p>{item.ratingDescription}</p>
                  </div>
                ))}
              </div>
              <hr />
              <div id="skill" className={styles.mainSkillList}>
                <h3>Skills</h3>
                <div className={styles.skillList}>
                  {mentorInfo.skillList.map((skill, index) => (
                    <div key={index} className={styles.skillItem}>
                      {skill}
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
