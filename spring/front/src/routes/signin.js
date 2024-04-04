import { useContext, useState } from "react";
import { signIn } from "../services/authService"
import { AppContext } from "../contexts/contexts";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import { useCookies } from "react-cookie";


export default function Signin() {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [cookies] = useCookies(['app']);

    const appContext = useContext(AppContext);
    
    const navigate = useNavigate();

    return (
        <section>
            <input
                placeholder="Email"
                type="email"
                onChange={(e) => setEmail(e.target.value)}
            />
            <input
                placeholder="Пароль"
                type="password"
                onChange={(e) => setPassword(e.target.value)}
            />
            <button
                onClick={signInClick}>
                Login
            </button>
        </section>
    );

    async function signInClick() {
        const data = await signIn(email, password);
        appContext.setCookie('userId', data.id);
        appContext.setCookie('token', data.token);
        appContext.setIsAuthenticated(true);
        axios.defaults.headers.common['Authorization'] = 'Bearer ' + data.token;
        navigate('/');
    }
}