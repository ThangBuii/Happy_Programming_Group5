import { useContext } from "react";
import { ApplicationContext } from "./AppRoutes";
import { Navigate, Outlet, useNavigate } from "react-router";


const PrivateMenteeRoute = () => {
    const navigate = useNavigate();
    const { user } = useContext(ApplicationContext);

    if (user.isAuthenticated && user.role===2) {
        return <Outlet/>
    }
    return <Navigate to="/" />
};

export default PrivateMenteeRoute;