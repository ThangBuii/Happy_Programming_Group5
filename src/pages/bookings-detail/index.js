import { useLocation, useNavigate, useParams } from "react-router";
import { CircularProgress, Container } from "@mui/material";
import { useEffect, useState } from "react";
import AvatarDefault from "../../assets/avatar-thinking-3-svgrepo-com.svg";
import styles from "./index.module.css";
import { request } from "../../axios_helper";

const BookingsDetail = () => {
  const params = useParams();
  const location = useLocation();
  const navigate = useNavigate();
  const prevPath = location.state?.prevPath || null;
  const [booking, setBooking] = useState(null);
  const [isLoading, seIsLoading] = useState(true);
  const { booking_id } = useParams();

  //call API
  useEffect(() => {
    seIsLoading(true);
    request("GET", `/api/booking/${booking_id}`)
      .then((response) => {
        setBooking(response.data);
      })
      .catch((err) => {
        console.log(err);
      })
      .finally(() => {
        seIsLoading(false);
      });
  }, [booking_id]);

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
                  src={booking.mentorAvatar || AvatarDefault}
                  alt="avatar"
                />
                <div className={styles.infoLeft}>
                  <h4>{booking.mentorUsername}</h4>
                  <p>{booking.mentorEmail}</p>
                </div>
              </div>
            </div>

            <div className={styles.contentWrapper}>
              <h4>Mentee Info</h4>
              <div className={styles.mentorInfoWrapper}>
                <img
                  src={booking.menteeAvatar || AvatarDefault}
                  alt="avatar"
                />
                <div className={styles.infoLeft}>
                  <h4>{booking.menteeUsername}</h4>
                  <p>{booking.menteeEmail}</p>
                </div>
              </div>
              {booking.status !== 0 && (
                <>
                  <h4>Bookings Scheduled Date</h4>
                  <div className={styles.bookingDetail}>
                    {booking.scheduleDate}
                  </div>
                  <h4>Bookings Scheduled T ime</h4>
                  <div className={styles.bookingDetail}>
                    {booking.scheduleTime}
                  </div>
                </>
              )}

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
