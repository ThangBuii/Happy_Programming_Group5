import MainLayout from "../../component/main-layout";
import { useEffect, useState } from "react";
import { Button, CircularProgress, Pagination, Rating } from "@mui/material";
import BookmarkIcon from "@mui/icons-material/Bookmark";
import SearchIcon from "@mui/icons-material/Search";
import BookmarkBorderIcon from "@mui/icons-material/BookmarkBorder";
import { useNavigate } from "react-router";
import styles from "./index.module.css";

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

const fakeFavouriteMentorList = [
  createData(
    "mentor1",
    "nguyen trong tai",
    "https://cdn.mentorcruise.com/cache/ab5639698bbe4e80c445054291de2c29/5b5b3a3e039a50f9/ff33510e7837884ddcc3f2f27030a124.jpg",
    [
      "Product Designer at Financial Times Lead Product Designer with 18+ years of experience",
      "Sr. Software Engineer at eBay",
    ],
    "Miklos is a highly experienced Lead UX Designer/Product Designer with over 18 years of experience. He is also a speaker, writer, and mentor with over 10,000 followers on Medium, LinkedIn, and Twitter. Miklos has been mentoring for more than five years and has a proven track record of success, having …",
    true,
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
    false,
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
    true,
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
    true,
    5.0,
    7,
    ["UX", "UI", "Product Designer", "Portfolio Review", "Career Advice"]
  ),
];

const Favourite = () => {
  const navigate = useNavigate();
  const [page, setPage] = useState(1);
  const [isLoading, seIsLoading] = useState(true);
  const [searchFavourite, setSearchFavourite] = useState("");
  const [mentorList, setMentorList] = useState([...fakeFavouriteMentorList]);

  const handleChange = (event, value) => {
    setPage(value);
  };

  useEffect(() => {
    seIsLoading(true);
    Promise.all([fetch("http://localhost:9999/all-mentor")])
      .then((responses) => {
        return Promise.all(responses.map((response) => response.json()));
      })
      .then(([data1, data2]) => {
        setMentorList(data1);
      })
      .catch((err) => {
        console.log(err);
        setMentorList([...fakeFavouriteMentorList]);
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
                          onClick={() =>
                            navigate(`/mentor/${mentor.accountId}`)
                          }
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
                          <Rating
                            defaultValue={mentor.averateRatings}
                            readOnly
                          />
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
                        <div className={styles.actionWrapper}>
                          <Button
                            variant="outlined"
                            onClick={() =>
                              navigate(`/mentor/${mentor.accountId}`)
                            }
                          >
                            View Profile
                          </Button>
                          <Button variant="contained">Book Now</Button>
                        </div>
                        <div className={styles.bookMark}>
                          {mentor.isBookMark ? (
                            <BookmarkIcon />
                          ) : (
                            <BookmarkBorderIcon />
                          )}
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
