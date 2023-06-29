import { Container, Rating } from "@mui/material";
import SearchIcon from "@mui/icons-material/Search";
import CustomInputFilter from "../../component/custom-input-filter";
import Checkbox from "@mui/material/Checkbox";
import { useEffect, useState } from "react";
import CustomButton from "../../component/custom-button";
import { useNavigate } from "react-router";
import StarBorderIcon from "@mui/icons-material/StarBorder";
import CircularProgress from "@mui/material/CircularProgress";
import styles from "./index.module.css";

function createData(
  accountId,
  name,
  imageUrl,
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
    achievement,
    description,
    tag,
    averateRatings,
    numOfReivews,
    skillList,
  };
}

const fakeData = [
  createData(
    "mentor1",
    "nguyen trong tai",
    "https://cdn.mentorcruise.com/cache/ab5639698bbe4e80c445054291de2c29/5b5b3a3e039a50f9/ff33510e7837884ddcc3f2f27030a124.jpg",
    [
      "Product Designer at Financial Times Lead Product Designer with 18+ years of experience",
      "Sr. Software Engineer at eBay",
    ],
    "Miklos is a highly experienced Lead UX Designer/Product Designer with over 18 years of experience. He is also a speaker, writer, and mentor with over 10,000 followers on Medium, LinkedIn, and Twitter. Miklos has been mentoring for more than five years and has a proven track record of success, having …",
    "Quick Responder",
    5.0,
    7,
    ["UX", "UI", "Product Designer", "Portfolio Review", "Career Advice"]
  ),
  createData(
    "mentor2",
    "nguyen trong tai",
    "https://cdn.mentorcruise.com/cache/ab5639698bbe4e80c445054291de2c29/5b5b3a3e039a50f9/ff33510e7837884ddcc3f2f27030a124.jpg",
    ["Mentor: Career Growth | Leadership | Product Marketing"],
    "Miklos is a highly experienced Lead UX Designer/Product Designer with over 18 years of experience. He is also a speaker, writer, and mentor with over 10,000 followers on Medium, LinkedIn, and Twitter. Miklos has been mentoring for more than five years and has a proven track record of success, having …",
    "Quick Responder",
    5.0,
    7,
    ["UX", "UI", "Product Designer", "Portfolio Review", "Career Advice"]
  ),
  createData(
    "mentor3",
    "nguyen trong tai",
    "https://cdn.mentorcruise.com/cache/ab5639698bbe4e80c445054291de2c29/5b5b3a3e039a50f9/ff33510e7837884ddcc3f2f27030a124.jpg",
    [
      "Founder at multiple digital agencies",
      "Product Designer at Financial Times Lead Product Designer with 18+ years of experience",
    ],
    "Miklos is a highly experienced Lead UX Designer/Product Designer with over 18 years of experience. He is also a speaker, writer, and mentor with over 10,000 followers on Medium, LinkedIn, and Twitter. Miklos has been mentoring for more than five years and has a proven track record of success, having …",
    "Quick Responder",
    5.0,
    7,
    ["UX", "UI", "Product Designer", "Portfolio Review", "Career Advice"]
  ),
  createData(
    "mentor4",
    "nguyen trong tai",
    "https://cdn.mentorcruise.com/cache/ab5639698bbe4e80c445054291de2c29/5b5b3a3e039a50f9/ff33510e7837884ddcc3f2f27030a124.jpg",
    [
      "Product Designer at Financial Times Lead Product Designer with 18+ years of experience",
      "Product Leadership Coach (ex-Director of Product) at ex-ServiceNow, ex-Yandex",
    ],
    "Miklos is a highly experienced Lead UX Designer/Product Designer with over 18 years of experience. He is also a speaker, writer, and mentor with over 10,000 followers on Medium, LinkedIn, and Twitter. Miklos has been mentoring for more than five years and has a proven track record of success, having …",
    "Quick Responder",
    5.0,
    7,
    ["UX", "UI", "Product Designer", "Portfolio Review", "Career Advice"]
  ),
];

const fakeSkillSearch = [
  "Leadership",
  "Project Management",
  "Sales",
  "Startup",
  "Career",
  "Product Designer",
  "UX Design",
  "Interviews",
  "Product Designer1",
  "Software Engineer",
  "Javascript Developer",
  "Python Developer",
  "Product Strategy",
];

const handleBuildFilterSkills = (skillList) => {
  return skillList.map((skill) => ({
    skillName: skill,
    isChoosed: false,
  }));
};

const FindMentor = () => {
  const [mentorList, setMentorList] = useState([]);
  const [isLoading, seIsLoading] = useState(true);
  const [originFilterListSkill, setOriginFilterListSkill] = useState([]);
  const [filterListSkill, setFilterListSkill] = useState([]);
  const [filterSearch, setFilterSearch] = useState("");
  const navigate = useNavigate();

  useEffect(() => {
    seIsLoading(true);
    Promise.all([
      fetch("http://localhost:9999/all-mentor"),
      fetch("http://localhost:9999/all-skills"),
    ])
      .then((responses) => {
        return Promise.all(responses.map((response) => response.json()));
      })
      .then(([data1, data2]) => {
        setMentorList(data1);
        setOriginFilterListSkill(handleBuildFilterSkills(data2));
      })
      .catch((err) => {
        console.log(err);
        setMentorList(fakeData);
        setOriginFilterListSkill(handleBuildFilterSkills(fakeSkillSearch));
      })
      .finally(() => {
        seIsLoading(false);
      });
  }, []);

  useEffect(() => {
    if (originFilterListSkill.length > 0) {
      const newFilterListSkill = originFilterListSkill.filter((skill) =>
        skill.skillName.toLowerCase().includes(filterSearch.toLowerCase())
      );
      setFilterListSkill(newFilterListSkill);
    }
  }, [originFilterListSkill, filterSearch]);

  const handleClickFilterItem = (filterName) => {
    const newFilterSkill = originFilterListSkill.map((item) => {
      if (item.skillName !== filterName) return item;
      else
        return {
          skillName: item.skillName,
          isChoosed: !item.isChoosed,
        };
    });

    setOriginFilterListSkill(newFilterSkill);
  };

  return (
    <div className={styles.layoutWrapper}>
      <Container
        maxWidth="lg"
        sx={{
          padding: "32px 0",
        }}
      >
        <div className={styles.inputWrapper}>
          <input type="text" placeholder="Search by skill or job title" />
          <SearchIcon />
        </div>
        <div className={styles.skillWrapper}>
          <CustomInputFilter
            filterTitle={"Skills"}
            isDropDown={true}
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
                      key={item.skillName}
                      className={styles.searchItem}
                      onClick={() => handleClickFilterItem(item.skillName)}
                    >
                      <Checkbox
                        sx={{ width: "14px", height: "14px", margin: "6px 0" }}
                        checked={item.isChoosed}
                        readOnly
                      />
                      <span className={styles.filterName}>
                        {item.skillName}
                      </span>
                      {/* <span className={styles.filterRemain}>441</span> */}
                    </div>
                  ))}
                </div>
              </>
            }
          />
        </div>
      </Container>
      <hr />
      <Container maxWidth="lg">
        {isLoading ? (
          <div className={styles.customLoading}>
            <CircularProgress />
          </div>
        ) : (
          <>
            <div className={styles.searchResult}>
              {mentorList.length} mentors found
            </div>
            <div className={styles.mentorListWrapper}>
              {mentorList.map((mentor) => (
                <div
                  key={mentor.accountId}
                  className={styles.mentorItemWrapper}
                >
                  <img
                    src={mentor.imageUrl}
                    alt="avatar"
                    onClick={() => navigate(`/mentor/${mentor.accountId}`)}
                  />
                  <div className={styles.mentorInfoWrapper}>
                    <h2
                      className={styles.mentorName}
                      onClick={() => navigate(`/mentor/${mentor.accountId}`)}
                    >
                      {mentor.name}
                    </h2>
                    <div className={styles.archieveList}>
                      {mentor.achievement.map((item, index) => (
                        <div key={index} className={styles.archieveItem}>
                          {item}
                        </div>
                      ))}
                    </div>
                    <div className={styles.ratingWrapper}>
                      <Rating defaultValue={mentor.averateRatings} readOnly />
                      <div className={styles.ratings}>
                        <b>{mentor.averateRatings}</b>
                      </div>
                      <span>({mentor.numOfReivews} reviews)</span>
                    </div>
                    <div className={styles.description}>
                      {mentor.description}
                    </div>
                    <div className={styles.skillList}>
                      {mentor.skillList.map((skill, index) => (
                        <div key={index} className={styles.skillItem}>
                          {skill}
                        </div>
                      ))}
                    </div>
                    <CustomButton
                      content={"View Profille"}
                      onClick={() => navigate(`/mentor/${mentor.accountId}`)}
                    />
                    <div className={styles.tag}>
                      <StarBorderIcon />
                      <span>{mentor.tag}</span>
                    </div>
                  </div>
                </div>
              ))}
            </div>
            <div className={styles.customBtn}>
              <CustomButton
                content={"Show more"}
                onClick={() => console.log("hihi")}
              />
            </div>
          </>
        )}
      </Container>
    </div>
  );
};

export default FindMentor;
