import { useContext } from "react";
import { ApplicationContext } from "./AppRoutes";
import { Outlet, useNavigate } from "react-router";


const PrivateRoute = () => {
    const navigate = useNavigate();
    const { user } = useContext(ApplicationContext);

    if (!user.isAuthenticated) {
        navigate("/login");
    }
    return <Outlet/>
};

export default PrivateRoute;