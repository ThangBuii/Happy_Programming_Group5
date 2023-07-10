import React, { useContext, useEffect, useState } from "react";
import MaleOutlinedIcon from "@mui/icons-material/MaleOutlined";
import FeMaleOutlinedIcon from "@mui/icons-material/FemaleOutlined";
import { Button, CircularProgress, Container } from "@mui/material";
import ProfileHelper from "../../component/profile-helper";
import { useNavigate } from "react-router";
import EditIcon from "@mui/icons-material/Edit";
import { request } from "../../axios_helper";
import styles from "./index.module.css";
import { ApplicationContext } from "../../routes/AppRoutes";

const Profile = () => {
  const navigate = useNavigate();
  const [myProfile, setMyProfile] = useState({});
  const [isLoading, seIsLoading] = useState(true);
  const { user } = useContext(ApplicationContext);
  const role = user.role; 

  useEffect(() => {
    seIsLoading(true);
    if (role === -1) return;
    const url = role === 1 ? "/api/mentor/profile" : "/api/mentee/profile"
    request("GET", url)
      .then((response) => {
        setMyProfile(response.data);
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
      <div className={styles.breadcumBarWrapper}>
        <div className={styles.bcLeft}>
          <div>
            <span className={styles.bcHome} onClick={() => navigate("/")}>
              Home
            </span>
            <span className={styles.bcPersonProfile}>
              {myProfile.role === 0 ? "Mentee" : "Mentor"} Profile
            </span>
          </div>
          <h2>{myProfile.role === 0 ? "Mentee" : "Mentor"} Profile</h2>
        </div>
        <div className={styles.bcRight}>
          <div className={styles.actionEdit}>
            <Button
              variant="contained"
              startIcon={<EditIcon />}
              onClick={() => navigate("/profile/edit")}
            >
              Edit Profile
            </Button>
          </div>
        </div>
      </div>
      <div className={styles.containerWrapper}>
        {isLoading ? (
          <div className={styles.loadingWrapper}>
            <CircularProgress />
          </div>
        ) : (
          <Container
            maxWidth="lg"
            sx={{
              padding: "30px 0 0",
            }}
          >
            <div className={styles.personProfileWrapper}>
              <img src={myProfile.avatar} alt="avatar" />
              <h2>{myProfile.username}</h2>
              <p>{myProfile.short_description}</p>
            </div>

            <ProfileHelper
              title={"About Me"}
              content={
                <div className={styles.aboutMe}>
                  <p>{myProfile.description}</p>
                </div>
              }
            />

            {myProfile.role === 1 && (
              <>
                <ProfileHelper
                  title={"Short Description"}
                  content={
                    <div className={styles.aboutMe}>
                      <p>{myProfile.short_description}</p>
                    </div>
                  }
                />
                <ProfileHelper
                  title={"Qualification"}
                  content={
                    <div className={styles.profileDetailWrapper}>
                      <div className={styles.profileDetailItem}>
                        <p>University</p>
                        <p>{myProfile.university}</p>
                      </div>
                      <div className={styles.profileDetailItem}>
                        <p>Major</p>
                        <p>{myProfile.major}</p>
                      </div>
                      <div className={styles.profileDetailItem}>
                        <p>Degree</p>
                        <p>{myProfile.degree}</p>
                      </div>
                    </div>
                  }
                />
                <ProfileHelper
                  title={"Skills"}
                  content={
                    <div className={styles.skillList}>
                      {myProfile.skills.map((skill, index) => (
                        <div key={index} className={styles.skillItem}>
                          {skill.skill_name}
                        </div>
                      ))}
                    </div>
                  }
                />
              </>
            )}

            <ProfileHelper
              title={"Personal Details"}
              content={
                <div className={styles.profileDetailWrapper}>
                  <div className={styles.profileDetailItem}>
                    <p>Gender</p>
                    <p>
                      {myProfile.gender === 0 ? (
                        <>
                          <MaleOutlinedIcon />
                          Male
                        </>
                      ) : (
                        <>
                          <FeMaleOutlinedIcon />
                          Female
                        </>
                      )}
                    </p>
                  </div>
                  <div className={styles.profileDetailItem}>
                    <p>Date of Birth</p>
                    <p>{myProfile.dob}</p>
                  </div>
                </div>
              }
            />
            <ProfileHelper
              title={"Location"}
              content={
                <div className={styles.profileDetailWrapper}>
                  <div className={styles.profileDetailItem}>
                    <p>Country</p>
                    <p>{myProfile.country}</p>
                  </div>
                  <div className={styles.profileDetailItem}>
                    <p>City</p>
                    <p>{myProfile.city}</p>
                  </div>
                </div>
              }
            />
          </Container>
        )}
      </div>
    </div>
  );
};

export default Profile;
