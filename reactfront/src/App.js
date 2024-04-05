import './App.css';
import Signin from './routes/signin';
import Signup from './routes/signup';
import Root from './routes/root';
import NotFound from './routes/notFound';
import {
  createBrowserRouter,
  RouterProvider,
} from "react-router-dom";
import { AppContext } from "../src/contexts/contexts";
import Cards from './routes/cards';
import Profile from './routes/profile';
import { useCookies } from "react-cookie";
import { useEffect, useState } from "react";
import { getUserById } from './services/userService';
import axios from 'axios';
import AddCard from './routes/addCard';


export default function App() {
  const [cookies, setCookie, removeCookie] = useCookies(['app']);
  const [isAuthenticated, setIsAuthenticated] = useState(cookies.token !== undefined);
  const [user, setUser] = useState({});

  useEffect(() => {
    if (cookies.userId !== undefined) {
      getUserById(cookies.userId).then(user => setUser(user));
    }
  }, [isAuthenticated]);

  const state = {
    setCookie,
    removeCookie,
    isAuthenticated,
    setIsAuthenticated,
    user
  };

  const authenticatedRouter = createBrowserRouter([
    {
      path: "/",
      element: <Root/>,
      errorElement: <NotFound />,
      children: [
        {
          path: "/cards",
          element: <Cards />
        },
        {
          path: "/addcard",
          element: <AddCard />
        },
        {
          path: "/profile",
          element: <Profile />
        }
      ]
    }
  ]);

  const anonymousRouter = createBrowserRouter([
    {
      path: "/",
      element: <Root/>,
      errorElement: <NotFound />,
      children: [
        {
          path: "/signup",
          element: <Signup />
        },
        {
          path: "/signin",
          element: <Signin />
        }
      ]
    }
  ]);

  if (cookies.token !== undefined) {
    axios.defaults.headers.common['Authorization'] = 'Bearer ' + cookies.token;
  }
  else {
    axios.defaults.headers.common['Authorization'] = '';
  }

  return (
    <AppContext.Provider value={state}>
      <RouterProvider router={isAuthenticated ? authenticatedRouter : anonymousRouter} />
    </AppContext.Provider>
  );
}