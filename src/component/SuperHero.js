import { useEffect, useState } from "react";
import Card from "./Card";

import Slider from "react-slick";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";

const SuperHero = () => {
    const [comment, setComment] = useState([]);
    useEffect(() => {
        fetch("http://localhost:9999/Comment")
            .then((res) => res.json())
            .then((resp) => setComment(resp.filter((comment) => comment.vote >= 7)));
    }, []);
    const settings = {
        dots: true,
        infinite: false,
        speed: 500,
        slidesToShow: 4,
        slidesToScroll: 3,
        initialSlide: 0,
        responsive: [
            {
                breakpoint: 1024,
                settings: {
                    slidesToShow: 2,
                    slidesToScroll: 2,
                    infinite: true,
                    dots: true,
                },
            },
            {
                breakpoint: 600,
                settings: {
                    slidesToShow: 2,
                    slidesToScroll: 2,
                    initialSlide: 2,
                },
            },
            {
                breakpoint: 480,
                settings: {
                    slidesToShow: 1,
                    slidesToScroll: 1,
                },
            },
        ],
    };
    return (
        <Slider {...settings}>
            {comment.map((m) => (
                <Card title={m.title} imgURL={m.thumbnail} id={m.id} />
            ))}
        </Slider>
      
    );
};

export default SuperHero;
