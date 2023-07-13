import { Button, Checkbox, CircularProgress, IconButton } from "@mui/material";
import MainLayout from "../../component/main-layout";
import AvatarDefault from "../../assets/avatar-thinking-3-svgrepo-com.svg";
import { useContext, useEffect, useState } from "react";
import CustomInputFilter from "../../component/custom-input-filter";
import CloseIcon from "@mui/icons-material/Close";
import styles from "./index.module.css";
import { request } from "../../axios_helper";
import { ApplicationContext } from "../../routes/AppRoutes";
import { useNavigate } from "react-router";
// 2-mentee, 1-mentor

export const handleBuildFilterSkills = (skills, mySkill = []) => {
  return skills.map((skill) => {
    if (mySkill.some((item) => item.skill_name === skill.skill_name))
      return {
        skill_id: skill.skill_id,
        skill_name: skill.skill_name,
        isChoosed: true,
      };
    else
      return {
        skill_id: skill.skill_id,
        skill_name: skill.skill_name,
        isChoosed: false,
      };
  });
};

const EditProfile = () => {
  const [isLoading, seIsLoading] = useState(true);
  const [profile, setProfile] = useState({});
  const { user } = useContext(ApplicationContext);
  const role = user.role;
  const [originFilterListSkill, setOriginFilterListSkill] = useState([]);
  const [filterListSkill, setFilterListSkill] = useState([]);
  const [filterSearch, setFilterSearch] = useState("");
  const [selectedPhoto, setSelectedPhoto] = useState(null);
  const navigate = useNavigate();
  const imageSource = profile.avatar
    ? `data:image/jpeg;base64, ${profile.avatar}`
    : AvatarDefault;

  useEffect(() => {
    seIsLoading(true);
    if (role === -1) return;
    const url = role === 1 ? "/api/mentor/profile" : "/api/mentee/profile";
    Promise.all([request("GET", url), request("GET", "/api/men/skills")])
      .then((responses) => {
        return Promise.all(responses.map((response) => response.data));
      })
      .then(([data1, data2]) => {
        setProfile(data1);
        setOriginFilterListSkill(handleBuildFilterSkills(data2, data1.skills));
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
        skill.skill_name.toLowerCase().includes(filterSearch.toLowerCase())
      );
      setFilterListSkill(newFilterListSkill);
    }
  }, [originFilterListSkill, filterSearch]);

  const handleClickFilterItem = (skill) => {
    let nameState = false;
    const newFilterSkill = originFilterListSkill.map((item) => {
      if (item.skill_name !== skill.skill_name) return item;
      else {
        nameState = !item.isChoosed;

        return {
          skill_id: item.skill_id,
          skill_name: item.skill_name,
          isChoosed: nameState,
        };
      }
    });

    if (nameState)
      setProfile((pre) => ({
        ...pre,
        skills: [
          ...pre.skills,
          { skill_id: skill.skill_id, skill_name: skill.skill_name },
        ],
      }));
    else
      setProfile((pre) => ({
        ...pre,
        skills: [...pre.skills].filter(
          (item) => item.skill_name !== skill.skill_name
        ),
      }));
    setOriginFilterListSkill(newFilterSkill);
  };

  const handlePhotoChange = (e) => {
    const file = e.target.files[0];

    // Validate file type
    if (file && file.type.startsWith("image/")) {
      setSelectedPhoto(file);

      const reader = new FileReader();
      reader.onloadend = () => {
        const base64String = reader.result.split(",")[1];
        setProfile((prevProfile) => ({
          ...prevProfile,
          avatar: base64String,
        }));
      };
      reader.readAsDataURL(file);
    } else {
      // Invalid file type, show error or reset the selected photo
      setSelectedPhoto(null);
      setProfile((prevProfile) => ({
        ...prevProfile,
        avatar: AvatarDefault,
      }));
      // Show an error message or handle the invalid file type here
    }
  };

  const handleSaveChange = () => {
    const url = role === 1 ? "/api/mentor/profile" : "/api/mentee/profile";
    request("POST", url, profile)
      .then((response) => {
        navigate("/profile");
        // Handle success or show a success message to the user
      })
      .catch((error) => {
        console.log("API error:", error);
        // Handle error or show an error message to the user
      });
  };

  return (
    <MainLayout
      pageTitle={"Edit Profile"}
      layoutContent={
        <div className={styles.layoutWrapper}>
          {isLoading ? (
            <div className={styles.loadingWrapper}>
              <CircularProgress />
            </div>
          ) : (
            <>
              <div className="row form-row">
                <div className="col-12 col-md-12 mb-3">
                  <div className="form-group">
                    <div className={styles.changeAvatar}>
                      <div className={styles.profileImage}>
                        <img src={imageSource} alt="avatar" />
                      </div>
                      <div className={styles.uploadImg}>
                        <div className={styles.changePhotoBtn}>
                          <span>
                            <i className="fa fa-upload"></i> Upload Photo
                          </span>
                          {/* onChange o input voi cai base64 de thay doi state cua avatar */}
                          <input
                            type="file"
                            className={styles.upload}
                            onChange={handlePhotoChange}
                          />
                        </div>
                        <small
                          className="form-text text-muted"
                          style={{ fontSize: "12px" }}
                        >
                          Allowed JPG, GIF or PNG. Max size of 2MB
                        </small>
                      </div>
                    </div>
                  </div>
                </div>
                <div className="col-12 col-md-6 mb-3">
                  <div className="form-group">
                    <label className="mb-2">Username</label>
                    <input
                      type="text"
                      className="form-control"
                      value={profile.username}
                      onChange={(e) =>
                        setProfile((pre) => ({
                          ...pre,
                          username: e.target.value,
                        }))
                      }
                    />
                  </div>
                </div>
                <div className="col-12 col-md-6 mb-3">
                  <div className="form-group">
                    <label className="mb-2">Date of Birth</label>
                    <div className="cal-icon">
                      <input
                        type="date"
                        className="form-control datetimepicker"
                        value={profile.dob}
                        onChange={(e) =>
                          setProfile((pre) => ({ ...pre, dob: e.target.value }))
                        }
                      />
                    </div>
                  </div>
                </div>
                <div className="col-12 col-md-6 mb-3">
                  <div className="form-group">
                    <label className="mb-2">Gender</label>
                    <select
                      className="form-control select"
                      value={profile.gender === 0 ? "Female" : "Male"}
                      onChange={(e) =>
                        setProfile((pre) => ({
                          ...pre,
                          gender: e.target.value === "Female" ? 0 : 1,
                        }))
                      }
                    >
                      <option></option>
                      <option>Male</option>
                      <option>Female</option>
                    </select>
                  </div>
                </div>
                <div className="col-12 col-md-6 mb-3">
                  <div className="form-group">
                    <label className="mb-2">Country</label>
                    <input
                      type="text"
                      className="form-control"
                      value={profile.country}
                      onChange={(e) =>
                        setProfile((pre) => ({
                          ...pre,
                          country: e.target.value,
                        }))
                      }
                    />
                  </div>
                </div>
                <div className="col-12 col-md-6 mb-3">
                  <div className="form-group">
                    <label className="mb-2">City</label>
                    <input
                      type="text"
                      className="form-control"
                      value={profile.city}
                      onChange={(e) =>
                        setProfile((pre) => ({ ...pre, city: e.target.value }))
                      }
                    />
                  </div>
                </div>

                {profile.role === 1 && (
                  <>
                    <div className="col-12 col-md-6 mb-3">
                      <div className="form-group">
                        <label className="mb-2">University</label>
                        <input
                          type="text"
                          className="form-control"
                          value={profile.university}
                          onChange={(e) =>
                            setProfile((pre) => ({
                              ...pre,
                              university: e.target.value,
                            }))
                          }
                        />
                      </div>
                    </div>
                    <div className="col-12 col-md-6 mb-3">
                      <div className="form-group">
                        <label className="mb-2">Major</label>
                        <input
                          type="text"
                          className="form-control"
                          value={profile.major}
                          onChange={(e) =>
                            setProfile((pre) => ({
                              ...pre,
                              major: e.target.value,
                            }))
                          }
                        />
                      </div>
                    </div>
                    <div className="col-12 col-md-6 mb-3">
                      <div className="form-group">
                        <label className="mb-2">Degree</label>
                        <input
                          type="text"
                          className="form-control"
                          value={profile.degree}
                          onChange={(e) =>
                            setProfile((pre) => ({
                              ...pre,
                              degree: e.target.value,
                            }))
                          }
                        />
                      </div>
                    </div>
                    <div className="col-12 mb-3">
                      <div className="form-group">
                        {/* <label className="mb-2">Skills</label> */}
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
                                onChange={(e) =>
                                  setFilterSearch(e.target.value)
                                }
                              />
                              <div className={styles.searchList}>
                                {filterListSkill.map((item) => (
                                  <div
                                    key={item.skill_name}
                                    className={styles.searchItem}
                                    onClick={() => handleClickFilterItem(item)}
                                  >
                                    <Checkbox
                                      sx={{
                                        width: "14px",
                                        height: "14px",
                                        margin: "6px 0",
                                      }}
                                      checked={item.isChoosed}
                                      readOnly
                                    />
                                    <span className={styles.filterName}>
                                      {item.skill_name}
                                    </span>
                                    {/* <span className={styles.filterRemain}>441</span> */}
                                  </div>
                                ))}
                              </div>
                            </>
                          }
                        />
                        <div className={styles.skillList}>
                          {profile.skills.map((skill, index) => (
                            <div key={index} className={styles.skillItem}>
                              {skill.skill_name}
                              <IconButton
                                color="secondary"
                                className={styles.customDeleteSkill}
                                onClick={() => handleClickFilterItem(skill)}
                              >
                                <CloseIcon />
                              </IconButton>
                            </div>
                          ))}
                        </div>
                      </div>
                    </div>
                    <div className="col-12 mb-3">
                      <div className="form-group">
                        <label className="mb-2">Short Description</label>
                        <textarea
                          type="text"
                          className="form-control"
                          value={profile.short_description}
                          onChange={(e) =>
                            setProfile((pre) => ({
                              ...pre,
                              short_description: e.target.value,
                            }))
                          }
                          rows={2}
                        />
                      </div>
                    </div>
                  </>
                )}

                <div className="col-12 mb-3">
                  <div className="form-group">
                    <label className="mb-2">Description</label>
                    <textarea
                      type="text"
                      className="form-control"
                      value={profile.description}
                      onChange={(e) =>
                        setProfile((pre) => ({
                          ...pre,
                          description: e.target.value,
                        }))
                      }
                      rows={4}
                    />
                  </div>
                </div>
              </div>
              <div className={styles.sectionSubmit}>
                <Button variant="contained" onClick={handleSaveChange}>
                  Save Changes
                </Button>
              </div>
            </>
          )}
        </div>
      }
    />
  );
};

export default EditProfile;
