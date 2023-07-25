import { useContext, useState } from "react";
import { ApplicationContext } from "./AppRoutes";
import { Navigate, Outlet, useNavigate } from "react-router";


const PrivateMenteeRoute = () => {
    const navigate = useNavigate();
    const { user } = useContext(ApplicationContext);
    const [errorMessage, setErrorMessage] = useState('');
    if (user.isAuthenticated && user.role===2) {
        return <Outlet/>
    }else{
        return <Navigate to="/" state={{ errorMessage: "You are not authorized to access that page." }} />;
    }
};

export default PrivateMenteeRoute;