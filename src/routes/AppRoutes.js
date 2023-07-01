import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Favorite from '../pages/Favorite';
import FindMentor from '../pages/FindMentor';
import History from '../pages/History';
import SigIn from '../pages/SignIn';
import SigUp from '../pages/SignUp';
import Home from '../pages/Home';

import Booking from '../pages/booking/bookings';
import Dashboard from '../pages/dashboard/Dashboard';
import FavouriteMentor from '../pages/favorite/Favorite';

import Navbar from '../component/admin/navbar/Navbar';
import React, { useState } from 'react';
 
export const ApplicationContext = React.createContext([])

const AppRoutes = () => {
    const [user, setUser] = useState([])

    const makeSignIn = (user)=>{
        setUser(user);
    }
    const makeSignOut = (user)=>{
        setUser([])
    }
    return (
        <ApplicationContext.Provider value={{user, setUser,makeSignIn,makeSignOut}}>
        <Router>
        
            <Routes>

                <Route path='/' element={<Home />} />
                <Route path='/findmentor' element={<FindMentor />} />
                <Route path='/favorite' element={<Favorite />} />
                <Route path='history' element={<History />} />
                <Route path='/login' element={<SigIn />} />
                <Route path='/resgiter' element={<SigUp />} />
                <Route path='/bookings' element={<Booking />} />
                <Route path='/dashboard' element={<Dashboard />} />
                <Route path='favorite-mentor' element={<FavouriteMentor />} />
               
               
               
                {/*Call Dashboard Admin */}
                <Route path="/admin" element={<Navbar/>}/>
                
                
            </Routes>




        </Router>
        </ApplicationContext.Provider>
    );
}

export default AppRoutes;

