import { Alert, Container, Rating, Slide, Snackbar } from "@mui/material";
import CustomInputFilter from "../../component/custom-input-filter";
import CustomButton from "../../component/custom-button";
import Checkbox from "@mui/material/Checkbox";
import { useContext, useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router";
import SearchIcon from "@mui/icons-material/Search";
import CircularProgress from "@mui/material/CircularProgress";
import BookmarkIcon from "@mui/icons-material/Bookmark";
import BookmarkBorderIcon from "@mui/icons-material/BookmarkBorder";
import styles from "./index.module.css";
import AvatarDefault from "../../assets/avatar-thinking-3-svgrepo-com.svg";
import { request } from "../../axios_helper";
import { ApplicationContext } from "../../routes/AppRoutes";

const handleBuildFilterSkills = (skillList) => {
  return skillList.map((skill) => ({
    skillId: skill.skill_id,
    skillName: skill.skill_name,
    isChoosed: false,
  }));
};

const FindMentor = () => {
  const [mentorList, setMentorList] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  const [originFilterListSkill, setOriginFilterListSkill] = useState([]);
  const [filterListSkill, setFilterListSkill] = useState([]);
  const [filterSearch, setFilterSearch] = useState("");
  const [skillIdList, setSkillIdList] = useState([]);
  const [errorMessage, setErrorMessage] = useState("");
  const [snackbarOpen, setSnackbarOpen] = useState(false);
  const { user } = useContext(ApplicationContext);
  const role = user.role;
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
    fetch();
  }, [skillIdList]);

  const fetch =() => {
    const fetchData = async () => {
      if (skillIdList.length === 0) {
        return request("GET", "/api/public/findMentor?skill_id=0");
      } else {
        const queryParams = skillIdList.map((id) => `skill_id=${id}`).join("&");
        return request("GET", `/api/public/findMentor?${queryParams}`);
      }
    };

    fetchData()
      .then((data) => {
        setMentorList(data.data);
      })
      .catch((err) => {
        console.log(err);
      })
      .finally(() => {
        setIsLoading(false);
      });
  };

  useEffect(() => {
    const filteredSkillIds = filterListSkill
      .filter((skill) => skill.isChoosed === true)
      .map((item) => item.skillId);
    console.log(">>check SubmitSkill id>> ", filteredSkillIds);
    setSkillIdList(filteredSkillIds);
  }, [filterListSkill]);

  useEffect(() => {
    if (originFilterListSkill.length > 0) {
      const newFilterListSkill = originFilterListSkill.filter((skill) =>
        skill.skillName.toLowerCase().includes(filterSearch.toLowerCase())
      );
      setFilterListSkill(newFilterListSkill);
    }
  }, [originFilterListSkill, filterSearch]);

  useEffect(() => {
    const fetchSkills = async () => {
      try {
        const response = await request("GET", "/api/public/men/skills");
        setOriginFilterListSkill(handleBuildFilterSkills(response.data));
      } catch (error) {
        console.error(error);
      }
    };

    fetchSkills()
      .catch((err) => {
        console.log(err);
      });
  }, []);

  const handleClickFilterItem = (filterName) => {
    const newFilterSkill = originFilterListSkill.map((item) => {
      if (item.skillName !== filterName) return item;
      else
        return {
          skillId: item.skillId,
          skillName: item.skillName,
          isChoosed: !item.isChoosed,
        };
    });

    setOriginFilterListSkill(newFilterSkill);
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
      <Container
        maxWidth="lg"
        sx={{
          padding: "32px 0",
        }}
      >
        <div className={styles.inputWrapper}>
          <input
            type="text"
            placeholder="Search by skill or job title"
            value={filterSearch}
            onChange={(e) => setFilterSearch(e.target.value)}
          />
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
                    src={
                      mentor.avatar
                        ? `data:image/jpeg;base64, ${mentor.avatar}`
                        : AvatarDefault
                    }
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
                      content={"View Profile"}
                      onClick={() => handleClickViewMentor(mentor.mentor_id)}
                    />
                    <div className={styles.bookMark}>
                      {mentor.favorite ? (
                        <BookmarkIcon onClick={() => handleBookmarkClick(mentor.mentor_id, 0)} />
                      ) : (
                        <BookmarkBorderIcon onClick={() => handleBookmarkClick(mentor.mentor_id, 1)} />
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

export default FindMentor;
