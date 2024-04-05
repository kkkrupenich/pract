import { useContext, useState } from "react";
import { signIn } from "../services/authService"
import { AppContext } from "../contexts/contexts";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import './auth.css';


export default function Signin() {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const appContext = useContext(AppContext);

    const navigate = useNavigate();

    return (
        <div className="form-login">
            <div className="form-group row">
                <label htmlFor="inputEmail">Email</label>
                <input placeholder="Email" type="email" id="inputEmail" required onChange={(e) => setEmail(e.target.value)} />
            </div>
            <div className="form-group row">
                <label htmlFor="inputPassword">Password</label>
                <input placeholder="Password" type="password" id="inputPassword" required onChange={(e) => setPassword(e.target.value)} />
            </div>
            <div className="button">
                <button className="btn btn-primary" onClick={signInClick}>Sign in</button>
            </div>
        </div>

    );

    async function signInClick() {
        console.log("here");
        if (email !== '' && password !== '') {
            console.log("inside");
            const data = await signIn(email, password);
            if (data !== "no user with creds") {
                appContext.setCookie('userId', data.id);
                appContext.setCookie('token', data.token);
                appContext.setIsAuthenticated(true);
                axios.defaults.headers.common['Authorization'] = 'Bearer ' + data.token;
                console.log("logged");
                navigate('/');
            }
        }
    }
}