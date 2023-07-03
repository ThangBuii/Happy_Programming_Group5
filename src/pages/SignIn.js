import { useContext, useState } from 'react';
import '../styles/SignIn.css';
import { } from 'react-bootstrap';
import { Link, useNavigate } from 'react-router-dom';
import {request, setAuthToken} from '../axios_helper'
import { ApplicationContext } from '../routes/AppRoutes';
import '../auth/utils';

const SigIn = () => {
    const navigate = useNavigate();
    const [userName, setUserName] = useState('')
    const [password, setPassword] = useState('')
    const [isValid, setIsValid] = useState(true);
    const [errorMessage, setErrorMessage] = useState('');

    const {makeSignIn} = useContext(ApplicationContext);

    const handleSignIn = () => {
        const payload = {
            account: {
                email: userName,
                password: password
            }
        };
    
        request("POST", 
        "/api/login",
         payload).then((response) => {
                setAuthToken(response.data.account.token)
                // Handle the response here (e.g., navigate to a new page)
                navigate('/');
            })
            .catch(error => {
                // Handle the error here (e.g., show an error message)
                setIsValid(false);
                if (error.response && error.response.data && error.response.data.errors) {
                    setErrorMessage(error.response.data.errors.message);
                  } else {
                    setErrorMessage("An error occurred. Please try again.");
                  }
                console.error(error);
            });

        
    }
    makeSignIn(user);

    return (
        <div className="login-bg">
            <div className="login-container">
                <div className="login-content-row">
                    <div className="col-12 text-login">SIGN IN</div>

                    <div className="col-12 form-group login-input">
                        <label>Email:</label>
                        <input
                            type="email"
                            value={userName} onChange={(e) => { setUserName(e.target.value) }}
                            className="form-control" placeholder="Enter your username" />
                    </div>

                    <div className="col-12 form-group login-input">
                        <label>Password:</label>
                        <input
                            type="password"
                            value={password} onChange={(e) => { setPassword(e.target.value) }}
                            className="form-control" placeholder="Enter your password" />

                    </div>
                        <div>
                    <button className="btn-login" onClick={handleSignIn}>Login</button>
                    </div>
                    {!isValid && <p class="error-message">{errorMessage}</p>}

                    <div className="auth-links">
                        <Link to="/forgotpassword" className="forgot-pwd">Forgot your password</Link>
                        <p>Don't have an account? 
                        <Link to="/resgiter" className="signup-link">Sign Up</Link>
                       
                        </p>
                    </div>


                </div>
            </div>
        </div>
    );
}

export default SigIn;
