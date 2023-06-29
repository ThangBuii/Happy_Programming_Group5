import Logo from '../assets/logo.png';
import styles from'../styles/Footer.css';
const Footer = () => {
    return (
      <div className={styles.footerWrapper}>
        <div className={styles.footerDs}>
          <div className={styles.dSHead}>
           <image src={Logo}/>
            <h1>HAPPY PROGRAMMING</h1>
          </div>
          <p>
            Your trusted source to find highly-vetted mentors & industry
            professionals to move your career ahead.
          </p>
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
  