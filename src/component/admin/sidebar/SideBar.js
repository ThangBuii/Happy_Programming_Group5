import React from "react";


import '../sidebar/SideBar.css';
import { NavLink } from "react-router-dom";
import { Nav } from "react-bootstrap";

function SideBar() {
  return (
    <aside className="sidebar">
      <Nav>
        <NavLink to="/admin" className="nav-link" activeClassName="active">
          DASHBOARD
        </NavLink>

        <NavLink to="/listmovie" className="nav-link" activeClassName="active">
          <p>LIST MOVIE</p>
        </NavLink>

        <NavLink to="/staff" className="nav-link" activeClassName="active">
          <p>STAFF</p>
        </NavLink>

        <NavLink to="/banner" className="nav-link" activeClassName="active">
          <p>BANNER</p>
        </NavLink>

        <NavLink to="/feedback" className="nav-link" activeClassName="active">
          <p>FEEDBACK</p>
        </NavLink>
      </Nav>
    </aside>
  );
}

export default SideBar;