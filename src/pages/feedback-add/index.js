import { Button, Container, TextField, Rating, Snackbar, Slide, Alert } from "@mui/material";
import styles from "./index.module.css";
import { useNavigate } from "react-router";
import { useEffect, useState } from "react";
import { request } from "../../axios_helper";
import Feedback from './../feedback/index';
import { ApplicationContext } from "../../routes/AppRoutes";



const FeedbackAdd = () => {
    const navigate = useNavigate();
    
    const [inputValue, setInputValue] = useState({ rating: "", content: "" });
    const [mentor, setMentor] = useState([])
    const [mentorIdChoosed, setMentorIdChoosed] = useState("");
    const [errorMessage, setErrorMessage] = useState("");
    const [snackbarOpen, setSnackbarOpen] = useState(false);

    useEffect(() => {

        request("GET", "/api/mentee/mentorfeedback")
            .then((response) => {
                setMentor(response.data);
            })
            .catch((err) => {
                console.log(err);
            })
    }, []);
    const handleSubmit = (e) => {
        e.preventDefault();

        const data = {
            mentor_id: mentorIdChoosed,
            rating: inputValue.rating,
            content: inputValue.content
        }
        request("POST", "/api/mentee/feedback", data)
            .then((response) => {
                navigate("/feedback")

            })//phải co một cái call api vào ddeerr lấy list mentor xong truyền vào cái select ý
            .catch((error) => {
                setSnackbarOpen(true);
                if (
                    error.response &&
                    error.response.data &&
                    error.response.data.errors
                ) {
                    setErrorMessage(error.response.data.errors.message);
                } else {
                    setErrorMessage("An error occurred. Please try again.");
                }
                console.error(error);
            });

    };
    return (
        <div className={styles.layoutWrapper}>
            <div className={styles.breadcumBarWrapper}>
                <div className={styles.bcLeft}>
                    <div>
                        <span className={styles.bcHome} onClick={() => navigate("/")}>
                            Home
                        </span>
                        <span
                            className={styles.bcPersonProfile}
                            onClick={() => navigate("/feedback")}
                        >
                            Feedback
                        </span>
                        <span className={styles.bcPersonProfile}>Add Feedback</span>
                    </div>
                    <h2>Add Feedback</h2>
                </div>
            </div>
            <div className={styles.containerWrapper}>
                <Container
                    maxWidth="xl"
                    sx={{
                        padding: "20px 0 0",
                    }}
                >
                    <h4>Choose Session: </h4>
                    <select
                        value={mentorIdChoosed}
                        onChange={(e) => setMentorIdChoosed(e.target.value)}
                    >
                        <option value={""}>Select</option>
                        {mentor.map((item) => (
                            <option key={item.mentor_id} value={item.mentor_id}>
                                {item.mentor_name}
                            </option>
                        ))}
                    </select>

                    <h4>Rating</h4>

                    <Rating
                        value={inputValue.rating}
                        onChange={(e, newValue) => setInputValue({ ...inputValue, rating: newValue })}
                        precision={0.5}
                    />


                    <h4>Content</h4>
                    <TextField
                        sx={{ mb: "10px" }}
                        fullWidth
                        hiddenLabel
                        value={inputValue.content}
                        onChange={(event) => {
                            setInputValue((pre) => ({ ...pre, content: event.target.value }));
                        }}
                        multiline
                        rows={4}
                    />
                    <div className={styles.reportAction}>
                        <Button onClick={handleSubmit} variant="contained" sx={{ width: "100px" }}>
                            Add
                        </Button>
                    </div>
                </Container>
            </div>
            <Snackbar
                anchorOrigin={{ vertical: 'top', horizontal: 'right' }}
                open={snackbarOpen}
                autoHideDuration={2000}
                onClose={() => setSnackbarOpen(false)}
                style={{ marginTop: "40px" }}
                TransitionComponent={({ children }) => (
                    <Slide direction="left" in={snackbarOpen}>
                        {children}
                    </Slide>
                )}
            >
                <Alert severity="error" variant="filled" sx={{ width: "100%" }}>
                    {errorMessage}
                </Alert>
            </Snackbar>
        </div>


    );
};

export default FeedbackAdd;
