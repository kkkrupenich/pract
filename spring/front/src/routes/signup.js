import { useContext, useState } from "react";
import { signUp } from "../services/authService"
import { AppContext } from "../contexts/contexts";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import { useCookies } from "react-cookie";


export default function Signin() {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [fio, setFio] = useState('');
    const [serialNumber, setSerialNumber] = useState('');
    const [identificationNumber, setIdentificationNumber] = useState('');
    const [registration, setRegistration] = useState('');
    const [issueDate, setIssueDate] = useState('');
    const [expirationDate, setExpirationDate] = useState('');
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
                placeholder="Password"
                type="password"
                onChange={(e) => setPassword(e.target.value)}
            />
            <input
                placeholder="FIO"
                type="text"
                onChange={(e) => setFio(e.target.value)}
            />
            <input
                placeholder="Serial number"
                type="text"
                onChange={(e) => setSerialNumber(e.target.value)}
            />
            <input
                placeholder="Identifiaction number"
                type="text"
                onChange={(e) => setIdentificationNumber(e.target.value)}
            />
            <input
                placeholder="Registration"
                type="text"
                onChange={(e) => setRegistration(e.target.value)}
            />
            <input
                placeholder="Issue date"
                type="date"
                onChange={(e) => setIssueDate(e.target.value)}
            />
            <input
                placeholder="Expiration date"
                type="date"
                onChange={(e) => setExpirationDate(e.target.value)}
            />
            <button
                onClick={signUpClick}>
                Register
            </button>
        </section>
    );

    async function signUpClick() {
        const data = await signUp(email, password, fio, serialNumber, identificationNumber, registration, issueDate, expirationDate);
        appContext.setCookie('userId', data.id);
        appContext.setCookie('token', data.token);
        appContext.setIsAuthenticated(true);
        axios.defaults.headers.common['Authorization'] = 'Bearer ' + cookies.token;
        navigate('/');
    }
}