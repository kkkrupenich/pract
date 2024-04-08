import { useContext, useState } from "react";
import { AppContext } from "../contexts/contexts";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import { updatePassword } from "../services/userService";


export default function Profile() {
    const [password, setPassword] = useState('');
    const appContext = useContext(AppContext);
    const navigate = useNavigate();

    return (
        <div className="form-login">
            <div className="form-group row">
                <input placeholder="Password" type="password" onChange={(e) => setPassword(e.target.value)} />
            </div>
            <div className="button">
                <button className="btn btn-success" onClick={updatePasswordOnClick}>
                    Update
                </button>
            </div>
            <div className="button">
                <button className="btn btn-primary" onClick={logoutButtonOnClick}>
                    Logout
                </button>
            </div>
        </div>
    );

    async function logoutButtonOnClick() {
        appContext.removeCookie('userId');
        appContext.removeCookie('token');
        appContext.setIsAuthenticated(false);
        axios.defaults.headers.common['Authorization'] = '';
        navigate('/');
    }

    async function updatePasswordOnClick() {
        updatePassword(password);
        navigate('/');
    }
}