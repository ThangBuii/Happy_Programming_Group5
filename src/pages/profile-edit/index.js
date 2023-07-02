import { Button } from "@mui/material";
import MainLayout from "../../component/main-layout";
import AvatarDefault from "../../assets/avatar-thinking-3-svgrepo-com.svg";
import styles from "./index.module.css";
import { useEffect, useState } from "react";

const fakeProfileData = {
  accountId: "user1",
  firstName: "John",
  lastName: "Smith",
  imageUrl:
    "https://mentoring.dreamguystech.com/reactjs/template/5cf07dabbcf2db6a07ca8336a3538cf5.jpg",
  dob: "02/27/1997",
  bloodGroup: "A-",
  email: "abc@gmail.com",
  mobile: "012345678",
  address: "806 Twin Willow Lane",
  city: "Old Forge",
  state: "Newyork",
  zipCode: "13420",
  country: "United States",
};

const EditProfile = () => {
  const [profile, setProfile] = useState({ ...fakeProfileData });

  useEffect(() => {
    fetch("http://localhost:9999/my-profile")
      .then((resp) => resp.json())
      .then((data) => {
        setProfile(data);
      })
      .catch((err) => {
        console.log(err);
        setProfile({ ...fakeProfileData });
      });
  }, []);

  return (
    <MainLayout
      pageTitle={"Edit Profile"}
      layoutContent={
        <div className={styles.layoutWrapper}>
          <div className="row form-row">
            <div className="col-12 col-md-12 mb-3">
              <div className="form-group">
                <div className={styles.changeAvatar}>
                  <div className={styles.profileImage}>
                    <img src={profile.imageUrl || AvatarDefault} alt="avatar" />
                  </div>
                  <div className={styles.uploadImg}>
                    <div className={styles.changePhotoBtn}>
                      <span>
                        <i className="fa fa-upload"></i> Upload Photo
                      </span>
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
                <label className="mb-2">First Name</label>
                <input
                  type="text"
                  className="form-control"
                  value={profile.firstName}
                  onChange={(e) =>
                    setProfile((pre) => ({ ...pre, firstName: e.target.value }))
                  }
                />
              </div>
            </div>
            <div className="col-12 col-md-6 mb-3">
              <div className="form-group">
                <label className="mb-2">Last Name</label>
                <input
                  type="text"
                  className="form-control"
                  value={profile.lastName}
                  onChange={(e) =>
                    setProfile((pre) => ({ ...pre, lastName: e.target.value }))
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
                <label className="mb-2">Blood Group</label>
                <select
                  className="form-control select"
                  value={profile.bloodGroup}
                  onChange={(e) =>
                    setProfile((pre) => ({
                      ...pre,
                      bloodGroup: e.target.value,
                    }))
                  }
                >
                  <option>A-</option>
                  <option>A+</option>
                  <option>B-</option>
                  <option>B+</option>
                  <option>AB-</option>
                  <option>AB+</option>
                  <option>O-</option>
                  <option>O+</option>
                </select>
              </div>
            </div>
            <div className="col-12 col-md-6 mb-3">
              <div className="form-group">
                <label className="mb-2">Email ID</label>
                <input
                  type="email"
                  className="form-control"
                  value={profile.email}
                  onChange={(e) =>
                    setProfile((pre) => ({ ...pre, email: e.target.value }))
                  }
                />
              </div>
            </div>
            <div className="col-12 col-md-6 mb-3">
              <div className="form-group">
                <label className="mb-2">Mobile</label>
                <input
                  type="text"
                  className="form-control"
                  value={profile.mobile}
                  onChange={(e) =>
                    setProfile((pre) => ({ ...pre, mobile: e.target.value }))
                  }
                />
              </div>
            </div>
            <div className="col-12 mb-3">
              <div className="form-group">
                <label className="mb-2">Address</label>
                <input
                  type="text"
                  className="form-control"
                  value={profile.address}
                  onChange={(e) =>
                    setProfile((pre) => ({ ...pre, address: e.target.value }))
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
                <label className="mb-2">State</label>
                <input
                  type="text"
                  className="form-control"
                  value={profile.state}
                  onChange={(e) =>
                    setProfile((pre) => ({ ...pre, state: e.target.value }))
                  }
                />
              </div>
            </div>
            <div className="col-12 col-md-6 mb-3">
              <div className="form-group">
                <label className="mb-2">Zip Code</label>
                <input
                  type="text"
                  className="form-control"
                  value={profile.zipCode}
                  onChange={(e) =>
                    setProfile((pre) => ({ ...pre, zipCode: e.target.value }))
                  }
                />
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
                    setProfile((pre) => ({ ...pre, country: e.target.value }))
                  }
                />
              </div>
            </div>
          </div>
          <div class={styles.sectionSubmit}>
            <Button variant="contained">Save Changes</Button>
          </div>
        </div>
      }
    />
  );
};

export default EditProfile;
