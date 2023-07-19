import React from "react";
import { Container } from "react-bootstrap";
import "../styles/Card.css"; // Import CSS của thẻ Card

const Card = ({ title, name, imgURL, body, id }) => {
  return (
    <Container>
      <div className="card-container">
        <div className="card m-4">
          <img
            className="card-img-top"
            src={imgURL}
            
          />
          <div className="card-body">
            <h5 className="card-title-name">{name}</h5>
            <p className="card-title-title">{title}</p>
            
          </div>
        </div>
      </div>
    </Container>
  );
};

export default Card;
