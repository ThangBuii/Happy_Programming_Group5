
import styles from'../../component/Footer/index.module.css';
const Footer = () => {
    return (
      <div className={styles.footerWrapper}>
        <div className={styles.footerDs}>
          <div className={styles.dSHead}>
         
            <h1>HAPPY PROGRAMMING</h1>
          </div>
          
        </div>
        <div className={styles.footerItem}>
          <h2>For mentee</h2>
          <span>Find mentors</span>
          <span>Find mentors</span>
          <span>Find mentors</span>
          <span>Find mentors</span>
        </div>
        <div className={styles.footerItem}>
          <h2>For mentor</h2>
          <span>Find mentors</span>
          <span>Find mentors</span>
          <span>Find mentors</span>
          <span>Find mentors</span>
        </div>
        <div className={styles.footerItem}>
          <h2>Support</h2>
          <span>Find mentors</span>
          <span>Find mentors</span>
          <span>Find mentors</span>
          <span>Find mentors</span>
        </div>
      </div>
    );
  };
  
  export default Footer;
  