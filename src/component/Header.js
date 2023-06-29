import { Navbar, Nav, Container } from "react-bootstrap";
import NavbarCollapse from "react-bootstrap/esm/NavbarCollapse";
import { Link } from "react-router-dom";
import "../styles/Header.css";
import { FaSearch, FaHeart, FaHistory, FaFingerprint, FaUser } from 'react-icons/fa';



function Header() {
  return (
    <div>
      <Navbar className="header" expand="lg" >
        <Container>
        
          <Navbar.Brand as={Link} to="/" className="brand">
            Happy Programming
          </Navbar.Brand>
          <Navbar.Toggle aria-controls="basic-navbar-nav" />
          <NavbarCollapse id="basic-navbar-nav">
            <Nav className="me-auto">
              <Nav.Link as={Link} to="/findmentor">
                <FaSearch /> Find Mentor
              </Nav.Link>
              <Nav.Link as={Link} to="/favorite">
                <FaHeart /> Favorite
              </Nav.Link>
              <Nav.Link as={Link} to="/history">
                <FaHistory /> History
              </Nav.Link>
            </Nav>
            <Nav className="ms-auto">
              <Nav.Link as={Link} to="/login">
                <FaFingerprint /> Sign In
              </Nav.Link>
              <Nav.Link as={Link} to="resgiter">
                <FaUser /> Sign Up
              </Nav.Link>
            </Nav>
          </NavbarCollapse>
        </Container>
      </Navbar>
    </div>
  );

}
export default Header;

