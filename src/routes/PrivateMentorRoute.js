import { useContext, useEffect, useState } from "react";
import { ApplicationContext } from "./AppRoutes";
import { Navigate, Outlet, useNavigate } from "react-router";


const PrivateMentorRoute = () => {
    const navigate = useNavigate();
    const { user } = useContext(ApplicationContext);
    const [errorMessage, setErrorMessage] = useState('');

    if (user.isAuthenticated && user.role === 1) {
        return <Outlet />;
    } else {
        
        return <Navigate to="/" state={{ errorMessage: "You are not authorized to access that page." }} />;

    }
};

export default PrivateMentorRoute;