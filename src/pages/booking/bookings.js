import {PencilSquare, Trash3Fill } from "react-bootstrap-icons";
import { Link } from "react-router-dom"
import { Col, Row, Table } from "react-bootstrap";
import { useEffect, useState } from "react";


const Booking = () => {

  const [bookings, setBooking] = useState([]);
  //call API
  useEffect(() => {
    fetch("http://localhost:9999/Booking")
      .then((resp) => resp.json())
      .then((data) => {
        setBooking(data);
      });
  }, []);

  // delete
  const handleDelete = (name) => {
    if (window.confirm("Muon xoa-name: " + name + "?")) {
      fetch("http://localhost:9999/Booking/" + name, {
        method: "DELETE",
      })
        .then(() => {
          alert("Delete success");
          window.location.reload();
        })
        .catch((err) => {
          console.log(err.message);
        });
    }
  };


  return (
    <Row>
      <Col>
        <Row>
          <Col>
            <h2 style={{ textAlign: "center" }}>List of booking</h2>
          </Col>
        </Row>



        <Row>
          <Col>
            <Table>
              <thead>
                <tr>
                  <th>Name</th>
                  <th>Email</th>
                  <th>SCHEDULED DATE</th>
                  <th>SCHEDULED TIMINGS</th>
                  <th colSpan={2}>Action</th>
                </tr>
              </thead>
              <tbody>
                {bookings.map((b) => (
                  <tr key={b.name}>
                    <td>{b.name}</td>

                    <td>{b.email}</td>
                    <td>{b.date}</td>
                    <td>{b.timings}</td>
                    <td>
                      <Link onClick={() => handleDelete(b.name)}><Trash3Fill /></Link>
                    </td>
                    <td>
                     <PencilSquare />
                    </td>


                  </tr>
                ))}
              </tbody>
            </Table>
          </Col>
        </Row>
      </Col>
    </Row>
  );
};

export default Booking;
