import { useContext } from "react";
import { AppContext } from "../contexts/contexts";
import { useNavigate } from "react-router-dom";
import axios from "axios";


export default function Profile() {
    const appContext = useContext(AppContext);
    const navigate = useNavigate();

    return (
        <section>
            <button
                onClick={logoutButtonOnClick}
            >
                Выйти
            </button>
        </section>
    );

    async function logoutButtonOnClick() {
        appContext.removeCookie('userId');
        appContext.removeCookie('token');
        appContext.setIsAuthenticated(false);
        axios.defaults.headers.common['Authorization'] = '';
        navigate('/');
    }
}