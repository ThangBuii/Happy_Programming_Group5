import { useContext, useState } from 'react';
import '../styles/SignIn.css';
import { } from 'react-bootstrap';
import { Link, useNavigate } from 'react-router-dom';
import { ApplicationContext } from '../routes/AppRoutes';
import '../auth/utils';
const SigIn = () => {
    const navigate = useNavigate();
    const [userName, setUserName] = useState('')
    const [password, setPassword] = useState('')
    const [isValid, setIsValid] = useState(true);

    const {makeSignIn} = useContext(ApplicationContext);

    const handleSignIn = () => {
        console.log('username: ' + userName);
        console.log('password: ' + password)
        // Kiểm tra tên đăng nhập và mật khẩu
        if (userName === "admin" && password === "12345") {
            setIsValid(true);
            navigate('/');
        } else {
            setIsValid(false);
        }
    }
    makeSignIn(user);

    return (
        <div className="login-bg">
            <div className="login-container">
                <div className="login-content-row">
                    <div className="col-12 text-login">SIGN IN</div>

                    <div className="col-12 form-group login-input">
                        <label>UserName:</label>
                        <input
                            type="text"
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
                    {!isValid && <p class="error-message">Tên đăng nhập hoặc mật khẩu không chính xác!</p>}

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
