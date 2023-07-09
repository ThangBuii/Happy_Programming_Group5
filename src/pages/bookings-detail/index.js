import { useLocation, useNavigate, useParams } from "react-router";
import { CircularProgress, Container } from "@mui/material";
import { useEffect, useState } from "react";
import AvatarDefault from "../../assets/avatar-thinking-3-svgrepo-com.svg";
import styles from "./index.module.css";

// Mentor Info(Ava,Usernam,Email), Mentee info(Ava,Username,Email), Scheduled date,Scheduled time, Status, Created Date

function createData(
  mentorInfo,
  menteeInfo,
  scheduledDate,
  scheduledTime,
  status,
  createdDate
) {
  return {
    mentorInfo,
    menteeInfo,
    scheduledDate,
    scheduledTime,
    status,
    createdDate,
  };
}

const fakeBookingDetailData = createData(
  {
    accountId: "user1",
    name: "Le Van Luyen",
    email: "luyen@xxvideo.com",
    imageUrl:
      "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTu5iuH9GH49VUAv0qvlrKiFRnsgEC6maRA9g&usqp=CAU",
  },
  {
    accountId: "user2",
    name: "Ngo Ba Kha",
    email: "kha@gmail.com",
    imageUrl:
      "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTu5iuH9GH49VUAv0qvlrKiFRnsgEC6maRA9g&usqp=CAU",
  },
  "08, August, 2023",
  "08, August, 2023",
  0,
  "08, August, 2023"
);

const BookingsDetail = () => {
  const params = useParams();
  const location = useLocation();
  const navigate = useNavigate();
  const prevPath = location.state?.prevPath || null;
  const [booking, setBooking] = useState(null);
  const [isLoading, seIsLoading] = useState(true);

  //call API
  useEffect(() => {
    fetch(`http://localhost:9999/booking-details/${params.id}`)
      .then((resp) => resp.json())
      .then((data) => {
        setBooking(data);
      })
      .catch((err) => {
        console.log(err);
        setBooking({ ...fakeBookingDetailData });
      })
      .finally(() => {
        seIsLoading(false);
      });
  }, [params.id]);

  return (
    <div className={styles.layoutWrapper}>
      <div className={styles.breadcumBarWrapper}>
        <div className={styles.bcLeft}>
          <div>
            <span className={styles.bcHome} onClick={() => navigate("/")}>
              Home
            </span>
            {prevPath && (
              <span
                className={styles.bcPersonProfile}
                onClick={() => navigate(prevPath.to)}
              >
                {prevPath.represent}
              </span>
            )}
            <span className={styles.bcPersonProfile}>Booking Detail</span>
          </div>
          <h2>Booking Detail</h2>
        </div>
      </div>
      <div className={styles.containerWrapper}>
        {isLoading ? (
          <div className={styles.loadingWrapper}>
            <CircularProgress />
          </div>
        ) : (
          <Container
            maxWidth="xl"
            sx={{
              padding: "30px 0 0",
            }}
          >
            <h4 className={styles.menteeInfo}>Mentor Info</h4>
            <div className={styles.topWrapper}>
              <div className={styles.mentorInfoWrapper}>
                <img
                  src={booking.mentorInfo.imageUrl || AvatarDefault}
                  alt="avatar"
                />
                <div className={styles.infoLeft}>
                  <h4>{booking.mentorInfo.name}</h4>
                  <p>{booking.mentorInfo.email}</p>
                </div>
              </div>
            </div>

            <div className={styles.contentWrapper}>
              <h4>Mentee Info</h4>
              <div className={styles.mentorInfoWrapper}>
                <img
                  src={booking.menteeInfo.imageUrl || AvatarDefault}
                  alt="avatar"
                />
                <div className={styles.infoLeft}>
                  <h4>{booking.menteeInfo.name}</h4>
                  <p>{booking.menteeInfo.email}</p>
                </div>
              </div>
              <h4>Bookings Scheduled Date</h4>
              <div className={styles.bookingDetail}>
                {booking.scheduledDate}
              </div>
              <h4>Bookings Scheduled T ime</h4>
              <div className={styles.bookingDetail}>
                {booking.scheduledTime}
              </div>
              <h4>Create Date</h4>
              <div className={styles.bookingDetail}>{booking.createdDate}</div>
              <h4>Bookings Status</h4>
              <div className={styles.bookingDetail}>
                <span
                  className={
                    booking.status === 0
                      ? styles.pendindStatus
                      : booking.status === 1
                      ? styles.acceptStatus
                      : booking.status === 2
                      ? styles.rejectStatus
                      : ""
                  }
                >
                  {booking.status === 0
                    ? "Pending"
                    : booking.status === 1
                    ? "Accepted"
                    : booking.status === 2
                    ? "Rejected"
                    : ""}
                </span>
              </div>
            </div>
          </Container>
        )}
      </div>
    </div>
  );
};

export default BookingsDetail;
