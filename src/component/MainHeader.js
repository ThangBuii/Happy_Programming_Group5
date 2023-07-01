import { } from 'react-bootstrap';
import Image from '../assets/2.png';
import Online from '../assets/5.png';
import Coding from '../assets/coding.png';
import Point from '../assets/point.png';
import '../styles/Home.css';
import { } from 'react-icons';
import { FaSearch } from 'react-icons/fa';


const MainHeader = () => {
    return (
        <header className="main_header">
            <div className="container main_header-container">
                <div className="main_header-center">
                    <h2>Welcome to Happy Programming!
                        <br /> Take the first step toward unlocking your potential and
                        let our mentors guide you towards success in the exciting world of coding
                    </h2>
                 
                </div>

                <div className="row">
                    <div className="col-md-6 d-flex align-items-center justify-content-center">
                        <img
                            className="img-resize"
                            src={Image}
                            alt="Main Header Assest" />
                    </div>
                    <div className="col-md-6 d-flex align-items-center justify-content-center">
                        <div className="overlap-group">
                            <h2 className="title headline-fr">
                                Popular searches
                            </h2>
                            <div className="aspnet-container subtext-fr">
                                <div className="aspnet">
                                    Asp.net
                                </div>

                            </div>
                            <div className="overlap-group2">
                                <div className="java subtext-fr">
                                    Java
                                </div>
                            </div>
                            <div className="overlap-group4">
                                <div className="c subtext-fr">
                                    C++
                                </div>
                            </div>
                            <div className="overlap-group3">
                                <div className="spring-boot subtext-fr">
                                    Spring boot
                                </div>
                            </div>

                        </div>
                    </div>
                </div>



                {/*Page 2 */}
                <setion>
                    <div className="container">
                        <h4 className="per_content">Empower Your Programming Journey</h4>
                        <p>Discover the powerful features that make Happy Programming the ultimate destintion for
                            aspring programmers and coding enthusiasts. Our platform offers a range of Innovative tools
                            and personalized support to help you thrive in your programming journey.</p>
                        <div className=" per_item">
                            <img src={Online} alt="online learning" />
                            <div className="per_item-heading">
                                <h4>Personalized Mentor Matching</h4>
                                <p>Our platform utillzes advanced algorithms to match you with mentors based on you programming goals,
                                    experience level, and preferred programming languages.</p>
                            </div>
                        </div>


                        <div className=" per_item">
                            <img src={Coding} alt="Coding" />
                            <div className="per_item-heading">
                                <h4>Code Reviews</h4>
                                <p>Accelerate your growth wit comprehensive code reiews from experienced
                                    mentors, as they guide you towards writing cleaner, more efficient code,
                                    and help you understand industry best practiecs.</p>
                            </div>
                        </div>


                        <div className=" per_item">
                            <img src={Point} alt="Point" />
                            <div className="per_item-heading">
                                <h5>Real-World Industry Insights</h5>
                                <p>Stay ahead of the curve with exclusive industry insights
                                    from our mentors, who can provide invaluable knowledge
                                    and practical advice on industry standards, emerging technologies
                                    and best practices.</p>
                            </div>
                        </div>

                    </div>
                    <button className="per_btn">Find my mentor</button>
                </setion>

           


            </div>



        </header>

    );
}
export default MainHeader;
