import { Navbar, Nav, Container } from "react-bootstrap";
import NavbarCollapse from "react-bootstrap/esm/NavbarCollapse";
import { Link, useLocation, useNavigate } from "react-router-dom";
import "../styles/Header.css";
import {
  FaSearch,
  
 
  FaFingerprint,
  FaUser,
  FaDashcube,
} from "react-icons/fa";

function Header({user, makeSignOut}) {
  const location = useLocation();
  const navigate = useNavigate();

  const handleSignOut = (e) => {
    e.preventDefault();
    makeSignOut();
    localStorage.clear();
    navigate('/login');
  }

  return (
    <div
      className={location.pathname.includes("admin") ? "headerAdminStyle" : ""}
    >
      <Navbar className="header" expand="lg">
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
              {/* <Nav.Link as={Link} to="/favorite">
                <FaHeart /> Favorite
              </Nav.Link> */}
              {(user.role===1 || user.role===2) && <Nav.Link as={Link} to="/dashboard">
                <FaDashcube /> Dashboard
              </Nav.Link>}
              {user.role===0 && <Nav.Link as={Link} to="/admin">
                <FaDashcube /> Admin Dashboard
              </Nav.Link>}
            </Nav>
            <Nav className={`ms-auto ${user.isAuthenticated? 'd-none' : ''}`}>
              <Nav.Link as={Link} to="/login">
                <FaFingerprint /> Sign In
              </Nav.Link>
              <Nav.Link as={Link} to="resgiter">
                <FaUser /> Sign Up
              </Nav.Link>
            </Nav>
            <Nav className={`ms-auto ${!user.isAuthenticated? 'd-none' : ''}`}>
            <Nav.Link as={Link} onClick={(e)=>handleSignOut(e)}>
                <FaUser /> Sign Out
              </Nav.Link>
            </Nav>
          </NavbarCollapse>
        </Container>
      </Navbar>
    </div>
  );
}
export default Header;
