import { Button, Container, TextField, Rating } from "@mui/material";
import styles from "./index.module.css";
import { useNavigate } from "react-router";
import { useEffect, useState } from "react";
import { request } from "../../axios_helper";
import Feedback from './../feedback/index';



const FeedbackAdd = () => {
    const navigate = useNavigate();
    const [inputValue, setInputValue] = useState({ rating: "", content: "" });
    const [mentor, setMentor] = useState([])
    const [mentorIdChoosed, setMentorIdChoosed] = useState("");

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
            mentor_id : mentorIdChoosed,
            rating : inputValue.rating,
            content: inputValue.content
        }
        request("POST", "/api/mentee/feedback", data)
            .then((response) => {
                navigate("/feedback")

            })//phải co một cái call api vào ddeerr lấy list mentor xong truyền vào cái select ý
            .catch((error) => {
                // Handle the error here (e.g., show an error message)


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
        </div>


    );
};

export default FeedbackAdd;
