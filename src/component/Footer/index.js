import { useLocation } from "react-router";
import Logo from "../../assets/logo.png";
import styles from "./index.module.css";
import Dashboard from './../../pages/dashboard/index';
import {
  FaEnvelope,


  FaPhone,
  FaLocationArrow,

  FaFacebook,
  FaTelegram,
  FaInstagram,
} from "react-icons/fa";
import { Link } from "react-router-dom";

const Footer = () => {
  const location = useLocation();

  return (
    <div
      className={`${styles.footerWrapper} ${location.pathname.includes("admin") ? styles.adminStyle : ""
        }`}
    >
      <div className={styles.footerDs}>
        <div className={styles.dSHead}>
          <img src={Logo} alt="avatar" />
          <h1>HAPPY PROGRAMMING</h1>
        </div>
        <p>
          Your trusted source to find highly-vetted mentors & industry
          professionals to move your career ahead.
        </p>
        <div className={styles.dSHead}>
          <FaFacebook />    <FaTelegram />    <FaInstagram />
        </div>

      </div>
      <div className={styles.footerItem}>
        <h2>For mentee</h2>
        <span>Sign In</span>
        <span>Sing Up</span>
        <span>Sign Out</span>
        <span>View Dashboard</span>
        <span>View Booking</span>
      </div>
      <div className={styles.footerItem}>
        <h2>For mentor</h2>
        <span>Sign In</span>
        <span>Sign Up</span>
        <span>Sign Out</span>
        <span>View Dashboard</span>
        <span>View Bookings</span>
      </div>
      <div className={styles.footerItem}>
        <h2>Contact Us</h2>
        <span><FaLocationArrow />  Thạch Hoà, Thạch Thất, Hà Nội</span>
        <span><FaPhone />  +84 2977404334</span>
        <span><FaEnvelope />  happyprogrammingwebsite@gmail.com</span>

      </div>
    </div>
  );
};

export default Footer;
