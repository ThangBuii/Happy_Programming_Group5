import React from "react";
import MaleOutlinedIcon from "@mui/icons-material/MaleOutlined";
// import FemaleOutlinedIcon from "@mui/icons-material/FemaleOutlined";
import { Button, Container } from "@mui/material";
import ProfileHelper from "../../component/profile-helper";
import { useNavigate } from "react-router";
import EditIcon from "@mui/icons-material/Edit";
import styles from "./index.module.css";

const Profile = () => {
  const navigate = useNavigate();

  return (
    <div className={styles.layoutWrapper}>
      <div className={styles.breadcumBarWrapper}>
        <div className={styles.bcLeft}>
          <div>
            <span className={styles.bcHome} onClick={() => navigate("/")}>
              Home
            </span>
            <span className={styles.bcPersonProfile}>Mentee Profile</span>
          </div>
          <h2>Mentee Profile</h2>
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
      <Container
        maxWidth="lg"
        sx={{
          padding: "30px 0 0",
        }}
      >
        <div className={styles.personProfileWrapper}>
          <img
            src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTu5iuH9GH49VUAv0qvlrKiFRnsgEC6maRA9g&usqp=CAU"
            alt="avatar"
          />
          <h2>Jonathan Doe</h2>
          <p>English Literature (M.A)</p>
        </div>

        <ProfileHelper
          title={"About Me"}
          content={
            <div className={styles.aboutMe}>
              <p>
                Lorem Ipsum is simply dummy text of the printing and typesetting
                industry. Lorem Ipsum has been the industry's standard dummy
                text ever since the 1500s, when an unknown printer took a galley
                of type and scrambled it to make a type specimen book. It has
                survived not only five centuries, but also the leap into
                electronic typesetting, remaining essentially unchanged.
              </p>
              <p>
                Contrary to popular belief, Lorem Ipsum is not simply random
                text. It has roots in a piece of classical Latin literature from
                45 BC, making it over 2000 years old.
              </p>
            </div>
          }
        />

        <ProfileHelper
          title={"Personal Details"}
          content={
            <div className={styles.profileDetailWrapper}>
              <div className={styles.profileDetailItem}>
                <p>Gender</p>
                <p>
                  <MaleOutlinedIcon />
                  Male
                </p>
              </div>
              <div className={styles.profileDetailItem}>
                <p>Date of Birth</p>
                <p>01 - 02 - 2000</p>
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
                <p>India</p>
              </div>
              <div className={styles.profileDetailItem}>
                <p>City</p>
                <p>Coimbatore</p>
              </div>
            </div>
          }
        />
      </Container>
    </div>
  );
};

export default Profile;
