import { Container, Rating } from "@mui/material";
import CustomInputFilter from "../../component/custom-input-filter";
import CustomButton from "../../component/custom-button";
import Checkbox from "@mui/material/Checkbox";
import { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router";
import SearchIcon from "@mui/icons-material/Search";
import CircularProgress from "@mui/material/CircularProgress";
import BookmarkIcon from "@mui/icons-material/Bookmark";
import BookmarkBorderIcon from "@mui/icons-material/BookmarkBorder";
import styles from "./index.module.css";
import AvatarDefault from "../../assets/avatar-thinking-3-svgrepo-com.svg";
import { request } from "../../axios_helper";

const handleBuildFilterSkills = (skillList) => {
  return skillList.map((skill) => ({
    skillName: skill.skill_name,
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
  const location = useLocation();

  const handleClickViewMentor = (id) => {
    navigate(`/mentor/${id}`, {
      state: {
        prevPath: {
          to: location.pathname,
          represent: "Find Mentor",
        },
      },
    });
  };

  useEffect(() => {
    seIsLoading(true);
    Promise.all([
      request("GET",`/api/public/findMentor?skill_id=0`),
      request("GET",`/api/public/men/skills`),
    ])
      .then(([data1, data2]) => {
        setMentorList(data1.data);
        setOriginFilterListSkill(handleBuildFilterSkills(data2.data));
      })
      .catch((err) => {
        console.log(err);
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
                    src={mentor.avatar ? `data:image/jpeg;base64, ${mentor.avatar}` : AvatarDefault}
                    alt="avatar"
                    onClick={() => handleClickViewMentor(mentor.mentor_id)}
                  />
                  <div className={styles.mentorInfoWrapper}>
                    <h2
                      className={styles.mentorName}
                      onClick={() => handleClickViewMentor(mentor.mentor_id)}
                    >
                      {mentor.username}
                    </h2>
                    <div className={styles.archieveList}>
                      
                        <div className={styles.archieveItem}>
                          {mentor.short_description}
                        </div>
                      
                    </div>
            
                    <div className={styles.description}>
                      {mentor.description}
                    </div>
                    <div className={styles.skillList}>
                      {mentor.skills.map((skill, index) => (
                        <div key={index} className={styles.skillItem}>
                          {skill.skill_name}
                        </div>
                      ))}
                    </div>
                    <CustomButton
                      content={"View Profille"}
                      onClick={() => handleClickViewMentor(mentor.mentor_id)}
                    />
                    <div className={styles.bookMark}>
                      {mentor.favorite ? (
                        <BookmarkIcon />
                      ) : (
                        <BookmarkBorderIcon />
                      )}
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
