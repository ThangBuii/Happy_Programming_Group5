import { Button, Checkbox, CircularProgress, IconButton } from "@mui/material";
import MainLayout from "../../component/main-layout";
import AvatarDefault from "../../assets/avatar-thinking-3-svgrepo-com.svg";
import { useEffect, useState } from "react";
import CustomInputFilter from "../../component/custom-input-filter";
import CloseIcon from "@mui/icons-material/Close";
import styles from "./index.module.css";

// 0-mentee, 1-mentor

const fakeProfileData = {
  accountId: "user1",
  username: "John",
  avatar:
    "https://mentoring.dreamguystech.com/reactjs/template/5cf07dabbcf2db6a07ca8336a3538cf5.jpg",
  dob: "1997-02-27",
  gender: "Male",
  major: "abc@gmail.com",
  degree: "012345678",
  shortDescription: "806 Twin Willow Lane",
  city: "Old Forge",
  university: "Newyork",
  description: "13420",
  country: "United States",
  role: 1,
  skillList: ["Product Strategy", "Angular"],
};

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
  "UX",
  "Design",
  "Node",
  "Angular",
  "C#",
  "AWS",
  "JavaScript",
  "Coding",
  "NestJS",
  "React",
  "Typescript",
  "Ionic",
  "Framework",
  "NodeJS",
];

const handleBuildFilterSkills = (skillList, mySkill) => {
  return skillList.map((skill) => {
    if (mySkill.includes(skill))
      return {
        skillName: skill,
        isChoosed: true,
      };
    else
      return {
        skillName: skill,
        isChoosed: false,
      };
  });
};

const EditProfile = () => {
  const [isLoading, seIsLoading] = useState(true);
  const [profile, setProfile] = useState({});
  const [originFilterListSkill, setOriginFilterListSkill] = useState([]);
  const [filterListSkill, setFilterListSkill] = useState([]);
  const [filterSearch, setFilterSearch] = useState("");

  useEffect(() => {
    seIsLoading(true);
    Promise.all([
      fetch("http://localhost:9999/my-profile"),
      fetch("http://localhost:9999/all-skills"),
    ])
      .then((responses) => {
        return Promise.all(responses.map((response) => response.json()));
      })
      .then(([data1, data2]) => {
        setProfile(data1);
        setOriginFilterListSkill(
          handleBuildFilterSkills(data2, data1.skillList)
        );
      })
      .catch((err) => {
        console.log(err);
        setProfile({ ...fakeProfileData });
        setOriginFilterListSkill(
          handleBuildFilterSkills(fakeSkillSearch, fakeProfileData.skillList)
        );
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
    let nameState = false;
    const newFilterSkill = originFilterListSkill.map((item) => {
      if (item.skillName !== filterName) return item;
      else {
        nameState = !item.isChoosed;

        // call api add || delete skill db
        console.log(profile.accountId, filterName, nameState);

        return {
          skillName: item.skillName,
          isChoosed: nameState,
        };
      }
    });

    if (nameState)
      setProfile((pre) => ({
        ...pre,
        skillList: [...pre.skillList, filterName],
      }));
    else
      setProfile((pre) => ({
        ...pre,
        skillList: [...pre.skillList].filter((item) => item !== filterName),
      }));
    setOriginFilterListSkill(newFilterSkill);
  };

  const handleSaveChange = () => {
    console.log(">>check data: ", profile);
    // send post
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
                        <img
                          src={profile.avatar || AvatarDefault}
                          alt="avatar"
                        />
                      </div>
                      <div className={styles.uploadImg}>
                        <div className={styles.changePhotoBtn}>
                          <span>
                            <i className="fa fa-upload"></i> Upload Photo
                          </span>
                          {/* onChange o input voi cai base64 de thay doi state cua avatar */}
                          <input type="file" className={styles.upload} />
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
                      value={profile.gender}
                      onChange={(e) =>
                        setProfile((pre) => ({
                          ...pre,
                          gender: e.target.value,
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
                {profile.role === 1 && (
                  <>
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
                                    key={item.skillName}
                                    className={styles.searchItem}
                                    onClick={() =>
                                      handleClickFilterItem(item.skillName)
                                    }
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
                                      {item.skillName}
                                    </span>
                                    {/* <span className={styles.filterRemain}>441</span> */}
                                  </div>
                                ))}
                              </div>
                            </>
                          }
                        />
                        <div className={styles.skillList}>
                          {profile.skillList.map((skill, index) => (
                            <div key={index} className={styles.skillItem}>
                              {skill}
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
                          value={profile.shortDescription}
                          onChange={(e) =>
                            setProfile((pre) => ({
                              ...pre,
                              shortDescription: e.target.value,
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
