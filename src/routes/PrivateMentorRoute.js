import { useContext } from "react";
import { ApplicationContext } from "./AppRoutes";
import { Navigate, Outlet, useNavigate } from "react-router";


const PrivateMentorRoute = () => {
    const navigate = useNavigate();
    const { user } = useContext(ApplicationContext);

    if (user.isAuthenticated && user.role===1) {
        return <Outlet/>
    }
    return <Navigate to="/" />
};

export default PrivateMentorRoute;