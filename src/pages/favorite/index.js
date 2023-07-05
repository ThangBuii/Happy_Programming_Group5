import MainLayout from "../../component/main-layout";
import { useEffect, useState } from "react";
import { Button, CircularProgress, Pagination, Rating } from "@mui/material";
import BookmarkIcon from "@mui/icons-material/Bookmark";
import SearchIcon from "@mui/icons-material/Search";
import BookmarkBorderIcon from "@mui/icons-material/BookmarkBorder";
import { useLocation, useNavigate } from "react-router";
import styles from "./index.module.css";
import { request } from '../../axios_helper'

function createData(
  accountId,
  name,
  imageUrl,
  achievement,
  description,
  isBookMark,
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
    isBookMark,
    averateRatings,
    numOfReivews,
    skillList,
  };
}

const Favourite = () => {
  const navigate = useNavigate();
  const location = useLocation();
  const [page, setPage] = useState(1);
  const [isLoading, seIsLoading] = useState(true);
  const [searchFavourite, setSearchFavourite] = useState("");
  const [mentorList, setMentorList] = useState([]);

  const handleClickViewMentor = (id) => {
    navigate(`/mentor/${id}`, {
      state: {
        prevPath: {
          to: location.pathname,
          represent: "Favourite Mentor",
        },
      },
    });
  };

  const handleChange = (event, value) => {
    setPage(value);
  };

  // useEffect(() => {
  //   seIsLoading(true);
  //   Promise.all([fetch("http://localhost:9999/all-mentor")])
  //     .then((responses) => {
  //       return Promise.all(responses.map((response) => response.json()));
  //     })
  //     .then(([data1, data2]) => {
  //       setMentorList(data1);
  //     })
  //     .catch((err) => {
  //       console.log(err);
  //       setMentorList([...fakeFavouriteMentorList]);
  //     })
  //     .finally(() => {
  //       seIsLoading(false);
  //     });
  // }, []);

  useEffect(() => {
    request("GET", "/api/mentee/favorite")
      .then((response) => {
        setMentorList(response.data);
      })
      .catch((error) => {
        console.error(error);
      })
      .finally(() => {
        seIsLoading(false);
      });
  }, []);

  return (
    <div className={styles.layoutWrapper}>
      <MainLayout
        pageTitle={"Favourites"}
        titleControl={
          <div className={styles.inputWrapper}>
            <input
              className={styles.customInput}
              type="text"
              placeholder="Search Favourites..."
              value={searchFavourite}
              onChange={(e) => setSearchFavourite(e.target.value)}
            />
            <div className={styles.searchWrapper}>
              <SearchIcon />
            </div>
          </div>
        }
        layoutContent={
          <div className={styles.mentorList}>
            {isLoading ? (
              <div className={styles.customLoading}>
                <CircularProgress />
              </div>
            ) : (
              <>
                <div className={styles.mentorListWrapper}>
                  {mentorList.map((mentor) => (
                    <div
                      key={mentor.mentor_id}
                      className={styles.mentorItemWrapper}
                    >
                      <img
                        src={mentor.avatar}
                        alt="avatar"
                        onClick={() => handleClickViewMentor(mentor.mentor_id)}
                      />
                      <div className={styles.mentorInfoWrapper}>
                        <h2
                          className={styles.mentorName}
                          onClick={() =>
                            handleClickViewMentor(mentor.mentor_id)
                          }
                        >
                          {mentor.username}
                        </h2>
                        <div className={styles.archieveList}>
                          {mentor.short_description}
                        </div>

                        <div className={styles.description}>
                          {mentor.description}
                        </div>
                        
                        <div className={styles.skillList}>
                          {mentor.skills.map((skill) => (
                            <div key={skill.skill_id} className={styles.skillItem}>
                              {skill.skill_name}
                            </div>
                          ))}
                        </div>
                        <div className={styles.actionWrapper}>
                          <Button
                            variant="outlined"
                            onClick={() =>
                              handleClickViewMentor(mentor.mentor_id)
                            }
                          >
                            View Profile
                          </Button>
                          <Button variant="contained">Book Now</Button>
                        </div>
                        <div className={styles.bookMark}>

                          <BookmarkIcon />

                        </div>
                      </div>
                    </div>
                  ))}
                </div>
                <div className={styles.paginationWrapper}>
                  <Pagination
                    count={10}
                    color="primary"
                    shape="rounded"
                    size="large"
                    page={page}
                    onChange={handleChange}
                  />
                </div>
              </>
            )}
          </div>
        }
      />
    </div>
  );
};

export default Favourite;
