import MainLayout from "../../component/main-layout";
import { useEffect, useState } from "react";
import { Button, CircularProgress, Pagination, Rating } from "@mui/material";
import BookmarkIcon from "@mui/icons-material/Bookmark";
import SearchIcon from "@mui/icons-material/Search";
import BookmarkBorderIcon from "@mui/icons-material/BookmarkBorder";
import { useLocation, useNavigate } from "react-router";
import styles from "./index.module.css";
import { request } from '../../axios_helper'
import AvatarDefault from "../../assets/avatar-thinking-3-svgrepo-com.svg";


const Favourite = () => {
  const navigate = useNavigate();
  const location = useLocation();
  const [page, setPage] = useState(1);
  const [isLoading, seIsLoading] = useState(true);
  const [searchFavourite, setSearchFavourite] = useState("");
  const [mentorList, setMentorList] = useState([]);

  const mentorsPerPage = 10; // Number of mentors to display per page

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

  const handleBookmarkClick = (id) => {
    // Send delete request to the backend
    request("DELETE", `/api/mentee/favorite/${id}`)
    .then(response => {
      // Handle successful deletion
      // Fetch the updated list of mentors
      return request("GET", "/api/mentee/favorite");
    })
    .then(updatedResponse => {
      // Update the mentorList state with the updated list
      setMentorList(updatedResponse.data);
    })
    .catch(error => {
      // Handle error
      console.error('Error deleting bookmark:', error);
    });
  };

  // Calculate the start and end index of the mentors to display on the current page
  const startIndex = (page - 1) * mentorsPerPage;
  const endIndex = startIndex + mentorsPerPage;
  const mentorsToShow = mentorList.slice(startIndex, endIndex);

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
                  {mentorsToShow.map((mentor) => (
                    <div
                      key={mentor.mentor_id}
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
                          <BookmarkIcon onClick={() => handleBookmarkClick(mentor.mentor_id)}/>
                        </div>
                      </div>
                    </div>
                  ))}
                </div>
                <div className={styles.paginationWrapper}>
                  <Pagination
                    count={Math.ceil(mentorList.length / mentorsPerPage)}
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
