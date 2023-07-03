import styles from "./index.module.css";

const ProfileHelper = ({ title, content }) => {
  return (
    <div className={styles.profileWrapper}>
      <h1>{title}</h1>
      <hr />
      {content}
    </div>
  );
};

export default ProfileHelper;
